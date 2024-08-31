package net.hwyz.iov.cloud.tsp.tbox.api.feign.service;

import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.RemoteControlRequest;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.response.TboxCmdResponse;

/**
 * TBOX业务相关服务接口
 *
 * @author hwyz_leo
 */
public interface TboxServiceApi {

    /**
     * 远程控制
     *
     * @param request 远控请求
     * @return TBOX指令响应
     */
    TboxCmdResponse remoteControl(RemoteControlRequest request);

}
