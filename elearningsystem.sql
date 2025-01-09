-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Nov 29, 2024 at 05:23 PM
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
-- Database: `elearningsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Adminid` int(11) NOT NULL,
  `fname` varchar(150) NOT NULL,
  `lname` varchar(150) DEFAULT NULL,
  `Email_ID` varchar(250) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `picture` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Adminid`, `fname`, `lname`, `Email_ID`, `username`, `password`, `Gender`) VALUES
(1, 'Abdul', 'wahith', 'baasith@email.com', 'admin', 'admin', 'Male');
INSERT INTO `admin` (`Adminid`, `fname`, `lname`, `Email_ID`, `username`, `password`, `Gender`, `picture`) VALUES
(2, 'Icbt', 'Batti', 'bac@email.com', 'batti123', '12345', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `course_ID` int(11) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `Content` varchar(3000) NOT NULL,
  `teacherID` int(11) NOT NULL,
  `Subject_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_ID`, `Name`, `Description`, `Content`, `teacherID`, `Subject_ID`) VALUES
(1, 'Ancient Kingdoms of Sri Lanka', 'An exploration of ancient kingdoms like Anuradhapura and Polonnaruwa.', 'Detailed content on historical sites and ancient cultures.', 1, 1),
(2, 'Mastering Sinhalese Poetry', 'Learn to appreciate and write Sinhalese poetry.', 'Content includes poetic forms and examples of classical Sinhalese literature.', 2, 2),
(3, 'Sri Lanka’s Tea Industry: A Global Perspective', 'Understand the history, growth, and impact of Sri Lanka’s tea industry.', 'Content includes the process of tea production and global trade insights.', 3, 3),
(4, 'Preserving Sri Lankan Wildlife', 'A course focused on conservation efforts and biodiversity in Sri Lanka.', 'Content includes information on Sri Lankan elephants, leopards, and endemic species.', 4, 4),
(5, 'The Practice of Buddhism in Sri Lanka', 'Learn about Buddhism’s roots and influence in Sri Lanka.', 'Content includes ancient practices, temples, and cultural integration.', 5, 5),
(6, 'applied maths', 'advance maths', 'althorithams and diagrams', 1, 1),
(7, 'tamil lit', 'learning tamil lit', '', 7, 9);

-- --------------------------------------------------------

--
-- Table structure for table `enrollments`
--

CREATE TABLE `enrollments` (
  `Enrollment_ID` int(11) NOT NULL,
  `Enrollment_Date` date NOT NULL,
  `course_ID` int(11) NOT NULL,
  `stdID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `enrollments`
--

