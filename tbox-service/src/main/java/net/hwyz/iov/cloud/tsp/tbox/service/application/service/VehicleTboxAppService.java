package net.hwyz.iov.cloud.tsp.tbox.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao.VehicleTboxDao;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao.VehicleTboxLogDao;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.VehicleTboxLogPo;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.VehicleTboxPo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆车联终端相关应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleTboxAppService {

    private final VehicleTboxDao vehicleTboxDao;
    private final VehicleTboxLogDao vehicleTboxLogDao;

    /**
     * 根据车架号或序列号获取车辆车联终端
     *
     * @param sn 序列号
     * @return 车辆车联终端
     */
    public VehicleTboxPo get(String vin, String sn) {
        List<VehicleTboxPo> vehicleTboxPoList = vehicleTboxDao.selectPoByExample(VehicleTboxPo.builder().vin(vin).sn(sn).build());
        return vehicleTboxPoList.isEmpty() ? null : vehicleTboxPoList.get(0);
    }

    /**
     * 记录车辆车联终端变更日志
     *
     * @param vehicleTboxPo 车辆车联终端对象
     * @param remark        变更备注
     */
    private void recordLog(VehicleTboxPo vehicleTboxPo, String remark) {
        vehicleTboxLogDao.insertPo(VehicleTboxLogPo.builder()
                .vin(vehicleTboxPo.getVin())
                .sn(vehicleTboxPo.getSn())
                .description(remark)
                .build());
    }

}
