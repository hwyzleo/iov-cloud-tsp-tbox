package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.TboxPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车联终端信息表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-14
 */
@Mapper
public interface TboxDao extends BaseDao<TboxPo, Long> {

    /**
     * 根据序列号查询车联终端信息
     *
     * @param sn 序列号
     * @return 车联终端信息
     */
    TboxPo selectBySn(String sn);

}
