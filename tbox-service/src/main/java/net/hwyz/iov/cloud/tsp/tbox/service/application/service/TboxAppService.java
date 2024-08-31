package net.hwyz.iov.cloud.tsp.tbox.service.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.enums.RemoteControlType;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.response.TboxCmdResponse;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.factory.TboxFactory;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.repository.RemoteControlRepository;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.service.TboxService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * TBOX业务相关应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TboxAppService {

    private final TboxService tboxService;
    private final TboxFactory tboxFactory;
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
        // TODO 往网关发送远控消息
        return TboxCmdResponse.builder()
                .vin(vin)
                .cmdId(remoteControlOrigin.getCmdId())
                .build();
    }

}
