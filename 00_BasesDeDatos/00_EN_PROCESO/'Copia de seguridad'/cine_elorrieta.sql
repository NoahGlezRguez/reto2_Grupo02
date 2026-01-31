-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 21-01-2026 a las 15:20:10
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cine_elorrieta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cine`
--

CREATE TABLE `cine` (
  `IDCine` int(10) UNSIGNED NOT NULL,
  `nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cine`
--

INSERT INTO `cine` (`IDCine`, `nom`) VALUES
(1, 'Elorrieta Cines');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `DNI` varchar(9) NOT NULL,
  `NomCli` varchar(20) NOT NULL,
  `Ape` varchar(20) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `userpassword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`DNI`, `NomCli`, `Ape`, `mail`, `userpassword`) VALUES
('12345678A', 'ana', 'rosa', 'ana@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055'),
('12345678B', 'juan', 'ramirez', 'juan@gmail.com', 'd93591bdf7860e1e4ee2fca799911215'),
('12345678C', 'maria', 'hernandez', 'maria@gmail.com', '7cc5ca26d6fbb6db2b134ef07cc68925'),
('21321265A', 'luis', 'martinez', 'luis@gmail.com', 'e9510081ac30ffa83f10b68cde1cac07'),
('54769853Ñ', 'J', 'PG', 'jpg@gmail.com', '907e06a16862d82ca6907a28a68d0ad6');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `IDCompra` int(10) UNSIGNED NOT NULL,
  `FecCompra` timestamp NOT NULL DEFAULT current_timestamp(),
  `plataforma` enum('web','app') NOT NULL,
  `descuento` decimal(5,2) NOT NULL,
  `total` decimal(4,2) NOT NULL,
  `DNI` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`IDCompra`, `FecCompra`, `plataforma`, `descuento`, `total`, `DNI`) VALUES
(1, '2026-01-20 19:40:41', 'web', 0.00, 6.30, '12345678A'),
(2, '2026-01-20 19:40:41', 'app', 20.00, 25.60, '54769853Ñ'),
(3, '2026-01-20 19:40:41', 'web', 20.00, 11.60, '12345678A'),
(4, '2026-01-20 19:40:41', 'app', 30.00, 16.31, '12345678B'),
(5, '2026-01-20 19:40:41', 'web', 0.00, 8.00, '21321265A'),
(6, '2026-01-20 19:40:41', 'app', 20.00, 13.28, '54769853Ñ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada`
--

CREATE TABLE `entrada` (
  `IDEntrada` int(10) UNSIGNED NOT NULL,
  `CantPersonas` int(10) UNSIGNED NOT NULL,
  `importe` decimal(4,2) NOT NULL,
  `IDSesion` int(10) UNSIGNED NOT NULL,
  `IDCompra` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`IDEntrada`, `CantPersonas`, `importe`, `IDSesion`, `IDCompra`) VALUES
(1, 1, 6.30, 15, 1),
(2, 2, 16.00, 21, 2),
(3, 2, 16.00, 36, 2),
(4, 1, 6.30, 3, 3),
(5, 1, 6.90, 7, 3),
(6, 1, 7.22, 1, 4),
(7, 1, 8.00, 9, 4),
(8, 1, 6.30, 15, 4),
(9, 1, 8.00, 10, 5),
(10, 1, 7.65, 8, 6),
(11, 1, 9.00, 11, 6),
(12, 2, 18.00, 11, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `IDGen` int(10) UNSIGNED NOT NULL,
  `NomGen` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`IDGen`, `NomGen`) VALUES
(1, 'Terror'),
(2, 'Accion'),
(3, 'Comedia'),
(4, 'Romance'),
(5, 'Musical');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `IDpeli` int(10) UNSIGNED NOT NULL,
  `NomPeli` varchar(50) NOT NULL,
  `Duracion` int(10) UNSIGNED NOT NULL,
  `Caratula` varchar(50) DEFAULT NULL,
  `IDGen` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`IDpeli`, `NomPeli`, `Duracion`, `Caratula`, `IDGen`) VALUES
