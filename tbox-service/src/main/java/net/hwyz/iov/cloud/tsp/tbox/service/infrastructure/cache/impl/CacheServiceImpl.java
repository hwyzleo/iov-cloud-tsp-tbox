package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.cache.impl;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.enums.RemoteControlType;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.contract.enums.MsgFlow;
import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.cache.CacheService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 缓存服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * Redis Key前缀：远控
     */
    private static final String REDIS_KEY_PREFIX_REMOTE_CONTROL = "tbox:remote-control:";

    @Override
    public Optional<RemoteControlDo> getRemoteControl(String vin, RemoteControlType remoteControlType) {
        String remoteControlDoJson = redisTemplate.opsForValue().get(REDIS_KEY_PREFIX_REMOTE_CONTROL + vin + "-" + remoteControlType.name());
        if (StrUtil.isNotBlank(remoteControlDoJson)) {
            JSONObject jsonObject = JSONUtil.parseObj(remoteControlDoJson);
            RemoteControlDo remoteControlDo = RemoteControlDo.builder()
                    .vin(jsonObject.getStr("vin"))
                    .cmdId(jsonObject.getStr("cmdId"))
                    .type(RemoteControlType.valueOf(jsonObject.getStr("type")))
                    .params(jsonObject.getJSONObject("params").toBean(new TypeReference<>() {
                    }))
                    .msgFlow(MsgFlow.valueOf(jsonObject.getStr("msgFlow")))
                    .msgRetry(jsonObject.getInt("msgRetry"))
                    .msgTime(jsonObject.getDate("msgTime"))
                    .firstDownTime(jsonObject.getDate("firstDownTime"))
                    .build();
            remoteControlDo.stateLoad();
            return Optional.of(remoteControlDo);
        }
        return Optional.empty();
    }

    @Override
    public void setRemoteControl(RemoteControlDo remoteControlDo) {
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX_REMOTE_CONTROL + remoteControlDo.getVin() + "-" + remoteControlDo.getType().name(),
                JSONUtil.parse(remoteControlDo).toJSONString(0));
    }

}
