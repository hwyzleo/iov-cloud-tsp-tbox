package net.hwyz.iov.cloud.tsp.tbox.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.RemoteControlRequest;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.response.TboxCmdResponse;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.ExTboxCmdService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 车联终端指令相关服务降级处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
public class ExTboxCmdServiceFallbackFactory implements FallbackFactory<ExTboxCmdService> {

    @Override
    public ExTboxCmdService create(Throwable throwable) {
        return new ExTboxCmdService() {
            @Override
            public TboxCmdResponse remoteControl(RemoteControlRequest request) {
                logger.error("车联终端指令相关服务对车辆[{}]远程控制[{}]", request.getVin(), request.getType(), throwable);
                return null;
            }
        };
    }
}
