/*
SQLyog v10.2 
MySQL - 5.1.60-community : Database - eshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eshop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `eshop`;

/*Table structure for table `evaluate` */

DROP TABLE IF EXISTS `evaluate`;

CREATE TABLE `evaluate` (
  `evaluate_id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_no` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `evaluate_date` datetime DEFAULT NULL,
  `evaluate_level` int(11) DEFAULT '4' COMMENT '1-差评 2-一般 3-比较满意 4-好评',
  `evaluate_content` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`evaluate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `evaluate` */

insert  into `evaluate`(`evaluate_id`,`orders_no`,`user_id`,`nick_name`,`goods_id`,`evaluate_date`,`evaluate_level`,`evaluate_content`) values (1,'201504302331034',4,'陈生生',1,'2015-05-01 00:16:04',3,'商品还算很好滴'),(2,'201504302331034',4,'陈生生',5,'2015-05-01 00:16:04',3,'商品还算很好滴');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_type_id` int(11) DEFAULT '0',
  `goods_name` varchar(225) DEFAULT NULL,
  `goods_pic` varchar(225) DEFAULT NULL,
  `goods_price1` double DEFAULT '0',
  `goods_price2` double DEFAULT NULL,
  `goods_count` int(11) DEFAULT NULL,
  `goods_date` date DEFAULT NULL,
  `goods_desc` text,
  `goods_flag` int(11) DEFAULT '1' COMMENT '1：正常 2：促销',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`goods_type_id`,`goods_name`,`goods_pic`,`goods_price1`,`goods_price2`,`goods_count`,`goods_date`,`goods_desc`,`goods_flag`) values (1,1,'抽风式无敌散热器1','1.jpg',88,80,98,'2015-04-30','抽风式无敌散热器1抽风式无敌散热器1抽风式无敌散热器1',2),(2,2,'智能四核手机你的爱1','2.jpg',66,66,100,'2015-05-01','智能四核手机你的爱1智能四核手机你的爱1智能四核手机你的爱1',1),(3,3,'和秋叶一起学PPT1','3.jpg',88,82,100,'2015-04-30','和秋叶一起学PPT1和秋叶一起学PPT1和秋叶一起学PPT1',2),(4,4,'越野自行车超配模式1','4.jpg',99,99,100,'2015-05-01','越野自行车超配模式1越野自行车超配模式1越野自行车超配模式1',1),(5,5,'流行款经典香包1','5.jpg',74.5,68,99,'2015-04-30','流行款经典香包1流行款经典香包1流行款经典香包1',2),(6,6,'美味的干锅1','6.jpg',88,88,100,'2015-04-30','美味的干锅1美味的干锅1美味的干锅1',1),(7,7,'美味的酒水1','7.jpg',88,85,98,'2015-04-30','美味的酒水1美味的酒水1美味的酒水1',2),(8,1,'抽风式无敌散热器2','1.jpg',66,66,100,'2015-05-01','抽风式无敌散热器2抽风式无敌散热器2抽风式无敌散热器2',1),(9,2,'智能四核手机你的爱2','2.jpg',88,75,100,'2015-04-30','智能四核手机你的爱2智能四核手机你的爱2智能四核手机你的爱2',2),(10,3,'和秋叶一起学PPT2','3.jpg',99,99,100,'2015-04-30','和秋叶一起学PPT2和秋叶一起学PPT2和秋叶一起学PPT2',1),(11,4,'越野自行车超配模式2','4.jpg',74.5,65,100,'2015-04-30','越野自行车超配模式2越野自行车超配模式2越野自行车超配模式2',2),(12,5,'美味的干锅2','5.jpg',88,88,100,'2015-04-30','美味的干锅2美味的干锅2美味的干锅2',1),(13,6,'美味的酒水2','6.jpg',88,88,100,'2015-04-30','美味的酒水2美味的酒水2美味的酒水2',1);

/*Table structure for table `goods_type` */

DROP TABLE IF EXISTS `goods_type`;

