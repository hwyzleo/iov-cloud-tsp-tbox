package net.hwyz.iov.cloud.tsp.tbox.api.feign.service;

import net.hwyz.iov.cloud.framework.common.constant.ServiceNameConstants;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.TboxExService;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.BatchImportTboxRequest;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.factory.ExTboxInfoServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 车联终端信息相关服务接口
 *
 * @author hwyz_leo
 */
@FeignClient(contextId = "exTboxInfoService", value = ServiceNameConstants.TSP_TBOX, path = "/service/tbox", fallbackFactory = ExTboxInfoServiceFallbackFactory.class)
public interface ExTboxInfoService {

    /**
     * 批量导入车联终端数据
     *
     * @param request 批量导入车联终端请求
     */
    @PostMapping("/batchImport")
    void batchImport(@RequestBody @Validated BatchImportTboxRequest request);

    /**
     * 根据序列号获取车联终端信息
     *
     * @param sn 序列号
     * @return 车联终端信息
     */
    @GetMapping("/{sn}")
    TboxExService getBySn(@PathVariable String sn);

}
