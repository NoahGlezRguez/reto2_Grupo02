CREATE DATABASE  IF NOT EXISTS `cine_elorrieta` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cine_elorrieta`;
-- MySQL dump 10.13  Distrib 8.0.44, for macos15 (arm64)
--
-- Host: localhost    Database: cine_elorrieta
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `Cine`
--

DROP TABLE IF EXISTS `Cine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cine` (
  `IDCine` int unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  PRIMARY KEY (`IDCine`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_es_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cine`
--

LOCK TABLES `Cine` WRITE;
/*!40000 ALTER TABLE `Cine` DISABLE KEYS */;
INSERT INTO `Cine` VALUES (1,'Elorrieta Cines');
/*!40000 ALTER TABLE `Cine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cliente` (
  `DNI` char(9) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  `NomCli` varchar(20) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  `Ape` varchar(20) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  `mail` varchar(100) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  `userpassword` varchar(255) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  PRIMARY KEY (`DNI`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_es_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES ('12325578Z','NO','Gonzalez','noahyrjyrjyj@gmail.com','25d55ad283aa400af464c76d713c07ad'),('12345678A','ana','rosa','ana@gmail.com','81dc9bdb52d04dc20036dbd8313ed055'),('12345678B','juan','ramirez','juan@gmail.com','d93591bdf7860e1e4ee2fca799911215'),('12345678C','maria','hernandez','maria@gmail.com','7cc5ca26d6fbb6db2b134ef07cc68925'),('12345678Z','NOAH','Gonzalez','noah@gmail.com','25d55ad283aa400af464c76d713c07ad'),('21321265A','luis','martinez','luis@gmail.com','e9510081ac30ffa83f10b68cde1cac07'),('54769853Ñ','J','PG','jpg@gmail.com','907e06a16862d82ca6907a28a68d0ad6');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Compra`
--

DROP TABLE IF EXISTS `Compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Compra` (
  `IDCompra` int unsigned NOT NULL AUTO_INCREMENT,
  `FecCompra` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `plataforma` enum('web','app') COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  `descuento` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `DNI` char(9) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  PRIMARY KEY (`IDCompra`),
  KEY `FK_Cliente_Compra` (`DNI`),
  CONSTRAINT `FK_Cliente_Compra` FOREIGN KEY (`DNI`) REFERENCES `Cliente` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_es_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Compra`
--

LOCK TABLES `Compra` WRITE;
/*!40000 ALTER TABLE `Compra` DISABLE KEYS */;
INSERT INTO `Compra` VALUES (1,'2026-01-20 17:30:00','web',0.00,6.30,'12345678A'),(2,'2026-01-20 17:31:00','app',12.80,51.20,'54769853Ñ'),(3,'2026-01-20 17:32:00','web',2.64,10.56,'12345678A'),(4,'2026-01-20 17:33:00','app',6.45,15.05,'12345678B'),(5,'2026-01-20 17:34:00','web',0.00,8.00,'21321265A'),(6,'2026-01-20 17:35:00','app',6.93,27.72,'54769853Ñ'),(7,'2026-02-06 08:30:02','web',0.00,6.00,'12345678A');
/*!40000 ALTER TABLE `Compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Entrada`
--

DROP TABLE IF EXISTS `Entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Entrada` (
  `IDEntrada` int unsigned NOT NULL AUTO_INCREMENT,
  `CantPersonas` int unsigned NOT NULL,
  `importe` decimal(10,2) NOT NULL,
  `IDSesion` int unsigned NOT NULL,
  `IDCompra` int unsigned NOT NULL,
  PRIMARY KEY (`IDEntrada`),
  KEY `FK_Sesion_Entrada` (`IDSesion`),
  KEY `FK_Compra_Entrada` (`IDCompra`),
  CONSTRAINT `FK_Compra_Entrada` FOREIGN KEY (`IDCompra`) REFERENCES `Compra` (`IDCompra`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Sesion_Entrada` FOREIGN KEY (`IDSesion`) REFERENCES `Sesion` (`IDsesion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_es_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Entrada`
--

LOCK TABLES `Entrada` WRITE;
/*!40000 ALTER TABLE `Entrada` DISABLE KEYS */;
INSERT INTO `Entrada` VALUES (1,1,6.30,15,1),(2,2,16.00,21,2),(3,2,16.00,36,2),(4,1,6.30,3,3),(5,1,6.90,7,3),(6,1,7.22,1,4),(7,1,8.00,9,4),(8,1,6.30,15,4),(9,1,8.00,10,5),(10,1,7.65,8,6),(11,1,9.00,11,6),(12,2,18.00,11,6),(13,1,6.00,61,7);
/*!40000 ALTER TABLE `Entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `IDGen` int unsigned NOT NULL AUTO_INCREMENT,
  `NomGen` varchar(20) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  PRIMARY KEY (`IDGen`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_es_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'Terror'),(2,'Accion'),(3,'Comedia'),(4,'Romance'),(5,'Musical');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula` (
  `IDpeli` int unsigned NOT NULL AUTO_INCREMENT,
  `NomPeli` varchar(50) COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  `Duracion` int unsigned NOT NULL,
  `Caratula` varchar(50) COLLATE utf8mb4_es_0900_ai_ci DEFAULT NULL,
  `IDGen` int unsigned NOT NULL,
  PRIMARY KEY (`IDpeli`),
  KEY `FK_genero_pelicula` (`IDGen`),
  CONSTRAINT `FK_genero_pelicula` FOREIGN KEY (`IDGen`) REFERENCES `Genero` (`IDGen`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_es_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` VALUES (1,'Pinky Pink',120,'img/film1.png',5),(2,'Salvando al Presidente',130,'img/film2.png',2),(3,'Tú, ella, él y yo',90,'img/film3.png',4),(4,'Un pobre de derechas',110,'img/film4.png',3),(5,'Sweet dreams',140,'img/film5.png',1),(6,'El llanto maldito',115,'img/film6.png',1),(7,'Mi madre y yo',100,'img/film7.png',3),(8,'The F*k end of the world',125,'img/film8.png',2),(9,'El sillón',135,'img/film9.png',1);
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `numSala` int unsigned NOT NULL AUTO_INCREMENT,
  `IDCine` int unsigned NOT NULL,
  `aforo` int DEFAULT NULL,
  PRIMARY KEY (`numSala`,`IDCine`),
  KEY `FK_Cine_sala` (`IDCine`),
  CONSTRAINT `FK_Cine_sala` FOREIGN KEY (`IDCine`) REFERENCES `Cine` (`IDCine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_es_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (1,1,30),(2,1,50),(3,1,65),(4,1,30),(5,1,20),(6,1,60);
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sesion` (
  `IDsesion` int unsigned NOT NULL AUTO_INCREMENT,
  `fec` date NOT NULL,
  `hora_ini` time NOT NULL,
  `hora_fin` time NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `IDCine` int unsigned NOT NULL,
  `NumSala` int unsigned NOT NULL,
  `IDPeli` int unsigned NOT NULL,
  PRIMARY KEY (`IDsesion`),
  KEY `FK_Sala_Sesion` (`IDCine`,`NumSala`),
  KEY `FK_Pelicula_Sesion` (`IDPeli`),
  CONSTRAINT `FK_Pelicula_Sesion` FOREIGN KEY (`IDPeli`) REFERENCES `Pelicula` (`IDpeli`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Sala_Sesion` FOREIGN KEY (`IDCine`, `NumSala`) REFERENCES `sala` (`IDCine`, `numSala`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_es_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesion`
--

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` VALUES (1,'2026-01-20','15:30:00','17:30:00',7.22,1,1,1),(2,'2026-01-20','15:30:00','17:40:00',5.25,1,2,2),(3,'2026-01-20','15:30:00','17:00:00',6.30,1,3,3),(4,'2026-01-20','16:00:00','17:50:00',5.20,1,4,4),(5,'2026-01-20','18:00:00','19:50:00',6.10,1,4,4),(6,'2026-01-20','18:10:00','20:10:00',8.50,1,1,2),(7,'2026-01-20','18:10:00','19:50:00',6.90,1,5,7),(8,'2026-01-20','18:30:00','20:45:00',7.65,1,6,9),(9,'2026-01-20','18:30:00','20:25:00',8.00,1,3,6),(10,'2026-01-20','19:00:00','21:20:00',8.50,1,2,5),(11,'2026-01-20','20:00:00','22:05:00',9.00,1,4,8),(12,'2026-01-20','21:30:00','23:10:00',8.00,1,6,7),(13,'2026-01-21','15:30:00','17:30:00',7.22,1,1,1),(14,'2026-01-21','15:30:00','17:40:00',5.25,1,2,2),(15,'2026-01-21','15:30:00','17:00:00',6.30,1,3,3),(16,'2026-01-21','16:00:00','17:50:00',5.20,1,4,4),(17,'2026-01-21','18:00:00','19:50:00',6.10,1,4,4),(18,'2026-01-21','18:10:00','20:10:00',8.50,1,1,2),(19,'2026-01-21','18:10:00','19:50:00',6.90,1,5,7),(20,'2026-01-21','18:30:00','20:45:00',7.65,1,6,9),(21,'2026-01-21','18:30:00','20:25:00',8.00,1,3,6),(22,'2026-01-21','19:00:00','21:20:00',8.50,1,2,5),(23,'2026-01-21','20:00:00','22:05:00',9.00,1,4,8),(24,'2026-01-21','21:30:00','23:10:00',8.00,1,6,7),(25,'2026-01-22','15:30:00','17:30:00',7.22,1,1,1),(26,'2026-01-22','15:30:00','17:40:00',5.25,1,2,2),(27,'2026-01-22','15:30:00','17:00:00',6.30,1,3,3),(28,'2026-01-22','16:00:00','17:50:00',5.20,1,4,4),(29,'2026-01-22','18:00:00','19:50:00',6.10,1,4,4),(30,'2026-01-22','18:10:00','20:10:00',8.50,1,1,2),(31,'2026-01-22','18:10:00','19:50:00',6.90,1,5,7),(32,'2026-01-22','18:30:00','20:45:00',7.65,1,6,9),(33,'2026-01-22','18:30:00','20:25:00',8.00,1,3,6),(34,'2026-01-22','19:00:00','21:20:00',8.50,1,2,5),(35,'2026-01-22','20:00:00','22:05:00',9.00,1,4,8),(36,'2026-01-22','21:30:00','23:10:00',8.00,1,6,7),(37,'2026-02-10','15:30:00','17:30:00',7.20,1,1,1),(38,'2026-02-10','16:00:00','18:10:00',6.00,1,2,4),(39,'2026-02-10','17:00:00','19:20:00',8.50,1,3,5),(40,'2026-02-10','18:30:00','20:05:00',6.80,1,4,7),(41,'2026-02-10','19:00:00','21:15:00',7.50,1,5,9),(42,'2026-02-10','21:30:00','23:35:00',9.00,1,6,8),(43,'2026-02-11','15:30:00','17:00:00',6.20,1,1,3),(44,'2026-02-11','16:00:00','18:10:00',7.90,1,2,2),(45,'2026-02-11','18:00:00','20:20:00',8.70,1,3,5),(46,'2026-02-11','18:30:00','20:05:00',6.50,1,4,7),(47,'2026-02-11','20:00:00','22:05:00',8.90,1,5,8),(48,'2026-02-11','21:30:00','23:25:00',8.00,1,6,6),(49,'2026-02-12','15:30:00','17:30:00',7.20,1,1,1),(50,'2026-02-12','16:00:00','18:15:00',7.80,1,2,9),(51,'2026-02-12','18:00:00','19:30:00',6.10,1,3,3),(52,'2026-02-12','18:30:00','20:20:00',6.50,1,4,4),(53,'2026-02-12','20:00:00','22:20:00',8.50,1,5,5),(54,'2026-02-12','21:30:00','23:10:00',7.90,1,6,7),(55,'2026-02-13','15:30:00','17:45:00',7.40,1,1,8),(56,'2026-02-13','16:00:00','17:55:00',6.30,1,2,6),(57,'2026-02-13','18:00:00','20:10:00',8.20,1,3,2),(58,'2026-02-13','18:30:00','20:05:00',6.70,1,4,7),(59,'2026-02-13','20:00:00','22:20:00',9.10,1,5,5),(60,'2026-02-13','21:30:00','23:45:00',8.80,1,6,9),(61,'2026-02-14','15:30:00','17:00:00',6.00,1,1,3),(62,'2026-02-14','16:00:00','18:10:00',7.60,1,2,2),(63,'2026-02-14','18:00:00','20:20:00',8.50,1,3,5),(64,'2026-02-14','18:30:00','20:05:00',6.90,1,4,7),(65,'2026-02-14','20:00:00','22:05:00',8.90,1,5,8),(66,'2026-02-14','21:30:00','23:10:00',7.80,1,6,6),(67,'2026-02-15','15:30:00','17:30:00',7.20,1,1,1),(68,'2026-02-15','16:00:00','18:15:00',7.80,1,2,9),(69,'2026-02-15','18:00:00','19:30:00',6.10,1,3,3),(70,'2026-02-15','18:30:00','20:20:00',6.50,1,4,4),(71,'2026-02-15','20:00:00','22:20:00',8.50,1,5,5),(72,'2026-02-15','21:30:00','23:35:00',9.00,1,6,8),(73,'2026-02-16','15:30:00','17:00:00',6.20,1,1,3),(74,'2026-02-16','16:00:00','18:10:00',7.90,1,2,2),(75,'2026-02-16','18:00:00','20:20:00',8.70,1,3,5),(76,'2026-02-16','18:30:00','20:05:00',6.50,1,4,7),(77,'2026-02-16','20:00:00','22:05:00',8.90,1,5,8),(78,'2026-02-16','21:30:00','23:25:00',8.00,1,6,6),(79,'2026-02-17','15:30:00','17:30:00',7.20,1,1,1),(80,'2026-02-17','16:00:00','18:15:00',7.80,1,2,9),(81,'2026-02-17','18:00:00','19:30:00',6.10,1,3,3),(82,'2026-02-17','18:30:00','20:20:00',6.50,1,4,4),(83,'2026-02-17','20:00:00','22:20:00',8.50,1,5,5),(84,'2026-02-17','21:30:00','23:10:00',7.90,1,6,7),(85,'2026-02-18','18:30:00','20:45:00',7.65,1,6,9),(86,'2026-02-18','20:00:00','22:05:00',9.00,1,4,8);
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-06  9:36:00
