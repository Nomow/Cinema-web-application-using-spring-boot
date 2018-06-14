-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 14, 2018 at 05:24 PM
-- Server version: 5.7.22-0ubuntu0.16.04.1
-- PHP Version: 7.0.30-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinema`
--

-- --------------------------------------------------------

--
-- Table structure for table `BoughtSeats`
--

CREATE TABLE `BoughtSeats` (
  `id` int(11) NOT NULL,
  `session_id` int(11) NOT NULL,
  `payment_system_id` int(11) NOT NULL,
  `row` int(11) NOT NULL,
  `col` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `order_number` varchar(255) NOT NULL,
  `phone_number` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Cinema`
--

CREATE TABLE `Cinema` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `name` varchar(45) CHARACTER SET ucs2 COLLATE ucs2_latvian_ci NOT NULL,
  `address` varchar(120) CHARACTER SET ucs2 COLLATE ucs2_latvian_ci NOT NULL,
  `phone_number` varchar(50) CHARACTER SET ucs2 COLLATE ucs2_latvian_ci NOT NULL,
  `email` varchar(255) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Cinema`
--

INSERT INTO `Cinema` (`id`, `city_id`, `name`, `address`, `phone_number`, `email`, `latitude`, `longitude`) VALUES
(1, 1, 'Kino Rio', 'Pils iela 28', '+37122480248', 'kniorio@kinorio.lv\r\n\r\n', 57.3964964, 21.5645983),
(2, 2, 'Baldones kinoteātris', 'Daugavas iela 2', '+37167932738', 'baldoneskino@kinobaldone.lv\r\n', 56.75637912, 24.39237399),
(3, 3, 'Kino Balle', 'Rožu laukums 5/6', '+3712948484', 'kinoballe@ballekino.lv\r\n', 56.5078547, 21.0127689),
(4, 5, 'Silver Screen', 'Kaulu iela 5', '+37129575751', 'silverscreen@daugavpiln.lv', 55.8730708, 26.5180319),
(5, 6, 'Forumcinemas', 'Janvara iela 13', '+3716565651', 'kino@forumcinemas.lv', 56.946342, 24.1168375);

-- --------------------------------------------------------

--
-- Table structure for table `City`
--

CREATE TABLE `City` (
  `id` int(11) NOT NULL,
  `name` varchar(45) CHARACTER SET ucs2 COLLATE ucs2_latvian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `City`
--

INSERT INTO `City` (`id`, `name`) VALUES
(1, 'Ventspils'),
(2, 'Baldone'),
(3, 'Liepāja'),
(4, 'Dobele'),
(5, 'Daugavpils'),
(6, 'Rīga');

-- --------------------------------------------------------

--
-- Table structure for table `Genres`
--

CREATE TABLE `Genres` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Genres`
--

INSERT INTO `Genres` (`id`, `name`) VALUES
(1, 'Action'),
(2, 'Adventure'),
(3, 'Comedy'),
(4, 'Crime'),
(5, 'Drama'),
(6, 'Fantasy'),
(7, 'Historical'),
(8, 'Historical fiction'),
(9, 'Horror'),
(10, 'Magical realism'),
(11, 'Mystery'),
(12, 'Paranoid Fiction'),
(13, 'Philosophical'),
(14, 'Political'),
(15, 'Romance'),
(16, 'Saga'),
(17, 'Satire'),
(18, 'Science fiction'),
(19, 'Slice of Life'),
(20, 'Social'),
(21, 'Speculative'),
(22, 'Thriller'),
(23, 'Urban'),
(24, 'Western'),
(25, 'Animation');

-- --------------------------------------------------------

--
-- Table structure for table `Hall`
--

