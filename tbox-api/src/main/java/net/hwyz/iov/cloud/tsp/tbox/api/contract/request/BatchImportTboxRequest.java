package net.hwyz.iov.cloud.tsp.tbox.api.contract.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.TboxExService;

import java.util.List;

/**
 * 批量导入车联终端信息请求
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchImportTboxRequest {

    /**
     * 车联终端列表
     */
    @NotEmpty(message = "车联终端列表不能为空")
    private List<TboxExService> tboxList;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 批次号
     */
    private String batchNum;

}
