package net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.repository;

import net.hwyz.iov.cloud.tsp.framework.commons.domain.BaseRepository;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.enums.RemoteControlType;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;

import java.util.Map;

/**
 * 远控领域仓库接口
 *
 * @author hwyz_leo
 */
public interface RemoteControlRepository extends BaseRepository<String, RemoteControlDo> {

    /**
     * 获取或新建远控领域对象
     *
     * @param remoteControlDo 远控领域对象
     * @return 远控领域对象
     */
    RemoteControlDo getOrCreate(RemoteControlDo remoteControlDo);

}