CREATE TABLE `Hall` (
  `id` int(11) NOT NULL,
  `cinema_id` int(11) NOT NULL,
  `rows` int(11) NOT NULL,
  `cols` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Hall`
--

INSERT INTO `Hall` (`id`, `cinema_id`, `rows`, `cols`) VALUES
(1, 1, 10, 15),
(2, 1, 20, 15),
(3, 1, 25, 15),
(4, 2, 30, 30),
(5, 2, 15, 15),
(6, 3, 40, 10),
(7, 4, 55, 15),
(8, 4, 33, 20),
(9, 5, 13, 10),
(10, 5, 10, 10),
(11, 5, 16, 8),
(12, 5, 9, 9);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `Movie`
--

CREATE TABLE `Movie` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `time` time NOT NULL,
  `year` int(11) NOT NULL,
  `director` varchar(50) NOT NULL,
  `youtube_trailer_url` varchar(2083) NOT NULL,
  `imdb_url` varchar(2083) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Movie`
--

INSERT INTO `Movie` (`id`, `name`, `description`, `time`, `year`, `director`, `youtube_trailer_url`, `imdb_url`) VALUES
(1, 'Pulp Fiction', ' The lives of two mob hitmen, a boxer, a gangster\'s wife, and a pair of diner bandits intertwine in four tales of violence and redemption. ', '02:34:00', 1994, ' Quentin Tarantino ', 'https://www.youtube.com/watch?v=s7EdQ4FqbhY', 'https://www.imdb.com/title/tt0110912/?ref_=fn_al_tt_1'),
(2, 'Reservoir Dogs', ' After a simple jewelry heist goes terribly wrong, the surviving criminals begin to suspect that one of them is a police informant. ', '01:34:00', 1992, ' Quentin Tarantino ', 'https://www.youtube.com/watch?v=vayksn4Y93A', 'https://www.imdb.com/title/tt0105236/?ref_=fn_al_tt_1'),
(3, 'Memento', ' A man juggles searching for his wife\'s murderer and keeping his short-term memory loss from being an obstacle. ', '01:53:00', 2000, 'Christopher Nolan', 'https://www.youtube.com/watch?v=UFuFFdK7i44', '0'),
(4, 'The Usual Suspects', ' A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which began when five criminals met at a seemingly random police lineup. ', '01:46:00', 1995, 'Bryan Singer', 'https://www.youtube.com/watch?v=oiXdPolca5w', 'https://www.imdb.com/title/tt0114814/?ref_=fn_al_tt_1'),
(5, 'Se7en', ' Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives. ', '02:07:00', 1995, 'David Fincher', 'https://www.youtube.com/watch?v=znmZoVkCjpI', 'https://www.imdb.com/title/tt0114369/?ref_=fn_al_tt_1'),
(6, 'Inception', ' A thief, who steals corporate secrets through the use of dream-sharing technology, is given the inverse task of planting an idea into the mind of a CEO. ', '02:28:00', 2010, 'Christopher Nolan', 'https://www.youtube.com/watch?v=YoHD9XEInc0', 'https://www.imdb.com/title/tt1375666/?ref_=fn_al_tt_1'),
(7, 'Donnie Darko', ' A troubled teenager is plagued by visions of a man in a large rabbit suit who manipulates him to commit a series of crimes, after he narrowly escapes a bizarre accident. ', '01:53:00', 2001, 'Richard Kelly ', 'https://www.youtube.com/watch?v=ZZyBaFYFySk', 'https://www.imdb.com/title/tt0246578/?ref_=fn_al_tt_1');

-- --------------------------------------------------------

--
-- Table structure for table `MovieGenres`
--

CREATE TABLE `MovieGenres` (
  `id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `genres_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `MovieGenres`
--

INSERT INTO `MovieGenres` (`id`, `movie_id`, `genres_id`) VALUES
(1, 1, 2),
(2, 1, 17),
(3, 1, 9),
(4, 1, 24),
(5, 2, 3),
(6, 2, 19),
(7, 3, 5),
(8, 4, 13),
(9, 4, 21),
(10, 5, 22),
(11, 6, 24),
(12, 6, 23),
(13, 7, 6),
(14, 7, 19);

-- --------------------------------------------------------

--
-- Table structure for table `PaymentSystem`
--

CREATE TABLE `PaymentSystem` (
  `id` int(11) NOT NULL,
  `name` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Session`
--

CREATE TABLE `Session` (
  `id` int(11) NOT NULL,
  `cinema_id` int(11) NOT NULL,
  `movies_id` int(11) NOT NULL,
  `hall_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `price_children` double NOT NULL,
  `price_adults` double NOT NULL,
  `price_senoirs` double NOT NULL,
  `price_handicapped` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `BoughtSeats`
--
ALTER TABLE `BoughtSeats`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Cinema`
--
ALTER TABLE `Cinema`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `City`
--
ALTER TABLE `City`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Genres`
--
ALTER TABLE `Genres`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Hall`
--
ALTER TABLE `Hall`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Movie`
--
ALTER TABLE `Movie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `MovieGenres`
--
ALTER TABLE `MovieGenres`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `PaymentSystem`
--
ALTER TABLE `PaymentSystem`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Session`
--
ALTER TABLE `Session`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `BoughtSeats`
--
ALTER TABLE `BoughtSeats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Cinema`
--
ALTER TABLE `Cinema`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `City`
--
ALTER TABLE `City`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `Genres`
--
ALTER TABLE `Genres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `Hall`
--
ALTER TABLE `Hall`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `Movie`
--
ALTER TABLE `Movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `MovieGenres`
--
ALTER TABLE `MovieGenres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `Session`
--
ALTER TABLE `Session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
