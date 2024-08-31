package net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.service;

import net.hwyz.iov.cloud.tsp.tbox.api.contract.enums.RemoteControlType;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;

import java.util.Map;

/**
 * TBOX领域服务接口
 *
 * @author hwyz_leo
 */
public interface TboxService {

    /**
     * 获取或新建远控领域对象
     *
     * @param remoteControlDo 远控领域对象
     * @return 远控领域对象
     */
    RemoteControlDo getOrCreateRemoteControl(RemoteControlDo remoteControlDo);

}
