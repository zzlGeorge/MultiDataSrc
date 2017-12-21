/*
Navicat MySQL Data Transfer

Source Server         : localcon
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : multi_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-21 17:26:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_details
-- ----------------------------
DROP TABLE IF EXISTS `db_details`;
CREATE TABLE `db_details` (
  `id` int(11) NOT NULL,
  `driverName` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_details
-- ----------------------------
INSERT INTO `db_details` VALUES ('1', 'com.mysql.jdbc.Driver', 'jdbc:mysql://localhost:3306/', 'root', '123456', '2017-12-21 16:52:18');
INSERT INTO `db_details` VALUES ('2', 'com.microsoft.sqlserver.jdbc.SQLServerDriver', 'jdbc:sqlserver://192.168.20.164:1433;databaseName=', 'sa', 'MK2017!', '2017-12-19 09:29:03');
INSERT INTO `db_details` VALUES ('3', 'oracle.jdbc.driver.OracleDriver', 'jdbc:oracle:thin:@192.168.20.11:1521:orcl', 'ecology', 'Medicalsystem0815', '2017-12-19 09:28:59');

-- ----------------------------
-- Table structure for db_src_info
-- ----------------------------
DROP TABLE IF EXISTS `db_src_info`;
CREATE TABLE `db_src_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `srcName` varchar(255) DEFAULT NULL,
  `srcUrlId` int(10) DEFAULT NULL,
  `srcDbName` varchar(255) DEFAULT NULL COMMENT '数据库名',
  `type` int(10) DEFAULT NULL COMMENT '类型',
  `status` int(10) DEFAULT NULL COMMENT '状态；1：启用  0：不启用',
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `数据库链接` (`srcUrlId`),
  CONSTRAINT `数据库链接` FOREIGN KEY (`srcUrlId`) REFERENCES `db_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_src_info
-- ----------------------------
INSERT INTO `db_src_info` VALUES ('1', '本地库multiDB', '1', 'multi_db', '1', '1', '2017-12-12 10:31:52');
INSERT INTO `db_src_info` VALUES ('2', '奉化区人民医院', '2', 'MedicalSIMS001', '2', '1', '2017-12-19 09:28:06');
INSERT INTO `db_src_info` VALUES ('3', '美康OA', '3', 'ECOLOGY', '3', '1', '2017-12-19 09:27:55');

-- ----------------------------
-- Table structure for src_and_mapper
-- ----------------------------
DROP TABLE IF EXISTS `src_and_mapper`;
CREATE TABLE `src_and_mapper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `srcId` int(11) DEFAULT NULL,
  `mapperId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_and_mapper
-- ----------------------------
INSERT INTO `src_and_mapper` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for src_mappers
-- ----------------------------
DROP TABLE IF EXISTS `src_mappers`;
CREATE TABLE `src_mappers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `relativePath` varchar(255) DEFAULT NULL,
  `mapperName` varchar(255) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of src_mappers
-- ----------------------------
INSERT INTO `src_mappers` VALUES ('1', 'mappers/', 'DBDetailsMapper.xml', '1', '2017-12-21 15:43:29', '数据库连接信息');
