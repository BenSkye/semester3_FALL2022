/*
THÔNG TIN CHÍNH LÀ CỘT, VALUE DĐ CHÍNH LÀ CELL

*/
CREATE DATABASE DBDESGIN_VACCINATION
USE DBDESGIN_VACCINATION

CREATE TABLE VaccinationV1 
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30),
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE, --cấm trùng chứ không phải key
	InjectionInfo nvarchar(255)
)
--CÁCH THIẾT KẾ NÀY LƯU TRỮ ĐƯỢC KHÔNG ?

INSERT INTO VaccinationV1 VALUES('00000000001',N'Nguyễn',N'An','090x','AZ 28/09/2021 ĐH FPT | AZ 28.10.2021 BV LÊ VĂN THỊNH THỦ ĐỨC')


CREATE TABLE VaccinationV2 
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30),
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE, --cấm trùng chứ không phải key
	Dose1 nvarchar(50),
	Dose2 nvarchar(50)

)
/*

Ưu: 
gọn gàng,select gọn

Nhược:
NULL!!
THÊM MŨI 3 LẠI PHẢI THÊM CỘT

*/
--vì số lần chích còn có thể gia tăng cho từng người, mũi 2, mũi nhắc, mũi thường niên
--ai chích nhiều thì nhiều dòng


CREATE TABLE PersonV3
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30),
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE, --cấm trùng chứ không phải key
	

)

CREATE TABLE Vaccinationv3
(	
	Dose nvarchar(100),
	PersonID char(11) REFERENCES PersonV3(ID)

)
-----------------------------------------
CREATE TABLE PersonV4
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30),
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE, --cấm trùng chứ không phải key
	

)

CREATE TABLE Vaccinationv4
(	
	--Dose nvarchar(100),
	Dose int,
	InjectionDate datetime,
	Vaccin nvarchar(50),
	Lot nvarchar(20),
	[Location] nvarchar(50),
	PersonID char(11) REFERENCES PersonV3(ID)

)

--------------------------