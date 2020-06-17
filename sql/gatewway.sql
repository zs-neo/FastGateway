/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : gatewway

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2020-06-17 15:49:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gw_api
-- ----------------------------
DROP TABLE IF EXISTS `gw_api`;
CREATE TABLE `gw_api` (
  `id` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `timeout` int(11) DEFAULT NULL,
  `createdtime` datetime DEFAULT NULL,
  `modifiedtime` datetime DEFAULT NULL,
  `sysid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gw_api
-- ----------------------------
INSERT INTO `gw_api` VALUES ('1', '123123', 'com.example.demo.service.UserService', 'queryUser', 'v1', '10', '2020-06-01 21:47:10', '2020-06-12 21:47:13', '1');

-- ----------------------------
-- Table structure for gw_api_param
-- ----------------------------
DROP TABLE IF EXISTS `gw_api_param`;
CREATE TABLE `gw_api_param` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `sequence` int(10) DEFAULT NULL,
  `createdtime` datetime DEFAULT NULL,
  `modifiedtime` datetime DEFAULT NULL,
  `api_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gw_api_param
-- ----------------------------
INSERT INTO `gw_api_param` VALUES ('1', 'username', 'java.lang.String', '0', '2020-06-14 09:24:10', '2020-06-14 09:24:13', '1');
INSERT INTO `gw_api_param` VALUES ('2', 'password', 'java.lang.String', '1', '2020-06-17 14:40:39', '2020-06-17 14:40:41', '1');

-- ----------------------------
-- Table structure for gw_ip_bwlist
-- ----------------------------
DROP TABLE IF EXISTS `gw_ip_bwlist`;
CREATE TABLE `gw_ip_bwlist` (
  `id` int(11) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `createdtime` datetime DEFAULT NULL,
  `modifiedtime` datetime DEFAULT NULL,
  `isblack` int(1) DEFAULT '0',
  `iswhite` int(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gw_ip_bwlist
-- ----------------------------
INSERT INTO `gw_ip_bwlist` VALUES ('1', '1.1.1.1', '2020-06-14 09:24:50', '2020-06-14 09:24:47', '0', '0');

-- ----------------------------
-- Table structure for gw_sys
-- ----------------------------
DROP TABLE IF EXISTS `gw_sys`;
CREATE TABLE `gw_sys` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `createdtime` datetime DEFAULT NULL,
  `modifiedtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gw_sys
-- ----------------------------
INSERT INTO `gw_sys` VALUES ('1', 'usersystem', '用户系统', '2020-06-14 09:22:21', '2020-06-14 09:22:24');
