package net.hwyz.iov.cloud.tsp.tbox.service.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.util.StrUtil;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.VehicleTboxExService;
import net.hwyz.iov.cloud.tsp.tbox.service.application.service.VehicleTboxAppService;
import net.hwyz.iov.cloud.tsp.tbox.service.facade.assembler.VehicleTboxExServiceAssembler;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.exception.TboxBaseException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 车辆车联终端相关服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service/vehicleTbox")
public class VehicleTboxServiceController {

    private final VehicleTboxAppService vehicleTboxAppService;

    /**
     * 根据车架号或序列号获取车辆车联终端
     *
     * @param vin 车架号
     * @param sn  序列号
     * @return 车辆车联终端
     */
    @GetMapping("")
    public VehicleTboxExService get(@RequestParam(required = false) String vin, @RequestParam(required = false) String sn) {
        logger.info("根据车架号[{}]或序列号[{}]获取车辆车联终端", vin, sn);
        if (StrUtil.isBlank(vin) && StrUtil.isBlank(sn)) {
            throw new TboxBaseException("车架号与序列号不能都为空");
        }
        return VehicleTboxExServiceAssembler.INSTANCE.fromPo(vehicleTboxAppService.get(vin, sn));
    }

    /**
     * 车辆绑定车联终端
     *
     * @param vehicleTbox 车辆车联终端
     */
    @PostMapping("/bind")
    public void bind(@RequestBody @Validated VehicleTboxExService vehicleTbox) {
        logger.info("绑定车辆[{}]车联终端[{}]", vehicleTbox.getVin(), vehicleTbox.getSn());
        vehicleTboxAppService.bind(vehicleTbox.getVin(), vehicleTbox.getSn());
    }

}
