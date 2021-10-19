-- 1、用户表
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`
(
    `id`          int(32)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sid`         varchar(64) NOT NULL COMMENT 'sid',
    `lockVersion` int(32)              DEFAULT '0' COMMENT '版本锁',
    `createTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifyTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `isEnable`    int(32)              DEFAULT '1' COMMENT '是否可用：1可用，0不可用',
    `state`       int(32)              DEFAULT '1' COMMENT '状态',
    `isDelete`    int(32)              DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
    `remarks`     varchar(255)         DEFAULT NULL COMMENT '备注',
    `createBySid` varchar(64)          DEFAULT NULL COMMENT '创建人sid',
    `updateBySid` varchar(64)          DEFAULT NULL COMMENT '修改人sid',
    `userName`    VARCHAR(64) NOT NULL COMMENT '用户名',
    `password`    VARCHAR(64) NOT NULL COMMENT '密码',
    `nickname`    VARCHAR(64) NOT NULL COMMENT '用户昵称',
    `userType`    varchar(64)          DEFAULT NULL COMMENT '用户类型',
    `headImage`   varchar(64)          DEFAULT NULL COMMENT '用户头像',
    `isAlphaUser` int(32)              DEFAULT NULL COMMENT '是否是内测用户:1是内测，0是正式',
    PRIMARY KEY (`id`),
    KEY `id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

-- 2、用户真实信息表
DROP TABLE IF EXISTS `system_user_real_message`;
CREATE TABLE `system_user_real_mess`
(
    `id`          int(32)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sid`         varchar(64) NOT NULL COMMENT 'sid',
    `lockVersion` int(32)              DEFAULT '0' COMMENT '版本锁',
    `createTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifyTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `isEnable`    int(32)              DEFAULT '1' COMMENT '是否可用：1可用，0不可用',
    `state`       int(32)              DEFAULT '1' COMMENT '状态',
    `isDelete`    int(32)              DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
    `remarks`     varchar(255)         DEFAULT NULL COMMENT '备注',
    `createBySid` varchar(64)          DEFAULT NULL COMMENT '创建人sid',
    `updateBySid` varchar(64)          DEFAULT NULL COMMENT '修改人sid',
    `name`        VARCHAR(64) NOT NULL COMMENT '姓名',
    `sex`         VARCHAR(64) NOT NULL COMMENT '性别',
    `nickname`    VARCHAR(64) NOT NULL COMMENT '用户昵称',
    `birthday`    timestamp   NULL     DEFAULT NULL COMMENT '生日',
    `address`     varchar(64)          DEFAULT NULL COMMENT '通讯地址',
    `regionSid`   varchar(255)         DEFAULT NULL COMMENT '区域sid',
    `mobile`      varchar(255)         DEFAULT NULL COMMENT '联系方式',
    PRIMARY KEY (`id`),
    KEY `id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='用户真实信息表';

-- 3、区域-省
DROP TABLE IF EXISTS `system_area_province`;
CREATE TABLE `system_area_province`
(
    `id`          int(32)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sid`         varchar(64) NOT NULL COMMENT 'sid',
    `lockVersion` int(32)              DEFAULT '0' COMMENT '版本锁',
    `createTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifyTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `isEnable`    int(32)              DEFAULT '1' COMMENT '是否可用：1可用，0不可用',
    `state`       int(32)              DEFAULT '1' COMMENT '状态',
    `isDelete`    int(32)              DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
    `remarks`     varchar(255)         DEFAULT NULL COMMENT '备注',
    `createBySid` varchar(64)          DEFAULT NULL COMMENT '创建人sid',
    `updateBySid` varchar(64)          DEFAULT NULL COMMENT '修改人sid',
    `name`        VARCHAR(64) NOT NULL COMMENT '名称',
    `code`        VARCHAR(64) NOT NULL COMMENT '编号',
    PRIMARY KEY (`id`),
    KEY `id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='区域-省';

-- 4、区域-市
DROP TABLE IF EXISTS `system_area_city`;
CREATE TABLE `system_area_city`
(
    `id`          int(32)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sid`         varchar(64) NOT NULL COMMENT 'sid',
    `lockVersion` int(32)              DEFAULT '0' COMMENT '版本锁',
    `createTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifyTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `isEnable`    int(32)              DEFAULT '1' COMMENT '是否可用：1可用，0不可用',
    `state`       int(32)              DEFAULT '1' COMMENT '状态',
    `isDelete`    int(32)              DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
    `remarks`     varchar(255)         DEFAULT NULL COMMENT '备注',
    `createBySid` varchar(64)          DEFAULT NULL COMMENT '创建人sid',
    `updateBySid` varchar(64)          DEFAULT NULL COMMENT '修改人sid',
    `provinceSid` varchar(64)          DEFAULT NULL COMMENT '省级sid',
    `name`        VARCHAR(64) NOT NULL COMMENT '名称',
    `code`        VARCHAR(64) NOT NULL COMMENT '编号',
    PRIMARY KEY (`id`),
    KEY `id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='区域-市';

DROP TABLE IF EXISTS `system_area_county`;
CREATE TABLE `system_area_county`
(
    `id`          int(32)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sid`         varchar(64) NOT NULL COMMENT 'sid',
    `lockVersion` int(32)              DEFAULT '0' COMMENT '版本锁',
    `createTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifyTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `isEnable`    int(32)              DEFAULT '1' COMMENT '是否可用：1可用，0不可用',
    `state`       int(32)              DEFAULT '1' COMMENT '状态',
    `isDelete`    int(32)              DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
    `remarks`     varchar(255)         DEFAULT NULL COMMENT '备注',
    `createBySid` varchar(64)          DEFAULT NULL COMMENT '创建人sid',
    `updateBySid` varchar(64)          DEFAULT NULL COMMENT '修改人sid',
    `citySid`     varchar(64)          DEFAULT NULL COMMENT '市级sid',
    `name`        VARCHAR(64) NOT NULL COMMENT '名称',
    `code`        VARCHAR(64) NOT NULL COMMENT '编号',
    PRIMARY KEY (`id`),
    KEY `id` (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='区域-县区';

