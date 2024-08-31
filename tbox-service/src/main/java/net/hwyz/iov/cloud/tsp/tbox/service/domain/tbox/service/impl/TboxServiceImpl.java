package net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.repository.RemoteControlRepository;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.service.TboxService;
import org.springframework.stereotype.Service;

/**
 * TBOX领域服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TboxServiceImpl implements TboxService {

    private final RemoteControlRepository remoteControlRepository;

    @Override
    public RemoteControlDo getOrCreateRemoteControl(RemoteControlDo remoteControlDo) {
        return remoteControlRepository.getOrCreate(remoteControlDo);
    }

}
