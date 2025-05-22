package net.hwyz.iov.cloud.tsp.tbox.service.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.TboxExService;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.request.BatchImportTboxRequest;
import net.hwyz.iov.cloud.tsp.tbox.service.application.service.TboxInfoAppService;
import net.hwyz.iov.cloud.tsp.tbox.service.facade.assembler.TboxExServiceAssembler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 车联终端信息相关服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service/tbox")
public class TboxInfoServiceController {

    private final TboxInfoAppService tboxInfoAppService;

    /**
     * 批量导入车联终端数据
     *
     * @param request 批量导入车联终端请求
     */
    @PostMapping("/batchImport")
    public void batchImport(@RequestBody @Validated BatchImportTboxRequest request) {
        logger.info("批量导入车联终端数据[{}]", request.getBatchNum());
        tboxInfoAppService.batchImport(request.getBatchNum(), request.getSupplierCode(),
                TboxExServiceAssembler.INSTANCE.toPoList(request.getTboxList()));
    }

    /**
     * 根据序列号获取车联终端信息
     *
     * @param sn 序列号
     * @return 车联终端信息
     */
    @GetMapping("/{sn}")
    public TboxExService getBySn(@PathVariable String sn) {
        logger.info("根据序列号[{}]获取车联终端信息", sn);
        return TboxExServiceAssembler.INSTANCE.fromPo(tboxInfoAppService.getBySn(sn));
    }

}
