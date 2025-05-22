package net.hwyz.iov.cloud.tsp.tbox.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.TboxExService;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.BatchImportTboxRequest;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.ExTboxInfoService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 车联终端信息相关服务降级处理
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

            @Override
            public TboxExService getBySn(String sn) {
                logger.error("车联终端信息相关服务根据序列号[{}]获取车联终端信息调用异常", sn, throwable);
                return null;
            }
        };
    }
}
