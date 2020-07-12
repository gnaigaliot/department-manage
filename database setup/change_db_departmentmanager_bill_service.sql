DROP TABLE IF EXISTS `bill_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bill_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `edited_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_department` int(11) NOT NULL,
  `total_price` int(11) DEFAULT NULL,
  `code_apartment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_department` (`id_department`),
  CONSTRAINT `bill_service_ibfk_1` FOREIGN KEY (`id_department`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;