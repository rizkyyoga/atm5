-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 29, 2019 at 01:14 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `app`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_number` varchar(6) NOT NULL,
  `balance` double DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `pin` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_number`, `balance`, `name`, `pin`) VALUES
('000001', 1000, 'Rizky', '000001'),
('000002', 1500, 'Yoga', '000002'),
('000003', 1000, 'Oktora', '000003'),
('000004', 1000, 'Jean', '000004'),
('000005', 1000, 'Bruce', '000005'),
('000006', 1000, 'Diana', '000006'),
('000007', 1000, 'Clark', '000007'),
('000008', 1000, 'Hal', '000008'),
('000009', 1000, 'Tiara', '000009'),
('000010', 1000, 'Tony', '000010'),
('000011', 1000, 'Steve', '000011'),
('000012', 1000, 'Natasha', '000012'),
('000013', 1000, 'Mike', '000013'),
('000014', 1000, 'Angel', '000014'),
('000015', 1000, 'Leo', '000015'),
('000016', 1000, 'Buble', '000016'),
('000017', 1000, 'Risa', '000017'),
('000018', 1000, 'Dola', '000018'),
('000019', 1000, 'Cara Casey', '000019'),
('000020', 1000, 'Waleed Grant', '000020'),
('000021', 1000, 'Agnes Mclaughlin', '000021'),
('000022', 1000, 'Jasmin Castaneda', '000022'),
('000023', 1000, 'Mary Wise', '000023'),
('000024', 1000, 'Borys Peel', '000024'),
('000025', 1000, 'Kiya Millar', '000025'),
('000026', 1000, 'Willow Whyte', '000026'),
('000027', 1000, 'Jareth Hunt', '000027'),
('000028', 1000, 'Alba Curran', '000028'),
('000029', 1000, 'Arham Woods', '000029'),
('000030', 1000, 'Youssef Flowers', '000030'),
('000031', 1000, 'Maia Forbes', '000031'),
('000032', 1000, 'Patrycja Carson', '000032'),
('000033', 1000, 'Colby Jarvis', '000033'),
('000034', 1000, 'Dayna Owen', '000034'),
('000035', 1000, 'Allen Wall', '000035'),
('000036', 1000, 'Linzi Hatfield', '000036'),
('000037', 1000, 'Ava-Rose Duggan', '000037'),
('000038', 1000, 'Jenna Orr', '000038'),
('000039', 1000, 'Billy-Joe Camacho', '000039'),
('000040', 1000, 'Robbie Pitts', '000040'),
('000041', 1000, 'Federico Seymour', '000041'),
('000042', 1000, 'Roxie Levine', '000042'),
('000043', 1000, 'Shae Mcfarland', '000043'),
('000044', 1000, 'Abby Foley', '000044'),
('000045', 1000, 'Brady Sykes', '000045'),
('000046', 1000, 'Karis Malone', '000046'),
('000047', 1000, 'Bartlomiej Berger', '000047'),
('000048', 1000, 'Chelsea Sawyer', '000048'),
('000049', 1000, 'Sam Nguyen', '000049'),
('000050', 1000, 'Georgina Vu', '000050'),
('000051', 1000, 'Tracey Vaughan', '000051'),
('000052', 1000, 'Nahla Clark', '000052'),
('000053', 1000, 'Ayana Holcomb', '000053'),
('000054', 1000, 'Bobbi Cobb', '000054'),
('000055', 1000, 'Casper Nichols', '000055'),
('000056', 1000, 'Paige Wilde', '000056'),
('000057', 1000, 'Kaan Frey', '000057'),
('000058', 1000, 'Connor Fountain', '000058'),
('000059', 1000, 'Indigo Tillman', '000059'),
('000060', 1000, 'Cathy Elliott', '000060'),
('000061', 1000, 'Bobbie Fowler', '000061'),
('000062', 1000, 'Katya Vasquez', '000062'),
('000063', 1000, 'Aida Walmsley', '000063'),
('000064', 1000, 'Finnian Higgs', '000064'),
('000065', 1000, 'Eleni Reynolds', '000065'),
('000066', 1000, 'Dwayne Jordan', '000066'),
('000067', 1000, 'Hashim Pruitt', '000067'),
('000068', 1000, 'Olli Delacruz', '000068'),
('000069', 1000, 'Havin Simmonds', '000069'),
('000070', 1000, 'Jocelyn Cochran', '000070'),
('000071', 1000, 'Merryn Sexton', '000071'),
('000072', 1000, 'Eshaal Bateman', '000072'),
('000073', 1000, 'Leticia Giles', '000073'),
('000074', 1000, 'Gordon Deacon', '000074'),
('000075', 1000, 'Amelia-Mae Handley', '000075'),
('000076', 1000, 'Kaycee Whitney', '000076'),
('000077', 1000, 'Beverley Sears', '000077'),
('000078', 1000, 'Safwan Griffith', '000078'),
('000079', 1000, 'Terence Buck', '000079'),
('000080', 1000, 'Oisin Cantu', '000080'),
('000081', 1000, 'Callan Snyder', '000081'),
('000082', 1000, 'Robin Henderson', '000082'),
('000083', 1000, 'Kester Bernard', '000083'),
('000084', 1000, 'Brandon-Lee Espinoza', '000084'),
('000085', 1000, 'Amina Munoz', '000085'),
('000086', 1000, 'Greyson Emerson', '000086'),
('000087', 1000, 'Hester Ferguson', '000087'),
('000088', 1000, 'Mia-Rose Bowler', '000088'),
('000089', 1000, 'Brianna Collier', '000089'),
('000090', 1000, 'Elsie-Mae Jeffery', '000090'),
('000091', 1000, 'Josiah Traynor', '000091'),
('000092', 1000, 'Debra Reeve', '000092'),
('000093', 1000, 'Sabah Daly', '000093'),
('000094', 1000, 'Shyam Craft', '000094'),
('000095', 1000, 'Jawad Carroll', '000095'),
('000096', 1000, 'Owais Merrill', '000096'),
('000097', 1000, 'Devon Barajas', '000097'),
('000098', 1000, 'Larry Mendez', '000098'),
('000099', 1000, 'Cairo Turner', '000099'),
('000100', 1000, 'Carol Roberson', '000100'),
('000101', 1000, 'Alia Baker', '000101'),
('000102', 1000, 'Tamara Sheridan', '000102'),
('000103', 1000, 'Hannah Bowes', '000103'),
('000104', 1000, 'Victoria Rankin', '000104'),
('000105', 1000, 'Justine Fritz', '000105'),
('000106', 1000, 'Roma Bass', '000106'),
('000107', 1000, 'Safiyyah Beasley', '000107'),
('000108', 1000, 'Lilly-Mae Nash', '000108'),
('000109', 1000, 'Hajrah Meza', '000109'),
('000110', 1000, 'Don Drummond', '000110'),
('000111', 1000, 'Lily-Grace Blanchard', '000111'),
('000112', 1000, 'Alya Hopkins', '000112'),
('000113', 1000, 'Chloe-Ann Spooner', '000113'),
('000114', 1000, 'Hina Mccormick', '000114'),
('000115', 1000, 'Abigale Halliday', '000115'),
('000116', 1000, 'Roberta Carey', '000116'),
('000117', 1000, 'Francis Bishop', '000117'),
('000118', 1000, 'Elize Chase', '000118'),
('000119', 1000, 'Diane Shaw', '000119'),
('000120', 1000, 'Nikolas Peck', '000120'),
('000121', 1000, 'Shivam Bannister', '000121'),
('000122', 1000, 'Ashlee Mayer', '000122'),
('000123', 1000, 'Kamil Whitaker', '000123'),
('000124', 1000, 'Ailsa Bowman', '000124'),
('000125', 1000, 'Claire Woodard', '000125'),
('000126', 1000, 'Nathanael Horner', '000126'),
('000127', 1000, 'Elle Orozco', '000127'),
('000128', 1000, 'Mila-Rose Nolan', '000128'),
('000129', 1000, 'Maximillian Floyd', '000129'),
('000130', 1000, 'Herbert Lyon', '000130'),
('000131', 1000, 'Ollie Contreras', '000131'),
('000132', 1000, 'Yousuf Benjamin', '000132'),
('000133', 1000, 'Ingrid Hall', '000133'),
('000134', 1000, 'Rogan Traynor', '000134'),
('000135', 1000, 'Gurdeep Salas', '000135'),
('000136', 1000, 'Teri Schneider', '000136'),
('000137', 1000, 'Yu Travers', '000137'),
('000138', 1000, 'Ria Bird', '000138'),
('000139', 1000, 'Mariyah Hernandez', '000139'),
('000140', 1000, 'Markus Hensley', '000140'),
('000141', 1000, 'Mathias King', '000141'),
('000142', 1000, 'Kerry Briggs', '000142'),
('000143', 1000, 'Jess Randall', '000143'),
('000144', 1000, 'Vicky Brookes', '000144'),
('000145', 1000, 'Kitty Short', '000145'),
('000146', 1000, 'Umer Robbins', '000146'),
('000147', 1000, 'Don Whitley', '000147'),
('000148', 1000, 'Hubert Cherry', '000148'),
('000149', 1000, 'Nicholas Healy', '000149'),
('000150', 1000, 'Farhan O\'Quinn', '000150'),
('000151', 1000, 'Patrick Sexton', '000151'),
('000152', 1000, 'Dustin Austin', '000152'),
('000153', 1000, 'Walter Booth', '000153'),
('000154', 1000, 'Danica Bate', '000154'),
('000155', 1000, 'Ema Barton', '000155'),
('000156', 1000, 'Azra Tapia', '000156'),
('000157', 1000, 'Jayden-Lee Holcomb', '000157'),
('000158', 1000, 'Jesse Cobb', '000158'),
('000159', 1000, 'Milton Montgomery', '000159'),
('000160', 1000, 'Ravi Greenaway', '000160'),
('000161', 1000, 'Malcolm Middleton', '000161'),
('000162', 1000, 'Harvie Gough', '000162'),
('000163', 1000, 'Gareth Crane', '000163'),
('000164', 1000, 'Willa Chase', '000164'),
('000165', 1000, 'Millicent Goddard', '000165'),
('000166', 1000, 'Diogo Mair', '000166'),
('000167', 1000, 'Eleasha Key', '000167'),
('000168', 1000, 'Gabrielius Page', '000168'),
('000169', 1000, 'Virgil Alcock', '000169'),
('000170', 1000, 'Asif Casey', '000170'),
('000171', 1000, 'Carolyn Good', '000171'),
('000172', 1000, 'Orla Oconnor', '000172'),
('000173', 1000, 'Rita Montes', '000173'),
('000174', 1000, 'Susanna Gilliam', '000174'),
('000175', 1000, 'Nel Payne', '000175'),
('000176', 1000, 'Wilma England', '000176'),
('000177', 1000, 'Vikram Martins', '000177'),
('000178', 1000, 'Kishan Perry', '000178'),
('000179', 1000, 'Adelaide Porter', '000179'),
('000180', 1000, 'Gracie Busby', '000180'),
('000181', 1000, 'Garrett Cote', '000181'),
('000182', 1000, 'Tyreke Farrow', '000182'),
('000183', 1000, 'Suman Young', '000183'),
('000184', 1000, 'Cole Spence', '000184'),
('000185', 1000, 'Sahar Connolly', '000185'),
('000186', 1000, 'Kamal Mckenna', '000186'),
('000187', 1000, 'Mikayla Burgess', '000187'),
('000188', 1000, 'Alisa Corbett', '000188'),
('000189', 1000, 'Oliver Calvert', '000189'),
('000190', 1000, 'Elias Lacey', '000190'),
('000191', 1000, 'Corinne Green', '000191'),
('000192', 1000, 'Atif Hyde', '000192'),
('000193', 1000, 'Keziah Hodgson', '000193'),
('000194', 1000, 'Jaeden Joyce', '000194'),
('000195', 1000, 'Darrel Hutchinson', '000195'),
('000196', 1000, 'Antonia Grey', '000196'),
('000197', 1000, 'Rian Neville', '000197'),
('000198', 1000, 'Shreya Hunt', '000198'),
('112233', 1000, 'John Doe', '012108'),
('112244', 1000, 'Jane Doe', '932012');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `account` varchar(6) NOT NULL,
  `destination_account` varchar(6) DEFAULT NULL,
  `reference` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `amount`, `date`, `type`, `account`, `destination_account`, `reference`) VALUES
(305, 100, '2019-10-29 13:13:39', 'WITHDRAW', '000001', NULL, NULL),
(306, 100, '2019-10-29 13:13:41', 'WITHDRAW', '000001', NULL, NULL),
(307, 100, '2019-10-29 13:13:44', 'WITHDRAW', '000001', NULL, NULL),
(308, 50, '2019-10-29 13:13:46', 'WITHDRAW', '000001', NULL, NULL),
(309, 500, '2019-10-29 13:13:58', 'TRANSFER', '000001', '000002', '538471');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_number`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKflw7pgdaxqqtc83ru6m214qh9` (`account`),
  ADD KEY `FK96h2rq3ilp8xwf6rt0eq19swq` (`destination_account`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=310;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK96h2rq3ilp8xwf6rt0eq19swq` FOREIGN KEY (`destination_account`) REFERENCES `account` (`account_number`),
  ADD CONSTRAINT `FKflw7pgdaxqqtc83ru6m214qh9` FOREIGN KEY (`account`) REFERENCES `account` (`account_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
