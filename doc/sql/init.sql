DROP TABLE IF EXISTS `db_tbox`.`tb_cmd_record`;
CREATE TABLE `db_tbox`.`tb_cmd_record`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `vin`         VARCHAR(20) NOT NULL COMMENT '车架号',
    `cmd_id`      VARCHAR(64) NOT NULL COMMENT '指令ID',
    `cmd_type`    VARCHAR(50) NOT NULL COMMENT '指令类型',
    `cmd_param`   JSON                 DEFAULT NULL COMMENT '指令参数',
    `msg_flow`    TINYINT              DEFAULT NULL COMMENT '消息流向：1 上行，2 下行',
    `msg_retry`   SMALLINT             DEFAULT NULL COMMENT '消息重试次数',
    `msg_time`    TIMESTAMP            DEFAULT NULL COMMENT '消息时间',
    `description` VARCHAR(255)         DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   VARCHAR(64)          DEFAULT NULL COMMENT '创建者',
    `modify_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`   VARCHAR(64)          DEFAULT NULL COMMENT '修改者',
    `row_version` INT                  DEFAULT 1 COMMENT '记录版本',
    `row_valid`   TINYINT              DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='TBOX指令记录表';