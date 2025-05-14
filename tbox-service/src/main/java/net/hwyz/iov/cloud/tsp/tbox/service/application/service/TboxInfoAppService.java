package net.hwyz.iov.cloud.tsp.tbox.service.application.service;

import cn.hutool.core.util.ObjUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.util.StrUtil;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao.TboxDao;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.dao.TboxLogDao;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.TboxLogPo;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.TboxPo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TBOX信息相关应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TboxInfoAppService {

    private final TboxDao tboxDao;
    private final TboxLogDao tboxLogDao;

    /**
     * 批量导入车联终端信息
     *
     * @param batchNum     批次号
     * @param supplierCode 供应商编码
     * @param tboxList     车联终端列表
     */
    public void batchImport(String batchNum, String supplierCode, List<TboxPo> tboxList) {
        if (StrUtil.isBlank(supplierCode)) {
            logger.warn("数据批次[{}]车联终端信息供应商编码为空", batchNum);
        }
        for (TboxPo tboxPo : tboxList) {
            if (ObjUtil.isNull(tboxDao.selectBySn(tboxPo.getSn()))) {
                tboxPo.setSupplierCode(supplierCode);
                tboxDao.insertPo(tboxPo);
                recordLog(tboxPo, "数据批次[" + batchNum + "]数据导入");
            } else {
                logger.warn("数据批次[{}]车联终端信息[{}]已存在", batchNum, tboxPo.getSn());
            }
        }
    }

    /**
     * 记录车联终端信息变更日志
     *
     * @param tboxPo 车联终端对象
     * @param remark 变更备注
     */
    private void recordLog(TboxPo tboxPo, String remark) {
        tboxLogDao.insertPo(TboxLogPo.builder()
                .sn(tboxPo.getSn())
                .configWord(tboxPo.getConfigWord())
                .hardwareVer(tboxPo.getHardwareVer())
                .softwareVer(tboxPo.getSoftwareVer())
                .hardwareNo(tboxPo.getHardwareNo())
                .softwareNo(tboxPo.getSoftwareNo())
                .hsm(tboxPo.getHsm())
                .iccid1(tboxPo.getIccid1())
                .iccid2(tboxPo.getIccid2())
                .description(remark)
                .build());
    }

}
