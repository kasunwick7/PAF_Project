-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: product_management_db
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `products_id` int(11) NOT NULL AUTO_INCREMENT,
  `research_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `stock_quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `added_date` date DEFAULT NULL,
  PRIMARY KEY (`products_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,1,'bb','bb',1,12,'2021-04-20'),(2,1,'aa','aa',1,10,'2021-04-20'),(3,1,'aa','aa',1,10,'2021-04-20'),(4,1,'aa','aa',1,10,'2021-04-20'),(5,1,'aa','aa',1,10,'2021-04-20'),(8,1,'aa','aa',1,10,'2021-04-20'),(9,1,'aa','aa',1,10,'2021-04-20'),(13,1,'aa','aa',1,10,'2021-04-20'),(14,1,'aa','aa',1,10,'2021-04-20'),(15,1,'aa','aa',1,10,'2021-04-20'),(16,1,'aa','aa',1,10,'2021-04-20'),(17,1,'aa','aa',1,10,'2021-04-20'),(18,1,'aa','aa',1,10,'2021-04-20'),(19,1,'aa','aa',1,10,'2021-04-20'),(20,1,'aa','aa',1,10,'2021-04-20'),(21,1,'aa','aa',1,10,'2021-04-20');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_sold`
--

DROP TABLE IF EXISTS `products_sold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products_sold` (
  `products_id` int(11) NOT NULL,
  `buyer_id` int(11) DEFAULT NULL,
  `sold_date` date DEFAULT NULL,
  PRIMARY KEY (`products_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_sold`
--

LOCK TABLES `products_sold` WRITE;
/*!40000 ALTER TABLE `products_sold` DISABLE KEYS */;
INSERT INTO `products_sold` VALUES (1,1,'2021-04-20'),(2,2,'2021-04-20');
/*!40000 ALTER TABLE `products_sold` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-25 18:43:34
