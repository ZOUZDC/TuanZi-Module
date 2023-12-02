# TuanZi-Module


```
CREATE TABLE `xxx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  
  `remark` varchar(500)  NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` int(11) NOT NULL DEFAULT '0',
  `create_by` varchar(50)  NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50)  NOT NULL DEFAULT '',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='XXX';

```