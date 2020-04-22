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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `books` (
  `Serial` varchar(255) NOT NULL,
  `BookName` varchar(255) NOT NULL,
  `Author` varchar(255) NOT NULL,
  `Generation` varchar(255) NOT NULL,
  `PrintedDate` date DEFAULT NULL,
  `Category` varchar(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `DateOfPurchase` date DEFAULT NULL,
  `PlaceOnShelf` varchar(255) NOT NULL,
  `TableOfContentAsPDF` varchar(255) DEFAULT NULL,
  `NumberOfCopies` varchar(255) NOT NULL,
  `NumberOfAvailabeCopies` varchar(255) NOT NULL,
  PRIMARY KEY (`Serial`),
  UNIQUE KEY `Serial_UNIQUE` (`Serial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('Fe21','Algebra III','Felix','3','2002-05-14','Applied Mathematics','This is a very usefull math book','2003-02-15','Fe','Fe21.pdf','4','0'),('fx11','JavaFX11','Mati Golani','6','2019-02-15','Inforamation Systems','#%$&','2019-01-31','Fx','fx11.pdf','57','57'),('Gr34','Green Fields','Dr.Sue','1','2017-02-08','Biotechnology','desc','2019-02-20','Gr','Gr34.pdf','43','40'),('Gr35','Green Fields II','Dr.Sue','2','2019-02-21','Biotechnology','desc','2019-02-28','Gr','Gr35.pdf','23','22'),('Pd22','PdfSampleBook','Java','2','2019-02-06','Software','Nothing to say','2019-02-14','Pd','Pd22.pdf','5','5');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
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
