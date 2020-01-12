/*
 Navicat Premium Data Transfer

 Source Server         : spring-mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : mbanq

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/01/2020 12:44:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `authority` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `authorities_idx_1`(`username`, `authority`) USING BTREE,
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('john', 'ROLE_EMPLOYEE');
INSERT INTO `authorities` VALUES ('mary', 'ROLE_EMPLOYEE');
INSERT INTO `authorities` VALUES ('mary', 'ROLE_MANAGER');
INSERT INTO `authorities` VALUES ('susan', 'ROLE_ADMIN');
INSERT INTO `authorities` VALUES ('susan', 'ROLE_EMPLOYEE');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'product id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'product name',
  `price` float(10, 2) NOT NULL DEFAULT 0.00 COMMENT 'product price',
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'create by user',
  `mod_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_date` date NULL DEFAULT NULL COMMENT 'created date',
  `mod_date` date NULL DEFAULT NULL COMMENT 'modified date',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `pro_name`(`name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (3, 'test', 10.00, 'susan', 'susan', '2020-01-11', '2020-01-11');
INSERT INTO `product` VALUES (10, '8up', 10.00, 'susan', 'susan', '2020-01-11', '2020-01-11');
INSERT INTO `product` VALUES (11, 'K Karabav', 14.00, 'mary', 'mary', '2020-01-11', '2020-01-11');
INSERT INTO `product` VALUES (9, '7up2', 10.00, 'john', 'john', '2020-01-11', '2020-01-11');
INSERT INTO `product` VALUES (12, 'Oral', 10.00, 'john', 'john', '2020-01-11', '2020-01-11');
INSERT INTO `product` VALUES (13, 'Vital', 2.50, 'john', 'john', '2020-01-11', '2020-01-11');
INSERT INTO `product` VALUES (16, 'Sumsung', 230.00, 'mary', 'mary', '2020-01-12', '2020-01-12');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` char(68) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('john', '{bcrypt}$2a$10$VZgEm.Cc0Iz2.tzAHRt46ONeM9H3w3mttSzn7FBxKipZiOGJKwjsy', 1);
INSERT INTO `users` VALUES ('mary', '{bcrypt}$2a$10$hzoH/iDeNU9QwqkCef95CeivJSeXhP0ULgfQjl3mkpgo7LkcY84lm', 1);
INSERT INTO `users` VALUES ('susan', '{bcrypt}$2a$10$hzoH/iDeNU9QwqkCef95CeivJSeXhP0ULgfQjl3mkpgo7LkcY84lm', 1);

SET FOREIGN_KEY_CHECKS = 1;
