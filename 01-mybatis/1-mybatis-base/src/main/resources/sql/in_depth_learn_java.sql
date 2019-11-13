USE `in_depth_learn_java`;

-- 创建角色表
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
insert  into `role`(`role_id`,`role_name`) values (1,'超级管理员');

-- 创建用户表
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `user_sex` tinyint(4) DEFAULT NULL COMMENT '用户性别(1-男,2-女)',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
insert  into `user`(`user_id`,`user_name`,`user_sex`,`birthday`,`address`) values (1,'JeffOsmond',1,'2019-11-13 16:16:26','北京市北京市');
insert  into `user`(`user_id`,`user_name`,`user_sex`,`birthday`,`address`) values (2,'Alice',2,'2019-11-24 16:16:28','河北省唐山市');