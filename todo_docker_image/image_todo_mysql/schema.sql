-- 创建数据库
create database IF NOT EXISTS `todo` default character set utf8 collate utf8_general_ci;

use todo;

-- 表的结构 `todo`

CREATE TABLE IF NOT EXISTS `todo` (
  `id` bigint(20) NOT NULL COMMENT PRIMARY KEY AUTO_INCREMENT '待办事件id',
  `name` varchar(255) NOT NULL COMMENT '事件名',
  `done` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否完成，已完成为1',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，删除为1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

