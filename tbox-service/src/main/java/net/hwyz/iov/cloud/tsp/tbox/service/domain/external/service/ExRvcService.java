package net.hwyz.iov.cloud.tsp.tbox.service.domain.external.service;

import net.hwyz.iov.cloud.tsp.rvc.api.contract.response.ControlResponse;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.external.ExRvcServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 外部远控业务服务
 *
 * @author hwyz_leo
 */
@FeignClient(name = "rvc-service", path = "/service/rvc", fallbackFactory = ExRvcServiceFallbackFactory.class)
public interface ExRvcService {

    /**
     * 更新远控指令状态
     *
     * @param cmdId    指令ID
     * @param response 远控指令响应
     */
    @PostMapping("/cmd/{cmdId}")
    void updateCmdState(@PathVariable("cmdId") String cmdId, @RequestBody ControlResponse response);

}
