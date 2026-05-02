-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2024 at 09:25 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `laundry_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblcustomer`
--

CREATE TABLE `tblcustomer` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `customer_address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phno` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblcustomer`
--

INSERT INTO `tblcustomer` (`customer_id`, `customer_name`, `customer_address`, `email`, `phno`, `password`, `role_id`) VALUES
(34, 'pk', 'surat', 'pk@gmail.com', '8989988989', 'PBKDF2WithHmacSHA256:2048:dMl4HOh3L9qJG8z57eTaQr4mxT3oyUXC6fvvVjj62Vg=:4ryO9HD76M7hxgKBre2ID2MYRHZiGBMlb7qZ6yvb2Qo=', 1),
(35, 'pk', 'surat', 'pk@gmail.com', '8989988989', 'PBKDF2WithHmacSHA256:2048:KYY9jh9lSLCTy8r+dmuZgJvoISuOTEu5Rft3V6xAgds=:TxjmaS8RX614H3CrLJR1oBUPk7aesEW5Vni1vsT3+6c=', 1),
(36, 'null', 'null', 'null', 'null', 'PBKDF2WithHmacSHA256:2048:UGmR+KMjqzg3Gp4Awc8YtboIloMIo69D2168cDOL4As=:FQWoi3hodRszQ22kjWbtjnn3QefsZL1AYxB1tHozCZs=', 1),
(38, 'kp', 'Bardoli', 'kp@gmail.com', '8989898889', 'PBKDF2WithHmacSHA256:2048:aj6TBoplDhKBpaFfjAMmWhX5ZYTFrVpNvAgMbtEPVz0=:YfhXtF0tH7nNNIe11rQuPglZWeq1Piuy4oQa1v7boJg=', 1),
(39, 'xxx', 'aa', 'xxx@gmail.com', '45454545454', 'PBKDF2WithHmacSHA256:2048:UCwNG9ZWc6ttn3uhiO5gZbVhhlBquaj+MC4NRTc5A4Y=:ZREAuqJrFGUHPt6dPP7+v1kkKH5KfBowDvvxemhNxLc=', 2),
(40, 'yagnik', 'vyara', 'yagnik@mail.com', '45452', 'PBKDF2WithHmacSHA256:2048:HLtPNNo5rSG+TmnJTAyMKXXl5Mn853I2CQojKv9IKNc=:hBjKjMmYcbdczV5sgIuB2QQPtLvn2uKA1DVm1IPVY54=', 1),
(43, 'mmm', 'mm', 'mmm', '787878', 'PBKDF2WithHmacSHA256:2048:VNaXDFEOgvsWOkn1lM7fjRbBj0OEnp7JjBXO+LyiL8M=:GBVTNf+UvG2PSjc1kY2OR6kSgI4tfrSW7fB93+6kGLI=', 1),
(44, 'Admin', 'Surat', 'admin@gmail.com', '7878788787878', 'PBKDF2WithHmacSHA256:2048:vRXRNNCoCBONm8eZ5kmhiDkszBosXKtVKrERtpBt1hE=:+S3X3lnhLGwUGtyowQakY5AloojxN580ulRjEcCrdZo=', 2),
(45, 'Pratik', 'Surat', 'pratik@gmail.com', '89898989898', 'PBKDF2WithHmacSHA256:2048:IjE8Re9oTlqlR1xNEVhZlPOaHVVELxmAxE0cQZ7rcpg=:fdfeMp3gcEVUd1GavjwNwlr4OAqj9z7howC+S5izutk=', 1),
(47, 'Rana', 'vyara', 'rana11@gmail.com', '04545454454', 'PBKDF2WithHmacSHA256:2048:wEVZEneMliHL6heTLcOxtT7GtfeQaAXARYlaS+xG5Pc=:EI0HroLF06v63fUApJWk2hhDu04X6KSkdAE586VYV20=', 1),
(48, 'pkk', 'vyara', 'pkrana020803@gmail.com', '04545454454', 'PBKDF2WithHmacSHA256:2048:h9nkDWukVgBxzlMhHgl8hdWcCfWFq+o9QbsUwdWSPp0=:2HfgEVQhrsvuRg6CGHKKhhhRq8YEqKIC7Cb9Md0mbhE=', 2),
(49, 'hari', 'vyara', 'unknowndevloper77@gmail.com', '04545454454', 'PBKDF2WithHmacSHA256:2048:SNY+NdINtuZz0KJXhOZmvuUWqEHt6ZjN94nZl8zaEMo=:f5Ye5hPSiN3qoasTpWooWdN8KRDmVJtB9+MQ3nsFtEc=', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tblemployee`
--

