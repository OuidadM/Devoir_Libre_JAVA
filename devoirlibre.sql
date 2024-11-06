-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2024 at 04:09 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `devoirlibre`
--

-- --------------------------------------------------------

--
-- Table structure for table `banques`
--

CREATE TABLE `banques` (
  `id` int(11) NOT NULL,
  `pays` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `banques`
--

INSERT INTO `banques` (`id`, `pays`) VALUES
(4444, 'Maroc');

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `numClient` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`numClient`, `nom`, `prenom`, `adresse`, `phone`, `email`) VALUES
(7, 'laro', 'mina', 'ggggg', '555555', '@@@@@'),
(8, 'Mov', 'Ouidad', 'HHH', '0600000000', '@@@@'),
(9, 'Azizi', 'HAnaa', '@@@', '&&&&', 'gmail'),
(10, 'Roudani', 'Mariem', '@@@@@@@@@', '$$$$$$$', 'm@gmail.com'),
(11, 'Mochariq', 'Ouidad', 'Alia', '##$', '@@@@'),
(12, 'Madini', 'Nada', 'hhhh', '55555', '@@@@@'),
(13, 'Robaai', 'Malak', 'gggg', '6666', '@@@@'),
(14, 'Rohani', 'Roquaya', '@@@', '444444', '@@@'),
(15, 'Nouali', 'Rabiaa', '@@@', '555', '@@@'),
(16, 'Roba', 'Hind', '@@@', '333', '@@@'),
(17, 'Roquayq', 'Maliki', '@@@@', '444', '@@2'),
(18, 'Ichari', 'Alia', '@@@', '3333', '@@@'),
(19, 'Nouh', 'Noura', '@@@', '4444', '@@@@'),
(20, 'Fariss', 'Nada', '@@@', '3333', '@@@@'),
(21, 'Nossair', 'Nabil', '@22', '333', '@@@'),
(22, 'Mansourri', 'Loqqi', '@@@', '3333', '@@@'),
(23, 'Mochariq', 'Ouidad', 'Marrakech', '3333', 'mochariqouidad@gmail.com'),
(24, 'Mochariq', 'Ouidad', 'Marrakech', '33333', 'mochariqouidad@gmail.com'),
(25, 'Mochariq', 'Ouidad', 'Marrakech', '2222', 'mochariqouidad@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `comptes`
--

CREATE TABLE `comptes` (
  `numCompte` int(11) NOT NULL,
  `dateCreation` date DEFAULT NULL,
  `dateUpdate` date DEFAULT NULL,
  `devise` varchar(20) DEFAULT NULL,
  `numClient` int(11) DEFAULT NULL,
  `banque_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comptes`
--

INSERT INTO `comptes` (`numCompte`, `dateCreation`, `dateUpdate`, `devise`, `numClient`, `banque_id`) VALUES
(1, '2024-11-03', '2024-11-03', 'Dolar', 7, 4444),
(2, '2024-11-03', '2024-11-03', 'MAD', 8, 4444),
(3, '2024-11-03', '2024-11-03', 'MAD', 9, 4444),
(4, '2024-11-03', '2024-11-03', 'Euro', 10, 4444),
(5, '2024-11-04', '2024-11-04', 'Dollar', 11, 4444),
(6, '2024-11-04', '2024-11-04', 'Euros', 12, 4444),
(7, '2024-11-04', '2024-11-04', 'Yuan', 13, 4444),
(8, '2024-11-04', '2024-11-04', 'Euro', 14, 4444),
(10, '2024-11-06', '2024-11-06', 'MAD', 15, 4444),
(11, '2024-11-06', '2024-11-06', 'Dollar', 16, 4444),
(12, '2024-11-06', '2024-11-06', 'Dollar', 17, 4444),
(13, '2024-11-06', '2024-11-06', 'Dollar', 18, 4444),
(14, '2024-11-06', '2024-11-06', 'MAD', 19, 4444),
(15, '2024-11-06', '2024-11-06', 'Euro', 20, 4444),
(16, '2024-11-06', '2024-11-06', 'Euro', 21, 4444),
(17, '2024-11-06', '2024-11-06', 'Euro', 22, 4444),
(18, '2024-11-06', '2024-11-06', 'Mad', 23, 4444),
(19, '2024-11-06', '2024-11-06', 'MAD', 24, 4444),
(20, '2024-11-06', '2024-11-06', 'MAD', 25, 4444);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banques`
--
ALTER TABLE `banques`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`numClient`);

--
-- Indexes for table `comptes`
--
ALTER TABLE `comptes`
  ADD PRIMARY KEY (`numCompte`),
  ADD KEY `numClient` (`numClient`),
  ADD KEY `fk_banque_id` (`banque_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `numClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `comptes`
--
ALTER TABLE `comptes`
  MODIFY `numCompte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comptes`
--
ALTER TABLE `comptes`
  ADD CONSTRAINT `comptes_ibfk_1` FOREIGN KEY (`numClient`) REFERENCES `clients` (`numClient`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_banque_id` FOREIGN KEY (`banque_id`) REFERENCES `banques` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
