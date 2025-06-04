-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 31, 2024 at 05:23 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `railway`
--

-- --------------------------------------------------------

--
-- Table structure for table `cancelbooking`
--

CREATE TABLE `cancelbooking` (
  `Sr No.` int(11) NOT NULL,
  `BookingId` int(11) NOT NULL,
  `LoginId` int(11) NOT NULL,
  `TrainId` int(11) NOT NULL,
  `TrainName` varchar(50) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `seatNo` varchar(4) NOT NULL,
  `Source` varchar(20) NOT NULL,
  `Destination` varchar(20) NOT NULL,
  `Departure_Time` time NOT NULL,
  `Arrival_Time` time NOT NULL,
  `Date_Of_Journey` date NOT NULL,
  `Refund_Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cancelbooking`
--

INSERT INTO `cancelbooking` (`Sr No.`, `BookingId`, `LoginId`, `TrainId`, `TrainName`, `FullName`, `Age`, `Gender`, `seatNo`, `Source`, `Destination`, `Departure_Time`, `Arrival_Time`, `Date_Of_Journey`, `Refund_Price`) VALUES
(1, 1, 1, 6, 'ADI VANDE BHARAT (22926)', 'K N S', 19, 'MALE', 'MB08', 'DWARKA', 'AHMEDABAD', '04:10:01', '10:10:29', '2024-09-01', 272.5),
(2, 2, 2, 13, 'VANDE BHARAT EXP (20902)', 'S K N', 19, 'Male', 'UB03', 'AHMEDABAD', 'SURAT', '15:00:20', '17:10:20', '2024-02-01', 347.5);

-- --------------------------------------------------------

--
-- Table structure for table `passengerbooking`
--

CREATE TABLE `passengerbooking` (
  `BookingId` int(11) NOT NULL,
  `LoginId` int(11) NOT NULL,
  `TrainId` int(11) NOT NULL,
  `TrainName` varchar(50) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `seatNo` varchar(4) NOT NULL,
  `Source` varchar(20) NOT NULL,
  `Destination` varchar(20) NOT NULL,
  `Departure_Time` time NOT NULL,
  `Arrival_Time` time NOT NULL,
  `Date_Of_Journey` date NOT NULL,
  `Ticket_Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `passengerbooking`
--

INSERT INTO `passengerbooking` (`BookingId`, `LoginId`, `TrainId`, `TrainName`, `FullName`, `Age`, `Gender`, `seatNo`, `Source`, `Destination`, `Departure_Time`, `Arrival_Time`, `Date_Of_Journey`, `Ticket_Price`) VALUES
(3, 3, 1, 'SAURASHTRA JANTA EXP (19217)', 'L N S', 51, 'Female', 'UB03', 'AHMEDABAD', 'JUNAGADH', '22:20:08', '04:38:04', '2024-02-01', 865),
(4, 4, 13, 'VANDE BHARAT EXP (20902)', 'L O P', 12, 'Male', 'UB06', 'AHMEDABAD', 'SURAT', '15:00:20', '17:10:20', '2024-02-01', 1390);

-- --------------------------------------------------------

--
-- Table structure for table `passengerdetails`
--

CREATE TABLE `passengerdetails` (
  `LoginId` int(11) NOT NULL,
  `BookingId` int(11) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `MobileNo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `passengerdetails`
--

INSERT INTO `passengerdetails` (`LoginId`, `BookingId`, `FullName`, `Age`, `Gender`, `MobileNo`) VALUES
(3, 3, 'L N S', 51, 'Female', '1234567890'),
(4, 4, 'L O P', 12, 'Male', '1234567890');

-- --------------------------------------------------------

--
-- Table structure for table `rbs`
--

CREATE TABLE `rbs` (
  `LoginId` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rbs`
--

INSERT INTO `rbs` (`LoginId`, `Username`, `Password`) VALUES
(1, 'khush123', '12345678'),
(2, 'kns123456', '12345678'),
(3, 'KSN7890', '96385274'),
(4, '1234567', '12345678');

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE `routes` (
  `trainId` int(11) NOT NULL,
  `TrainName` varchar(200) NOT NULL,
  `Source` varchar(20) NOT NULL,
  `Destination` varchar(20) NOT NULL,
  `TicketPrice` double NOT NULL,
  `TrainSourceTime` time NOT NULL,
  `TrainDestinationTime` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`trainId`, `TrainName`, `Source`, `Destination`, `TicketPrice`, `TrainSourceTime`, `TrainDestinationTime`) VALUES
(1, 'SAURASHTRA JANTA EXP (19217)', 'AHMEDABAD', 'JUNAGADH', 865, '22:20:08', '04:38:04'),
(2, 'SMNH JBP EXP (11463)', 'JUNAGADH', 'AHMEDABAD', 865, '11:22:44', '18:25:49'),
(3, 'JAM HUMSAFAR (22923)', 'AHMEDABAD', 'RAJKOT', 710, '08:05:57', '12:02:57'),
(4, 'RJT CBE EXP (16613)', 'RAJKOT', 'AHMEDABAD', 710, '21:20:30', '01:05:56'),
(5, 'OKHA VANDE B (22925)', 'AHMEDABAD', 'DWARKA', 1090, '18:10:35', '23:54:39'),
(6, 'ADI VANDE BHARAT (22926)', 'DWARKA', 'AHMEDABAD', 1090, '04:10:01', '10:10:29'),
(7, 'JBP SOMNATH EXP (11464)', 'AHMEDABAD', 'VERAVAL', 1020, '08:20:14', '17:25:15'),
(8, 'SMNH JBP EXP (11463)', 'VERAVAL', 'AHMEDABAD', 1020, '10:05:18', '18:25:19'),
(9, 'BDTS BVC SF EXP (12971)', 'AHMEDABAD', 'BHAVNAGAR', 760, '02:50:55', '07:32:55'),
(10, 'BVC BDTS SF EXP (12972)', 'BHAVNAGAR', 'AHMEDABAD', 760, '18:39:38', '23:35:38'),
(11, 'BSBS OKHA SF EXP (22970)', 'AHMEDABAD', 'JAMNAGAR', 845, '22:45:20', '04:15:20'),
(12, 'OKHA SHM SF EXP (22905)', 'JAMNAGAR', 'AHMEDABAD', 845, '11:17:06', '17:30:00'),
(13, 'VANDE BHARAT EXP (20902)', 'AHMEDABAD', 'SURAT', 1390, '15:00:20', '17:10:20'),
(14, 'ADI DURONTO (12298)', 'SURAT', 'AHMEDABAD', 1390, '03:10:08', '06:25:12'),
(15, 'KUTCH EXPRESS (22955)', 'AHMEDABAD', 'BHUJ', 945, '01:45:34', '08:30:34'),
(16, 'SAYAJINAGRI EXP (20908)', 'BHUJ', 'AHMEDABAD', 945, '22:35:36', '05:10:39'),
(17, 'ADI KOP EXP (11049)', 'AHMEDABAD', 'NAVSARI', 710, '20:15:09', '02:32:15'),
(18, 'BL VADNAGAR SUP (20959)', 'NAVSARI', 'AHMEDABAD', 710, '06:10:25', '10:10:35');

-- --------------------------------------------------------

--
-- Table structure for table `seatbooking`
--

CREATE TABLE `seatbooking` (
  `BookingId` int(11) NOT NULL,
  `LoginId` int(11) NOT NULL,
  `TrainId` int(11) NOT NULL,
  `TrainName` varchar(50) NOT NULL,
  `seatNo` varchar(4) NOT NULL,
  `Source` varchar(20) NOT NULL,
  `Destination` varchar(20) NOT NULL,
  `DepartureTime` time NOT NULL,
  `ArrivalTime` time NOT NULL,
  `date` date NOT NULL,
  `TicketPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seatbooking`
--

INSERT INTO `seatbooking` (`BookingId`, `LoginId`, `TrainId`, `TrainName`, `seatNo`, `Source`, `Destination`, `DepartureTime`, `ArrivalTime`, `date`, `TicketPrice`) VALUES
(3, 3, 1, 'SAURASHTRA JANTA EXP (19217)', 'UB03', 'AHMEDABAD', 'JUNAGADH', '22:20:08', '04:38:04', '2024-02-01', 865),
(4, 4, 13, 'VANDE BHARAT EXP (20902)', 'UB06', 'AHMEDABAD', 'SURAT', '15:00:20', '17:10:20', '2024-02-01', 1390);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cancelbooking`
--
ALTER TABLE `cancelbooking`
  ADD PRIMARY KEY (`Sr No.`);

--
-- Indexes for table `passengerbooking`
--
ALTER TABLE `passengerbooking`
  ADD PRIMARY KEY (`BookingId`);

--
-- Indexes for table `passengerdetails`
--
ALTER TABLE `passengerdetails`
  ADD PRIMARY KEY (`BookingId`);

--
-- Indexes for table `rbs`
--
ALTER TABLE `rbs`
  ADD PRIMARY KEY (`LoginId`);

--
-- Indexes for table `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`trainId`);

--
-- Indexes for table `seatbooking`
--
ALTER TABLE `seatbooking`
  ADD PRIMARY KEY (`BookingId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cancelbooking`
--
ALTER TABLE `cancelbooking`
  MODIFY `Sr No.` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `passengerdetails`
--
ALTER TABLE `passengerdetails`
  MODIFY `BookingId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `rbs`
--
ALTER TABLE `rbs`
  MODIFY `LoginId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `routes`
--
ALTER TABLE `routes`
  MODIFY `trainId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `seatbooking`
--
ALTER TABLE `seatbooking`
  MODIFY `BookingId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
