-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 04 Oca 2025, 16:35:05
-- Sunucu sürümü: 10.4.32-MariaDB
-- PHP Sürümü: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `javaotopark`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `isim`
--

CREATE TABLE `isim` (
  `id` int(11) NOT NULL,
  `Ad` varchar(50) DEFAULT NULL,
  `Soyad` varchar(50) DEFAULT NULL,
  `Telefon` varchar(15) DEFAULT NULL,
  `plaka` varchar(15) DEFAULT NULL,
  `krediKarti` varchar(25) DEFAULT NULL,
  `borc` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `isim`
--

INSERT INTO `isim` (`id`, `Ad`, `Soyad`, `Telefon`, `plaka`, `krediKarti`, `borc`) VALUES
(1, 'aziz', 'cakar', '2312312', '34gp2020', '12312312', 0),
(2, NULL, NULL, NULL, NULL, NULL, NULL),
(3, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'Efekan', 'Efe', '200000', '34EFE34', '123123123', 0),
(6, NULL, NULL, NULL, NULL, NULL, NULL),
(7, NULL, NULL, NULL, NULL, NULL, NULL),
(8, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 'İbrahim', 'Tercan', '80808008', '34TC34', '1231231231232', 200),
(10, NULL, NULL, NULL, NULL, NULL, NULL),
(11, NULL, NULL, NULL, NULL, NULL, NULL),
(12, NULL, NULL, NULL, NULL, NULL, NULL),
(13, NULL, NULL, NULL, NULL, NULL, NULL),
(14, NULL, NULL, NULL, NULL, NULL, NULL),
(18, NULL, NULL, NULL, NULL, NULL, NULL),
(19, NULL, NULL, NULL, NULL, NULL, NULL),
(20, 'emine', 'çakar', '15151', '34ee1978', '1231231', 0),
(22, 'ahmet', 'güzelküçük', '12312312', '34ejr2020', '1312312312', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanici`
--

CREATE TABLE `kullanici` (
  `kullanici_id` int(11) NOT NULL,
  `kullanici_adi` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `kullanici`
--

INSERT INTO `kullanici` (`kullanici_id`, `kullanici_adi`) VALUES
(1, 'John Doe');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `otopark`
--

CREATE TABLE `otopark` (
  `otopark_id` int(11) NOT NULL,
  `otopark_adi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `otopark`
--

INSERT INTO `otopark` (`otopark_id`, `otopark_adi`) VALUES
(1, 'Gelişim Otopark'),
(2, 'Bayrampaşa Otopark'),
(3, 'Sultangazi');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yer`
--

CREATE TABLE `yer` (
  `otopark_id` int(11) DEFAULT NULL,
  `yer_id` text DEFAULT NULL,
  `durum` int(11) DEFAULT NULL,
  `kullanici_id` int(11) DEFAULT NULL,
  `tarih` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `yer`
--

INSERT INTO `yer` (`otopark_id`, `yer_id`, `durum`, `kullanici_id`, `tarih`) VALUES
(1, 'A1', 0, NULL, NULL),
(1, 'A2', 0, NULL, NULL),
(1, 'A3', 0, NULL, NULL),
(1, 'A4', 0, NULL, NULL),
(1, 'A5', 0, NULL, NULL),
(2, 'B1', 0, NULL, NULL),
(2, 'B2', 0, NULL, NULL),
(2, 'B3', 0, NULL, NULL),
(2, 'B4', 0, NULL, NULL),
(2, 'B5', 0, NULL, NULL),
(3, 'C1', 0, NULL, NULL),
(3, 'C2', 0, NULL, NULL),
(3, 'C3', 0, NULL, NULL),
(3, 'C4', 0, NULL, NULL),
(3, 'C5', 0, NULL, NULL);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `isim`
--
ALTER TABLE `isim`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `kullanici`
--
ALTER TABLE `kullanici`
  ADD PRIMARY KEY (`kullanici_id`);

--
-- Tablo için indeksler `otopark`
--
ALTER TABLE `otopark`
  ADD PRIMARY KEY (`otopark_id`);

--
-- Tablo için indeksler `yer`
--
ALTER TABLE `yer`
  ADD KEY `otopark_id` (`otopark_id`),
  ADD KEY `kullanici_id` (`kullanici_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `isim`
--
ALTER TABLE `isim`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Tablo için AUTO_INCREMENT değeri `kullanici`
--
ALTER TABLE `kullanici`
  MODIFY `kullanici_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Tablo için AUTO_INCREMENT değeri `otopark`
--
ALTER TABLE `otopark`
  MODIFY `otopark_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `yer`
--
ALTER TABLE `yer`
  ADD CONSTRAINT `yer_ibfk_1` FOREIGN KEY (`otopark_id`) REFERENCES `otopark` (`otopark_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
