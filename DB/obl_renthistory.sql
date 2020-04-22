-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: obl
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `renthistory`
--

DROP TABLE IF EXISTS `renthistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `renthistory` (
  `RowNum` int(11) NOT NULL,
  `BookSerial` varchar(255) NOT NULL,
  `UserID` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `DateOfRent` date DEFAULT NULL,
  `DateOfReturn` date DEFAULT NULL,
  `DateOfRealReturn` date DEFAULT NULL,
  `IsPopular` varchar(255) NOT NULL,
  PRIMARY KEY (`RowNum`),
  UNIQUE KEY `RowNum_UNIQUE` (`RowNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renthistory`
--

LOCK TABLES `renthistory` WRITE;
/*!40000 ALTER TABLE `renthistory` DISABLE KEYS */;
INSERT INTO `renthistory` VALUES (0,'Gr34','312592199','Green Fields','2019-02-02','2019-02-03','2019-02-04','0'),(1,'Gr34','312592199','Green Fields','2019-02-03','2019-02-04','2019-02-05','0'),(2,'Gr34','312592199','Green Fields','2019-02-04','2019-02-06',NULL,'0'),(3,'Gr34','312592198','Green Fields','2019-02-04','2019-02-18',NULL,'0'),(4,'Fe21','312592198','Algebra III','2019-02-04','2019-02-08',NULL,'1'),(5,'Fe21','312576325','Algebra III','2019-02-04','2019-02-11',NULL,'0'),(6,'fx11','312576325','JavaFx','2019-02-04','2019-02-12',NULL,'0'),(7,'Gr35','312592199','JavaFx','2019-01-02','2019-01-05','2019-01-20','0'),(8,'Gr34','312592199','Algebra III','2019-01-04','2019-01-11','2019-01-18','0');
/*!40000 ALTER TABLE `renthistory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-04  7:13:07
