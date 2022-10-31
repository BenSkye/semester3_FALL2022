/*
THÔNG TIN CHÍNH LÀ CỘT, VALUE DĐ CHÍNH LÀ CELL

*/
CREATE DATABASE DBDESGIN_VACCINATION
USE DBDESGIN_VACCINATION

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
	PersonID char(11) REFERENCES PersonV4(ID)

)
--------------------------
--------------------------
/*
Vì cột vaccin cho phép gõ các giá trị tên vc một cách tự do -> inconsistences
Tham chiếu từ danh sách có sẵn
*/

CREATE TABLE PersonV5
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30),
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE, --cấm trùng chứ không phải key
	

)

CREATE TABLE


CREATE TABLE Vaccinationv5
(	
	--Dose nvarchar(100),
	Dose int,
	InjectionDate datetime,
	Vaccin nvarchar(30),
	Lot nvarchar(20),
	[Location] nvarchar(50),
	PersonID char(11) REFERENCES PersonV4(ID)

)