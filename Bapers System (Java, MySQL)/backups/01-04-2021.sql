-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: database
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `carddetails`
--

DROP TABLE IF EXISTS `carddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carddetails` (
  `CardNumber` varchar(25) NOT NULL,
  `ExpiryDate` date NOT NULL,
  `SecurityCode` varchar(25) NOT NULL,
  `CustomerID` int NOT NULL,
  `JobID` int NOT NULL,
  `CardType` varchar(45) NOT NULL,
  PRIMARY KEY (`JobID`),
  KEY `FKCardDetail529698` (`CustomerID`),
  CONSTRAINT `FKCardDetail529698` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `JobIDCardDet` FOREIGN KEY (`JobID`) REFERENCES `job` (`JobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carddetails`
--

LOCK TABLES `carddetails` WRITE;
/*!40000 ALTER TABLE `carddetails` DISABLE KEYS */;
INSERT INTO `carddetails` VALUES ('1234','2026-02-28','321',1,1,'Visa'),('1234','2026-02-28','321',1,2,'Visa'),('1234','2026-02-28','321',1,3,'Visa'),('1234','2026-02-28','321',1,4,'Visa'),('1234','2026-02-28','321',1,5,'Visa'),('1234','2026-02-28','321',1,6,'Visa'),('1234','2026-02-28','321',1,7,'Visa'),('1234','2026-02-28','321',1,8,'Visa'),('1234','2026-02-28','321',1,9,'Visa'),('1234','2026-02-28','321',1,10,'Visa'),('1234','2026-02-28','321',1,11,'Visa'),('1234','2026-02-28','321',1,12,'Visa'),('1234','2026-02-28','321',1,13,'Visa'),('1234','2026-02-28','321',1,14,'Visa'),('1234','2026-02-28','321',1,15,'Visa'),('1234','2026-02-28','321',1,16,'Visa'),('1234','2026-02-28','321',1,17,'Visa'),('1234','2026-02-28','321',1,18,'Visa'),('1234','2026-02-28','321',1,19,'Visa'),('1234','2026-02-28','321',1,20,'Visa'),('1234','2026-02-28','321',1,21,'Visa'),('1234','2026-02-28','321',1,22,'Visa'),('1234','2026-02-28','321',1,23,'Visa'),('1234','2026-02-28','321',1,24,'Visa'),('1234','2026-02-28','321',1,25,'Visa'),('1234','2026-02-28','321',1,26,'Visa'),('1234','2026-02-28','321',1,27,'Visa'),('1234','2026-02-28','321',1,28,'Visa'),('1234','2026-02-28','321',1,29,'Visa'),('1234','2026-02-28','321',1,30,'Visa'),('4444','2026-02-28','123',8,34,'Visa'),('4444','2026-02-28','123',8,35,'Visa'),('4444','2026-02-28','123',8,36,'Visa'),('4444','2026-02-28','123',8,37,'Visa'),('4444','2026-02-28','123',8,38,'Visa'),('4444','2026-02-28','123',8,39,'Visa'),('4444','2026-02-28','123',8,40,'Visa'),('4444','2026-02-28','123',8,41,'Visa'),('4444','2026-02-28','123',8,42,'Visa'),('4444','2026-02-28','123',8,43,'Visa'),('4444','2026-02-28','123',8,44,'Visa'),('4444','2026-02-28','123',8,45,'Visa'),('4444','2026-02-28','123',8,46,'Visa'),('4444','2026-02-28','123',8,47,'Visa'),('4444','2026-02-28','123',8,48,'Visa'),('4444','2026-02-28','123',8,49,'Visa'),('4444','2026-02-28','123',8,50,'Visa'),('4444','2026-02-28','123',8,51,'Visa'),('4444','2026-02-28','123',8,52,'Visa'),('4444','2026-02-28','123',8,53,'Visa'),('9999','2023-01-29','888',11,57,'Visa'),('9999','2023-01-29','888',11,58,'Visa'),('9999','2023-01-29','888',11,59,'Visa'),('8888','2026-02-28','123',12,80,'Visa'),('8888','2026-02-28','123',12,81,'Visa');
/*!40000 ALTER TABLE `carddetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `PhoneNumber` varchar(45) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `CustomerTypeID` varchar(255) DEFAULT 'Normal',
  `DiscountTypeID` int DEFAULT '4',
  PRIMARY KEY (`CustomerID`),
  KEY `FKCustomer875277` (`CustomerTypeID`),
  KEY `FKCustomer867473` (`DiscountTypeID`),
  CONSTRAINT `FKCustomer867473` FOREIGN KEY (`DiscountTypeID`) REFERENCES `discounttype` (`DiscountTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'David','Rhind','Northampton Square, London EC1V 0HB',NULL,'David.Rhind@city.ac.uk','Valued',1),(2,'Boris','Berezovsky','12, Bond Street, London WC1V 8HU',NULL,'Boris.B@yahoo.com','Valued',2),(7,'Alex','Wright','25 Bond Street, London WC1V 8LS',NULL,'Alex.Wright@infopharma.com','Valued',3),(8,'Sarah','Brocklehurst','12 Charter Street, London W1 8NS',NULL,'Sarah.Brocklehurst@hello.com','Valued',3),(9,'Eva','Bauyer','1, Liverpool street, London EC2V 8NS ',NULL,'eva.bauyer@gmail.com','Valued',1),(10,'Ivan','Grey','NA',NULL,'IvanGrey@email.com','Normal',4),(11,'Boris','Bridge','NA',NULL,'BorisBridge@email.com','Normal',4),(12,'Aravin','Naren','123 Fake Ave',NULL,'ANaren@email.com','Valued',2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounttype`
--

DROP TABLE IF EXISTS `discounttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discounttype` (
  `DiscountTypeID` int NOT NULL AUTO_INCREMENT,
  `DiscountType` varchar(20) NOT NULL,
  PRIMARY KEY (`DiscountTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounttype`
--

LOCK TABLES `discounttype` WRITE;
/*!40000 ALTER TABLE `discounttype` DISABLE KEYS */;
INSERT INTO `discounttype` VALUES (1,'Fixed'),(2,'Variable'),(3,'Flexible'),(4,'None');
/*!40000 ALTER TABLE `discounttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed`
--

DROP TABLE IF EXISTS `fixed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixed` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `FixedRate` float DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  CONSTRAINT `CustomerIDFixed` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed`
--

LOCK TABLES `fixed` WRITE;
/*!40000 ALTER TABLE `fixed` DISABLE KEYS */;
INSERT INTO `fixed` VALUES (1,0.03),(9,0.03);
/*!40000 ALTER TABLE `fixed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flexbands`
--

DROP TABLE IF EXISTS `flexbands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flexbands` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `Band1` float NOT NULL,
  `Band2` float NOT NULL,
  `Band1Discount` float NOT NULL,
  `Band2Discount` float NOT NULL,
  `Band3Discount` float NOT NULL,
  PRIMARY KEY (`CustomerID`),
  CONSTRAINT `CustomerIDFlex` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexbands`
--

LOCK TABLES `flexbands` WRITE;
/*!40000 ALTER TABLE `flexbands` DISABLE KEYS */;
INSERT INTO `flexbands` VALUES (7,1000,2000,0,1,2),(8,1000,2000,0,1,2);
/*!40000 ALTER TABLE `flexbands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `JobID` int NOT NULL AUTO_INCREMENT,
  `Urgency` varchar(255) DEFAULT 'No',
  `CustomerID` int DEFAULT NULL,
  `Deadline` datetime DEFAULT NULL,
  `SpecialInstructions` varchar(255) DEFAULT 'None',
  `Price` float DEFAULT NULL,
  `NumberOfTasksRemaining` int DEFAULT NULL,
  `DateCreated` date DEFAULT NULL,
  `PaidStatus` varchar(255) DEFAULT 'Unpaid',
  `PayByDate` date DEFAULT NULL,
  `EstimatedTimeRemaining` time DEFAULT NULL,
  `JobStatus` varchar(45) DEFAULT 'Processing',
  PRIMARY KEY (`JobID`),
  KEY `FKJob455908` (`CustomerID`),
  CONSTRAINT `FKJob455908` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'Normal',1,'2020-12-21 14:07:12','None',66.445,2,'2020-12-20','Paid','2021-01-10','03:00:00','Processing'),(2,'Normal',1,'2020-12-21 14:07:12','None',66.445,2,'2020-12-20','Paid','2021-01-10','03:00:00','Processing'),(3,'Normal',1,'2020-12-21 14:07:12','None',66.445,2,'2020-12-20','Paid','2021-01-10','03:00:00','Processing'),(4,'Normal',1,'2020-12-21 14:07:12','None',66.445,2,'2020-12-20','Paid','2021-01-10','03:00:00','Processing'),(5,'Normal',1,'2020-12-21 14:07:12','None',66.445,2,'2020-12-20','Paid','2021-01-10','03:00:00','Processing'),(6,'Normal',1,'2020-12-21 14:07:12','None',72.265,3,'2020-12-20','Paid','2021-01-10','03:30:00','Processing'),(7,'Normal',1,'2020-12-21 14:07:12','None',72.265,3,'2020-12-20','Paid','2021-01-10','03:30:00','Processing'),(8,'Normal',1,'2020-12-21 14:07:12','None',72.265,3,'2020-12-20','Paid','2021-01-10','03:30:00','Processing'),(9,'Normal',1,'2020-12-21 14:07:12','None',72.265,3,'2020-12-20','Paid','2021-01-10','03:30:00','Processing'),(10,'Normal',1,'2020-12-21 14:07:12','None',72.265,3,'2020-12-20','Paid','2021-01-10','03:30:00','Processing'),(11,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(12,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(13,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(14,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(15,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(16,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(17,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(18,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(19,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(20,'Normal',1,'2020-12-24 14:07:12','None',101.85,3,'2020-12-23','Paid','2021-01-10','04:00:00','Processing'),(21,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(22,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(23,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(24,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(25,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(26,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(27,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(28,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(29,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(30,'Normal',1,'2020-12-24 14:07:12','None',91.471,3,'2020-12-23','Paid','2021-01-10','03:15:00','Processing'),(31,'urgent',10,'2020-12-23 20:07:12','None',165.8,2,'2020-12-23','Paid','2020-12-23','03:45:00','Processing'),(32,'urgent',10,'2020-12-23 20:07:12','None',165.8,2,'2020-12-23','Paid','2020-12-23','03:45:00','Processing'),(33,'urgent',10,'2020-12-23 20:07:12','None',165.8,2,'2020-12-23','Paid','2020-12-23','03:45:00','Processing'),(34,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(35,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(36,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(37,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(38,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(39,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(40,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(41,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(42,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(43,'Normal',8,'2020-12-29 14:21:12','None',86,2,'2020-12-28','Paid','2021-01-23','02:00:00','Processing'),(44,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(45,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(46,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(47,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(48,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(49,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(50,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(51,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(52,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(53,'Normal',8,'2020-12-29 14:21:12','None',129.5,2,'2020-12-28','Paid','2021-01-23','02:30:00','Processing'),(54,'very urgent',7,'2021-01-10 18:32:54','None',165.8,2,'2021-01-10','Unpaid','2021-01-10','03:45:00','Processing'),(55,'very urgent',7,'2021-01-10 18:32:54','None',165.8,2,'2021-01-10','Unpaid','2021-01-10','03:45:00','Processing'),(56,'very urgent',7,'2021-01-10 18:32:54','None',165.8,2,'2021-01-10','Unpaid','2021-01-10','03:45:00','Processing'),(57,'urgent',11,'2021-01-25 18:32:54','None',171.8,3,'2021-01-25','Paid','2021-01-25','04:15:00','Processing'),(58,'urgent',11,'2021-01-25 18:32:54','None',171.8,3,'2021-01-25','Paid','2021-01-25','04:15:00','Processing'),(59,'urgent',11,'2021-01-25 18:32:54','None',171.8,3,'2021-01-25','Paid','2021-01-25','04:15:00','Processing'),(60,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(61,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(62,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(63,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(64,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(65,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(66,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(67,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(68,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(69,'Normal',8,'2021-02-03 18:32:54','None',94.3,3,'2021-02-01','Unpaid','2021-02-10','03:15:00','Processing'),(70,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(71,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(72,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(73,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(74,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(75,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(76,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(77,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(78,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(79,'Normal',8,'2021-02-03 18:32:54','None',86,2,'2021-02-01','Unpaid','2021-02-10','02:00:00','Processing'),(80,'Normal',12,'2021-04-02 16:10:27','None',74.5,3,'2021-04-01','Paid','2021-05-10','03:30:00','Processing'),(81,'urgent',12,'2021-04-01 22:14:36','None',135.5,3,'2021-04-01','Paid','2021-05-10','03:00:00','Processing'),(82,'Normal',12,'2021-04-02 22:24:56','None',74.5,3,'2021-04-01','Paid','2021-05-10','03:30:00','Processing'),(83,'Normal',12,'2021-04-02 22:25:44','None',74.5,3,'2021-04-01','Unpaid','2021-05-10','03:30:00','Processing'),(84,'Normal',12,'2021-04-02 22:27:30','None',65,3,'2021-04-01','Unpaid','2021-05-10','03:30:00','Processing');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobtasks`
--

DROP TABLE IF EXISTS `jobtasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobtasks` (
  `JobID` int NOT NULL,
  `TaskID` int NOT NULL,
  `StaffWorker` int DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `Status` varchar(255) DEFAULT 'Pending',
  `Price` float DEFAULT NULL,
  PRIMARY KEY (`JobID`,`TaskID`),
  KEY `FKJob Tasks912651` (`TaskID`),
  KEY `FKJob Tasks588651` (`StaffWorker`),
  CONSTRAINT `FKJob Tasks588651` FOREIGN KEY (`StaffWorker`) REFERENCES `staff` (`StaffID`),
  CONSTRAINT `FKJob Tasks66035` FOREIGN KEY (`JobID`) REFERENCES `job` (`JobID`),
  CONSTRAINT `FKJob Tasks912651` FOREIGN KEY (`TaskID`) REFERENCES `task` (`TaskID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobtasks`
--

LOCK TABLES `jobtasks` WRITE;
/*!40000 ALTER TABLE `jobtasks` DISABLE KEYS */;
INSERT INTO `jobtasks` VALUES (1,1,NULL,NULL,'Pending',19),(1,2,NULL,NULL,'Pending',49.5),(2,1,NULL,NULL,'Pending',19),(2,2,NULL,NULL,'Pending',49.5),(3,1,NULL,NULL,'Pending',19),(3,2,NULL,NULL,'Pending',49.5),(4,1,NULL,NULL,'Pending',19),(4,2,NULL,NULL,'Pending',49.5),(5,1,NULL,NULL,'Pending',19),(5,2,NULL,NULL,'Pending',49.5),(6,1,NULL,NULL,'Pending',19),(6,2,NULL,NULL,'Pending',49.5),(6,3,NULL,NULL,'Pending',6),(7,1,NULL,NULL,'Pending',19),(7,2,NULL,NULL,'Pending',49.5),(7,3,NULL,NULL,'Pending',6),(8,1,NULL,NULL,'Pending',19),(8,2,NULL,NULL,'Pending',49.5),(8,3,NULL,NULL,'Pending',6),(9,1,NULL,NULL,'Pending',19),(9,2,NULL,NULL,'Pending',49.5),(9,3,NULL,NULL,'Pending',6),(10,1,NULL,NULL,'Pending',19),(10,2,NULL,NULL,'Pending',49.5),(10,3,NULL,NULL,'Pending',6),(11,1,NULL,NULL,'Pending',19),(11,3,NULL,NULL,'Pending',6),(11,4,NULL,NULL,'Pending',80),(12,1,NULL,NULL,'Pending',19),(12,3,NULL,NULL,'Pending',6),(12,4,NULL,NULL,'Pending',80),(13,1,NULL,NULL,'Pending',19),(13,3,NULL,NULL,'Pending',6),(13,4,NULL,NULL,'Pending',80),(14,1,NULL,NULL,'Pending',19),(14,3,NULL,NULL,'Pending',6),(14,4,NULL,NULL,'Pending',80),(15,1,NULL,NULL,'Pending',19),(15,3,NULL,NULL,'Pending',6),(15,4,NULL,NULL,'Pending',80),(16,1,NULL,NULL,'Pending',19),(16,3,NULL,NULL,'Pending',6),(16,4,NULL,NULL,'Pending',80),(17,1,NULL,NULL,'Pending',19),(17,3,NULL,NULL,'Pending',6),(17,4,NULL,NULL,'Pending',80),(18,1,NULL,NULL,'Pending',19),(18,3,NULL,NULL,'Pending',6),(18,4,NULL,NULL,'Pending',80),(19,1,NULL,NULL,'Pending',19),(19,3,NULL,NULL,'Pending',6),(19,4,NULL,NULL,'Pending',80),(20,1,NULL,NULL,'Pending',19),(20,3,NULL,NULL,'Pending',6),(20,4,NULL,NULL,'Pending',80),(21,3,NULL,NULL,'Pending',6),(21,4,NULL,NULL,'Pending',80),(21,6,NULL,NULL,'Pending',8.3),(22,3,NULL,NULL,'Pending',6),(22,4,NULL,NULL,'Pending',80),(22,6,NULL,NULL,'Pending',8.3),(23,3,NULL,NULL,'Pending',6),(23,4,NULL,NULL,'Pending',80),(23,6,NULL,NULL,'Pending',8.3),(24,3,NULL,NULL,'Pending',6),(24,4,NULL,NULL,'Pending',80),(24,6,NULL,NULL,'Pending',8.3),(25,3,NULL,NULL,'Pending',6),(25,4,NULL,NULL,'Pending',80),(25,6,NULL,NULL,'Pending',8.3),(26,3,NULL,NULL,'Pending',6),(26,4,NULL,NULL,'Pending',80),(26,6,NULL,NULL,'Pending',8.3),(27,3,NULL,NULL,'Pending',6),(27,4,NULL,NULL,'Pending',80),(27,6,NULL,NULL,'Pending',8.3),(28,3,NULL,NULL,'Pending',6),(28,4,NULL,NULL,'Pending',80),(28,6,NULL,NULL,'Pending',8.3),(29,3,NULL,NULL,'Pending',6),(29,4,NULL,NULL,'Pending',80),(29,6,NULL,NULL,'Pending',8.3),(30,3,NULL,NULL,'Pending',6),(30,4,NULL,NULL,'Pending',80),(30,6,NULL,NULL,'Pending',8.3),(31,5,NULL,NULL,'Pending',110.3),(31,7,NULL,NULL,'Pending',55.5),(32,5,NULL,NULL,'Pending',110.3),(32,7,NULL,NULL,'Pending',55.5),(33,5,NULL,NULL,'Pending',110.3),(33,7,NULL,NULL,'Pending',55.5),(34,3,NULL,NULL,'Pending',6),(34,4,NULL,NULL,'Pending',80),(35,3,NULL,NULL,'Pending',6),(35,4,NULL,NULL,'Pending',80),(36,3,NULL,NULL,'Pending',6),(36,4,NULL,NULL,'Pending',80),(37,3,NULL,NULL,'Pending',6),(37,4,NULL,NULL,'Pending',80),(38,3,NULL,NULL,'Pending',6),(38,4,NULL,NULL,'Pending',80),(39,3,NULL,NULL,'Pending',6),(39,4,NULL,NULL,'Pending',80),(40,3,NULL,NULL,'Pending',6),(40,4,NULL,NULL,'Pending',80),(41,3,NULL,NULL,'Pending',6),(41,4,NULL,NULL,'Pending',80),(42,3,NULL,NULL,'Pending',6),(42,4,NULL,NULL,'Pending',80),(43,3,NULL,NULL,'Pending',6),(43,4,NULL,NULL,'Pending',80),(44,2,NULL,NULL,'Pending',49.5),(44,4,NULL,NULL,'Pending',80),(45,2,NULL,NULL,'Pending',49.5),(45,4,NULL,NULL,'Pending',80),(46,2,NULL,NULL,'Pending',49.5),(46,4,NULL,NULL,'Pending',80),(47,2,NULL,NULL,'Pending',49.5),(47,4,NULL,NULL,'Pending',80),(48,2,NULL,NULL,'Pending',49.5),(48,4,NULL,NULL,'Pending',80),(49,2,NULL,NULL,'Pending',49.5),(49,4,NULL,NULL,'Pending',80),(50,2,NULL,NULL,'Pending',49.5),(50,4,NULL,NULL,'Pending',80),(51,2,NULL,NULL,'Pending',49.5),(51,4,NULL,NULL,'Pending',80),(52,2,NULL,NULL,'Pending',49.5),(52,4,NULL,NULL,'Pending',80),(53,2,NULL,NULL,'Pending',49.5),(53,4,NULL,NULL,'Pending',80),(54,5,NULL,NULL,'Pending',110.3),(54,7,NULL,NULL,'Pending',55.5),(55,5,NULL,NULL,'Pending',110.3),(55,7,NULL,NULL,'Pending',55.5),(56,5,NULL,NULL,'Pending',110.3),(56,7,NULL,NULL,'Pending',55.5),(57,3,NULL,NULL,'Pending',6),(57,5,NULL,NULL,'Pending',110.3),(57,7,NULL,NULL,'Pending',55.5),(58,3,NULL,NULL,'Pending',6),(58,5,NULL,NULL,'Pending',110.3),(58,7,NULL,NULL,'Pending',55.5),(59,3,NULL,NULL,'Pending',6),(59,5,NULL,NULL,'Pending',110.3),(59,7,NULL,NULL,'Pending',55.5),(60,3,NULL,NULL,'Pending',6),(60,4,NULL,NULL,'Pending',80),(60,6,NULL,NULL,'Pending',8.3),(61,3,NULL,NULL,'Pending',6),(61,4,NULL,NULL,'Pending',80),(61,6,NULL,NULL,'Pending',8.3),(62,3,NULL,NULL,'Pending',6),(62,4,NULL,NULL,'Pending',80),(62,6,NULL,NULL,'Pending',8.3),(63,3,NULL,NULL,'Pending',6),(63,4,NULL,NULL,'Pending',80),(63,6,NULL,NULL,'Pending',8.3),(64,3,NULL,NULL,'Pending',6),(64,4,NULL,NULL,'Pending',80),(64,6,NULL,NULL,'Pending',8.3),(65,3,NULL,NULL,'Pending',6),(65,4,NULL,NULL,'Pending',80),(65,6,NULL,NULL,'Pending',8.3),(66,3,NULL,NULL,'Pending',6),(66,4,NULL,NULL,'Pending',80),(66,6,NULL,NULL,'Pending',8.3),(67,3,NULL,NULL,'Pending',6),(67,4,NULL,NULL,'Pending',80),(67,6,NULL,NULL,'Pending',8.3),(68,3,NULL,NULL,'Pending',6),(68,4,NULL,NULL,'Pending',80),(68,6,NULL,NULL,'Pending',8.3),(69,3,NULL,NULL,'Pending',6),(69,4,NULL,NULL,'Pending',80),(69,6,NULL,NULL,'Pending',8.3),(70,3,NULL,NULL,'Pending',6),(70,4,NULL,NULL,'Pending',80),(71,3,NULL,NULL,'Pending',6),(71,4,NULL,NULL,'Pending',80),(72,3,NULL,NULL,'Pending',6),(72,4,NULL,NULL,'Pending',80),(73,3,NULL,NULL,'Pending',6),(73,4,NULL,NULL,'Pending',80),(74,3,NULL,NULL,'Pending',6),(74,4,NULL,NULL,'Pending',80),(75,3,NULL,NULL,'Pending',6),(75,4,NULL,NULL,'Pending',80),(76,3,NULL,NULL,'Pending',6),(76,4,NULL,NULL,'Pending',80),(77,3,NULL,NULL,'Pending',6),(77,4,NULL,NULL,'Pending',80),(78,3,NULL,NULL,'Pending',6),(78,4,NULL,NULL,'Pending',80),(79,3,NULL,NULL,'Pending',6),(79,4,NULL,NULL,'Pending',80),(80,1,9,'2021-04-01 16:11:43','Complete',19),(80,2,9,'2021-04-01 16:11:49','In Progress',49.5),(80,3,NULL,NULL,'Pending',6),(81,2,NULL,NULL,'Pending',49.5),(81,3,NULL,NULL,'Pending',6),(81,4,NULL,NULL,'Pending',80),(82,1,NULL,NULL,'Pending',19),(82,2,NULL,NULL,'Pending',49.5),(82,3,NULL,NULL,'Pending',6),(83,1,NULL,NULL,'Pending',19),(83,2,NULL,NULL,'Pending',49.5),(83,3,NULL,NULL,'Pending',6),(84,1,NULL,NULL,'Pending',9.5),(84,2,NULL,NULL,'Pending',49.5),(84,3,NULL,NULL,'Pending',6);
/*!40000 ALTER TABLE `jobtasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `StaffID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Role` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`StaffID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Jack','D','123 Fake Avw','jack@yahoo.com','123','Office Manager',NULL),(9,'Manager','Manager','','Manager@Lab.com','Get_it_done','Office Manager',' 07123456'),(10,'Accountant','Accountant','','Account@Lab.com','Count_Money','Shift Manager',' 07456789'),(11,'Clerk','Clerk','','Clerk@Lab.com','Paperwork','Shift Manager',' 074568965'),(12,'Hello','Hello','','Hello@Lab.com','Hello_there','Receptionist',' 074982515'),(13,'Development','Development','','Development@Lab.com','Lot_smell','Technician','075432151321'),(14,'Copy','Copy','','Copy@Lab.com','Too_dark','Technician','07655482130'),(15,'Packer','Packer','','Packer@Lab.com','Pack_it','Technician','07542135421'),(16,'Finish','Finish','','Finish@Lab.com','Fine_touch','Technician',' 0754121324'),(17,'Aravin','Naren','NA','Aravin@Lab.com','123','Shift Manager',' 123'),(18,'John','Doe','NA','John@Lab.com','123','Receptionist',' 356');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `TaskID` int NOT NULL AUTO_INCREMENT,
  `Location` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `Duration` time DEFAULT NULL,
  PRIMARY KEY (`TaskID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'Copy Room','Use of large copy camera',19,'02:00:00'),(2,'Development area','Black and white film processing',49.5,'01:00:00'),(3,'Packing department','Bag up',6,'00:30:00'),(4,'Development Area','Colour film processing',80,'01:30:00'),(5,'Development Area','Colour Transparency processing',110.3,'03:00:00'),(6,'Copy Room','Use of small copy camera',8.3,'01:15:00'),(7,'Finishing room','Mount Transparencies',55.5,'00:45:00'),(9,'Dark Room','DG Processing',25,'01:25:00');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeofpayment`
--

DROP TABLE IF EXISTS `typeofpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeofpayment` (
  `JobID` int NOT NULL,
  `PaymentType` varchar(255) NOT NULL,
  PRIMARY KEY (`JobID`),
  CONSTRAINT `FKTypeOfPaym446426` FOREIGN KEY (`JobID`) REFERENCES `job` (`JobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeofpayment`
--

LOCK TABLES `typeofpayment` WRITE;
/*!40000 ALTER TABLE `typeofpayment` DISABLE KEYS */;
INSERT INTO `typeofpayment` VALUES (1,'Card'),(2,'Card'),(3,'Card'),(4,'Card'),(5,'Card'),(6,'Card'),(7,'Card'),(8,'Card'),(9,'Card'),(10,'Card'),(11,'Card'),(12,'Card'),(13,'Card'),(14,'Card'),(15,'Card'),(16,'Card'),(17,'Card'),(18,'Card'),(19,'Card'),(20,'Card'),(21,'Card'),(22,'Card'),(23,'Card'),(24,'Card'),(25,'Card'),(26,'Card'),(27,'Card'),(28,'Card'),(29,'Card'),(30,'Card'),(31,'Cash'),(32,'Cash'),(33,'Cash'),(34,'Card'),(35,'Card'),(36,'Card'),(37,'Card'),(38,'Card'),(39,'Card'),(40,'Card'),(41,'Card'),(42,'Card'),(43,'Card'),(44,'Card'),(45,'Card'),(46,'Card'),(47,'Card'),(48,'Card'),(49,'Card'),(50,'Card'),(51,'Card'),(52,'Card'),(53,'Card'),(57,'Card'),(58,'Card'),(59,'Card'),(80,'Card'),(81,'Card'),(82,'Cash');
/*!40000 ALTER TABLE `typeofpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variable`
--

DROP TABLE IF EXISTS `variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `variable` (
  `CustomerID` int NOT NULL,
  `TaskID` int NOT NULL,
  `DiscountPercentage` float DEFAULT '0',
  PRIMARY KEY (`CustomerID`,`TaskID`),
  KEY `CutomerIDVar_idx` (`CustomerID`),
  KEY `TaskIDVar_idx` (`TaskID`),
  CONSTRAINT `CutomerIDVar` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `TaskIDVar` FOREIGN KEY (`TaskID`) REFERENCES `task` (`TaskID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variable`
--

LOCK TABLES `variable` WRITE;
/*!40000 ALTER TABLE `variable` DISABLE KEYS */;
INSERT INTO `variable` VALUES (2,1,0.01),(2,2,0.01),(2,4,0.02),(2,5,0.02),(2,7,0.02),(12,1,0.5);
/*!40000 ALTER TABLE `variable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-01 22:40:38
