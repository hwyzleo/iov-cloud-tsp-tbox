package net.hwyz.iov.cloud.tsp.tbox.service.application.service;


import cn.hutool.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.rvc.api.contract.enums.RvcCmdState;
import net.hwyz.iov.cloud.tsp.rvc.api.contract.response.ControlResponse;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.enums.RemoteControlType;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.response.TboxCmdResponse;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.external.service.ExRvcService;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.factory.TboxFactory;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.repository.RemoteControlRepository;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.service.TboxService;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.cache.CacheService;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.msg.TboxCmdProducer;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao.CmdRecordDao;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.CmdRecordPo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 车联终端指令相关应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TboxCmdAppService {

    private final TboxService tboxService;
    private final TboxFactory tboxFactory;
    private final CacheService cacheService;
    private final ExRvcService exRvcService;
    private final CmdRecordDao cmdRecordDao;
    private final TboxCmdProducer tboxCmdProducer;
    private final RemoteControlRepository remoteControlRepository;

    /**
     * 远程控制
     *
     * @param vin    车架号
     * @param type   远控类型
     * @param params 远控参数
     * @return TBOX指令响应
     */
    public TboxCmdResponse remoteControl(String vin, RemoteControlType type, Map<String, Object> params) {
        logger.info("对车辆[{}]进行远程控制[{}]", vin, type);
        RemoteControlDo remoteControlNew = tboxFactory.buildDownRemoteControl(vin, type, params);
        RemoteControlDo remoteControlOrigin = tboxService.getOrCreateRemoteControl(remoteControlNew);
        remoteControlOrigin.handle(remoteControlNew);
        remoteControlRepository.save(remoteControlOrigin);
        tboxCmdProducer.send(vin, remoteControlOrigin.getParams());
        return TboxCmdResponse.builder()
                .vin(vin)
                .cmdId(remoteControlOrigin.getCmdId())
                .build();
    }

    /**
     * 远程控制响应
     *
     * @param vin       车架号
     * @param eventType 事件类型
     * @param results   远控结果
     */
    public void remoteControlResponse(String vin, String eventType, JSONObject results) {
        String cmdId = results.getStr("cmdId");
        RvcCmdState cmdState = RvcCmdState.valOf(results.getInt("state"));
        logger.info("响应车辆[{}]远程控制[{}]指令[{}]状态[{}]", vin, eventType, cmdId, cmdState);
        exRvcService.updateCmdState(cmdId, ControlResponse.builder()
                .vin(vin)
                .cmdId(cmdId)
                .cmdState(cmdState)
                .failureCode(results.getInt("failure"))
                .build());
    }

    /**
     * TBOX指令ACK
     *
     * @param cmdId   指令ID
     * @param ackTime ACK时间
     */
    public void cmdAck(String cmdId, Date ackTime) {
        logger.debug("收到TBOX指令[{}]的ACK", cmdId);
        CmdRecordPo cmdRecordPo = cmdRecordDao.selectPoByCmdId(cmdId);
        if (cmdRecordPo != null) {
            if (cmdRecordPo.getMsgAckTime() == null) {
                cmdRecordPo.setMsgAckTime(ackTime);
                cmdRecordDao.updatePo(cmdRecordPo);
                cacheService.removeRemoteControl(cmdId);
            } else {
                logger.warn("TBOX指令[{}]已经被ACK过", cmdId);
            }
        } else {
            logger.error("未找到ACK对应的TBOX指令[{}]", cmdId);
        }
    }

}
