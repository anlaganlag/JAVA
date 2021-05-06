DROP DATABASE IF EXISTS roses_order;
CREATE DATABASE IF NOT EXISTS roses_order DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use roses_order;

CREATE TABLE `goods_order` (
  `id` bigint(20) NOT NULL,
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `count` int(11) NOT NULL COMMENT '数量',
  `sum` decimal(10,0) DEFAULT NULL COMMENT '总金额',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '下单人id',
  `status` int(11) DEFAULT NULL COMMENT '订单状态：1.未完成    2.已完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

DROP TABLE IF EXISTS `rocketmq_transaction_log`;
CREATE TABLE `rocketmq_transaction_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `transaction_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事务id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `rocketmq_transaction_log` VALUES (1169258405755531265, '64671bb4-a2a2-4d4d-8277-177e151c838b', '存储本地事务');
INSERT INTO `rocketmq_transaction_log` VALUES (1169259223137300482, 'cb3f2f2a-c56d-4ff3-b3c9-3d7e13e8bb5e', '存储本地事务');

DROP DATABASE IF EXISTS roses_account;
CREATE DATABASE IF NOT EXISTS roses_account DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use roses_account;

CREATE TABLE `flow_record` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `name` varchar(255) NOT NULL COMMENT '流水名称',
  `sum` decimal(10,2) NOT NULL COMMENT '总价',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流水记录';

DROP DATABASE IF EXISTS roses_message;
CREATE DATABASE IF NOT EXISTS roses_message DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use roses_message;

CREATE TABLE `reliable_message` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `message_id` varchar(50) NOT NULL DEFAULT '' COMMENT '消息ID',
  `message_body` longtext NOT NULL COMMENT '消息内容',
  `message_data_type` varchar(50) DEFAULT NULL COMMENT '消息数据类型',
  `consumer_queue` varchar(100) NOT NULL DEFAULT '' COMMENT '消费队列',
  `message_send_times` smallint(6) NOT NULL DEFAULT '0' COMMENT '消息重发次数',
  `already_dead` char(1) NOT NULL DEFAULT '' COMMENT '是否死亡\r\n\r\nY：已死亡\r\nN：未死亡   \r\n',
  `status` varchar(20) NOT NULL DEFAULT '' COMMENT '状态 \r\n\r\nWAIT_VERIFY：待确认  \r\nSENDING：发送中',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  `biz_unique_id` bigint(20) DEFAULT NULL COMMENT '业务系统唯一id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `AK_Key_2` (`message_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='可靠消息表';