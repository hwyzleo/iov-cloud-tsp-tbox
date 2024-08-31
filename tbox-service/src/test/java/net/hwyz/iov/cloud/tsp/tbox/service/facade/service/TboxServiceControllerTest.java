package net.hwyz.iov.cloud.tsp.tbox.service.facade.service;

import cn.hutool.json.JSONUtil;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.enums.RemoteControlType;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.RemoteControlRequest;
import net.hwyz.iov.cloud.tsp.tbox.service.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TBOX业务相关服务接口测试类
 *
 * @author hwyz_leo
 */
@AutoConfigureMockMvc
public class TboxServiceControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    private final String path = "/service/tbox";

    @Test
    @Order(1)
    @DisplayName("远程控制")
    public void testRemoteControl() throws Exception {
        RemoteControlRequest request = new RemoteControlRequest();
        request.setVin(vin);
        request.setType(RemoteControlType.FIND_VEHICLE);
        request.setParams(Map.of());
        mockMvc.perform(MockMvcRequestBuilders
                        .post(path + "/action/remoteControl")
                        .content(JSONUtil.toJsonStr(request))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
