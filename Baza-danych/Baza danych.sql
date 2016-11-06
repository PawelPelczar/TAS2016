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
