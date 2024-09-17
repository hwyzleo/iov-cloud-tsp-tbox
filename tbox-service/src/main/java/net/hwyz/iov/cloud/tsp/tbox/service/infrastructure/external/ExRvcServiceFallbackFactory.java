package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.external;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.rvc.api.contract.response.ControlResponse;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.external.service.ExRvcService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 * 外部远控业务服务回退处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
public class ExRvcServiceFallbackFactory implements FallbackFactory<ExRvcService> {

    @Override
    public ExRvcService create(Throwable cause) {
        return new ExRvcService() {

            @Override
            public void updateCmdState(String cmdId, ControlResponse response) {
                if (logger.isDebugEnabled()) {
                    logger.warn("更新远控指令状态异常", cause);
                } else {
                    logger.warn("更新远控指令状态异常:[{}]", cause.getMessage());
                }
            }

        };
    }

}
