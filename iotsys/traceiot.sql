/*
Navicat MySQL Data Transfer

Source Server         : 192.168.89.233 developer
Source Server Version : 50543
Source Host           : 192.168.89.233:3306
Source Database       : traceiot

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2018-02-09 16:19:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `codekey` varchar(20) DEFAULT NULL COMMENT '代码key值',
  `codevalue` varchar(50) DEFAULT NULL COMMENT '代码value值',
  `codetype` varchar(20) DEFAULT NULL COMMENT '代码类型',
  `description` longtext COMMENT '描述',
  `parentid` int(11) DEFAULT NULL COMMENT '父类id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2214 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for device_info
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info` (
  `id` varchar(128) DEFAULT NULL,
  `deviceNo` varchar(64) DEFAULT NULL,
  `cr` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for iot_camera_info
-- ----------------------------
DROP TABLE IF EXISTS `iot_camera_info`;
CREATE TABLE `iot_camera_info` (
  `id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `camera_name` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '摄像头名称',
  `camera_unit_type` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备型号',
  `serial_number` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备序号',
  `camera_login_id` varchar(11) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '摄像头登录ID',
  `camera_password` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备密码',
  `camera_ptz_control` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '云台控制',
  `camera_zoom_control` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '变焦控制',
  `camera_channel` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '通道',
  `pk_enterprise_id` varchar(32) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '关联企业',
  `enterprise_name` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备所属的企业名称',
  `pk_device_id` varchar(32) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '视频服务器',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更细时间',
  `comments` text COLLATE utf8_sinhala_ci COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_device_model
-- ----------------------------
DROP TABLE IF EXISTS `iot_device_model`;
CREATE TABLE `iot_device_model` (
  `id` varchar(32) COLLATE utf8_sinhala_ci NOT NULL COMMENT '主键',
  `type_name` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备型号名称',
  `identification` int(255) DEFAULT NULL COMMENT '设备的类型',
  `comments` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_devices_fixed
-- ----------------------------
DROP TABLE IF EXISTS `iot_devices_fixed`;
CREATE TABLE `iot_devices_fixed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_no` varchar(32) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备号',
  `message` text COLLATE utf8_sinhala_ci COMMENT '信息',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=753 DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_devices_heartbeat
-- ----------------------------
DROP TABLE IF EXISTS `iot_devices_heartbeat`;
CREATE TABLE `iot_devices_heartbeat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_no` varchar(32) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备号',
  `message` text COLLATE utf8_sinhala_ci COMMENT '消息',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23433 DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_devices_info
-- ----------------------------
DROP TABLE IF EXISTS `iot_devices_info`;
CREATE TABLE `iot_devices_info` (
  `id` varchar(32) COLLATE utf8_sinhala_ci NOT NULL,
  `device_name` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '设备名称',
  `enterprise_id` varchar(32) COLLATE utf8_sinhala_ci NOT NULL COMMENT '设备所属的企业ID',
  `enterprise_name` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '设备所属的企业名称',
  `probe_number` int(11) NOT NULL COMMENT '探头数量',
  `controller_number` int(11) NOT NULL COMMENT '控制器数量',
  `serial_number` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '设备序列号',
  `type_id` varchar(32) COLLATE utf8_sinhala_ci NOT NULL COMMENT '设备型号id',
  `verification_code` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备验证码',
  `comments` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_devices_original
-- ----------------------------
DROP TABLE IF EXISTS `iot_devices_original`;
CREATE TABLE `iot_devices_original` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_no` varchar(32) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备序号',
  `message` text COLLATE utf8_sinhala_ci COMMENT '消息',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10502 DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_massif_product
-- ----------------------------
DROP TABLE IF EXISTS `iot_massif_product`;
CREATE TABLE `iot_massif_product` (
  `id` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '数据ID',
  `massif_id` varchar(32) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '地块id',
  `device_id` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备唯一编号			',
  `device_type` int(11) DEFAULT NULL COMMENT '地块添加的设备类型（视频/探头）1：物联网设备；2：摄像头',
  `enterprise_id` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '基地所属的企业ID',
  `enterprise_name` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '基地所属的企业名称',
  `base_id` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '基地ID',
  `base_name` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '基地名称',
  `massif_name` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '地块名称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间				',
  `upload_time` datetime DEFAULT NULL COMMENT '更新时间				',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_power_controller
-- ----------------------------
DROP TABLE IF EXISTS `iot_power_controller`;
CREATE TABLE `iot_power_controller` (
  `id` varchar(32) COLLATE utf8_sinhala_ci NOT NULL COMMENT '主键',
  `controller_name` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '设备型号名称',
  `device_serial_number` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '对应  iot_devices_info 表的序号',
  `survey_no` int(2) NOT NULL COMMENT '控制器的序号（通道号码）1-32',
  `power_switch` varchar(1) COLLATE utf8_sinhala_ci NOT NULL COMMENT '控制器的开关  3开  0关',
  `state` varchar(1) COLLATE utf8_sinhala_ci NOT NULL COMMENT '控制器是否修改过  0没有  1修改过',
  `ico_name` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '控制器的图片',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  `comments` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_probe
-- ----------------------------
DROP TABLE IF EXISTS `iot_probe`;
CREATE TABLE `iot_probe` (
  `id` varchar(32) COLLATE utf8_sinhala_ci NOT NULL,
  `survey_name` varchar(255) COLLATE utf8_sinhala_ci NOT NULL,
  `unit` varchar(255) COLLATE utf8_sinhala_ci NOT NULL,
  `device_serial_number` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '探测设备id对应iot_device_info表serial_number',
  `survey_no` int(255) NOT NULL COMMENT '探头编号  1号 2号',
  `survey_max` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '探头最大值',
  `survey_min` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '探头最小值',
  `sort_no` int(255) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_probe_specific_data
-- ----------------------------
DROP TABLE IF EXISTS `iot_probe_specific_data`;
CREATE TABLE `iot_probe_specific_data` (
  `id` varchar(255) COLLATE utf8_sinhala_ci NOT NULL,
  `pk_probe_id` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '探测器id',
  `pk_devices_id` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '设备id',
  `device_serial_number` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '采集器编号',
  `survey_no` int(11) NOT NULL COMMENT '探头号（通道号码）',
  `probe_data` float(255,0) NOT NULL COMMENT '探测器数据',
  `probe_unit` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '探测器单位',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_probe_specific_data_day
-- ----------------------------
DROP TABLE IF EXISTS `iot_probe_specific_data_day`;
CREATE TABLE `iot_probe_specific_data_day` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pk_probe_id` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '探测器id',
  `pk_devices_id` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备id',
  `probe_data` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '探测器数据',
  `probe_unit` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '探测器单位',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `survey_no` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_probe_specific_data_hour
-- ----------------------------
DROP TABLE IF EXISTS `iot_probe_specific_data_hour`;
CREATE TABLE `iot_probe_specific_data_hour` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pk_probe_id` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '探测器id',
  `pk_devices_id` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '设备id',
  `probe_data` varchar(255) COLLATE utf8_sinhala_ci NOT NULL COMMENT '探测器数据',
  `probe_unit` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '探测器单位',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `latest_value` int(11) DEFAULT NULL COMMENT '最新值',
  `average_value` int(10) unsigned zerofill DEFAULT NULL COMMENT '平局值',
  `survey_no` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4050 DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_probe_specific_data_month
-- ----------------------------
DROP TABLE IF EXISTS `iot_probe_specific_data_month`;
CREATE TABLE `iot_probe_specific_data_month` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pk_probe_id` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '探测器id',
  `pk_devices_id` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '设备id',
  `probe_data` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '探测器数据',
  `probe_unit` varchar(255) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '探测器单位',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `survey_no` int(255) DEFAULT NULL COMMENT '探头序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for iot_video_server_info
-- ----------------------------
DROP TABLE IF EXISTS `iot_video_server_info`;
CREATE TABLE `iot_video_server_info` (
  `id` varchar(11) COLLATE utf8_sinhala_ci NOT NULL COMMENT '数据ID',
  `server_address` varchar(11) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '服务器地址				',
  `service_name` varchar(11) COLLATE utf8_sinhala_ci DEFAULT NULL COMMENT '服务器供应商				',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间				',
  `upload_time` datetime DEFAULT NULL COMMENT '更新时间				',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_sinhala_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `passWord` varchar(20) NOT NULL,
  `account` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
