-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 05, 2020 at 06:52 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shopping`
--

-- --------------------------------------------------------

--
-- Table structure for table `boots`
--

CREATE TABLE `boots` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Brand` varchar(20) NOT NULL,
  `Price` int(6) NOT NULL,
  `Image1` varchar(100) NOT NULL,
  `Image2` varchar(100) NOT NULL,
  `Image3` varchar(100) NOT NULL,
  `Image4` varchar(100) NOT NULL,
  `Quantity` int(5) NOT NULL,
  `Discount` int(3) NOT NULL,
  `Size` varchar(2) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Type` varchar(6) NOT NULL,
  `UsedIn` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `boots`
--

INSERT INTO `boots` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('B_1', 'FST 3418', 'FAUSTO', 15000, 'Images/Boots/FAUSTO_Men_FST_3418_TAN/1.jpg', 'Images/Boots/FAUSTO_Men_FST_3418_TAN/2.jpg', 'Images/Boots/FAUSTO_Men_FST_3418_TAN/3.jpg', 'Images/Boots/FAUSTO_Men_FST_3418_TAN/4.jpg', 100, 30, '40', 'FAUSTO Description, begin!!', 'Male', 'Ankle Boots'),
('B_2', 'FST 3420', 'FAUSTO', 15000, 'Images/Boots/FAUSTO_Men_FST_3420_Brown/1.jpg', 'Images/Boots/FAUSTO_Men_FST_3420_Brown/2.jpg', 'Images/Boots/FAUSTO_Men_FST_3420_Brown/3.jpg', 'Images/Boots/FAUSTO_Men_FST_3420_Brown/4.jpg', 100, 10, '40', 'FAUSTO Description, begin!!', 'Male', 'Ankle Boots');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `ID` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Image` varchar(100) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Brand` varchar(50) NOT NULL,
  `Price` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `formal`
--

CREATE TABLE `formal` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Brand` varchar(20) DEFAULT NULL,
  `Price` int(6) DEFAULT NULL,
  `Image1` varchar(100) DEFAULT NULL,
  `Image2` varchar(100) DEFAULT NULL,
  `Image3` varchar(100) DEFAULT NULL,
  `Image4` varchar(100) DEFAULT NULL,
  `Quantity` int(5) DEFAULT NULL,
  `Discount` int(3) DEFAULT NULL,
  `Size` varchar(2) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Type` varchar(6) DEFAULT NULL,
  `UsedIn` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `formal`
--

INSERT INTO `formal` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('F_1', 'Man Durby', 'TR', 15000, 'Images/Formal/TR/1.jpeg', 'Images/Formal/TR/2.jpeg', 'Images/Formal/TR/3.jpeg', 'Images/Formal/TR/4.jpeg', 100, 0, '40', 'TR Description, begin!!', 'Male', 'Formals');

-- --------------------------------------------------------

--
-- Table structure for table `loafers`
--

CREATE TABLE `loafers` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Brand` varchar(20) NOT NULL,
  `Price` int(6) NOT NULL,
  `Image1` varchar(100) NOT NULL,
  `Image2` varchar(100) NOT NULL,
  `Image3` varchar(100) NOT NULL,
  `Image4` varchar(100) NOT NULL,
  `Quantity` int(5) NOT NULL,
  `Discount` int(3) NOT NULL,
  `Size` varchar(2) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Type` varchar(6) NOT NULL,
  `UsedIn` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loafers`
--

INSERT INTO `loafers` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('L_1', 'Navy Loafers', 'Mast and Harbour', 15000, 'Images/Loafer/MastandHarbour/1.jpg', 'Images/Loafer/MastandHarbour/2.jpg', 'Images/Loafer/MastandHarbour/3.jpg', 'Images/Loafer/MastandHarbour/4.jpg', 100, 0, '40', 'Loafer Description, begin!!', 'Male', 'Loafers');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `ID` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Image` varchar(100) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Brand` varchar(50) NOT NULL,
  `Price` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sandal`
--

CREATE TABLE `sandal` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Brand` varchar(20) DEFAULT NULL,
  `Price` int(6) DEFAULT NULL,
  `Image1` varchar(100) DEFAULT NULL,
  `Image2` varchar(100) DEFAULT NULL,
  `Image3` varchar(100) DEFAULT NULL,
  `Image4` varchar(100) DEFAULT NULL,
  `Quantity` int(5) DEFAULT NULL,
  `Discount` int(3) DEFAULT NULL,
  `Size` varchar(2) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Type` varchar(6) DEFAULT NULL,
  `UsedIn` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sandal`
--

INSERT INTO `sandal` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('S_1', 'Comfort', 'Roadster', 15000, 'Images/Sandal/Roadster/1.jpg', 'Images/Sandal/Roadster/2.jpg', 'Images/Sandal/Roadster/3.jpg', 'Images/Sandal/Roadster/4.jpg', 100, 0, '40', 'Sandal Description, begin!!', 'Male', 'Sandals');

-- --------------------------------------------------------

--
-- Table structure for table `skates`
--

CREATE TABLE `skates` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Brand` varchar(20) NOT NULL,
  `Price` int(6) NOT NULL,
  `Image1` varchar(100) NOT NULL,
  `Image2` varchar(100) NOT NULL,
  `Image3` varchar(100) NOT NULL,
  `Image4` varchar(100) NOT NULL,
  `Quantity` varchar(5) NOT NULL,
  `Discount` int(3) NOT NULL,
  `Size` varchar(2) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Type` varchar(6) NOT NULL,
  `UsedIn` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `skates`
--

