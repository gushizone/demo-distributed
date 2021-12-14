CREATE DATABASE `demo_spring_boot_admin` CHARACTER SET 'utf8mb4';
CREATE TABLE `demo_spring_boot_admin`.`item`
(
    `id`   bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(255) NOT NULL COMMENT '名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;