package net.hwyz.iov.cloud.tsp.tbox.service.facade.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.RemoteControlRequest;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.response.TboxCmdResponse;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.TboxServiceApi;
import net.hwyz.iov.cloud.tsp.tbox.service.application.service.TboxAppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TBOX业务相关服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service/tbox")
public class TboxServiceController implements TboxServiceApi {

    private final TboxAppService tboxAppService;

    /**
     * 远程控制
     *
     * @param request 远控请求
     * @return TBOX指令响应
     */
    @Override
    @PostMapping("/action/remoteControl")
    public TboxCmdResponse remoteControl(@RequestBody @Valid RemoteControlRequest request) {
        logger.info("对车辆[{}]进行远程控制[{}]", request.getVin(), request.getType());
        return tboxAppService.remoteControl(request.getVin(), request.getType(), request.getParams());
    }

}
