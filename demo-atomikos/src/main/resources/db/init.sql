CREATE DATABASE `demo_distributed_a` CHARACTER SET 'utf8mb4';
CREATE TABLE `demo_distributed_a`.`item_a`
(
    `id`   bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(255) NOT NULL COMMENT '名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE DATABASE `demo_distributed_b` CHARACTER SET 'utf8mb4';
CREATE TABLE `demo_distributed_b`.`item_b`
(
    `id`   bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(255) NOT NULL COMMENT '名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO `demo_distributed_a`.`item_a` (`name`) VALUES ('foo');

INSERT INTO `demo_distributed_b`.`item_b` (`name`) VALUES ('bar');
