DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(63) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `alias`      VARCHAR(63) NOT NULL DEFAULT '',
    `os`         TINYINT     NOT NULL DEFAULT 0 COMMENT '0=linux 1=windows',
    `host`       VARCHAR(63) NOT NULL DEFAULT '',
    `project_id` BIGINT      NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `deploy_task`;
CREATE TABLE `deploy_task`
(
    `id`           CHAR(32)    NOT NULL DEFAULT '' COMMENT '由业务生成的 UUID',
    `service_name` VARCHAR(63) NOT NULL DEFAULT '',
    `project_id`   BIGINT      NOT NULL DEFAULT 0,
    `machine_id`   BIGINT      NOT NULL DEFAULT 0,
    `status`       TINYINT     NOT NULL DEFAULT 0 COMMENT '0=未部署 1=已部署',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `deploy_task_dependency`;
CREATE TABLE `deploy_task_dependency`
(
    `id`                    BIGINT   NOT NULL AUTO_INCREMENT,
    `source_deploy_task_id` CHAR(32) NOT NULL DEFAULT '',
    `target_deploy_task_id` CHAR(32) NOT NULL DEFAULT '' COMMENT '被依赖的 deploy_task',
    PRIMARY KEY (`id`)
);
