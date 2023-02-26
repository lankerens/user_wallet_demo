mysqldump: [Warning] Using a password on the command line interface can be insecure.
-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: wallet
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `tbl_wallet`
--

DROP TABLE IF EXISTS `tbl_wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_wallet` (
  `id` bigint NOT NULL COMMENT 'id',
  `uid` bigint NOT NULL COMMENT '用户id',
  `balance` decimal(32,2) DEFAULT NULL COMMENT '余额',
  `state` int DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_wallet_uid_IDX` (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户钱包';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_wallet`
--

LOCK TABLES `tbl_wallet` WRITE;
/*!40000 ALTER TABLE `tbl_wallet` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_wallet_details`
--

DROP TABLE IF EXISTS `tbl_wallet_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_wallet_details` (
  `id` bigint DEFAULT NULL,
  `wid` bigint DEFAULT NULL COMMENT '钱包id',
  `type` int DEFAULT NULL COMMENT '增加还是减少',
  `change` decimal(32,2) DEFAULT NULL COMMENT '变动的值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `balance` decimal(32,2) DEFAULT NULL COMMENT '余额',
  `order_id` varchar(100) DEFAULT NULL COMMENT '订单号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='钱包变动明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_wallet_details`
--

LOCK TABLES `tbl_wallet_details` WRITE;
/*!40000 ALTER TABLE `tbl_wallet_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_wallet_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-26 14:44:59
