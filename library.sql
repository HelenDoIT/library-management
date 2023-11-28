
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'key',
  `name` varchar(20) DEFAULT NULL COMMENT 'BookName',
  `author` varchar(20) DEFAULT NULL COMMENT 'BookAuthor',
  `inventory` int(11) DEFAULT NULL COMMENT 'inventory',
  `dr` tinyint(2) DEFAULT '0' COMMENT 'drop tag [0-rase,1-drop]',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for lend_serial
-- ----------------------------
DROP TABLE IF EXISTS `lend_serial`;
CREATE TABLE `lend_serial` (
  `serial_no` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'key',
  `user_id` bigint(20) NOT NULL COMMENT 'UserId',
  `book_id` bigint(20) NOT NULL COMMENT 'BookId',
  `lend_num` int(11) DEFAULT NULL COMMENT 'LendNum',
  `lend_status` int(4) NOT NULL DEFAULT '0' COMMENT 'lending status [0-lending,1-returned]',
  `lend_date` datetime DEFAULT NULL COMMENT 'LendDate',
  `return_date` datetime DEFAULT NULL COMMENT 'ReturnDate',
  PRIMARY KEY (`serial_no`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'key',
  `name` varchar(20) DEFAULT NULL COMMENT 'UserName',
  `password` varchar(20) DEFAULT NULL COMMENT 'password',
  `role` varchar(8) DEFAULT NULL COMMENT 'UserRole[admin/user]',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;