INSERT INTO `skates` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('Sk_1', 'Inline Skates', 'VDNSI', 15000, 'Images/Skates/VDNSI/1.jpg', 'Images/Skates/VDNSI/2.jpg', 'Images/Skates/VDNSI/3.jpg', 'Images/Skates/VDNSI/4.jpg', '100', 0, '40', 'Skates Description, begin!!', 'Male', 'Skates');

-- --------------------------------------------------------

--
-- Table structure for table `sleeper`
--

CREATE TABLE `sleeper` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Brand` varchar(20) DEFAULT NULL,
  `Price` int(6) DEFAULT NULL,
  `Image1` varchar(100) DEFAULT NULL,
  `Image2` varchar(100) DEFAULT NULL,
  `Image3` varchar(100) DEFAULT NULL,
  `Image4` varchar(100) DEFAULT NULL,
  `Quantity` int(5) DEFAULT NULL,
  `Discount` int(3) DEFAULT NULL,
  `Size` varchar(2) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Type` varchar(6) DEFAULT NULL,
  `UsedIn` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sleeper`
--

INSERT INTO `sleeper` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('Sl_1', 'Flip-Flop', 'Benetton', 15000, 'Images/Sleeper/Flip-Flop/1.jpg', 'Images/Sleeper/Flip-Flop/2.jpg', 'Images/Sleeper/Flip-Flop/3.jpg', 'Images/Sleeper/Flip-Flop/4.jpg', 100, 0, '40', 'Sleeper Description, begin!!', 'Male', 'Sleepers');

-- --------------------------------------------------------

--
-- Table structure for table `sneakers`
--

CREATE TABLE `sneakers` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Brand` varchar(20) DEFAULT NULL,
  `Price` int(6) DEFAULT NULL,
  `Image1` varchar(100) DEFAULT NULL,
  `Image2` varchar(100) DEFAULT NULL,
  `Image3` varchar(100) DEFAULT NULL,
  `Image4` varchar(100) DEFAULT NULL,
  `Quantity` int(5) DEFAULT NULL,
  `Discount` int(3) DEFAULT NULL,
  `Size` varchar(2) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Type` varchar(6) DEFAULT NULL,
  `UsedIn` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sneakers`
--

INSERT INTO `sneakers` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('Sn_1', 'Casual', 'Red Rose', 15000, 'Images/Sneakers/Casual/1.jpg', 'Images/Sneakers/Casual/2.jpg', 'Images/Sneakers/Casual/3.jpg', 'Images/Sneakers/Casual/4.jpg', 100, 25, '40', 'Sneakers Description, begin!!', 'Male', 'Sneakers');

-- --------------------------------------------------------

--
-- Table structure for table `sports`
--

CREATE TABLE `sports` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Brand` varchar(20) NOT NULL,
  `Price` int(6) NOT NULL,
  `Image1` varchar(100) NOT NULL,
  `Image2` varchar(100) NOT NULL,
  `Image3` varchar(100) NOT NULL,
  `Image4` varchar(100) NOT NULL,
  `Quantity` int(5) NOT NULL,
  `Discount` int(3) NOT NULL,
  `Size` varchar(2) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Type` varchar(6) NOT NULL,
  `UsedIn` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sports`
--

INSERT INTO `sports` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('Sp_1', 'TheLebron 12', 'Nike', 15000, 'Images/Sports/TheLebron12/1.jpg', 'Images/Sports/TheLebron12/2.jpg', 'Images/Sports/TheLebron12/3.jpg', 'Images/Sports/TheLebron12/4.jpg', 100, 35, '40', 'Sports Description, begin!!', 'Male', 'Basketball');

-- --------------------------------------------------------

--
-- Table structure for table `swim`
--

CREATE TABLE `swim` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Brand` varchar(20) DEFAULT NULL,
  `Price` int(6) DEFAULT NULL,
  `Image1` varchar(100) DEFAULT NULL,
  `Image2` varchar(100) DEFAULT NULL,
  `Image3` varchar(100) DEFAULT NULL,
  `Image4` varchar(100) DEFAULT NULL,
  `Quantity` int(5) DEFAULT NULL,
  `Discount` int(3) DEFAULT NULL,
  `Size` varchar(2) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Type` varchar(6) DEFAULT NULL,
  `UsedIn` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `swim`
--

INSERT INTO `swim` (`ID`, `Name`, `Brand`, `Price`, `Image1`, `Image2`, `Image3`, `Image4`, `Quantity`, `Discount`, `Size`, `Description`, `Type`, `UsedIn`) VALUES
('Sw_1', 'Training Fins', 'Nivia', 15000, 'Images/swim/TrainingFins/1.jpg', 'Images/swim/TrainingFins/2.jpg', 'Images/swim/TrainingFins/3.jpg', 'Images/swim/TrainingFins/4.jpg', 100, 0, '40', 'Swim Fins Description, begin!!', 'Male', 'Swimming');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Email` varchar(100) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Password` varchar(235) NOT NULL,
  `PhoneNumber` varchar(10) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Email`, `FirstName`, `LastName`, `Password`, `PhoneNumber`, `Gender`, `Address`, `Image`) VALUES
('dsarthak12345@gmail.com', 'Sarthak', 'Dixit', '$2y$10$lktanDpnxgc6J5RjXRBLXOLsHv7uwk26Smtbk3DIMkbUFUDqM/TYi', '7992172348', 'male', 'asd/asd/asd', 'Images/171500293.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `boots`
--
ALTER TABLE `boots`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `formal`
--
ALTER TABLE `formal`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `loafers`
--
ALTER TABLE `loafers`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sandal`
--
ALTER TABLE `sandal`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `skates`
--
ALTER TABLE `skates`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sleeper`
--
ALTER TABLE `sleeper`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sneakers`
--
ALTER TABLE `sneakers`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sports`
--
ALTER TABLE `sports`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `swim`
--
ALTER TABLE `swim`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
