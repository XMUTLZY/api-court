/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : book-court

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 14/04/2020 14:18:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for arena
-- ----------------------------
DROP TABLE IF EXISTS `arena`;
CREATE TABLE `arena`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '球馆id',
  `boss_mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '球馆老板的手机号',
  `is_recommend` tinyint(2) NOT NULL COMMENT '是否为推荐（0：否 1：是）',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '球馆名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '球馆地址',
  `cover_image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '球馆封面图片',
  `status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '状态（0:休息 1:营业 2:删除）',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始营业时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束营业时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '球馆表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of arena
-- ----------------------------
INSERT INTO `arena` VALUES (1, '13200018965', 0, '斯台普斯', '洛杉矶', 'http://img0.imgtn.bdimg.com/it/u=1466466883,3662687013&fm=26&gp=0.jpg', 1, '2020-04-14 07:00:00', '2020-04-14 22:00:00');
INSERT INTO `arena` VALUES (2, '13200018999', 1, '甲骨文', '金州', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4262114989,3326469398&fm=26&gp=0.jpg', 1, '2020-04-14 07:30:00', '2020-04-14 21:00:00');

-- ----------------------------
-- Table structure for court
-- ----------------------------
DROP TABLE IF EXISTS `court`;
CREATE TABLE `court`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `arena_id` int(11) NOT NULL COMMENT '球馆id',
  `court_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '球场名',
  `cover_image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '球场封面',
  `rent_work` double NULL DEFAULT NULL COMMENT '工作日租金/小时',
  `rent_weekend` double NULL DEFAULT NULL COMMENT '周末租金/小时',
  `status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '状态（0:维护 1:营业 2:删除）',
  `score` double NULL DEFAULT NULL COMMENT '球场评分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '球场表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of court
-- ----------------------------
INSERT INTO `court` VALUES (1, 1, '斯台普斯球场1', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3739770140,2275781624&fm=11&gp=0.jpg', 50, 60, 1, 4.5);
INSERT INTO `court` VALUES (2, 1, '斯台普斯球场2', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3315589702,3886098884&fm=26&gp=0.jpg', 40, 70, 1, 4.7);
INSERT INTO `court` VALUES (3, 1, '斯台普斯球场3', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1227892120,1493298145&fm=26&gp=0.jpg', 70, 80, 1, 4.9);
INSERT INTO `court` VALUES (4, 2, '甲骨文球场1', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1227892120,1493298145&fm=26&gp=0.jpg', 60, 60, 1, 4.8);
INSERT INTO `court` VALUES (5, 2, '甲骨文球场2', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1227892120,1493298145&fm=26&gp=0.jpg', 50, 55, 1, 5);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (12);
INSERT INTO `hibernate_sequence` VALUES (12);
INSERT INTO `hibernate_sequence` VALUES (12);
INSERT INTO `hibernate_sequence` VALUES (12);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `wechat_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `portrait` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '吴鸿宇', '241426326', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2020-04-13 22:36:06');
INSERT INTO `user` VALUES (3, 'LZY', '1231547568756', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2020-04-13 22:38:53');
INSERT INTO `user` VALUES (4, 'LY', '42064@qq.com', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2020-04-13 22:39:16');
INSERT INTO `user` VALUES (6, '林哈哈', '18965805133', 'https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7504.JPG', '2020-04-14 02:33:04');

-- ----------------------------
-- Table structure for user_book
-- ----------------------------
DROP TABLE IF EXISTS `user_book`;
CREATE TABLE `user_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `court_id` int(11) NOT NULL COMMENT '球场id',
  `book_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '预约时间',
  `book_long` double NOT NULL COMMENT '预约时长',
  `money` double NOT NULL COMMENT '已付金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户预定表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_book
-- ----------------------------
INSERT INTO `user_book` VALUES (1, 6, 1, '2020-04-14 02:36:25', 120, 120);
INSERT INTO `user_book` VALUES (8, 6, 2, '2020-04-14 23:00:00', 140, 100);

-- ----------------------------
-- Table structure for user_collect
-- ----------------------------
DROP TABLE IF EXISTS `user_collect`;
CREATE TABLE `user_collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `arena_id` int(11) NOT NULL COMMENT '球馆id',
  `collect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收藏球馆表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_collect
-- ----------------------------
INSERT INTO `user_collect` VALUES (9, 6, 1, '2020-04-14 02:50:15');
INSERT INTO `user_collect` VALUES (10, 6, 2, '2020-04-14 02:50:20');

SET FOREIGN_KEY_CHECKS = 1;
