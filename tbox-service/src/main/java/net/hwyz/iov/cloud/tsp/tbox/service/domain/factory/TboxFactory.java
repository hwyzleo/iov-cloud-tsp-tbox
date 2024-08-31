package net.hwyz.iov.cloud.tsp.tbox.service.domain.factory;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.enums.RemoteControlType;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.contract.enums.MsgFlow;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * TBOX领域工厂类
 *
 * @author hwyz_leo
 */
@Component
public class TboxFactory {

    /**
     * 创建下行远控领域对象
     *
     * @param vin    车架号
     * @param type   远控类型
     * @param params 远控参数
     * @return 远控领域对象
     */
    public RemoteControlDo buildDownRemoteControl(String vin, RemoteControlType type, Map<String, Object> params) {
        return buildRemoteControl(vin, null, type, params, MsgFlow.DOWN, null);
    }

    /**
     * 创建远控领域对象
     *
     * @param vin     车架号
     * @param cmdId   指令ID
     * @param type    远控类型
     * @param params  远控参数
     * @param msgTime 消息时间
     * @return 远控领域对象
     */
    public RemoteControlDo buildRemoteControl(String vin, String cmdId, RemoteControlType type, Map<String, Object> params, MsgFlow msgFlow, Date msgTime) {
        if (msgTime == null) {
            msgTime = new Date();
        }
        RemoteControlDo remoteControlDo = RemoteControlDo.builder()
                .vin(vin)
                .cmdId(cmdId)
                .type(type)
                .params(params)
                .msgFlow(msgFlow)
                .msgTime(msgTime)
                .build();
        remoteControlDo.init();
        return remoteControlDo;
    }

}
