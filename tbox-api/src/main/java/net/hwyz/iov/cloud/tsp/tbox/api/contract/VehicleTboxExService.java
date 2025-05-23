package net.hwyz.iov.cloud.tsp.tbox.api.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对外服务车辆车联终端
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTboxExService {

    /**
     * 车架号
     */
    private String vin;

    /**
     * 序列号
     */
    private String sn;

}
