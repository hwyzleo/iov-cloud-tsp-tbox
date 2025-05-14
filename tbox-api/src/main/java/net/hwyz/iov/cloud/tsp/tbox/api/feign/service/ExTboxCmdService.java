package net.hwyz.iov.cloud.tsp.tbox.api.feign.service;

import net.hwyz.iov.cloud.framework.common.constant.ServiceNameConstants;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.RemoteControlRequest;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.response.TboxCmdResponse;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.factory.ExTboxCmdServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 车联终端指令相关服务接口
 *
 * @author hwyz_leo
 */
@FeignClient(contextId = "exTboxCmdService", value = ServiceNameConstants.TSP_TBOX, path = "/service/cmd", fallbackFactory = ExTboxCmdServiceFallbackFactory.class)
public interface ExTboxCmdService {

    /**
     * 远程控制
     *
     * @param request 远控请求
     * @return TBOX指令响应
     */
    @PostMapping("/action/remoteControl")
    TboxCmdResponse remoteControl(@RequestBody @Validated RemoteControlRequest request);

}
