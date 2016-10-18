USE MASTER
IF DB_ID('Sondy') IS NOT NULL DROP DATABASE Sondy
GO

IF DB_ID('Sondy') IS NULL CREATE DATABASE Sondy
GO

USE Sondy
IF OBJECT_ID('dbo.Admins', 'U') IS NOT NULL
  DROP TABLE dbo.Admins;

IF OBJECT_ID('dbo.Users', 'U') IS NOT NULL
  DROP TABLE dbo.Users;

IF OBJECT_ID('dbo.List_polls', 'U') IS NOT NULL
  DROP TABLE dbo.List_polls;

IF OBJECT_ID('Admins','U') IS NULL
CREATE TABLE Admins(
id_admin INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
login VARCHAR(40) NOT NULL,
password VARCHAR(40) NOT NULL,
name VARCHAR(40) NOT NULL,
surname VARCHAR(40) NOT NULL,
email VARCHAR(40) NOT NULL,
create_date DATE NOT NULL,
)

IF OBJECT_ID('Users','U') IS NULL
CREATE TABLE Users(
id_user INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
login VARCHAR(40) NOT NULL,
password VARCHAR(40) NOT NULL,
name VARCHAR(40) NOT NULL,
surname VARCHAR(40) NOT NULL,
email VARCHAR(40) NOT NULL,
create_date DATE NOT NULL,
)

IF OBJECT_ID('List_polls', 'U') IS NULL
CREATE TABLE List_polls(
id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
id_user INT REFERENCES Users(id_user) NOT NULL,
id_poll INT NOT NULL,
)

GO

CREATE PROCEDURE Create_poll
(
@id_poll INT,
@id_user INT
) AS
BEGIN
IF @id_poll IS NOT NULL
	IF OBJECT_ID(CONVERT(varchar(30), @id_poll), 'U') IS NULL
		BEGIN
			CREATE TABLE Poll(
			id_question INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
			id_user INT REFERENCES Users(id_user) NOT NULL,
			create_date DATE NOT NULL,
			title VARCHAR(250) NOT NULL,
			ans_1 VARCHAR(250) NOT NULL,
			ans_2 VARCHAR(250) NOT NULL,
			ans_3 VARCHAR(250),
			ans_4 VARCHAR(250),
			ans_5 VARCHAR(250),
			ans_6 VARCHAR(250),
			answers VARCHAR(6), /*ta zmienna będzie prawdopodobnie w innej tabeli*/
			)
			INSERT INTO List_polls VALUES (@id_user, @id_poll)
			RETURN 0
		END
	ELSE
		RETURN -1
ELSE
	RETURN -1
END
GO

EXECUTE Create_poll 1