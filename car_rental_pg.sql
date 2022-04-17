-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2017 at 03:28 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: "car_rental"
--

-- --------------------------------------------------------

--
-- Table structure for table "cost"
--

CREATE TABLE "cost" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "type" varchar(100) NOT NULL,
  "cost" varchar(100) NOT NULL,
  "name" varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "driver_info"
--

CREATE TABLE "driver_info" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "username" varchar(100) NOT NULL,
  "status" varchar(100) NOT NULL,
  "license_id" varchar(100) NOT NULL,
  "car_type" varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "invoice"
--

CREATE TABLE "invoice" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "uid" varchar(100) DEFAULT NULL,
  "type" varchar(100) NOT NULL,
  "rider_username" varchar(100) NOT NULL,
  "driver_username" varchar(100) DEFAULT NULL,
  "from_place" varchar(100) NOT NULL,
  "to_place" varchar(100) NOT NULL,
  "payment_status" varchar(100) NOT NULL,
  "delivery_status" varchar(100) NOT NULL,
  "distance" varchar(100) NOT NULL,
  "cost" varchar(100) NOT NULL,
  "address" varchar(10000) NOT NULL,
  "date" varchar(100) NOT NULL,
  "time" varchar(100) NOT NULL,
  "create_date" varchar(100) NOT NULL,
  "name" varchar(100) NOT NULL,
  "cell" varchar(100) NOT NULL,
  "email" varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "notification"
--

CREATE TABLE "notification" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "username" varchar(100) NOT NULL,
  "description" varchar(10000) NOT NULL,
  "date" varchar(100) NOT NULL,
  "time" varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "places"
--

CREATE TABLE "places" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "name" varchar(100) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table "user"
--

CREATE TABLE "users" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "username" varchar(100) NOT NULL,
  "password" varchar(100) NOT NULL,
  "name" varchar(100) DEFAULT NULL,
  "dob" varchar(20) DEFAULT NULL,
  "cell" varchar(100) DEFAULT NULL,
  "email" varchar(100) NOT NULL,
  "role" varchar(20) DEFAULT NULL,
  "image" varchar(300) DEFAULT NULL,
  "gender" varchar(20) DEFAULT NULL
);

--
-- Indexes for table "user"
--

ALTER TABLE "user" ADD UNIQUE (username, email);

INSERT INTO public.user (username, password, name, dob, cell, email, role, image, gender) VALUES('administrator', 'password', 'Bitto Kazi', '01-01-1995', '01715715431', 'admin@gmail.com', 'administrator', 'rider.jpg', 'male');
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
