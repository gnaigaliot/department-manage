DROP TABLE IF EXISTS `bill_service_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bill_service_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `id_bill_service` int(11) NOT NULL,
  `id_service_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_bill_service` (`id_bill_service`),
  KEY `id_service_type` (`id_service_type`),
  CONSTRAINT `bill_service_detail_ibfk_1` FOREIGN KEY (`id_bill_service`) REFERENCES `bill_service` (`id`),
  CONSTRAINT `bill_service_detail_ibfk_2` FOREIGN KEY (`id_service_type`) REFERENCES `bill_service_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;