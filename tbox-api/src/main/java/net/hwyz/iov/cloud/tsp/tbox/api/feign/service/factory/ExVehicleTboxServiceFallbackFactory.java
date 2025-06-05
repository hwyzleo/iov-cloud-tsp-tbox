package net.hwyz.iov.cloud.tsp.tbox.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.VehicleTboxExService;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.ExVehicleTboxService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 车辆车联终端相关服务降级处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
public class ExVehicleTboxServiceFallbackFactory implements FallbackFactory<ExVehicleTboxService> {

    @Override
    public ExVehicleTboxService create(Throwable throwable) {
        return new ExVehicleTboxService() {
            @Override
            public VehicleTboxExService get(String vin, String sn) {
                logger.error("车辆车联终端相关服务根据车架号[{}]或序列号[{}]获取车辆车联终端调用异常", vin, sn, throwable);
                return null;
            }

            @Override
            public void bind(VehicleTboxExService vehicleTbox) {
                logger.error("车辆车联终端相关服务车辆[{}]绑定车联终端[{}]调用异常", vehicleTbox.getVin(), vehicleTbox.getSn(), throwable);
            }
        };
    }
}