(1, 'Pinky Pink', 120, 'img/film1.png', 5),
(2, 'Salvando al Presidente Ryan', 130, 'img/film2.png', 2),
(3, 'Tú, ella, él y yo', 90, 'img/film3.png', 4),
(4, 'Un pobre de derechas', 110, 'img/film4.png', 3),
(5, 'Sweet dreams', 140, 'img/film5.png', 1),
(6, 'El llanto maldito', 115, 'img/film6.png', 1),
(7, 'Tu madre y yo', 100, 'img/film7.png', 3),
(8, 'The F*k end of the world', 125, 'img/film8.png', 2),
(9, 'El sillón', 135, 'img/film9.png', 1),
(10, 'Seldom: La pelicula', 5, 'img/film10.png', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `numSala` int(10) UNSIGNED NOT NULL,
  `IDCine` int(10) UNSIGNED NOT NULL,
  `aforo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`numSala`, `IDCine`, `aforo`) VALUES
(1, 1, 30),
(2, 1, 50),
(3, 1, 65),
(4, 1, 30),
(5, 1, 20),
(6, 1, 60);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesion`
--

CREATE TABLE `sesion` (
  `IDsesion` int(10) UNSIGNED NOT NULL,
  `fec` date NOT NULL,
  `hora_ini` time NOT NULL,
  `hora_fin` time NOT NULL,
  `precio` decimal(4,2) NOT NULL,
  `IDCine` int(10) UNSIGNED NOT NULL,
  `NumSala` int(10) UNSIGNED NOT NULL,
  `IDPeli` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sesion`
--

INSERT INTO `sesion` (`IDsesion`, `fec`, `hora_ini`, `hora_fin`, `precio`, `IDCine`, `NumSala`, `IDPeli`) VALUES
(1, '2026-01-20', '15:30:00', '17:30:00', 7.22, 1, 1, 1),
(2, '2026-01-20', '15:30:00', '17:40:00', 5.25, 1, 2, 2),
(3, '2026-01-20', '15:30:00', '17:00:00', 6.30, 1, 3, 3),
(4, '2026-01-20', '16:00:00', '17:50:00', 5.20, 1, 4, 4),
(5, '2026-01-20', '18:00:00', '19:50:00', 6.10, 1, 4, 4),
(6, '2026-01-20', '18:10:00', '20:10:00', 8.50, 1, 1, 2),
(7, '2026-01-20', '18:10:00', '19:50:00', 6.90, 1, 5, 7),
(8, '2026-01-20', '18:30:00', '20:45:00', 7.65, 1, 6, 9),
(9, '2026-01-20', '18:30:00', '20:25:00', 8.00, 1, 3, 6),
(10, '2026-01-20', '19:00:00', '21:20:00', 8.50, 1, 2, 5),
(11, '2026-01-20', '20:00:00', '22:05:00', 9.00, 1, 4, 8),
(12, '2026-01-20', '21:30:00', '23:10:00', 8.00, 1, 6, 7),
(13, '2026-01-21', '15:30:00', '17:30:00', 7.22, 1, 1, 1),
(14, '2026-01-21', '15:30:00', '17:40:00', 5.25, 1, 2, 2),
(15, '2026-01-21', '15:30:00', '17:00:00', 6.30, 1, 3, 3),
(16, '2026-01-21', '16:00:00', '17:50:00', 5.20, 1, 4, 4),
(17, '2026-01-21', '18:00:00', '19:50:00', 6.10, 1, 4, 4),
(18, '2026-01-21', '18:10:00', '20:10:00', 8.50, 1, 1, 2),
(19, '2026-01-21', '18:10:00', '19:50:00', 6.90, 1, 5, 7),
(20, '2026-01-21', '18:30:00', '20:45:00', 7.65, 1, 6, 9),
(21, '2026-01-21', '18:30:00', '20:25:00', 8.00, 1, 3, 6),
(22, '2026-01-21', '19:00:00', '21:20:00', 8.50, 1, 2, 5),
(23, '2026-01-21', '20:00:00', '22:05:00', 9.00, 1, 4, 8),
(24, '2026-01-21', '21:30:00', '23:10:00', 8.00, 1, 6, 7),
(25, '2026-01-22', '15:30:00', '17:30:00', 7.22, 1, 1, 1),
(26, '2026-01-22', '15:30:00', '17:40:00', 5.25, 1, 2, 2),
(27, '2026-01-22', '15:30:00', '17:00:00', 6.30, 1, 3, 3),
(28, '2026-01-22', '16:00:00', '17:50:00', 5.20, 1, 4, 4),
(29, '2026-01-22', '18:00:00', '19:50:00', 6.10, 1, 4, 4),
(30, '2026-01-22', '18:10:00', '20:10:00', 8.50, 1, 1, 2),
(31, '2026-01-22', '18:10:00', '19:50:00', 6.90, 1, 5, 7),
(32, '2026-01-22', '18:30:00', '20:45:00', 7.65, 1, 6, 9),
(33, '2026-01-22', '18:30:00', '20:25:00', 8.00, 1, 3, 6),
(34, '2026-01-22', '19:00:00', '21:20:00', 8.50, 1, 2, 5),
(35, '2026-01-22', '20:00:00', '22:05:00', 9.00, 1, 4, 8),
(36, '2026-01-22', '21:30:00', '23:10:00', 8.00, 1, 6, 7);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cine`
--
ALTER TABLE `cine`
  ADD PRIMARY KEY (`IDCine`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`DNI`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`IDCompra`),
  ADD KEY `FK_Cliente_Compra` (`DNI`);

--
-- Indices de la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`IDEntrada`),
  ADD KEY `FK_Sesion_Entrada` (`IDSesion`),
  ADD KEY `FK_Compra_Entrada` (`IDCompra`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`IDGen`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`IDpeli`),
  ADD KEY `FK_genero_pelicula` (`IDGen`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`numSala`,`IDCine`),
  ADD KEY `FK_Cine_sala` (`IDCine`);

--
-- Indices de la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD PRIMARY KEY (`IDsesion`),
  ADD KEY `FK_Sala_Sesion` (`IDCine`,`NumSala`),
  ADD KEY `FK_Pelicula_Sesion` (`IDPeli`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cine`
--
ALTER TABLE `cine`
  MODIFY `IDCine` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `IDCompra` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `entrada`
--
ALTER TABLE `entrada`
  MODIFY `IDEntrada` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `IDGen` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `IDpeli` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `numSala` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `sesion`
--
ALTER TABLE `sesion`
  MODIFY `IDsesion` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `FK_Cliente_Compra` FOREIGN KEY (`DNI`) REFERENCES `cliente` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD CONSTRAINT `FK_Compra_Entrada` FOREIGN KEY (`IDCompra`) REFERENCES `compra` (`IDCompra`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Sesion_Entrada` FOREIGN KEY (`IDSesion`) REFERENCES `sesion` (`IDsesion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD CONSTRAINT `FK_genero_pelicula` FOREIGN KEY (`IDGen`) REFERENCES `genero` (`IDGen`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `sala`
--
ALTER TABLE `sala`
  ADD CONSTRAINT `FK_Cine_sala` FOREIGN KEY (`IDCine`) REFERENCES `cine` (`IDCine`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD CONSTRAINT `FK_Pelicula_Sesion` FOREIGN KEY (`IDPeli`) REFERENCES `pelicula` (`IDpeli`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Sala_Sesion` FOREIGN KEY (`IDCine`,`NumSala`) REFERENCES `sala` (`IDCine`, `numSala`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
