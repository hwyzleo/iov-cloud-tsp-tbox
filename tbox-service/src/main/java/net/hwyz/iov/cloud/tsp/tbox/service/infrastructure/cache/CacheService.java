package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.cache;

import net.hwyz.iov.cloud.tsp.tbox.api.contract.enums.RemoteControlType;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;

import java.util.Optional;

/**
 * 缓存服务接口
 *
 * @author hwyz_leo
 */
public interface CacheService {

    /**
     * 获取远控领域对象
     *
     * @param vin               车架号
     * @param remoteControlType 远控类型
     * @return 远控领域对象
     */
    Optional<RemoteControlDo> getRemoteControl(String vin, RemoteControlType remoteControlType);

    /**
     * 设置远控领域对象
     *
     * @param remoteControlDo 远控领域对象
     */
    void setRemoteControl(RemoteControlDo remoteControlDo);

    /**
     * 删除远控领域对象
     *
     * @param cmdId 指令ID
     */
    void removeRemoteControl(String cmdId);

}
