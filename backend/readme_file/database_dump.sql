-- MySQL dump 10.13  Distrib 9.1.0, for Win64 (x86_64)
--
-- Host: localhost    Database: weibo
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `toward` int DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment-user-fk1` (`userid`),
  KEY `comment-weibo-fk1` (`toward`),
  CONSTRAINT `comment-weibo-fk1` FOREIGN KEY (`toward`) REFERENCES `weibo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'good','user002',1,'2025-4-7 14:50'),(2,'sad','user002',1,'2025-4-6 14:50'),(3,'angry','user001',1,'2025-4-5 13:50'),(4,'111','user002',1,'2025-4-5 13:50'),(5,'222','user001',2,'2025-4-5 13:13'),(6,'333','user001',4,'2025-4-5 13:40'),(7,'dji','user002',36,'2025-4-6 13:50'),(8,'ijk','user001',56,'2025-4-5 17:50'),(9,'sdji','user002',56,'2025-4-5 13:50'),(10,'我能','user001',58,'2025-4-5 13:50'),(11,'我也觉得','user001',1,'2025-4-8 14:20'),(12,'真好','user001',1,'2025-04-09 10:24'),(13,'你再试试看','user001',58,'2025-04-09 10:26'),(14,'abc','user001',56,'2025-04-09 10:41'),(15,'是啊','user002',37,'2025-04-09 10:42'),(16,'%%%%','user002',2,'2025-04-09 10:42'),(17,'456','user002',36,'2025-04-09 10:43'),(18,'我也是','user002',4,'2025-04-09 10:43'),(19,'嗯嗯','user001',37,'2025-04-09 16:14'),(20,'3333444','user002',2,'2025-04-09 20:06'),(21,'13221','user002',54,'2025-04-09 20:37'),(22,'123456\n','user002',64,'2025-04-09 22:12'),(23,'shide\n','user001',63,'2025-04-09 22:22'),(24,'yeeeee！！','user003',59,'2025-04-10 12:54'),(25,'123123','Yosemite',61,'2025-04-10 14:08'),(26,'hjkjhk','user001',61,'2025-04-10 15:14'),(27,'展示评论功能','user001',36,'2025-05-22 08:34');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `followerid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1` (`userid`),
  KEY `fk2` (`followerid`),
  CONSTRAINT `fk1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `fk2` FOREIGN KEY (`followerid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (43,'user003','user002'),(44,'user003','user001'),(55,'user107','user002'),(57,'Yosemite','user001'),(61,'user001','user002');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `weiboid` int DEFAULT NULL,
  `commentid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `like-user-fk1` (`userid`),
  KEY `like-weibo-fk2` (`weiboid`),
  KEY `like-comment-fk3` (`commentid`),
  CONSTRAINT `like-comment-fk3` FOREIGN KEY (`commentid`) REFERENCES `comment` (`id`),
  CONSTRAINT `like-user-fk1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `like-weibo-fk2` FOREIGN KEY (`weiboid`) REFERENCES `weibo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (1,'user001',1,1),(2,'user001',2,1),(4,'user001',30,NULL),(7,'user001',36,NULL),(9,'user001',37,NULL),(11,NULL,4,NULL),(13,'user001',55,NULL),(14,'user001',57,NULL),(16,'user001',54,NULL),(19,'user002',37,NULL),(20,'user002',36,NULL),(21,'user002',4,NULL),(23,'user002',56,NULL),(24,'user001',4,NULL),(25,'user002',54,NULL),(26,'user001',63,NULL),(27,'user003',59,NULL),(28,'user107',36,NULL),(29,'user107',4,NULL),(30,'user107',1,NULL),(31,'user107',66,NULL),(32,'user107',2,NULL),(33,'Yosemite',61,NULL),(34,'Yosemite',68,NULL),(35,'test002',30,NULL),(36,'user001',59,NULL),(38,'user001',68,NULL),(39,'user001',58,NULL),(41,'user001',70,NULL);
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subs`
--

