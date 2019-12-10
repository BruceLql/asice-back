/*
 Navicat Premium Data Transfer

 Source Server         : Bruce-asice
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 39.108.162.204:3306
 Source Schema         : asice_back

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 10/12/2019 10:31:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mb_reng_corporatesector
-- ----------------------------
DROP TABLE IF EXISTS `mb_reng_corporatesector`;
CREATE TABLE `mb_reng_corporatesector`  (
  `mrc_uuid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键主id',
  `mrc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `mrc_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `mrc_pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id',
  `mrc_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型（1公司、2部门）',
  `mrc_bz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注说明',
  `mrc_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(enable可用、disable禁用)',
  `mrc_jb` int(11) NULL DEFAULT NULL COMMENT '级别',
  `mrc_cdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `mrc_udate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `mrc_order` int(11) NULL DEFAULT NULL COMMENT '排序号',
  `mrc_001` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_002` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_003` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_004` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_005` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_006` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_007` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_008` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_009` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_010` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_011` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_012` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_013` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_014` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_015` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `mrc_016` int(11) NULL DEFAULT NULL COMMENT '预留',
  `mrc_017` int(11) NULL DEFAULT NULL COMMENT '预留',
  `mrc_018` int(11) NULL DEFAULT NULL COMMENT '预留',
  `mrc_019` int(11) NULL DEFAULT NULL COMMENT '预留',
  `mrc_020` int(11) NULL DEFAULT NULL COMMENT '预留',
  `mrc_gsid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  PRIMARY KEY (`mrc_uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司和部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_app_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_user`;
CREATE TABLE `sys_app_user`  (
  `USER_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RIGHTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROLE_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_LOGIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BZ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PHONE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SFID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `START_TIME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `END_TIME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `YEARS` int(10) NULL DEFAULT NULL,
  `NUMBER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_app_user
-- ----------------------------
INSERT INTO `sys_app_user` VALUES ('04762c0b28b643939455c7800c2e2412', 'dsfsd', 'f1290186a5d0b1ceab27f4e77c0c5d68', 'w', '', '55896f5ce3c0494fa6850775a4e29ff6', '', '', '0', '', '18766666666', '', '', '', 0, '001', '18766666666@qq.com');
INSERT INTO `sys_app_user` VALUES ('3faac8fe5c0241e593e0f9ea6f2d5870', 'dsfsdf', 'f1290186a5d0b1ceab27f4e77c0c5d68', 'wewe', '', '68f23fc0caee475bae8d52244dea8444', '', '', '1', '', '18767676767', '', '', '', 0, 'wqwe', 'qweqwe@qq.com');

-- ----------------------------
-- Table structure for sys_dictionaries
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionaries`;
CREATE TABLE `sys_dictionaries`  (
  `ZD_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BIANMA` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ORDY_BY` int(10) NULL DEFAULT NULL,
  `PARENT_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JB` int(10) NULL DEFAULT NULL,
  `P_BM` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ZD_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dictionaries
-- ----------------------------
INSERT INTO `sys_dictionaries` VALUES ('212a6765fddc4430941469e1ec8c8e6c', '人事部', '001', 1, 'c067fdaf51a141aeaa56ed26b70de863', 2, 'BM_001');
INSERT INTO `sys_dictionaries` VALUES ('3cec73a7cc8a4cb79e3f6ccc7fc8858c', '行政部', '002', 2, 'c067fdaf51a141aeaa56ed26b70de863', 2, 'BM_002');
INSERT INTO `sys_dictionaries` VALUES ('48724375640341deb5ef01ac51a89c34', '北京', 'dq001', 1, 'cdba0b5ef20e4fc0a5231fa3e9ae246a', 2, 'DQ_dq001');
INSERT INTO `sys_dictionaries` VALUES ('5a1547632cca449db378fbb9a042b336', '研发部', '004', 4, 'c067fdaf51a141aeaa56ed26b70de863', 2, 'BM_004');
INSERT INTO `sys_dictionaries` VALUES ('7f9cd74e60a140b0aea5095faa95cda3', '财务部', '003', 3, 'c067fdaf51a141aeaa56ed26b70de863', 2, 'BM_003');
INSERT INTO `sys_dictionaries` VALUES ('b861bd1c3aba4934acdb5054dd0d0c6e', '科技不', 'kj', 7, 'c067fdaf51a141aeaa56ed26b70de863', 2, 'BM_kj');
INSERT INTO `sys_dictionaries` VALUES ('c067fdaf51a141aeaa56ed26b70de863', '部门', 'BM', 1, '0', 1, 'BM');
INSERT INTO `sys_dictionaries` VALUES ('cdba0b5ef20e4fc0a5231fa3e9ae246a', '地区', 'DQ', 2, '0', 1, 'DQ');
INSERT INTO `sys_dictionaries` VALUES ('f184bff5081d452489271a1bd57599ed', '上海', 'SH', 2, 'cdba0b5ef20e4fc0a5231fa3e9ae246a', 2, 'DQ_SH');
INSERT INTO `sys_dictionaries` VALUES ('f30bf95e216d4ebb8169ff0c86330b8f', '客服部', '006', 6, 'c067fdaf51a141aeaa56ed26b70de863', 2, 'BM_006');

-- ----------------------------
-- Table structure for sys_gl_qx
-- ----------------------------
DROP TABLE IF EXISTS `sys_gl_qx`;
CREATE TABLE `sys_gl_qx`  (
  `GL_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ROLE_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FX_QX` int(10) NULL DEFAULT NULL,
  `FW_QX` int(10) NULL DEFAULT NULL,
  `QX1` int(10) NULL DEFAULT NULL,
  `QX2` int(10) NULL DEFAULT NULL,
  `QX3` int(10) NULL DEFAULT NULL,
  `QX4` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`GL_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_gl_qx
-- ----------------------------
INSERT INTO `sys_gl_qx` VALUES ('1', '2', 1, 1, 1, 1, 1, 1);
INSERT INTO `sys_gl_qx` VALUES ('2', '1', 1, 0, 1, 1, 0, 0);
INSERT INTO `sys_gl_qx` VALUES ('5532850ad08f4e71b5b99e1cda20fd2d', '1', 1, 1, 1, 1, 0, 0);
INSERT INTO `sys_gl_qx` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '7', 0, 0, 1, 0, 0, 0);
INSERT INTO `sys_gl_qx` VALUES ('68f23fc0caee475bae8d52244dea8444', '7', 0, 0, 1, 0, 0, 0);
INSERT INTO `sys_gl_qx` VALUES ('88eebc58e5b24265a2039d09d619b87c', '1', 1, 1, 1, 1, 0, 0);
INSERT INTO `sys_gl_qx` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '7', 1, 0, 1, 0, 0, 0);
INSERT INTO `sys_gl_qx` VALUES ('e74f713314154c35bd7fc98897859fe3', '6', 1, 1, 1, 1, 0, 0);
INSERT INTO `sys_gl_qx` VALUES ('e8a3f50f923342f7aa31579a80c6f60d', '1', 1, 1, 1, 1, 0, 0);
INSERT INTO `sys_gl_qx` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '7', 1, 1, 1, 0, 0, 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `MENU_ID` int(11) NOT NULL,
  `MENU_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENT_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_ORDER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_ICON` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '#', '0', '900', 'icon-cogs', '2');
INSERT INTO `sys_menu` VALUES (2, '组织管理', 'role.do', '1', '2', NULL, '2');
INSERT INTO `sys_menu` VALUES (5, '系统用户', 'user/listUsers.do', '1', '3', NULL, '2');
INSERT INTO `sys_menu` VALUES (8, '性能监控', 'druid/index.html', '9', '1', NULL, '2');
INSERT INTO `sys_menu` VALUES (9, '系统工具', '#', '0', '910', 'icon-lock', '2');
INSERT INTO `sys_menu` VALUES (10, '接口测试', 'tool/interfaceTest.do', '9', '2', NULL, '2');
INSERT INTO `sys_menu` VALUES (11, '发送邮件', 'tool/goSendEmail.do', '9', '3', NULL, '2');
INSERT INTO `sys_menu` VALUES (12, '置二维码', 'tool/goTwoDimensionCode.do', '9', '4', NULL, '2');
INSERT INTO `sys_menu` VALUES (13, '多级别树', 'tool/ztree.do', '9', '5', NULL, '2');
INSERT INTO `sys_menu` VALUES (14, '地图工具', 'tool/map.do', '9', '6', NULL, '2');
INSERT INTO `sys_menu` VALUES (20, '在线管理', 'onlinemanager/list.do', '1', '5', NULL, '2');
INSERT INTO `sys_menu` VALUES (21, '打印测试', 'tool/printTest.do', '9', '7', NULL, '2');
INSERT INTO `sys_menu` VALUES (31, '参数管理', '#', '0', '600', 'icon-th', '');
INSERT INTO `sys_menu` VALUES (116, '菜单配置', 'menu.do', '1', '23', NULL, '2');
INSERT INTO `sys_menu` VALUES (117, '字典配置', 'dictionaries.do?PARENT_ID=0', '1', '5432', NULL, '2');
INSERT INTO `sys_menu` VALUES (122, '公司和部门(新)', 'corporatesector/list.do', '31', '34242', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ROLE_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RIGHTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENT_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ADD_QX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DEL_QX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EDIT_QX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CHA_QX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `QX_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`) USING BTREE,
  INDEX `index_QX_ID`(`QX_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '22300665694081125256255088660432644348084022', '0', '1', '1', '1', '1', '1');
INSERT INTO `sys_role` VALUES ('2', '超级管理员', '195922889666713460002547992201157528289062', '1', '5359530355754517356808653299065326403622', '5359530355754517356808653299065326403622', '21693083967959563603050634455790200553510', '195922972743463196559790048689098795810598', '2');
INSERT INTO `sys_role` VALUES ('4', '畅容金融', '1983828751024246', '0', '0', '0', '0', '0', NULL);
INSERT INTO `sys_role` VALUES ('5532850ad08f4e71b5b99e1cda20fd2d', '客户', '190558125475725539539489780161790198415360', '1', '16333553612205046246241981156724874149888', '16333553612205046246241981156724874149888', '16333553612205046246241981156724874149888', '190558125475725539539489780161790198415360', '5532850ad08f4e71b5b99e1cda20fd2d');
INSERT INTO `sys_role` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '特级会员', '498', '7', '1048630', '0', '0', '0', '55896f5ce3c0494fa6850775a4e29ff6');
INSERT INTO `sys_role` VALUES ('6', '客户组', '18', '0', '1', '1', '1', '1', NULL);
INSERT INTO `sys_role` VALUES ('68f23fc0caee475bae8d52244dea8444', '中级会员', '498', '7', '0', '0', '0', '0', '68f23fc0caee475bae8d52244dea8444');
INSERT INTO `sys_role` VALUES ('7', '会员组', '498', '0', '0', '0', '0', '1', NULL);
INSERT INTO `sys_role` VALUES ('88eebc58e5b24265a2039d09d619b87c', '经理(业务员)', '10633793463431620964565387989341962278', '1', '0', '0', '0', '0', '88eebc58e5b24265a2039d09d619b87c');
INSERT INTO `sys_role` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '高级会员', '498', '7', '0', '0', '0', '0', 'b0c77c29dfa140dc9b14a29c056f824f');
INSERT INTO `sys_role` VALUES ('e74f713314154c35bd7fc98897859fe3', '黄金客户', '121694457621910022543683507716096', '6', '1', '1', '1', '1', 'e74f713314154c35bd7fc98897859fe3');
INSERT INTO `sys_role` VALUES ('e8a3f50f923342f7aa31579a80c6f60d', '普通管理员', '190558125475725539539489780161790198415360', '1', '', '', '', '190558125475725539539489780161790198415360', 'e8a3f50f923342f7aa31579a80c6f60d');
INSERT INTO `sys_role` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '初级会员', '498', '7', '1', '1', '1', '1', 'f944a9df72634249bbcb8cb73b0c9b86');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `USER_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RIGHTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROLE_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_LOGIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BZ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SKIN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NUMBER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PHONE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gs_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司id',
  `bm_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `jl_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经理id',
  `RECORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '矫注',
  `su_001` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `su_002` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `su_003` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `su_004` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `su_005` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '1dafa10ba840a6ac795c87807bd234db5db69599', '系统管理员', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2019-12-10 10:26:02', '127.0.0.1', '0', '这是系统管理员', 'default', 'admin@main.com', '001', '18788888888', NULL, NULL, NULL, '¢¨£hhiijj', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_qx
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_qx`;
CREATE TABLE `sys_user_qx`  (
  `U_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `C1` int(10) NULL DEFAULT NULL,
  `C2` int(10) NULL DEFAULT NULL,
  `C3` int(10) NULL DEFAULT NULL,
  `C4` int(10) NULL DEFAULT NULL,
  `Q1` int(10) NULL DEFAULT NULL,
  `Q2` int(10) NULL DEFAULT NULL,
  `Q3` int(10) NULL DEFAULT NULL,
  `Q4` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`U_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_qx
-- ----------------------------
INSERT INTO `sys_user_qx` VALUES ('1', 1, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `sys_user_qx` VALUES ('2', 1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO `sys_user_qx` VALUES ('5532850ad08f4e71b5b99e1cda20fd2d', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `sys_user_qx` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `sys_user_qx` VALUES ('68f23fc0caee475bae8d52244dea8444', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `sys_user_qx` VALUES ('88eebc58e5b24265a2039d09d619b87c', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `sys_user_qx` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `sys_user_qx` VALUES ('e74f713314154c35bd7fc98897859fe3', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `sys_user_qx` VALUES ('e8a3f50f923342f7aa31579a80c6f60d', 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `sys_user_qx` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', 0, 0, 0, 0, 0, 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
