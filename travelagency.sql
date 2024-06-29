-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Cze 27, 2024 at 09:04 PM
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
(11, 'Turunc Resort ', 'Malowniczo położony hotel o dogodnej lokalizacji niedaleko centrum miasta oraz pobliskich atrakcji. Komfortowo urządzone pokoje oraz bogaty All Inclusive są dodatkowym atutem tego hotelu. Hotel ceniony przede wszystkim za znakomitą kuchnię, serwis oraz położenie. Oferta skierowana głównie dla rodzin z dziećmi oraz dla osób pragnących odpocząć blisko natury.', '2024-07-19', '2024-07-24', 4491),
(12, 'Saluti Roma - Rzym i Watykan', 'Saluti Roma - Rzym i Watykan - 5 dni • Watykan - Kaplica Sykstyńska i Ogrody Watykańskie – serce państwa papieskiego • Fontanna di Trevi - barokowy skarb Rzymu • Schody Hiszpańskie i fontanna Barcaccia – jeden z najpiękniejszych placów rzymskich • antyczne Koloseum i Forum Romanum - architektoniczne cuda starożytności • Anioł Pański - spotkanie z Papieżem • Kwirynał - słynne rzymskie wzgórze • San Clemente - niepowtarzalna lekcja historii Rzymu', '2024-08-17', '2024-08-24', 1569),
(13, 'Amarina Queen Resort ', 'Pięciogwiazdkowy hotel położony przy pięknej piaszczystej plaży, który spełni oczekiwania zarówno osób dorosłych, jak i rodzin z dziećmi. Doskonałe miejsce na rodzinne wakacje.', '2024-08-15', '2024-08-23', 2767),
(14, 'Roma & Vaticano', 'ROMA - VATICANO - Doppio Italiano - 7 dni • Watykan - Bazylika św. Piotra, Muzea i Ogrody Watykańskie • Rezerwacja do Muzeów Watykańskich • Fontanna di Trevi - barokowy skarb Rzymu • Schody Hiszpańskie i Fontanna Barcaccia • Koloseum, Palatyn i Forum Romanum - architektoniczne cuda starożytności • Muzea Kapitolińskie - Marek Aureliusz i Wilczyca • Kwirynał i Awentyn - słynne rzymskie wzgórza • San Clemente - niepowtarzalna lekcja historii Rzymu • Katakumby św. Kalista - starożytna nekropolia • Rzymskie Bazyliki • Usta prawdy - chwila prawdy • Trastevere - wyjątkowa rzymska dzielnica', '2024-08-30', '2024-09-07', 2790),
(15, 'Golden Tulip President Hammamet', 'Hotel Golden Tulip President w Hammamet to luksusowe miejsce z bezpośrednim dostępem do jednej z najpiękniejszych plaż w okolicy. Oferuje bogate udogodnienia, w tym baseny, korty tenisowe, siłownię i dyskotekę. All Inclusive, komfortowe pokoje, darmowy Internet i specjalne atrakcje dla dzieci sprawiają, że to idealne miejsce na niezapomniane wakacje.', '2024-07-13', '2024-07-20', 1602),
(16, 'Bollywood i plaże Goa ', 'Zabytki Starego Goa • Świątynie na Wyspie Elefanta • Bombaj i Wrota Indii • jaskinie w Parku Sanjij Ghandi • studio Bollywood • złote plaże Goa', '2024-07-12', '2024-07-26', 5982),
(17, 'Bałkańskie Skarby ', 'Sarajewo i Mostar - najpiękniejsze w Bośni • Dubrownik - perła Adriatyku • wypoczynek w Czarnogórze • wycieczka do Albanii • Belgrad i Dunaj ', '2024-07-02', '2024-07-14', 1919),
(18, 'White Olive Elite Rethymno ', 'Należący do naszej własnej sieci hoteli - White Olive Elite Rethymno to miejsce, gdzie najlepiej możemy zadbać o naszych Klientów. Stworzyliśmy luksusowy kompleks gwarantujący komfortowy wypoczynek, błogi relaks oraz obsługę na wysokim poziomie.', '2024-09-21', '2024-09-28', 3737),
(19, 'Imperial Laguna by Faranda ', 'Budżetowa propozycja zakwaterowania dla wszystkich tych, dla których standard hotelu ma drugorzędne znaczenie i za niewygórowaną cenę chcą spędzić wakacje na Karaibach. Bardzo skromny hotel z niewielkim basenem w otoczeniu zieleni i atrakcji zona hotelera Cancun (restauracje, sklepy) w zasięgu spaceru.', '2024-07-27', '2024-08-03', 6072),
(20, 'Viva Azteca by Wyndham ', 'Hotel Viva Wyndham Azteca to doskonała propozycja, dla osób chcących cieszyć się niezobowiązującą atmosferą karaibskich wakacji. Nasi Goście szczególnie wysoko oceniają ofertę sportową i animacyjną oraz wyśmienite jedzenie. Piękna plaża zachęca do korzystania z uroków miejsca, a komfortowe pokoje, to idealne miejsce na chwilę wytchnienia po dniu pełnym wrażeń.', '2024-08-17', '2024-08-27', 7897);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_ogloszenia` int(11) NOT NULL,
  `aktywne` tinyint(1) NOT NULL DEFAULT 1,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `id_user`, `id_ogloszenia`, `aktywne`, `date`) VALUES
(18, 30, 11, 1, '2024-06-16'),
(19, 32, 20, 1, '2024-06-18'),
(20, 37, 20, 1, '2024-06-20'),
(21, 36, 19, 1, '2024-06-22'),
(22, 35, 18, 1, '2024-06-24'),
(23, 34, 17, 1, '2024-06-26'),
(24, 33, 16, 1, '2024-06-28'),
(25, 30, 16, 1, '2024-06-26');

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
(2, 'tomaszknapik', 'tk131452@stud.ur.edu.pl', 'TomaszKnapik123!', 'Tomasz', 'Knapik', 1),
(30, 'jan_kowalski', 'jan.kowalski@example.com', 'JanKowal123!', 'Jan', 'Kowalski', 0),
(31, 'anna_nowak', 'anna.nowak@example.com', 'AniaNowak2024', 'Anna', 'Nowak', 1),
(32, 'piotr_wisniewski', 'piotr.wisniewski@example.com', 'PiotrW5678', 'Piotr', 'Wiśniewski', 0),
(33, 'katarzyna_zielinska', 'katarzyna.zielinska@example.com', 'KasiaZiel123', 'Katarzyna', 'Zielińska', 0),
(34, 'tomasz_wojcik', 'tomasz.wojcik@example.com', 'TomekW1234', 'Tomasz', 'Wójcik', 0),
(35, 'krzysztof_kaminski', 'krzysztof.kaminski@example.com', 'KrzysKam123', 'Krzysztof ', 'Kamiński', 0),
(36, 'agnieszka_dabrowska', 'agnieszka.dabrowska@example.com', 'AgnieD1234', 'Agnieszka', 'Dąbrowska', 0),
(37, 'michal_nowicki', 'michal.nowicki@example.com', 'MichNow123', 'Michał', 'Nowicki', 0);

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
  MODIFY `id_ogloszenia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