DROP TABLE IF EXISTS `subs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subs` (
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `flowerer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`author`) USING BTREE,
  KEY `subs-user-fk2` (`flowerer`),
  CONSTRAINT `subs-user-fk1` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `subs-user-fk2` FOREIGN KEY (`flowerer`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subs`
--

LOCK TABLES `subs` WRITE;
/*!40000 ALTER TABLE `subs` DISABLE KEYS */;
INSERT INTO `subs` VALUES ('user001','user002'),('user002','Yosemity');
/*!40000 ALTER TABLE `subs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `fans` int DEFAULT NULL,
  `followers` int DEFAULT NULL,
  PRIMARY KEY (`id` DESC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Yosemity','Ss123456&','boy1.png',NULL,NULL,NULL),('Yosemite','Ss123456&','girl1.jpg','20150708214812.jpg',0,1),('user107','Pass108&','R-C.jfif','R-C (1).jfif',0,1),('user102','pass102','102.jpg',NULL,NULL,NULL),('user101','pass101','101.jpg',NULL,NULL,NULL),('user100','pass100',NULL,NULL,NULL,NULL),('user099','pass099',NULL,NULL,NULL,NULL),('user098','pass098',NULL,NULL,NULL,NULL),('user097','pass097',NULL,NULL,NULL,NULL),('user096','pass096',NULL,NULL,NULL,NULL),('user095','pass095',NULL,NULL,NULL,NULL),('user094','pass094',NULL,NULL,NULL,NULL),('user093','pass093',NULL,NULL,NULL,NULL),('user092','pass092',NULL,NULL,NULL,NULL),('user091','pass091',NULL,NULL,NULL,NULL),('user090','pass090',NULL,NULL,NULL,NULL),('user089','pass089',NULL,NULL,NULL,NULL),('user088','pass088',NULL,NULL,NULL,NULL),('user087','pass087',NULL,NULL,NULL,NULL),('user086','pass086',NULL,NULL,NULL,NULL),('user085','pass085',NULL,NULL,NULL,NULL),('user084','pass084',NULL,NULL,NULL,NULL),('user083','pass083',NULL,NULL,NULL,NULL),('user082','pass082',NULL,NULL,NULL,NULL),('user081','pass081',NULL,NULL,NULL,NULL),('user080','pass080',NULL,NULL,NULL,NULL),('user079','pass079',NULL,NULL,NULL,NULL),('user078','pass078',NULL,NULL,NULL,NULL),('user077','pass077',NULL,NULL,NULL,NULL),('user076','pass076',NULL,NULL,NULL,NULL),('user075','pass075',NULL,NULL,NULL,NULL),('user074','pass074',NULL,NULL,NULL,NULL),('user073','pass073',NULL,NULL,NULL,NULL),('user072','pass072',NULL,NULL,NULL,NULL),('user071','pass071',NULL,NULL,NULL,NULL),('user070','pass070',NULL,NULL,NULL,NULL),('user069','pass069',NULL,NULL,NULL,NULL),('user068','pass068',NULL,NULL,NULL,NULL),('user067','pass067',NULL,NULL,NULL,NULL),('user066','pass066',NULL,NULL,NULL,NULL),('user065','pass065',NULL,NULL,NULL,NULL),('user064','pass064',NULL,NULL,NULL,NULL),('user063','pass063',NULL,NULL,NULL,NULL),('user062','pass062',NULL,NULL,NULL,NULL),('user061','pass061',NULL,NULL,NULL,NULL),('user060','pass060',NULL,NULL,NULL,NULL),('user059','pass059',NULL,NULL,NULL,NULL),('user058','pass058',NULL,NULL,NULL,NULL),('user057','pass057',NULL,NULL,NULL,NULL),('user056','pass056',NULL,NULL,NULL,NULL),('user055','pass055',NULL,NULL,NULL,NULL),('user054','pass054',NULL,NULL,NULL,NULL),('user053','pass053',NULL,NULL,NULL,NULL),('user052','pass052',NULL,NULL,NULL,NULL),('user051','pass051',NULL,NULL,NULL,NULL),('user050','pass050',NULL,NULL,NULL,NULL),('user049','pass049',NULL,NULL,NULL,NULL),('user048','pass048',NULL,NULL,NULL,NULL),('user047','pass047',NULL,NULL,NULL,NULL),('user046','pass046',NULL,NULL,NULL,NULL),('user045','pass045',NULL,NULL,NULL,NULL),('user044','pass044',NULL,NULL,NULL,NULL),('user043','pass043',NULL,NULL,NULL,NULL),('user042','pass042',NULL,NULL,NULL,NULL),('user041','pass041',NULL,NULL,NULL,NULL),('user040','pass040',NULL,NULL,NULL,NULL),('user039','pass039',NULL,NULL,NULL,NULL),('user038','pass038',NULL,NULL,NULL,NULL),('user037','pass037',NULL,NULL,NULL,NULL),('user036','pass036',NULL,NULL,NULL,NULL),('user035','pass035',NULL,NULL,NULL,NULL),('user034','pass034',NULL,NULL,NULL,NULL),('user033','pass033',NULL,NULL,NULL,NULL),('user032','pass032',NULL,NULL,NULL,NULL),('user031','pass031',NULL,NULL,NULL,NULL),('user030','pass030',NULL,NULL,NULL,NULL),('user029','pass029',NULL,NULL,NULL,NULL),('user028','pass028',NULL,NULL,NULL,NULL),('user027','pass027',NULL,NULL,NULL,NULL),('user026','pass026',NULL,NULL,NULL,NULL),('user025','pass025',NULL,NULL,NULL,NULL),('user024','pass024',NULL,NULL,NULL,NULL),('user023','pass023',NULL,NULL,NULL,NULL),('user022','pass022',NULL,NULL,NULL,NULL),('user021','pass021',NULL,NULL,NULL,NULL),('user020','pass020',NULL,NULL,NULL,NULL),('user019','pass019',NULL,NULL,NULL,NULL),('user018','pass018',NULL,NULL,NULL,NULL),('user017','pass017',NULL,NULL,NULL,NULL),('user016','pass016',NULL,NULL,NULL,NULL),('user015','pass015',NULL,NULL,NULL,NULL),('user014','pass014',NULL,NULL,NULL,NULL),('user013','pass013',NULL,NULL,NULL,NULL),('user012','pass012',NULL,NULL,NULL,NULL),('user011','pass011',NULL,NULL,NULL,NULL),('user010','pass010',NULL,NULL,NULL,NULL),('user009','pass009',NULL,NULL,NULL,NULL),('user008','pass008',NULL,NULL,NULL,NULL),('user007','pass007',NULL,NULL,0,0),('user006','pass006',NULL,NULL,NULL,0),('user005','pass005',NULL,NULL,NULL,NULL),('user004','pass004',NULL,NULL,NULL,NULL),('user003','Pass003&','girl1.jpg','20150708214812.jpg',NULL,NULL),('user002','pass002','boy1.jpg','R-C (1).jfif',20,20),('user001','pass001','D06-min.jpg','20150708214812.jpg',10,7),('test002','Pass002&','1224.jpg','R-C (1).jfif',0,0),('test001','Pass001&',NULL,NULL,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weibo`
--

DROP TABLE IF EXISTS `weibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weibo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `tupian` varchar(255) DEFAULT NULL,
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `likes` int DEFAULT NULL,
  `comments` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `weibo-user-fk1` (`uid`),
  CONSTRAINT `weibo-user-fk1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weibo`
--

LOCK TABLES `weibo` WRITE;
/*!40000 ALTER TABLE `weibo` DISABLE KEYS */;
INSERT INTO `weibo` VALUES (1,'本站第一条博客','boy1.jpg','user001','2025/4/1',3,6),(2,'本站第二条博客','girl1.jpg','user001','2025/4/2',2,3),(4,'今天很开心','boy1.jpg','user001','2025/4/1',5,2),(30,'看到杨蓝陌\r\n在小红书装蒟蒻','20110619032258.jpg','user001','2025-04-03 18:15',2,0),(36,'123','R-C (2).jfif','user001','2025-04-03 19:41',4,3),(37,'风景真好','1.jfif','user001','2025-04-03 19:45',4,2),(53,'10086','1.jfif,20110619032258.jpg','user001','2025-04-03 20:03',4,0),(54,'今天很难过,lW_Wl','20110619032258.jpg,734BBCF96B72C5F406872F86DCB0B2FC.png,20150708214812.jpg,R-C.jfif,D06-min.jpg','user002','2025-04-03 20:31',7,1),(55,'今天很开心.U_U','20110619032258.jpg,734BBCF96B72C5F406872F86DCB0B2FC.png,20150708214812.jpg,R-C.jfif,D06-min.jpg,R-C (1).jfif,R-C (2).jfif,20110619032258.jpg,20150708214812.jpg,R-C.jfif,734BBCF96B72C5F406872F86DCB0B2FC.png,1.jfif','user001','2025-04-03 20:32',7,0),(56,'123','1.jfif','user001','2025-04-05 12:18',8,3),(57,'','1.jfif','user001','2025-04-05 12:19',3,0),(58,'能正常上传吗','D06-min.jpg,20150708214812.jpg,20110619032258.jpg','user001','2025-04-06 13:54',2,2),(59,'成功啦###','D06-min.jpg,R-C (1).jfif,20110619032258.jpg,R-C.jfif,20110619032258.jpg,20150708214812.jpg','user002','2025-04-09 19:57',NULL,NULL),(60,'','D06-min.jpg','user002','2025-04-09 20:16',NULL,NULL),(61,'原来是这样\r\n噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢','20110619032258.jpg','user002','2025-04-09 20:16',NULL,NULL),(62,'111111','','user002','2025-04-09 20:24',NULL,NULL),(63,'应该成功了\r\n(⊙﹏⊙)','R-C (1).jfif,20150708214812.jpg','user002','2025-04-09 20:25',NULL,NULL),(64,'无图片输入\r\n','','user002','2025-04-09 20:30',NULL,NULL),(65,'成功啦成功啦yeeeeee！','','user002','2025-04-09 20:33',NULL,NULL),(66,'####','1224.jpg','user001','2025-04-10 09:35',NULL,NULL),(68,'#(⊙﹏⊙)#','1224.jpg','Yosemite','2025-04-10 14:08',NULL,NULL),(69,'13333333','D06-min.jpg,20150708214812.jpg,20110619032258.jpg,R-C (2).jfif,20110619032258.jpg,R-C (2).jfif,20110619032258.jpg','user001','2025-04-10 15:13',NULL,NULL),(70,'展示发表功能','1.jfif,20150708214812.jpg,20110619032258.jpg,girl1.jpg,R-C (1).jfif,20150708214812.jpg,1224.jpg,R-C.jfif,20110619032258.jpg','user001','2025-05-22 08:28',NULL,NULL);
/*!40000 ALTER TABLE `weibo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-22 10:44:45
