CREATE DATABASE `demo_sharding` CHARACTER SET 'utf8mb4';

CREATE TABLE `demo_sharding`.`order_1`
(
    `id`      bigint(20) NOT NULL COMMENT '主键',
    `user_id` bigint(20) NOT NULL COMMENT '用户id',
    `remark`  varchar(255) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';

CREATE TABLE `demo_sharding`.`order_2`
(
    `id`      bigint(20) NOT NULL COMMENT '主键',
    `user_id` bigint(20) NOT NULL COMMENT '用户id',
    `remark`  varchar(255) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';

CREATE TABLE `demo_sharding`.`sys_dict`
(
    `id`   bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type` varchar(255) NOT NULL COMMENT '类型',
    `name` varchar(255) NOT NULL COMMENT '名称',
    `code` varchar(255) NOT NULL COMMENT '编码',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典表';