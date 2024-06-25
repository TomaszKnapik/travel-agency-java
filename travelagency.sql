-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Cze 23, 2024 at 10:39 AM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `travelagency`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ogloszenia`
--

CREATE TABLE `ogloszenia` (
  `id_ogloszenia` int(11) NOT NULL,
  `tytul` varchar(300) NOT NULL,
  `opis` varchar(1000) NOT NULL,
  `od_kiedy` date NOT NULL,
  `do_kiedy` date NOT NULL,
  `cena` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ogloszenia`
--

INSERT INTO `ogloszenia` (`id_ogloszenia`, `tytul`, `opis`, `od_kiedy`, `do_kiedy`, `cena`) VALUES
(2, 'Yasmin Bodrum Resort', 'Nowoczesny hotel, którego zdecydowanym atutem są przepiękne krajobrazy, piaszczysta plaża wyróżniona Błękitna Flagą i wygodna formuła Ultra All Inclusive. Idealne miejsce na rodzinne wakacje pełne atrakcji i rewelacyjna propozycja dla wszystkich lubiących aktywny wypoczynek.', '2024-08-08', '2024-08-22', 3626),
(3, 'Palm Beach Resort Hurghada\r\n', 'Dobra propozycja dla aktywnych, szczególnie ceniących położenie hotelu nad malowniczym Morzem Czerwonym.\r\n\r\n', '2024-07-04', '2024-07-17', 1220),
(11, 'ASD', '1234', '2024-06-01', '2024-06-22', 123),
(12, 'asdasd', 'asd', '2024-06-08', '2024-06-22', 123);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_ogloszenia` int(11) NOT NULL,
  `aktywne` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `id_user`, `id_ogloszenia`, `aktywne`) VALUES
(10, 3, 2, 1),
(15, 29, 12, 1),
(16, 29, 2, 1),
(17, 29, 11, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nazwa_uzytkownika` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `haslo` varchar(20) NOT NULL,
  `imie` varchar(30) NOT NULL,
  `nazwisko` varchar(30) NOT NULL,
  `Admin` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nazwa_uzytkownika`, `email`, `haslo`, `imie`, `nazwisko`, `Admin`) VALUES
(1, 'admin', 'admin@admin.com', 'admin', 'admin', 'admin', 1),
(2, 'TomaszKnapik', 'tk131452@stud.ur.edu.pl', 'TomaszKnapik123', 'Tomasz', 'Knapik', 1),
(3, 'Mariusz123', 'mariusznowak@gmail.com', 'Mariuszek123!', 'Mariusz', 'Nowak', 0),
(4, 'Tomeczek', 'Itomaszeki123@gmail.com', 'Isia123*', 'Tomasz', 'Knapik', 0),
(29, 'u', 'itomaszeki123@gmail.com', 'u', 'Tomasz', 'Knapik', 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `ogloszenia`
--
ALTER TABLE `ogloszenia`
  ADD PRIMARY KEY (`id_ogloszenia`);

--
-- Indeksy dla tabeli `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ogloszenia`
--
ALTER TABLE `ogloszenia`
  MODIFY `id_ogloszenia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
