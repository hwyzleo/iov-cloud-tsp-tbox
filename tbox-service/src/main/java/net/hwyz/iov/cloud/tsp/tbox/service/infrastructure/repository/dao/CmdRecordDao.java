package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.CmdRecordPo;
import net.hwyz.iov.cloud.tsp.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * TBOX指令记录表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2024-08-30
 */
@Mapper
public interface CmdRecordDao extends BaseDao<CmdRecordPo, Long> {

    /**
     * 根据指令ID查询指令记录
     *
     * @param cmdId 指令ID
     * @return 指令记录
     */
    CmdRecordPo selectPoByCmdId(String cmdId);

}
