-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20221117.561d9ca705
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2023 at 07:02 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paramedic`
--

-- --------------------------------------------------------

--
-- Table structure for table `ambulance`
--

CREATE TABLE `ambulance` (
  `ambulance_id` int(11) NOT NULL,
  `userlogin_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ambulance`
--

INSERT INTO `ambulance` (`ambulance_id`, `userlogin_id`) VALUES
(1, 2),
(2, 15);

-- --------------------------------------------------------

--
-- Table structure for table `disease_states`
--

CREATE TABLE `disease_states` (
  `disease_states_id` int(11) NOT NULL,
  `disease_states_name` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `disease_states`
--

INSERT INTO `disease_states` (`disease_states_id`, `disease_states_name`) VALUES
(2, 'headache'),
(3, 'chronic disease'),
(4, 'Stroke');

-- --------------------------------------------------------

--
-- Table structure for table `paramedic`
--


--
-- Dumping data for table `paramedic`
--

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL,
  `userlogin_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_id`, `userlogin_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `request_id` int(11) NOT NULL,
  `request_status` varchar(30) DEFAULT NULL,
  `patient_longitude` varchar(20) DEFAULT NULL,
  `patient_latitude` varchar(20) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `paramedic_id` int(11) DEFAULT NULL,
  `disease_state_id` int(11) DEFAULT NULL,
  `request_described` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`request_id`, `request_status`, `patient_longitude`, `patient_latitude`, `patient_id`, `paramedic_id`, `disease_state_id`, `request_described`) VALUES
(157, 'success', '21.1351783', '26.0859633', 1, 4, 2, 'good'),
(164, 'success', '21.1351783', '26.0859633', 1, 1, 4, 'good');

-- --------------------------------------------------------

--
-- Table structure for table `userlogin`
--

CREATE TABLE `userlogin` (
  `userlogin_id` int(11) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `full_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `role_name` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userlogin`
--

--
-- Triggers `userlogin`
--
DELIMITER $$
CREATE TRIGGER `Insert_Id_By_RoleName` AFTER INSERT ON `userlogin` FOR EACH ROW BEGIN
        IF new.role_name = 'Patient' THEN
          insert into patient(patient.patient_id,patient.userlogin_id)VALUES(DEFAULT,new.userlogin_id);
        ELSEIF new.role_name='Paramedic' THEN
            insert into paramedic(paramedic.paramedic_id,paramedic.userlogin_id)VALUES(DEFAULT,new.userlogin_id);
        ELSEIF new.role_name='Ambulance' THEN
            insert into ambulance(ambulance.ambulance_id,ambulance.userlogin_id)VALUES(DEFAULT,new.userlogin_id);
           END IF;
       END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ambulance`
--
ALTER TABLE `ambulance`
  ADD PRIMARY KEY (`ambulance_id`),
  ADD KEY `ambulance_fk1` (`userlogin_id`);

--
-- Indexes for table `disease_states`
--
ALTER TABLE `disease_states`
  ADD PRIMARY KEY (`disease_states_id`);

--
-- Indexes for table `paramedic`
--
ALTER TABLE `paramedic`
  ADD PRIMARY KEY (`paramedic_id`),
  ADD KEY `paramedic_fk1` (`userlogin_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patient_id`),
  ADD KEY `patient_fk1` (`userlogin_id`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `request_fk2` (`paramedic_id`),
  ADD KEY `request_fk3` (`patient_id`),
  ADD KEY `request_fk1` (`disease_state_id`);

--
-- Indexes for table `userlogin`
--
ALTER TABLE `userlogin`
  ADD PRIMARY KEY (`userlogin_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ambulance`
--
ALTER TABLE `ambulance`
  MODIFY `ambulance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `disease_states`
--
ALTER TABLE `disease_states`
  MODIFY `disease_states_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `paramedic`
--
ALTER TABLE `paramedic`
  MODIFY `paramedic_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=165;

--
-- AUTO_INCREMENT for table `userlogin`
--
ALTER TABLE `userlogin`
  MODIFY `userlogin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ambulance`
--
ALTER TABLE `ambulance`
  ADD CONSTRAINT `ambulance_fk1` FOREIGN KEY (`userlogin_id`) REFERENCES `userlogin` (`userlogin_id`) ON DELETE CASCADE;

--
-- Constraints for table `paramedic`
--
ALTER TABLE `paramedic`
  ADD CONSTRAINT `paramedic_fk1` FOREIGN KEY (`userlogin_id`) REFERENCES `userlogin` (`userlogin_id`) ON DELETE CASCADE;

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_fk1` FOREIGN KEY (`userlogin_id`) REFERENCES `userlogin` (`userlogin_id`) ON DELETE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_fk1` FOREIGN KEY (`disease_state_id`) REFERENCES `disease_states` (`disease_states_id`),
  ADD CONSTRAINT `request_fk2` FOREIGN KEY (`paramedic_id`) REFERENCES `paramedic` (`paramedic_id`),
  ADD CONSTRAINT `request_fk3` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