CREATE TABLE `tblemployee` (
  `emp_id` int(11) NOT NULL,
  `emp_name` varchar(255) NOT NULL,
  `services_id` int(11) NOT NULL,
  `salary` int(11) NOT NULL,
  `emp_address` varchar(255) NOT NULL,
  `emp_phono` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblemployee`
--

INSERT INTO `tblemployee` (`emp_id`, `emp_name`, `services_id`, `salary`, `emp_address`, `emp_phono`) VALUES
(2, 'Prakash', 3, 4000, '', ''),
(3, 'Raj', 2, 3000, '', ''),
(4, 'Krutika', 1, 7000, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `tblorder`
--

CREATE TABLE `tblorder` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `order_date` datetime NOT NULL,
  `pickup_date` datetime NOT NULL,
  `delivery_date` datetime NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblorder`
--

INSERT INTO `tblorder` (`order_id`, `customer_id`, `order_date`, `pickup_date`, `delivery_date`, `status`) VALUES
(154, 47, '2024-12-19 23:55:26', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(155, 47, '2024-12-20 01:07:54', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(156, 47, '2024-12-20 02:04:07', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(157, 47, '2024-12-20 02:12:58', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(158, 47, '2024-12-20 02:33:20', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(159, 47, '2024-12-20 02:36:56', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(160, 47, '2024-12-20 02:37:51', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(161, 47, '2024-12-20 02:40:02', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(162, 47, '2024-12-20 02:42:55', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(163, 47, '2024-12-20 02:43:19', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(164, 47, '2024-12-20 02:45:16', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(165, 47, '2024-12-20 02:46:19', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(166, 47, '2024-12-20 02:56:31', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(167, 47, '2024-12-20 03:09:34', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(168, 47, '2024-12-20 03:14:33', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(169, 47, '2024-12-20 03:18:46', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(170, 47, '2024-12-20 03:20:57', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(178, 45, '2024-12-20 03:38:34', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(179, 45, '2024-12-20 03:42:10', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(180, 47, '2024-12-20 03:43:25', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(181, 47, '2024-12-20 03:45:25', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(182, 48, '2024-12-20 12:12:40', '2024-12-12 00:00:00', '2024-12-12 00:00:00', 'Not'),
(194, 45, '2024-12-20 19:17:44', '2024-07-07 00:00:00', '2024-07-07 00:00:00', 'Not');

-- --------------------------------------------------------

--
-- Table structure for table `tblorderitem`
--

CREATE TABLE `tblorderitem` (
  `order_item_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `material` varchar(255) NOT NULL,
  `qty` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblorderitem`
--

INSERT INTO `tblorderitem` (`order_item_id`, `service_id`, `order_id`, `material`, `qty`, `photo`) VALUES
(31, 2, 155, 'Febric', 1, 'http://res.cloudinary.com/dkueai729/image/upload/v1734637090/Screenshot__136__png_1734637087617.jpg.png'),
(32, 2, 156, 'Febric', 2, 'http://res.cloudinary.com/dkueai729/image/upload/v1734640465/Screenshot__136__png_1734640463320.jpg.png'),
(43, 3, 194, 'Nilon', 1, 'http://res.cloudinary.com/dkueai729/image/upload/v1734702483/Screenshot__129__png_1734702480308.jpg.png');

-- --------------------------------------------------------

--
-- Table structure for table `tblpayment`
--

CREATE TABLE `tblpayment` (
  `pay_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `method` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tblrole`
--

CREATE TABLE `tblrole` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblrole`
--

INSERT INTO `tblrole` (`role_id`, `role_name`) VALUES
(1, 'user'),
(2, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `tblservice`
--

CREATE TABLE `tblservice` (
  `services_id` int(11) NOT NULL,
  `service_type` varchar(255) NOT NULL,
  `charge` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblservice`
--

INSERT INTO `tblservice` (`services_id`, `service_type`, `charge`) VALUES
(1, 'Dry_Clining', 500),
(2, 'Iron', 100),
(3, 'Washing', 250),
(4, 'Folding', 500);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblcustomer`
--
ALTER TABLE `tblcustomer`
  ADD PRIMARY KEY (`customer_id`),
  ADD KEY `tblcustomer_ibfk_1` (`role_id`);

--
-- Indexes for table `tblemployee`
--
ALTER TABLE `tblemployee`
  ADD PRIMARY KEY (`emp_id`),
  ADD KEY `services_id` (`services_id`);

--
-- Indexes for table `tblorder`
--
ALTER TABLE `tblorder`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `tblorderitem`
--
ALTER TABLE `tblorderitem`
  ADD PRIMARY KEY (`order_item_id`),
  ADD KEY `service_id` (`service_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `tblpayment`
--
ALTER TABLE `tblpayment`
  ADD PRIMARY KEY (`pay_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `tblrole`
--
ALTER TABLE `tblrole`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `tblservice`
--
ALTER TABLE `tblservice`
  ADD PRIMARY KEY (`services_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblcustomer`
--
ALTER TABLE `tblcustomer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `tblemployee`
--
ALTER TABLE `tblemployee`
  MODIFY `emp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tblorder`
--
ALTER TABLE `tblorder`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=198;

--
-- AUTO_INCREMENT for table `tblorderitem`
--
ALTER TABLE `tblorderitem`
  MODIFY `order_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `tblpayment`
--
ALTER TABLE `tblpayment`
  MODIFY `pay_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tblrole`
--
ALTER TABLE `tblrole`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tblservice`
--
ALTER TABLE `tblservice`
  MODIFY `services_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblcustomer`
--
ALTER TABLE `tblcustomer`
  ADD CONSTRAINT `tblcustomer_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tblrole` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tblemployee`
--
ALTER TABLE `tblemployee`
  ADD CONSTRAINT `tblemployee_ibfk_1` FOREIGN KEY (`services_id`) REFERENCES `tblservice` (`services_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tblorder`
--
ALTER TABLE `tblorder`
  ADD CONSTRAINT `tblorder_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `tblcustomer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tblorderitem`
--
ALTER TABLE `tblorderitem`
  ADD CONSTRAINT `tblorderitem_ibfk_3` FOREIGN KEY (`service_id`) REFERENCES `tblservice` (`services_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tblorderitem_ibfk_4` FOREIGN KEY (`order_id`) REFERENCES `tblorder` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tblpayment`
--
ALTER TABLE `tblpayment`
  ADD CONSTRAINT `tblpayment_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `tblcustomer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tblpayment_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `tblorder` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
