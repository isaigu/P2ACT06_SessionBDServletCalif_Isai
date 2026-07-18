-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-07-2026 a las 11:02:29
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sesionbdservletcalif`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `matricula` varchar(20) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `grupo` varchar(10) NOT NULL,
  `correo` varchar(120) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`matricula`, `nombre`, `grupo`, `correo`, `fecha_ingreso`) VALUES
('57231900067', 'Cuevas Garcia Mario', '9A', '57231900067@utrng.edu.mx', '2024-01-15'),
('57231900069', 'Flores Esteban Emmanuel', '9A', '57231900069@utrng.edu.mx', '2024-01-15'),
('57231900070', 'Gabriel Gonzalez Vicente Tadeo', '9A', '57231900070@utrng.edu.mx', '2024-01-15'),
('57231900072', 'Gutierrez Zamudio Isai', '9A', '57231900072@utrng.edu.mx', '2024-01-15'),
('57231900076', 'Jaimes Vazquez Victor', '9A', '57231900076@utrng.edu.mx', '2024-01-15'),
('57231900077', 'Moreno Hernandez Rosalinda', '9A', '57231900077@utrng.edu.mx', '2024-01-15'),
('57231900081', 'Torres Diaz Iris Gabriela', '9A', '57231900081@utrng.edu.mx', '2024-01-15'),
('57231900086', 'Bello Calixto Paola Yareni', '9A', '57231900086@utrng.edu.mx', '2024-01-15'),
('57231900095', 'Pascualeño Tlacotempa Deyanira', '9A', '57231900095@utrng.edu.mx', '2024-01-15'),
('57231900096', 'Ramirez Galindo Junior Gerardo', '9A', '57231900096@utrng.edu.mx', '2024-01-15'),
('57231900098', 'Ramos Lopez Axel Uriel', '9A', '57231900098@utrng.edu.mx', '2024-01-15'),
('57231900099', 'Sanchez Juarez Alma Delia', '9A', '57231900099@utrng.edu.mx', '2024-01-15'),
('57231900100', 'Santos Nava Alex Javier', '9A', '57231900100@utrng.edu.mx', '2024-01-15'),
('57231900101', 'Silverio Cuesta Francisco Javier', '9A', '57231900101@utrng.edu.mx', '2024-01-15'),
('57231900102', 'Torito Casarrubias Jessica Edith', '9A', '57231900102@utrng.edu.mx', '2024-01-15'),
('57231900104', 'Trinidad Jimenez Axel Andres', '9A', '57231900104@utrng.edu.mx', '2024-01-15'),
('57231900108', 'De Aquino Vargas Marco Antonio', '9A', '57231900108@utrng.edu.mx', '2024-01-15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificaciones`
--

CREATE TABLE `calificaciones` (
  `id_calificacion` int(11) NOT NULL,
  `matricula` varchar(20) NOT NULL,
  `id_materia` int(11) NOT NULL,
  `parcial1` decimal(5,2) DEFAULT 0.00,
  `parcial2` decimal(5,2) DEFAULT 0.00,
  `parcial3` decimal(5,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `calificaciones`
--

INSERT INTO `calificaciones` (`id_calificacion`, `matricula`, `id_materia`, `parcial1`, `parcial2`, `parcial3`) VALUES
(1, '57231900067', 1, 8.00, 0.00, 0.00),
(2, '57231900069', 1, 0.00, 0.00, 0.00),
(3, '57231900070', 1, 0.00, 0.00, 0.00),
(4, '57231900072', 1, 0.00, 0.00, 0.00),
(5, '57231900076', 1, 0.00, 0.00, 0.00),
(6, '57231900077', 1, 0.00, 0.00, 0.00),
(7, '57231900081', 1, 0.00, 0.00, 0.00),
(8, '57231900086', 1, 0.00, 0.00, 0.00),
(9, '57231900095', 1, 0.00, 0.00, 0.00),
(10, '57231900096', 1, 0.00, 0.00, 0.00),
(11, '57231900098', 1, 0.00, 0.00, 0.00),
(12, '57231900099', 1, 0.00, 0.00, 0.00),
(13, '57231900100', 1, 0.00, 0.00, 0.00),
(14, '57231900101', 1, 0.00, 0.00, 0.00),
(15, '57231900102', 1, 0.00, 0.00, 0.00),
(16, '57231900104', 1, 0.00, 0.00, 0.00),
(17, '57231900108', 1, 0.00, 0.00, 0.00),
(32, '57231900067', 2, 0.00, 0.00, 0.00),
(33, '57231900069', 2, 0.00, 0.00, 0.00),
(34, '57231900070', 2, 0.00, 0.00, 0.00),
(35, '57231900072', 2, 0.00, 0.00, 0.00),
(36, '57231900076', 2, 0.00, 0.00, 0.00),
(37, '57231900077', 2, 0.00, 0.00, 0.00),
(38, '57231900081', 2, 0.00, 0.00, 0.00),
(39, '57231900086', 2, 0.00, 0.00, 0.00),
(40, '57231900095', 2, 0.00, 0.00, 0.00),
(41, '57231900096', 2, 0.00, 0.00, 0.00),
(42, '57231900098', 2, 0.00, 0.00, 0.00),
(43, '57231900099', 2, 0.00, 0.00, 0.00),
(44, '57231900100', 2, 0.00, 0.00, 0.00),
(45, '57231900101', 2, 0.00, 0.00, 0.00),
(46, '57231900102', 2, 0.00, 0.00, 0.00),
(47, '57231900104', 2, 0.00, 0.00, 0.00),
(48, '57231900108', 2, 0.00, 0.00, 0.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE `materias` (
  `id_materia` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`id_materia`, `nombre`) VALUES
(1, 'Desarrollo Web Integral'),
(2, 'Desarrollo para Dispositivos Inteligentes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(120) NOT NULL,
  `es_admin` tinyint(1) NOT NULL DEFAULT 0,
  `correo_validado` tinyint(1) NOT NULL DEFAULT 0,
  `estado` tinyint(1) NOT NULL DEFAULT 0,
  `token_confirmacion` varchar(64) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `usuario`, `contrasena`, `nombre`, `correo`, `es_admin`, `correo_validado`, `estado`, `token_confirmacion`, `fecha_registro`) VALUES
(1, 'admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'Administrador Docente', 'admin@escuela.edu.mx', 1, 1, 1, NULL, '2026-07-17 11:35:17'),
(4, 'isai', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Isai Gutierrez Zamudio', '57231900072_i@utrng.edu.mx', 0, 1, 1, 'csodgyoanoehbctkrtc1qbnry5thn2n873y4rv6v', '2026-07-17 13:57:16');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`matricula`);

--
-- Indices de la tabla `calificaciones`
--
ALTER TABLE `calificaciones`
  ADD PRIMARY KEY (`id_calificacion`),
  ADD UNIQUE KEY `uk_matricula_materia` (`matricula`,`id_materia`),
  ADD KEY `id_materia` (`id_materia`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
  ADD PRIMARY KEY (`id_materia`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `calificaciones`
--
ALTER TABLE `calificaciones`
  MODIFY `id_calificacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `materias`
--
ALTER TABLE `materias`
  MODIFY `id_materia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `calificaciones`
--
ALTER TABLE `calificaciones`
  ADD CONSTRAINT `calificaciones_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `alumnos` (`matricula`) ON DELETE CASCADE,
  ADD CONSTRAINT `calificaciones_ibfk_2` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`id_materia`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
