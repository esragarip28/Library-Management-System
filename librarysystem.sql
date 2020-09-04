-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 03, 2020 at 12:56 AM
-- Server version: 8.0.19
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarysystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`username`, `password`) VALUES
('esra', 'esra');

-- --------------------------------------------------------

--
-- Table structure for table `books_database`
--

CREATE TABLE `books_database` (
  `id` int NOT NULL,
  `book_name` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `book_writer` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `book_type` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `book_publisher` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `books_database`
--

INSERT INTO `books_database` (`id`, `book_name`, `book_writer`, `book_type`, `book_publisher`) VALUES
(1, 'Beyaz Zambaklar Ülkesinde', 'Grigoriy Petrov', 'Roman', 'Ay'),
(2, 'Peri Gazozu', 'Ercan Kesal', 'Roman', 'Güneş'),
(3, 'Simyacı', 'Paulo Coelho', 'Roman', 'Yıldız'),
(4, 'Eylül', 'Sarah Jio', 'Roman', 'Ay'),
(5, 'Kürk Mantolu Madonna', 'Sabahattin Ali', 'Roman', 'Ay'),
(6, 'Sol Ayağım', 'Christy Brown', 'Roman', 'Güneş'),
(7, 'Nietzsche Ağladığında', 'Irvin D. Yalom', 'Roman', 'Mars'),
(8, 'Nutuk', 'Mustafa Kemal Atatürk', 'Roman', 'Mars'),
(9, 'Çalıkuşu', 'Reşat Nuri Güntekin', 'Roman', 'Güneş'),
(10, 'İnce Memed', 'Yaşar Kemal', 'Roman', 'Ay'),
(11, 'Saatleri Ayarlama Enstitüsü', 'Ahmet Hamdi Tanpınar', 'Roman', 'Ay'),
(12, 'Eylül', 'Mehmet Rauf', 'Roman', 'Ay'),
(13, 'Bereketli Topraklar Üzerinde', 'Orhan Kemal', 'Roman', 'Ay'),
(14, 'Tehlikeli Oyunlar', 'Oğuz Atay', 'Roman', 'Ay'),
(15, 'Ölmeye Yatmak', 'Adalet Ağaoğlu', 'Roman', 'Mars'),
(16, 'Kuyucuklu Yusuf', 'Sabahattin Ali', 'Roman', 'Mars'),
(17, 'Gölgesizler', 'Hasan Ali Toptaş', 'Roman', 'Mars'),
(18, 'Tuhaf Bir Kadın', 'Leyla Erbil', 'Roman', 'Mars');

-- --------------------------------------------------------

--
-- Table structure for table `user_database`
--

CREATE TABLE `user_database` (
  `id` int NOT NULL,
  `userrname` text NOT NULL,
  `password` text NOT NULL,
  `borrow_book` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_database`
--

INSERT INTO `user_database` (`id`, `userrname`, `password`, `borrow_book`) VALUES
(1, 'esra', 'esra', 'Nutuk'),
(2, 'muhammed', 'muhammed', '\r\n'),
(3, 'Halil', 'Garip', ''),
(4, 'Tuba', 'Tuba', ''),
(5, 'Elif', 'Elif', ''),
(6, 'Kaan', '12345', ''),
(7, 'Sinan', '246810', ''),
(8, 'Kerem', 'kerem123', ''),
(9, 'Eylül', 'west', NULL),
(10, 'esra', '1475', NULL),
(11, 'ebru', 'ebru', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books_database`
--
ALTER TABLE `books_database`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_database`
--
ALTER TABLE `user_database`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books_database`
--
ALTER TABLE `books_database`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `user_database`
--
ALTER TABLE `user_database`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
