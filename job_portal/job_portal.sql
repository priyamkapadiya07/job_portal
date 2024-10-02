-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 30, 2024 at 04:25 PM
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
-- Database: `job_portal`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddJob` (IN `t_id` INT(11), IN `t_compony_name` VARCHAR(50), IN `t_role` VARCHAR(20), IN `t_salary` VARCHAR(20), IN `t_location` VARCHAR(20), IN `t_vacancy` INT, IN `t_status` VARCHAR(20), IN `t_password` VARCHAR(20))   BEGIN
	INSERT INTO jobs VALUES(t_id,t_compony_name,t_role,t_salary,t_location,t_vacancy,t_status,t_password);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `AddUser` (IN `t_id` INT, IN `t_name` VARCHAR(50), IN `t_e_mail` VARCHAR(30), IN `t_education` VARCHAR(30), IN `t_preferences` VARCHAR(20), IN `t_experience` VARCHAR(20), IN `t_contact_no` VARCHAR(20), IN `t_password` VARCHAR(20))   BEGIN
  INSERT INTO user values(t_id,t_name,t_e_mail,t_education,t_preferences,t_experience,t_contact_no,t_password);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `employemant`
--

CREATE TABLE `employemant` (
  `Job_ID` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employemant`
--

INSERT INTO `employemant` (`Job_ID`, `candidate_id`, `date`) VALUES
(10, 50, '2024-08-28'),
(3, 51, '2024-08-30'),
(11, 4, '2024-08-30');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `id` int(11) NOT NULL,
  `compony_name` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  `salary` varchar(20) NOT NULL,
  `location` varchar(50) NOT NULL,
  `vacancy` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id`, `compony_name`, `role`, `salary`, `location`, `vacancy`, `status`, `password`) VALUES
(1, 'Walls, James and Nelson', 'Content Writer', '12 LPA', 'Ahmedabad', 8, 'Available', 'walls,@1594'),
(2, 'Parker Group', 'Business Analyst', '8 LPA', 'Kolkata', 4, 'Available', 'parker@7804'),
(3, 'Davis-Adams', 'UI/UX Designer', '8 LPA', 'Gurgaon', 5, 'Available', 'davis-adams@7853'),
(4, 'Cohen-Nelson', 'Business Analyst', '11 LPA', 'Noida', 9, 'Available', 'cohen-nelson@6544'),
(5, 'Ellis-Brown', 'Content Writer', '6 LPA', 'Noida', 10, 'Available', 'ellis-brown@4983'),
(6, 'Collins, Drake and Garcia', 'Network Engineer', '8 LPA', 'Pune', 5, 'Available', 'collins,@4618'),
(7, 'Lewis, Harris and Fernandez', 'Content Writer', '9 LPA', 'Ahmedabad', 8, 'Available', 'lewis,@8040'),
(8, 'Rodriguez PLC', 'HR Specialist', '10 LPA', 'Delhi', 5, 'Available', 'rodriguez@4245'),
(9, 'Rose, Wiley and Orr', 'UI/UX Designer', '15 LPA', 'Delhi', 8, 'Available', 'rose,@1601'),
(10, 'Lewis, Olson and Williams', 'Software Engineer', '8 LPA', 'Pune', 5, 'Available', 'lewis,@1654'),
(11, 'google', 'finence', '5 LPA', 'new york', 1, 'Available', 'g1');

--
-- Triggers `jobs`
--
DELIMITER $$
CREATE TRIGGER `Update_Availability` BEFORE UPDATE ON `jobs` FOR EACH ROW BEGIN 
	IF NEW.vacancy=0 THEN
    UPDATE jobs SET status='Unavailable';
    end if;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `job_request`
--

CREATE TABLE `job_request` (
  `requester_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `Status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `job_request`
--

INSERT INTO `job_request` (`requester_id`, `job_id`, `Status`) VALUES
(50, 10, 'Selected'),
(51, 3, 'Selected'),
(4, 11, 'Selected');

--
-- Triggers `job_request`
--
DELIMITER $$
CREATE TRIGGER `UserSelected` AFTER UPDATE ON `job_request` FOR EACH ROW BEGIN 
	IF NEW.status<>OLD.status then
    INSERT INTO employemant (job_id,candidate_id,date) 
    VALUES (NEW.job_id,NEW.requester_id,NOW());
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `e_mail` varchar(30) NOT NULL,
  `education` varchar(50) NOT NULL,
  `preferences` varchar(20) NOT NULL,
  `experience` varchar(20) NOT NULL,
  `contact_no` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `e_mail`, `education`, `preferences`, `experience`, `contact_no`, `password`) VALUES
(1, 'Mr. Matthew Estrada', 'stanleyrandy@hotmail.com', 'Middel school', 'REMOTE WORK', '9 Years', '+91 7865756964', 'mr.@2535'),
(2, 'priyam', 'priyam@gmail.com', 'Bachelor\'s degree in CE', 'office work', 'No Experience', '9687232448', '123'),
(3, 'uday', 'udaay@gmail.com', 'Master\'s degree in CE', 'flexible', 'No Experience', '1234567898', '12345'),
(4, 'shubham', '123@gmail.com', 'Master\'s degree in Finence', 'office work', '2 years', '9457248520', 'shubham');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
