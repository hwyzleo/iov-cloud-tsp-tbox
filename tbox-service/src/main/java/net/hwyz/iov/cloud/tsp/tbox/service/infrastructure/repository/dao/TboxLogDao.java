package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.TboxLogPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车联终端信息变更日志表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-14
 */
@Mapper
public interface TboxLogDao extends BaseDao<TboxLogPo, Long> {

}
