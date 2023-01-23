-- MySQL dump 10.13  Distrib 5.7.37, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: xcs_cf_points
-- ------------------------------------------------------
-- Server version	5.7.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contests`
--

DROP TABLE IF EXISTS `contests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '赛名',
  `type` varchar(10) NOT NULL COMMENT '类型',
  `time` date DEFAULT NULL COMMENT '时间',
  `total` int(11) DEFAULT NULL COMMENT '总题目数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='cf赛';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contests`
--

LOCK TABLES `contests` WRITE;
/*!40000 ALTER TABLE `contests` DISABLE KEYS */;
INSERT INTO `contests` VALUES (1,'Hello 2023','A','2023-01-03',8),(2,'Codeforces Round #842 (Div. 2)','A','2023-01-05',6),(3,'Educational Codeforces Round 141 (Rated for Div. 2)','A','2023-01-08',7),(4,'Codeforces Round #843 (Div. 2)','A','2023-01-10',7),(5,'Codeforces Round #844 (Div. 1 + Div. 2, based on VK Cup 2022 - Elimination Round)','A','2023-01-15',9),(6,'Codeforces Round #845 (Div. 2) and ByteRace 2023','A','2023-01-21',6),(7,'Educational Codeforces Round 142 (Rated for Div. 2)','A','2023-01-24',6),(8,'Codeforces Round #846 (Div. 2)','A','2023-01-25',6),(9,'Codeforces Round #847 (Div. 3)','B','2023-01-27',6);
/*!40000 ALTER TABLE `contests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `uid` int(11) NOT NULL COMMENT '学生id',
  `cid` int(11) NOT NULL COMMENT '比赛id',
  `duringScore` int(11) DEFAULT NULL COMMENT '正式赛时间内积分',
  `afterScore` int(11) DEFAULT NULL COMMENT '赛后补题',
  `aNum` int(11) DEFAULT NULL COMMENT 'A题数',
  KEY `score_contests_id_fk` (`cid`),
  KEY `score_user_id_fk` (`uid`),
  CONSTRAINT `score_contests_id_fk` FOREIGN KEY (`cid`) REFERENCES `contests` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='得分';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (5,1,700,0,4),(17,1,600,0,3),(15,1,500,0,4),(3,1,500,200,5),(29,1,500,0,3),(28,1,500,0,3),(20,1,500,0,2),(14,1,500,0,2),(6,1,400,0,3),(21,1,400,0,1),(27,1,400,0,1),(23,1,400,0,1),(1,1,400,0,3),(26,1,400,0,2),(16,1,400,0,1),(25,1,400,0,1),(59,1,0,0,4),(11,1,0,500,8),(9,1,0,500,8),(37,1,0,200,6),(48,1,0,0,3),(43,1,0,0,2),(31,1,0,0,1),(4,1,0,0,1),(12,1,0,0,1),(40,2,0,200,4),(5,2,600,200,4),(25,2,600,0,3),(29,2,500,200,4),(17,2,500,0,3),(28,2,500,0,3),(1,2,500,0,2),(15,2,500,200,4),(50,2,0,0,2),(4,2,0,0,1),(2,2,400,0,1),(20,2,400,0,2),(23,2,400,0,1),(3,2,400,0,3),(43,2,400,0,1),(9,2,400,0,1),(14,2,400,0,1),(16,2,400,0,1),(44,2,400,0,2),(42,2,400,0,1),(6,2,0,0,3),(26,2,0,0,2),(12,2,0,0,1),(12,3,0,0,2),(6,3,500,0,3),(28,3,400,0,2),(5,3,400,0,1),(55,3,400,0,1),(14,3,400,0,1),(26,3,400,0,2),(3,3,400,0,1),(29,3,400,200,4),(23,3,400,0,1),(42,3,400,0,1),(58,3,400,0,1),(40,3,400,200,4),(4,3,400,0,1),(27,3,400,0,1),(20,3,400,0,2),(44,3,400,0,1),(16,3,400,0,1),(15,3,400,0,2),(11,3,0,200,5),(1,3,0,0,1),(50,3,0,0,1),(41,3,0,0,1),(17,3,500,0,2),(25,3,500,0,2),(5,4,1300,0,5),(10,4,0,0,3),(25,4,600,0,3),(15,4,500,200,4),(3,4,500,0,2),(14,4,500,0,2),(2,4,500,0,2),(1,4,500,0,2),(41,4,400,0,1),(28,4,400,0,1),(40,4,0,200,4),(11,4,0,200,4),(32,4,0,0,3),(6,4,0,0,3),(20,4,0,0,2),(29,5,500,0,2),(25,5,500,0,2),(14,5,500,0,2),(15,5,500,0,2),(44,5,500,0,2),(10,5,0,0,1),(43,5,400,0,1),(20,5,400,0,1),(4,5,400,0,1),(40,5,400,0,1),(41,5,400,0,1),(49,5,400,0,1),(44,6,500,0,2),(6,6,500,0,2),(29,6,500,0,2),(41,6,500,0,2);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sno` varchar(15) NOT NULL COMMENT '学号',
  `account` varchar(100) NOT NULL COMMENT '账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='社员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'廖旭文','20011426','Aluomolo'),(2,'胡素婕','20011405','isSJa'),(3,'高思淼','19160037','Goes'),(4,'唐姚成','22012732','yizhitangzong'),(5,'罗莞东','19011416','BCXNSD'),(6,'塔拉','21100039','2B_dada'),(7,'蒋嘉俐','22012864','carrieJ'),(8,'董湘君','22190319','K_Hidden'),(9,'宰令喆','21010772','lxyzlz1'),(10,'占智博','22012621','ZZB040506'),(11,'吴锦耀','21011642','YQYjin'),(12,'吉钟燕','22012600','22mccartney'),(13,'嘉乐东珠','19170011','Kroos_8'),(14,'彭浩洋','22012657','oresama_'),(15,'陈俞宁','21011636','cyn_login'),(16,'骆嘉毅','22012928','dog_violet'),(17,'金诗航','22011318','unlucky_'),(18,'张圣梓','21011671','okkkkk'),(19,'童纪侃','22040076','tjk123'),(20,'刘孟宇','22012669','I_havenoname'),(21,'方亿凯','21011710','Terminater'),(22,'毛志鹏','22012932','Yuan99'),(23,'蔡银振','22012633','ss.001.cai'),(24,'高佳乐','22011257','ScarletCrow'),(25,'李艳丽','21011713','S_Gloria'),(26,'潘彤','21011688','HuaRan'),(27,'钟志和','20010681','SzkEC'),(28,'刘锦宏','21011658','liujh123'),(29,'马佳豪','21140093','btc9'),(30,'郑佳鹏','22012664','Suzuran1009120'),(31,'刘佳宁','22012691','aaaaaap'),(32,'赵宇','21011764','xq7666'),(33,'杜俊杰','21011668','whick'),(34,'王雅雯','22012604','isareyou'),(35,'刘耀文','22012747','Grianlyw'),(36,'刘群','21011651','Pa_tient'),(37,'赖梦婷','22012776','adlmikrey'),(38,'徐子悦','22012820','AM1YAMon3'),(39,'闫婷婷','22040090','tings'),(40,'温建平','21040102','Yiwauo'),(41,'冀焕翔','22012941','Hii_Jilir'),(42,'苏伊童','22012696','Erwin_Smith86'),(43,'韩少华','22012865','zeith'),(44,'朱佳琦','21011610','Jade_zjq'),(45,'安虹燃','21011617','AKCW7'),(46,'蓝杰','20011437','jackson91100'),(47,'许天一','22013015','22013015'),(48,'段莹莹','20011435','aslforyou'),(49,'陈艺','21011685','008emm'),(50,'戴梁琛','22012701','Promising_muc'),(51,'蔡克烨','21011623','ylyla'),(52,'孙沛琳','22011188','Zhamuling'),(53,'杨秀卓玛','20100064','Yangxiuzhuoma1211'),(54,'马婵媛','20011434','MCYHHH'),(55,'郝佳涛','21011640','Mingdi'),(56,'娜日泰','20100036','naritai'),(57,'李文韬','21011625','LiWentao1346'),(58,'叶璟霖','22012663','MUC_Lance'),(59,'李荣基','21011210','lirongji');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `imgUrl` varchar(100) DEFAULT NULL COMMENT '头像',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '用户类型 普通用户-0 管理员-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='登录网站的用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'321','58cf703f664397ec4f0ac359b84b565c','321@163.com','/file/1-d637e624-ccec-406a-afae-0be498728482.jpg',0),(2,'isSJa','58cf703f664397ec4f0ac359b84b565c','issja_hu@163.com','/file/2-dda90c33-75d5-4e6b-bb54-eeeeeeb7d909.jpg',1),(3,'issjaaa','58cf703f664397ec4f0ac359b84b565c','issja_hsj@163.com',NULL,0),(4,'cf','58cf703f664397ec4f0ac359b84b565c','cfcf@163.com',NULL,0),(5,'abcd','202cb962ac59075b964b07152d234b70','abcedf@163.com','/file/5-3a01b0b6-fa54-4636-b7d3-87b462aee0d8.jpg',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-23 18:12:04
