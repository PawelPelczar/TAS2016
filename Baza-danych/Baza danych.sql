Use master
IF DB_ID('Serwis') IS NOT NULL DROP DATABASE Serwis
GO

IF DB_ID('Serwis') IS NULL CREATE DATABASE Serwis
GO
 
USE Serwis
IF OBJECT_ID('dbo.Users', 'U') IS NOT NULL
  DROP TABLE dbo.Users;
   
IF OBJECT_ID('dbo.Sondy', 'U') IS NOT NULL
  DROP TABLE dbo.Sondy;

IF OBJECT_ID('dbo.Pytania', 'U') IS NOT NULL
  DROP TABLE dbo.Pytania;
 
IF OBJECT_ID('dbo.Odpowiedzi', 'U') IS NOT NULL
  DROP TABLE dbo.Odpowiedzi;
  
IF OBJECT_ID('dbo.Wyniki', 'U') IS NOT NULL
  DROP TABLE dbo.Wyniki;

IF OBJECT_ID('Users','U') IS NULL
CREATE TABLE Users(
id_user INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
login VARCHAR(40) NOT NULL,
password VARCHAR(100) NOT NULL,
name VARCHAR(40) NOT NULL,
surname VARCHAR(40) NOT NULL,
email VARCHAR(40) NOT NULL,
phone_number VARCHAR(40),
create_date DATE NOT NULL,
)

IF OBJECT_ID('Sondy','U') IS NULL
CREATE TABLE Sondy(
id_sondy INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
id_autor INT REFERENCES Users(id_user) NOT NULL,
title VARCHAR(100) NOT NULL,
description TEXT NOT NULL,
create_date DATE NOT NULL,
publication_date DATE NOT NULL,
liczba_pytan INT NOT NULL,
)
 
IF OBJECT_ID('Pytania','U') IS NULL
CREATE TABLE Pytania(
id_pytania INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
id_sondy INT REFERENCES Sondy(id_sondy) NOT NULL,
content TEXT NOT NULL,
)
 
IF OBJECT_ID('Odpowiedzi','U') IS NULL
CREATE TABLE Odpowiedzi(
id_odp INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
id_pytania INT REFERENCES Pytania(id_pytania) NOT NULL,
content VARCHAR(100) NOT NULL,
)

IF OBJECT_ID('Wyniki', 'U') IS NULL
CREATE TABLE Wyniki(
id_wynik INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
id_sondy INT REFERENCES Sondy(id_sondy) NOT NULL,
id_pytania INT REFERENCES Pytania(id_pytania) NOT NULL,
id_odp INT REFERENCES Odpowiedzi(id_odp) NOT NULL,
id_user INT REFERENCES Users(id_user) NOT NULL,
)

INSERT INTO Users VALUES
	('darooo', '938271dk', 'Dariusz', 'Kowalewski', 'darek@gmail.com', '654123987', '2016-03-15'),
	('marix', '718293mb', 'Marek', 'Bratkowski', 'marek@gmail.com', '951852753', '2016-02-20'),
	('lindex', '000000lm', 'Linda', 'Maciaszek', 'linda@gmail.com', '123456789', '2016-01-01'),
	('nati', '111111nm', 'Natalia', 'Misiaszek', 'natalia@gmail.com', '321654987', '2015-12-09'),
	('igucha', '222222ik', 'Iga', 'Kobelak', 'iga@gmail.com', '741852963', '2015-12-24'),
	('tomcio', '333333tm', 'Tomasz', 'Mróz', 'tomek@gmail.com', '852963741', '2015-11-03'),
	('marixon', '444444ok', 'Marek', 'Karwecki', 'maro@gmail.com', '874569123', '2015-10-29');

INSERT INTO Sondy VALUES
	(1, 'Ankieta nr 1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', '2016-03-16', '2016-03-18', 4),
	(3, 'Ankieta nr 2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', '2016-02-21', '2016-03-01', 2),
	(6, 'Ankieta nr 3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', '2015-11-20', '2015-12-24', 3);

INSERT INTO Pytania VALUES
	(1, 'Jaki jest wynik operacji 2+2?'),
	(1, 'Jaki jest wynik operacji 2*2?'),
	(1, 'Jaki jest wynik operacji 3+3-3?'),
	(1, 'Jaki jest wynik operacji 2*2*2?'),
	(2, 'Jak nazywa się najnowszy album Madonny?'),
	(2, 'Jak nazywa się najlepszy album Madonny?'),
	(3, 'Co chcesz zjeść na śniadanie?'),
	(3, 'Co chcesz zjeść na obiad?'),
	(3, 'Co chcesz zjeść na kolację?');

INSERT INTO Odpowiedzi VALUES
	(1, '3'),(1, '4'),(1, '5'),(1, '6'),
	(2, '4'),(2, '5'),(2, '6'),(2, '7'),
	(3, '1'),(3, '2'),(3, '3'),(3, '4'),
	(4, '8'),(4, '16'),(4, '32'),(4, '64'),
	(5, 'Rebel Heart'),(5, 'MDNA'),(5, 'Hard Candy'),(5,'Erotica'),(5, 'American Life'),(5, 'Ray of Light'),(5, 'True Blue'),
	(6, 'Rebel Heart'),(6, 'MDNA'),(6, 'Hard Candy'),(6,'Erotica'),(6, 'American Life'),
	(7, 'Frytki'), (7, 'Jajecznicę'), (7, 'Nie chcę jeść śniadania'),
	(8, 'Frytki'), (8, 'Schabowego z ziemniakami i kapustą kiszoną'), (8, 'Śledzie z wódką'), (8, 'Inna odpowiedź'),
	(9, 'Frytki'), (9, 'Banana'), (9, 'Nie jadam kolacji');

INSERT INTO Wyniki VALUES
	(1,1,2,3),(1,2,5,3),(1,3,11,3),(1,4,13,3),
	(2,5,17,2),(2,6,27,2),
	(2,5,17,4),(2,6,26,4);
