/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50152
Source Host           : localhost:3306
Source Database       : life_assistant

Target Server Type    : MYSQL
Target Server Version : 50152
File Encoding         : 65001

Date: 2018-01-21 15:37:49
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户编号',
  `account_money` decimal(10,0) NOT NULL COMMENT '账户总金额',
  `userId` int(11) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`accountId`),
  KEY `fk_account_users` (`userId`),
  CONSTRAINT `fk_account_users` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=10000006 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('10000003', '0', '4');
INSERT INTO `account` VALUES ('10000004', '70', '5');
INSERT INTO `account` VALUES ('10000005', '30', '6');

-- ----------------------------
-- Table structure for `account_log`
-- ----------------------------
DROP TABLE IF EXISTS `account_log`;
CREATE TABLE `account_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志编号',
  `accountId` int(11) NOT NULL COMMENT '转出账户',
  `transfer_account` int(11) NOT NULL COMMENT '转入账户',
  `type` varchar(10) NOT NULL COMMENT '类型',
  `log_time` datetime NOT NULL COMMENT '操作时间',
  `money` decimal(10,0) NOT NULL COMMENT '操作金额',
  PRIMARY KEY (`log_id`),
  KEY `fk_accountlog_account` (`accountId`),
  CONSTRAINT `fk_accountlog_account` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_log
-- ----------------------------
INSERT INTO `account_log` VALUES ('1', '10000004', '0', '转入', '2018-01-20 00:00:00', '100');
INSERT INTO `account_log` VALUES ('2', '10000004', '0', '转入', '2018-01-20 00:00:00', '0');
INSERT INTO `account_log` VALUES ('4', '10000004', '10000005', '转出', '2018-01-20 00:00:00', '10');
INSERT INTO `account_log` VALUES ('8', '10000004', '10000005', '转出', '2018-01-20 00:00:00', '10');
INSERT INTO `account_log` VALUES ('9', '10000004', '10000005', '转出', '2018-01-20 00:00:00', '10');
INSERT INTO `account_log` VALUES ('12', '10000005', '10000004', '转出', '2017-12-01 15:32:19', '10');
INSERT INTO `account_log` VALUES ('13', '10000005', '10000004', '转出', '2018-01-21 15:34:56', '10');

-- ----------------------------
-- Table structure for `memorandum`
-- ----------------------------
DROP TABLE IF EXISTS `memorandum`;
CREATE TABLE `memorandum` (
  `memorandum_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `memorandum_title` varchar(50) NOT NULL COMMENT '标题',
  `time` datetime NOT NULL COMMENT '时间',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`memorandum_id`),
  KEY `fk_memorandum_users` (`userId`),
  CONSTRAINT `fk_memorandum_users` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of memorandum
-- ----------------------------
INSERT INTO `memorandum` VALUES ('1', '大数据学习', '2018-01-20 23:04:37', '大数据学习安排', '5');

-- ----------------------------
-- Table structure for `ranking`
-- ----------------------------
DROP TABLE IF EXISTS `ranking`;
CREATE TABLE `ranking` (
  `rankingId` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分排行ID',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `integral` int(11) NOT NULL COMMENT '积分',
  `time` datetime NOT NULL COMMENT '耗时',
  PRIMARY KEY (`rankingId`),
  KEY `fk_ranking_users` (`userId`),
  CONSTRAINT `fk_ranking_users` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ranking
-- ----------------------------
INSERT INTO `ranking` VALUES ('1', '5', '1', '2018-01-21 14:23:07');
INSERT INTO `ranking` VALUES ('2', '6', '1', '2018-01-20 14:23:23');
INSERT INTO `ranking` VALUES ('3', '4', '0', '2018-01-21 14:23:45');
INSERT INTO `ranking` VALUES ('4', '5', '3', '2018-01-21 14:30:32');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `userPassword` varchar(50) NOT NULL COMMENT '用户密码',
  `realName` varchar(50) NOT NULL COMMENT '用户真实姓名',
  `tel` varchar(50) NOT NULL COMMENT '用户电话',
  `address` varchar(50) NOT NULL COMMENT '用户地址',
  `login_time` decimal(50,0) DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('4', 'a', 'a', 'a', '1', 'jn', null);
INSERT INTO `users` VALUES ('5', 'admin', 'root', 'root', '123', 'jn', '20180121153343');
INSERT INTO `users` VALUES ('6', 'user', '123', '456', '123456', 'jn', '20180121131349');