INSERT INTO `enrollments` (`Enrollment_ID`, `Enrollment_Date`, `course_ID`, `stdID`) VALUES
(1, '2024-01-15', 1, 1),
(2, '2024-01-20', 2, 2),
(3, '2024-01-25', 3, 3),
(4, '2024-02-01', 4, 4),
(5, '2024-02-10', 5, 5),
(6, '2024-11-29', 4, 1),
(7, '2024-11-29', 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `Message_ID` int(11) NOT NULL,
  `message` varchar(2000) NOT NULL,
  `time_Stamp` datetime NOT NULL,
  `User_ID` int(11) NOT NULL,
  `toUser_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`Message_ID`, `message`, `time_Stamp`, `User_ID`, `toUser_ID`) VALUES
(1, 'Can you share the notes for today\'s class?', '2024-01-25 10:30:00', 1, 2),
(2, 'When is the next lecture scheduled?', '2024-01-25 11:00:00', 3, 4),
(3, 'Thank you for the course recommendation!', '2024-01-26 08:45:00', 5, 1),
(4, 'Let’s form a group for the project.', '2024-01-27 14:15:00', 2, 3),
(5, 'I found a great resource for our assignment.', '2024-01-28 16:00:00', 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `messageusers`
--

CREATE TABLE `messageusers` (
  `User_ID` int(11) NOT NULL,
  `Message_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `messageusers`
--

INSERT INTO `messageusers` (`User_ID`, `Message_ID`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `stdID` int(11) NOT NULL,
  `fname` varchar(150) NOT NULL,
  `lname` varchar(150) DEFAULT NULL,
  `Email_ID` varchar(250) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Registration_Date` date NOT NULL,
  `Last_Login` datetime DEFAULT NULL,
  `picture` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`stdID`, `fname`, `lname`, `Email_ID`, `username`, `password`, `Gender`, `Registration_Date`, `Last_Login`) VALUES
(1, 'Ahamed', 'Sajee', 'as@student.com', 'as123', '12345', 'Male', '2024-11-27', '2024-11-29 20:34:09'),
(2, 'Nuwan', 'Perera', 'nuwan.perera@example.com', 'nuwan123', 'password123', 'Male', '2023-11-01', '2023-11-25 14:30:00', NULL),
(3, 'Amara', 'Silva', 'amara.silva@example.com', 'amara_s', 'securepass', 'Female', '2023-11-05', '2023-11-27 09:15:00', NULL),
(4, 'Sanjay', 'Wijesinghe', 'sanjay.w@example.com', 'sanjay_99', 'passw0rd', 'Male', '2023-11-10', '2023-11-28 10:00:00', NULL),
(5, 'Kavindi', 'De Silva', 'kavindi.d@example.com', 'kavindi22', 'mypassword', 'Female', '2023-11-15', '2023-11-28 18:45:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `Subject_ID` int(11) NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Adminid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`Subject_ID`, `Name`, `Adminid`) VALUES
(1, 'maths', 1),
(2, 'History of Sri Lanka', 1),
(3, 'Sinhalese Literature', 1),
(4, 'Ceylon Tea Industry', 1),
(5, 'Sri Lankan Wildlife Conservation', 1),
(6, 'Buddhism and Culture', 1),
(7, 'english', 1),
(9, 'Tamil', 1),
(10, 'Islam', 1);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherID` int(11) NOT NULL,
  `fname` varchar(150) NOT NULL,
  `lname` varchar(150) DEFAULT NULL,
  `Email_ID` varchar(250) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Registration_Date` date NOT NULL,
  `Last_Login` datetime DEFAULT NULL,
  `picture` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacherID`, `fname`, `lname`, `Email_ID`, `username`, `password`, `Gender`, `Registration_Date`, `Last_Login`) VALUES
(2, 'Fathima', 'Shima', 'fs@emai.com', 'fs123', '12345', 'Female', '2024-11-29', '2024-11-29 13:27:05');
INSERT INTO `teacher` (`teacherID`, `fname`, `lname`, `Email_ID`, `username`, `password`, `Gender`, `Registration_Date`, `Last_Login`) VALUES
(3, 'Abdul', 'Lah', 'a@email.com', 'a123', '12345', 'Male', '2024-11-29', NULL);
INSERT INTO `teacher` (`teacherID`, `fname`, `lname`, `Email_ID`, `username`, `password`, `Gender`, `Registration_Date`, `Last_Login`) VALUES
(4, 'Saboor', 'Mohideen', 'sm@email.com', 'sm123', '12345', 'Male', '2024-11-29', NULL);
INSERT INTO `teacher` (`teacherID`, `fname`, `lname`, `Email_ID`, `username`, `password`, `Gender`, `Registration_Date`, `Last_Login`) VALUES
(5, 'Fathima', 'Rejina', 'fr@email.com', 'fr123', '12345', 'Male', '2024-11-29', NULL);
INSERT INTO `teacher` (`teacherID`, `fname`, `lname`, `Email_ID`, `username`, `password`, `Gender`, `Registration_Date`, `Last_Login`) VALUES
(7, 'Kalai', 'Nila', 'nila@email.com', 'nila123', '12345', 'Female', '2024-11-29', '2024-11-29 20:36:52');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Adminid`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`course_ID`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD KEY `teacherID_fk` (`teacherID`),
  ADD KEY `subjectID_fk` (`Subject_ID`);

--
-- Indexes for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD PRIMARY KEY (`Enrollment_ID`),
  ADD UNIQUE KEY `unique_Enrollment` (`course_ID`,`stdID`),
  ADD KEY `stdID_fk` (`stdID`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`Message_ID`);

--
-- Indexes for table `messageusers`
--
ALTER TABLE `messageusers`
  ADD KEY `message_ID_fk` (`Message_ID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`stdID`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`Subject_ID`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD KEY `Adminid_fk` (`Adminid`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherID`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `Adminid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `course_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `enrollments`
--
ALTER TABLE `enrollments`
  MODIFY `Enrollment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `Message_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `stdID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `Subject_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `subjectID_fk` FOREIGN KEY (`Subject_ID`) REFERENCES `subjects` (`Subject_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `teacherID_fk` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`teacherID`) ON DELETE CASCADE;

--
-- Constraints for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD CONSTRAINT `courseID_fk` FOREIGN KEY (`course_ID`) REFERENCES `courses` (`course_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `stdID_fk` FOREIGN KEY (`stdID`) REFERENCES `student` (`stdID`) ON DELETE CASCADE;

--
-- Constraints for table `messageusers`
--
ALTER TABLE `messageusers`
  ADD CONSTRAINT `message_ID_fk` FOREIGN KEY (`Message_ID`) REFERENCES `messages` (`Message_ID`) ON DELETE CASCADE;

--
-- Constraints for table `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `Adminid_fk` FOREIGN KEY (`Adminid`) REFERENCES `admin` (`Adminid`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
