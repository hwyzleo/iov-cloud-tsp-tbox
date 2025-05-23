package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.VehicleTboxPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车辆车联终端表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-23
 */
@Mapper
public interface VehicleTboxDao extends BaseDao<VehicleTboxPo, Long> {

}
