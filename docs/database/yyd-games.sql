CREATE TABLE `competition_rules`
(
    `eventPattern` varchar(500)         DEFAULT NULL COMMENT '出厂安排',
    `id`           int(32)     NOT NULL AUTO_INCREMENT,
    `sid`          varchar(64) NOT NULL DEFAULT '0',
    `lockVersion`  int(32)     NOT NULL DEFAULT '0' COMMENT '记录版本，锁',
    `createTime`   timestamp   NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录创建时间',
    `modifyTime`   timestamp   NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '记录最后修改时间',
    `state`        int(11)     NOT NULL DEFAULT '1' COMMENT '记录状态值',
    `isEnable`     int(32)     NOT NULL DEFAULT '1' COMMENT '记录是否可用，1:可用，0:不可用',
    `isDelete`     int(32)     NOT NULL DEFAULT '0' COMMENT '记录是否被删除，0:未删除，1:已经删除',
    `remarks`      varchar(255)         DEFAULT NULL COMMENT '备注信息',
    `createBySid`  varchar(64)          DEFAULT NULL COMMENT '创建者',
    `updateBySid`  varchar(64)          DEFAULT NULL COMMENT '更新者',
    `matchId`      int(11)              DEFAULT NULL COMMENT '比赛id',
    `groups`       int(11)              DEFAULT '0' COMMENT '小组赛制（3代表3局2胜，5代表5局3胜，7代表7局4胜）',
    `knockout`     int(11)              DEFAULT '0' COMMENT '淘汰赛制（3代表3局2胜，5代表5局3胜，7代表7局4胜）',
    `finals`       int(11)              DEFAULT '0' COMMENT '决赛（3代表3局2胜，5代表5局3胜，7代表7局4胜）',
    `intos`        int(11)              DEFAULT '0' COMMENT '进入二阶段的人数（出线人数）',
    `eventType`    int(11)              DEFAULT '1' COMMENT '赛事类型，0是单循环、1是循环淘汰、2单淘汰',
    `ruleScores`   int(32)              DEFAULT '0' COMMENT '赛制分,(赛制/2+1)*11',
    PRIMARY KEY (`id`),
    UNIQUE KEY `rulesUnique` (`matchId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1561
  DEFAULT CHARSET = utf8 COMMENT ='赛制表(赛事安排)'
