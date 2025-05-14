package net.hwyz.iov.cloud.tsp.tbox.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.BatchImportTboxRequest;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.RemoteControlRequest;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.response.TboxCmdResponse;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.ExTboxInfoService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 车联终端指令相关服务降级处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
public class ExTboxInfoServiceFallbackFactory implements FallbackFactory<ExTboxInfoService> {

    @Override
    public ExTboxInfoService create(Throwable throwable) {
        return new ExTboxInfoService() {
            @Override
            public void batchImport(BatchImportTboxRequest request) {
                logger.error("车联终端信息相关服务批量导入车联终端数据[{}]调用异常", request.getBatchNum(), throwable);
            }
        };
    }
}
