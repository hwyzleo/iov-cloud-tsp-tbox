package net.hwyz.iov.cloud.tsp.tbox.service.facade.assembler;

import net.hwyz.iov.cloud.tsp.tbox.api.contract.VehicleTboxExService;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.VehicleTboxPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 对外服务车辆车联终端转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface VehicleTboxExServiceAssembler {

    VehicleTboxExServiceAssembler INSTANCE = Mappers.getMapper(VehicleTboxExServiceAssembler.class);

    /**
     * 数据传输对象转数据对象
     *
     * @param vehicleTboxExService 数据传输对象
     * @return 数据对象
     */
    @Mappings({})
    VehicleTboxPo toPo(VehicleTboxExService vehicleTboxExService);

    /**
     * 数据对象转数据传输对象
     *
     * @param vehicleTboxPo 数据对象
     * @return 数据传输对象
     */
    @Mappings({})
    VehicleTboxExService fromPo(VehicleTboxPo vehicleTboxPo);

}