CREATE TABLE `goods_type` (
  `goods_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_type_name` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`goods_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `goods_type` */

insert  into `goods_type`(`goods_type_id`,`goods_type_name`) values (1,'电脑配件'),(2,'手机通讯'),(3,'书籍类'),(4,'交通工具'),(5,'箱包类'),(6,'干锅类'),(7,'酒水类');

/*Table structure for table `info` */

DROP TABLE IF EXISTS `info`;

CREATE TABLE `info` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT,
  `info_title` varchar(225) DEFAULT NULL,
  `info_content` text,
  `info_admin` varchar(50) DEFAULT NULL,
  `info_date` datetime DEFAULT NULL,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `info` */

insert  into `info`(`info_id`,`info_title`,`info_content`,`info_admin`,`info_date`) values (1,'网上商城购物之常见帮助1','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助','管理员','2015-04-28 22:45:20'),(2,'网上商城购物之常见帮助2','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助1网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助','管理员','2015-04-28 22:46:06'),(3,'网上商城购物之常见帮助3','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助','管理员','2015-04-28 22:45:20'),(4,'网上商城购物之常见帮助4','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助1网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助','管理员','2015-04-28 22:46:06'),(6,'网上商城购物之常见帮助6','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助','管理员','2015-04-28 22:45:20'),(7,'网上商城购物之常见帮助7','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助1网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助','管理员','2015-04-28 22:46:06'),(8,'网上商城购物之常见帮助8','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助','管理员','2015-04-28 22:45:20'),(9,'网上商城购物之常见帮助9','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助1网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助网上商城购物之常见帮助','管理员','2015-04-28 22:46:06');

/*Table structure for table `logistics` */

DROP TABLE IF EXISTS `logistics`;

CREATE TABLE `logistics` (
  `logistics_id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_no` varchar(50) DEFAULT NULL,
  `logistics_date` datetime DEFAULT NULL,
  `logistics_desc` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`logistics_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `logistics` */

insert  into `logistics`(`logistics_id`,`orders_no`,`logistics_date`,`logistics_desc`) values (1,'201504302331034','2015-04-30 23:59:57','商品已经在武汉出库'),(2,'201504302331034','2015-05-01 02:00:23','商品转运至上海航空'),(3,'201504302331034','2015-05-01 08:01:46','商品到达北京集散中心'),(4,'201504302331034','2015-05-01 12:02:26','商品已送往用户手中'),(5,'201504302331034','2015-05-01 20:20:54','用户已经签收');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orders_id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_no` varchar(50) DEFAULT NULL,
  `orders_date` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `user_address` varchar(300) DEFAULT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `orders_money` double DEFAULT NULL,
  `send_id` int(11) DEFAULT NULL,
  `send_name` varchar(50) DEFAULT NULL,
  `orders_flag` int(11) DEFAULT '1' COMMENT '1：待收货 2-已收货 3-已评价',
  PRIMARY KEY (`orders_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`orders_id`,`orders_no`,`orders_date`,`user_id`,`real_name`,`user_address`,`user_phone`,`orders_money`,`send_id`,`send_name`,`orders_flag`) values (0,'201504302331034','2015-04-30',4,'陈生','北京天堂路666号','15088888883',228,2,'李梅',4),(4,'201505010004354','2015-05-01',4,'陈生','北京天堂路666号','15088888884',170,0,NULL,1);

/*Table structure for table `orders_detail` */

DROP TABLE IF EXISTS `orders_detail`;

CREATE TABLE `orders_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_no` varchar(50) DEFAULT NULL,
  `goods_id` int(11) DEFAULT '0',
  `goods_name` varchar(225) DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `goods_count` int(11) DEFAULT '1',
  `goods_money` double DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `orders_detail` */

insert  into `orders_detail`(`detail_id`,`orders_no`,`goods_id`,`goods_name`,`goods_price`,`goods_count`,`goods_money`) values (5,'201504302331034',1,'抽风式无敌散热器1',80,2,160),(6,'201504302331034',5,'流行款经典香包1',68,1,68),(7,'201505010004354',7,'美味的酒水1',85,2,170);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_pass` varchar(200) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `user_sex` int(11) DEFAULT '0' COMMENT '1：男 0：女',
  `user_address` varchar(300) DEFAULT NULL,
  `user_mail` varchar(50) DEFAULT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `user_flag` int(11) DEFAULT '1' COMMENT '1：配送中 2：无任务',
  `reg_date` datetime DEFAULT NULL,
  `user_type` int(11) DEFAULT '1' COMMENT '1：注册用户 2：配送人员 3：系统管理员 ',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_pass`,`real_name`,`nick_name`,`user_sex`,`user_address`,`user_mail`,`user_phone`,`user_flag`,`reg_date`,`user_type`) values (1,'admin','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','张丽',NULL,1,NULL,'admin@163.com','15088888883',2,'2014-03-01 23:08:39',3),(2,'limei','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李梅',NULL,2,NULL,'limei@163.com','15088888889',2,'2014-03-01 23:08:44',2),(3,'liling','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李玲',NULL,2,NULL,'liling@163.com','15088888888',2,'2014-03-01 23:08:46',2),(4,'test','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','陈生','陈生生',2,'北京天堂路666号','chensheng@163.com','15088888884',2,'2014-03-01 23:08:44',1),(5,'lichao','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李超','李超超',1,'天津天堂路666号','lichao@163.com','15088888887',2,'2014-03-01 23:08:44',1),(6,'zhangbin','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','张斌','张斌斌',1,'上海天堂路666号','zhangbin@163.com','15088888882',2,'2014-03-01 23:08:44',1),(7,'zhaohui','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','赵辉','赵辉辉',1,'河北天堂路666号','zhaohui@163.com','15088888881',2,'2014-03-01 23:08:44',1),(8,'zhangweiming','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','张伟明','小小明',1,'世纪天堂路666号','zhangweiming@163.com','15088888888',2,'2014-03-01 23:08:44',1),(9,'likunlun','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李昆仑','李昆仑',1,'河南天堂路666号','likunlun@163.com','15088888888',2,'2014-03-01 23:08:44',1),(10,'lijing','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李静','李静静',2,'湖南天堂路666号','lijing@163.com','15088888886',2,'2014-03-01 23:08:44',1),(11,'zhangyongle','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','张勇乐','张勇勇',1,'湖北天堂路666号','zhangyongle@163.com','15088888887',2,'2014-03-01 23:08:44',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
