package net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.assembler;

import net.hwyz.iov.cloud.tsp.tbox.service.domain.tbox.model.RemoteControlDo;
import net.hwyz.iov.cloud.tsp.tbox.service.infrastructure.repository.po.CmdRecordPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 远控命令记录数据对象转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface CmdRecordPoAssembler {

    CmdRecordPoAssembler INSTANCE = Mappers.getMapper(CmdRecordPoAssembler.class);

    /**
     * 数据对象转领域对象
     *
     * @param cmdRecordPo 数据对象
     * @return 远控领域对象
     */
    @Mappings({
            @Mapping(target = "type", source = "cmdType"),
            @Mapping(target = "params", expression = "java(net.hwyz.iov.cloud.framework.common.util.AssemblerHelper.json2Map(cmdRecordPo.getCmdParam()))"),
            @Mapping(target = "msgFlow", expression = "java(net.hwyz.iov.cloud.tsp.tbox.service.domain.contract.enums.MsgFlow.valOf(cmdRecordPo.getMsgFlow()))")
    })
    RemoteControlDo toRemoteControlDo(CmdRecordPo cmdRecordPo);

    /**
     * 远控领域对象转数据对象
     *
     * @param remoteControlDo 远控领域对象
     * @return 数据对象
     */
    @Mappings({
            @Mapping(target = "cmdType", expression = "java(net.hwyz.iov.cloud.tsp.tbox.service.domain.contract.enums.TboxCmdType.REMOTE_CONTROL.name())"),
            @Mapping(target = "cmdParam", expression = "java(net.hwyz.iov.cloud.framework.common.util.AssemblerHelper.map2Json(remoteControlDo.getParams()))"),
            @Mapping(target = "msgFlow", expression = "java(remoteControlDo.getMsgFlow().getValue())")
    })
    CmdRecordPo fromRemoteControlDo(RemoteControlDo remoteControlDo);

}
