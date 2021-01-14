-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aeroport
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `plane_ticket`
--

DROP TABLE IF EXISTS `plane_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane_ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `user_email` varchar(20) DEFAULT NULL,
  `pilot_email` varchar(20) DEFAULT NULL,
  `route_id` int DEFAULT NULL,
  `plane_model` varchar(20) DEFAULT NULL,
  `ticket_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `user_email` (`user_email`),
  KEY `pilot_email` (`pilot_email`),
  KEY `route_id` (`route_id`),
  CONSTRAINT `plane_ticket_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`),
  CONSTRAINT `plane_ticket_ibfk_2` FOREIGN KEY (`pilot_email`) REFERENCES `income_and_schedule` (`email`),
  CONSTRAINT `plane_ticket_ibfk_3` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane_ticket`
--

LOCK TABLES `plane_ticket` WRITE;
/*!40000 ALTER TABLE `plane_ticket` DISABLE KEYS */;
INSERT INTO `plane_ticket` VALUES (1,'user','worker@worker.com',1,'cumotor','1999'),(2,'user2','worker@worker.com',1,'cumotor','2000'),(3,'user','worker@worker.com',2,'cumotor','2001'),(4,'user','worker@worker.com',1,'a380','2021-01-13'),(5,'user','worker@worker.com',1,'747','2021-01-07'),(6,'user','worker@worker.com',1,'717','2021-01-29');
/*!40000 ALTER TABLE `plane_ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-14 14:15:35
