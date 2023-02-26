用户钱包
 `-lankerens`
---

<br>

主要类： `src/main/java/com/lankerens/uwd/service/impl/WalletServiceImpl.java`

<br>

数据库文件：`src/main/resources/static/init.sql`

```sql
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
```