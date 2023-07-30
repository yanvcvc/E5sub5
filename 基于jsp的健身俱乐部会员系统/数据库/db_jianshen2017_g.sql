/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : db_jianshen2017_g

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2017-02-19 17:46:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_card`
-- ----------------------------
DROP TABLE IF EXISTS `t_card`;
CREATE TABLE `t_card` (
  `card_id` int(11) NOT NULL,
  `card_leixiong` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `card_guize` text CHARACTER SET utf8,
  `card_youhuizhengce` text CHARACTER SET utf8,
  `card_one1` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `card_one2` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `card_one3` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `card_one4` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `card_one5` int(11) DEFAULT NULL,
  `card_one6` int(11) DEFAULT NULL,
  `card_one7` datetime DEFAULT NULL,
  `card_one8` datetime DEFAULT NULL,
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_card
-- ----------------------------
INSERT INTO `t_card` VALUES ('1', '普通卡', '普通卡普通卡普通卡', '普通卡普通卡普通卡', null, null, null, null, null, null, null, null);
INSERT INTO `t_card` VALUES ('2', '白金卡', '白金卡白金卡', '白金卡白金卡', null, null, null, null, null, null, null, null);
INSERT INTO `t_card` VALUES ('3', '钻石卡', '钻石卡钻石卡钻石卡', '钻石卡钻石卡钻石卡', null, null, null, null, null, null, null, null);
INSERT INTO `t_card` VALUES ('4', '白金卡', '白金卡白金卡白金卡', '白金卡白金卡白金卡', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_qicai`
-- ----------------------------
DROP TABLE IF EXISTS `t_qicai`;
CREATE TABLE `t_qicai` (
  `qicai_id` int(11) NOT NULL,
  `qicai_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `qicai_goumairiqi` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `qicai_beizhu` text CHARACTER SET utf8,
  `qicai_one1` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `qicai_one2` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `qicai_one3` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `qicai_one4` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `qicai_one5` int(11) DEFAULT NULL,
  `qicai_one6` int(11) DEFAULT NULL,
  `qicai_one7` datetime DEFAULT NULL,
  `qicai_one8` datetime DEFAULT NULL,
  PRIMARY KEY (`qicai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_qicai
-- ----------------------------
INSERT INTO `t_qicai` VALUES ('2', '跑步机', '2010-06-01', '跑步机跑步机跑步机跑步机跑步机', null, null, null, null, null, null, null, null);
INSERT INTO `t_qicai` VALUES ('3', '瑜伽器材', '2010-06-15', '瑜伽器材瑜伽器材', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_pw` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `user_realname` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_address` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_sex` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_tel` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_email` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_qq` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_man` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_age` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_birthday` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_xueli` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_one1` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_one2` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_one3` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_one4` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_one5` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_one6` int(11) DEFAULT NULL,
  `user_one7` int(11) DEFAULT NULL,
  `user_one8` int(11) DEFAULT NULL,
  `user_one9` datetime DEFAULT NULL,
  `user_one10` datetime DEFAULT NULL,
  `user_one11` bigint(20) DEFAULT NULL,
  `user_one12` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'a', 'a', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('2', '刘光亮', null, '1', null, '青岛市', '男', '1322222222222', 'liu@yaoo.cn', '12345', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('3', '张三', null, '2', null, '青岛是四方区', '男', '1322222222222', 'zhangsan@yahooo.c', '111111', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('5', '马大哈', null, '8', null, '上海浦东', '男', '1322222222222', 'madaha@yahooo.cn', '124333', null, null, null, null, '白金卡', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('6', '蔡依林', null, '8', null, '香港啥尖嘴', '女', '15054256789', 'caiyiling@163。com', '123456', null, null, null, null, '钻石卡', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('8', 'dd', null, '1', null, 'dd', '男', '2222', '222', '222', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_zhijiao`
-- ----------------------------
DROP TABLE IF EXISTS `t_zhijiao`;
CREATE TABLE `t_zhijiao` (
  `zhijiao_id` int(11) NOT NULL,
  `zhijiao_jiaolian_id` int(11) DEFAULT NULL,
  `zhijiao_jiaolian_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `zhijiao_kecheng` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `zhijiao_huiyuan` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `zhijiao_one1` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `zhijiao_one2` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `zhijiao_one3` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `zhijiao_one4` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `zhijiao_one5` int(11) DEFAULT NULL,
  `zhijiao_one6` int(11) DEFAULT NULL,
  `zhijiao_one7` datetime DEFAULT NULL,
  `zhijiao_one8` datetime DEFAULT NULL,
  PRIMARY KEY (`zhijiao_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_zhijiao
-- ----------------------------
INSERT INTO `t_zhijiao` VALUES ('3', null, 'df', 'df', 'df', null, null, null, null, null, null, null, null);
