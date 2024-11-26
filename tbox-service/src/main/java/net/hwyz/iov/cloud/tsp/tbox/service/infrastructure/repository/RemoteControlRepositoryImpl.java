package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.AbstractRepository;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.repository.RemoteControlRepository;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.cache.CacheService;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.assembler.CmdRecordPoAssembler;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao.CmdRecordDao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 远控领域仓库接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class RemoteControlRepositoryImpl extends AbstractRepository<String, RemoteControlDo> implements RemoteControlRepository {

    private final CacheService cacheService;
    private final CmdRecordDao cmdRecordDao;

    @Override
    public RemoteControlDo getOrCreate(RemoteControlDo remoteControlDo) {
        RemoteControlDo remoteControlDoLoad = cacheService.getRemoteControl(remoteControlDo.getVin(), remoteControlDo.getType()).orElse(remoteControlDo);
        remoteControlDoLoad.stateLoad();
        return remoteControlDoLoad;
    }

    @Override
    public Optional<RemoteControlDo> getById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean save(RemoteControlDo remoteControlDo) {
        switch (remoteControlDo.getState()) {
            case CHANGED -> {
                cmdRecordDao.insertPo(CmdRecordPoAssembler.INSTANCE.fromRemoteControlDo(remoteControlDo));
                cacheService.setRemoteControl(remoteControlDo);
            }
            default -> {
                return false;
            }
        }
        return true;
    }

}
