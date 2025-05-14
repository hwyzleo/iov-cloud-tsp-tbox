DROP TABLE IF EXISTS `db_tbox`.`tb_cmd_record`;
CREATE TABLE `db_tbox`.`tb_cmd_record`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `vin`          VARCHAR(20) NOT NULL COMMENT '车架号',
    `cmd_id`       VARCHAR(64) NOT NULL COMMENT '指令ID',
    `cmd_type`     VARCHAR(50) NOT NULL COMMENT '指令类型',
    `cmd_param`    JSON                 DEFAULT NULL COMMENT '指令参数',
    `msg_flow`     TINYINT              DEFAULT NULL COMMENT '消息流向：1 上行，2 下行',
    `msg_retry`    SMALLINT             DEFAULT NULL COMMENT '消息重试次数',
    `msg_time`     TIMESTAMP            DEFAULT NULL COMMENT '消息时间',
    `msg_ack_time` TIMESTAMP            DEFAULT NULL COMMENT '消息确认时间',
    `description`  VARCHAR(255)         DEFAULT NULL COMMENT '备注',
    `create_time`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`    VARCHAR(64)          DEFAULT NULL COMMENT '创建者',
    `modify_time`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`    VARCHAR(64)          DEFAULT NULL COMMENT '修改者',
    `row_version`  INT                  DEFAULT 1 COMMENT '记录版本',
    `row_valid`    TINYINT              DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`cmd_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='车联终端指令记录表';

DROP TABLE IF EXISTS `db_tbox`.`tb_tbox`;
CREATE TABLE `db_tbox`.`tb_tbox`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sn`            VARCHAR(255) NOT NULL COMMENT '序列号',
    `no`            VARCHAR(255)          DEFAULT NULL COMMENT '零件编号',
    `config_word`   VARCHAR(255)          DEFAULT NULL COMMENT '配置字',
    `supplier_code` VARCHAR(255)          DEFAULT NULL COMMENT '供应商编码',
    `hardware_ver`  VARCHAR(255)          DEFAULT NULL COMMENT '硬件版本号',
    `software_ver`  VARCHAR(255)          DEFAULT NULL COMMENT '软件版本号',
    `hardware_no`   VARCHAR(255)          DEFAULT NULL COMMENT '硬件零件号',
    `software_no`   VARCHAR(255)          DEFAULT NULL COMMENT '软件零件号',
    `hsm`           VARCHAR(100)          DEFAULT NULL COMMENT '硬件安全模块',
    `iccid1`        VARCHAR(50)  NOT NULL COMMENT '集成电路卡识别码1',
    `iccid2`        VARCHAR(50)           DEFAULT NULL COMMENT '集成电路卡识别码2',
    `imei`          VARCHAR(20)           DEFAULT NULL COMMENT '国际移动设备识别码',
    `description`   VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`     VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`     VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`   INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`     TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`sn`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='车联终端信息表';

DROP TABLE IF EXISTS `db_tbox`.`tb_tbox_log`;
CREATE TABLE `db_tbox`.`tb_tbox_log`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sn`           VARCHAR(255) NOT NULL COMMENT '序列号',
    `config_word`  VARCHAR(255)          DEFAULT NULL COMMENT '配置字',
    `hardware_ver` VARCHAR(255)          DEFAULT NULL COMMENT '硬件版本号',
    `software_ver` VARCHAR(255)          DEFAULT NULL COMMENT '软件版本号',
    `hardware_no`  VARCHAR(255)          DEFAULT NULL COMMENT '硬件零件号',
    `software_no`  VARCHAR(255)          DEFAULT NULL COMMENT '软件零件号',
    `hsm`          VARCHAR(100)          DEFAULT NULL COMMENT '硬件安全模块',
    `iccid1`       VARCHAR(50)           DEFAULT NULL COMMENT '集成电路卡识别码1',
    `iccid2`       VARCHAR(50)           DEFAULT NULL COMMENT '集成电路卡识别码2',
    `description`  VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`    VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`    VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`  INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`    TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    INDEX `idx_sn` (`sn`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='车联终端信息变更日志表';