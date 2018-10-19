/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : mybook

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2017-11-13 09:04:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `typeId` int(11) NOT NULL,
  `author` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `typeId` (`typeId`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `booktype` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books
-- ----------------------------

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype` (
  `typeId` int(11) NOT NULL auto_increment,
  `typeName` varchar(50) character set utf8 collate utf8_unicode_ci NOT NULL default '',
  PRIMARY KEY  (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=1142 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES ('4', '笑话');
INSERT INTO `booktype` VALUES ('6', '小说');
INSERT INTO `booktype` VALUES ('1135', '1123');
INSERT INTO `booktype` VALUES ('1136', '1123');
INSERT INTO `booktype` VALUES ('1137', '1123');
INSERT INTO `booktype` VALUES ('1138', '1123');
INSERT INTO `booktype` VALUES ('1139', '1123');
INSERT INTO `booktype` VALUES ('1140', '1123');
INSERT INTO `booktype` VALUES ('1141', '1123');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `M_ID` int(11) NOT NULL auto_increment,
  `M_NAME` varchar(255) default NULL,
  `M_STATE` int(11) default '0' COMMENT '0 启用 1 禁用',
  `M_URL` varchar(200) default '',
  `M_ICON` varchar(32) default '',
  `P_ID` int(11) default NULL,
  PRIMARY KEY  (`M_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '系统管理', '0', '', '', null);
INSERT INTO `tb_menu` VALUES ('3', '维护', '0', 'test.jsp', null, null);
INSERT INTO `tb_menu` VALUES ('4', '实时反馈', '0', 'hello.jsp', null, null);
INSERT INTO `tb_menu` VALUES ('7', '提交数据', '0', 'hello.jsp', null, null);
INSERT INTO `tb_menu` VALUES ('8', '已接收报文', '0', 'message/receiveMessagePage.action?type=select', 'redo.png', '4');
INSERT INTO `tb_menu` VALUES ('9', '菜单管理', '0', 'menuDetail.action', 'redo.png', '1');
INSERT INTO `tb_menu` VALUES ('10', '系统参数', '0', 'sysConfigDetail.action', 'redo.png', '3');
INSERT INTO `tb_menu` VALUES ('11', '角色管理', '0', 'roleDetail.action', '', '1');
INSERT INTO `tb_menu` VALUES ('14', '用户管理', '0', 'userDetail.action', '', '1');
INSERT INTO `tb_menu` VALUES ('15', '123', '1', '123', '', '7');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `ROLE_ID` int(11) NOT NULL auto_increment COMMENT '角色ID',
  `ROLE_NM` varchar(64) default '' COMMENT '角色名称',
  `M_NAME` varchar(1024) default '' COMMENT '权限列表',
  PRIMARY KEY  (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理员', '系统管理 菜单管理 角色管理 用户管理 ');
INSERT INTO `tb_role` VALUES ('6', '超级管理员', '系统管理 菜单管理 角色管理 用户管理 维护 系统参数 实时反馈 已接收报文 提交数据 创建报文 ');
INSERT INTO `tb_role` VALUES ('7', '普通管理员', '系统管理 菜单管理 角色管理 用户管理 ');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `R_ID` int(11) NOT NULL,
  `M_ID` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('5', '3');
INSERT INTO `tb_role_menu` VALUES ('5', '10');
INSERT INTO `tb_role_menu` VALUES ('4', '1');
INSERT INTO `tb_role_menu` VALUES ('4', '9');
INSERT INTO `tb_role_menu` VALUES ('4', '11');
INSERT INTO `tb_role_menu` VALUES ('1', '1');
INSERT INTO `tb_role_menu` VALUES ('1', '9');
INSERT INTO `tb_role_menu` VALUES ('1', '11');
INSERT INTO `tb_role_menu` VALUES ('1', '14');
INSERT INTO `tb_role_menu` VALUES ('6', '1');
INSERT INTO `tb_role_menu` VALUES ('6', '9');
INSERT INTO `tb_role_menu` VALUES ('6', '11');
INSERT INTO `tb_role_menu` VALUES ('6', '14');
INSERT INTO `tb_role_menu` VALUES ('6', '3');
INSERT INTO `tb_role_menu` VALUES ('6', '10');
INSERT INTO `tb_role_menu` VALUES ('6', '4');
INSERT INTO `tb_role_menu` VALUES ('6', '8');
INSERT INTO `tb_role_menu` VALUES ('6', '7');
INSERT INTO `tb_role_menu` VALUES ('6', '6');
INSERT INTO `tb_role_menu` VALUES ('7', '1');
INSERT INTO `tb_role_menu` VALUES ('7', '9');
INSERT INTO `tb_role_menu` VALUES ('7', '11');
INSERT INTO `tb_role_menu` VALUES ('7', '14');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `USER_ID` int(11) NOT NULL auto_increment COMMENT '主键 id',
  `LOGIN_NM` varchar(32) default NULL,
  `LOGIN_PWD` varchar(32) default NULL,
  `ROLE_ID` int(11) default '0' COMMENT '角色ID',
  `EM` varchar(64) default NULL,
  `REAL_NM` varchar(64) default '' COMMENT '真实姓名',
  `TE` varchar(255) default '' COMMENT '电话',
  `LOGIN_TIME` timestamp NULL default NULL COMMENT '最后登录时间',
  `STATE` int(11) default '0',
  PRIMARY KEY  (`USER_ID`),
  KEY `tb_user_ibfk_1` (`ROLE_ID`),
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `tb_role` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '0192023a7bbd73250516f069df18b500', '6', '18331861593@163.com', 'hzb', '18331861593', '2017-11-07 09:26:44', '0');
INSERT INTO `tb_user` VALUES ('2', '123', '202cb962ac59075b964b07152d234b70', '6', '123@16.com', '123', '123', null, '1');
INSERT INTO `tb_user` VALUES ('3', '234t', 'adcaec3805aa912c0d0b14a81bedb6ff', '1', '34567@163.com', '3456y7', '3456y', null, '1');
SET FOREIGN_KEY_CHECKS=1;
