package net.hwyz.iov.cloud.tsp.tbox.api.feign.service;

import net.hwyz.iov.cloud.framework.common.constant.ServiceNameConstants;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.VehicleTboxExService;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.factory.ExVehicleTboxServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 车辆车联终端相关服务接口
 *
 * @author hwyz_leo
 */
@FeignClient(contextId = "exVehicleTboxService", value = ServiceNameConstants.TSP_TBOX, path = "/service/vehicleTbox", fallbackFactory = ExVehicleTboxServiceFallbackFactory.class)
public interface ExVehicleTboxService {

    /**
     * 根据车架号或序列号获取车辆车联终端
     *
     * @param vin 车架号
     * @param sn  序列号
     * @return 车辆车联终端
     */
    @GetMapping("")
    VehicleTboxExService get(@RequestParam(required = false) String vin, @RequestParam(required = false) String sn);

}
