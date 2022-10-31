CREATE DATABASE DBDESIGN_ONETABLE
--DROP DATABASE DBDESIGN_ONETABLE
--TẠO RA CẤU TRÚC LƯU TRỮ CHỨA DATA BỎ VÀO DDL
USE DBDESIGN_ONETABLE

--TẠO TABLE ĐỂ LƯU TRỮ HỒ SƠ SINH VIÊN
-- MS,TÊN,NĂM SINH,DOB,ĐỊA CHỈ
--SINH VIÊN  ~ OBJECT ~~ ENTITY
--1 TABLE LƯU TRỮ NHIỀU ENTITY



--DROP TABLE StudentV1
CREATE TABLE StudentV1 (
	StudentID char(8),
	LastName nvarchar(40),
	FirstName nvarchar(10),
	DOB datetime,
	[Address] nvarchar(50)
)


CREATE TABLE StudentV2 (
	StudentID char(8) PRIMARY KEY,
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime, 
	[Address] nvarchar(50)
)

--THAO TÁC TRÊN DATA
 SELECT * FROM StudentV1

 --ĐƯA FULL THÔNG TIN
 INSERT INTO StudentV1 VALUES('SE123456',N'Nguyễn',N'An','2003-1-1',N'TP Hồ Chí Minh')
 
-- MỘT SỐ CỘT CHƯA THÊM NHẬP INFO ĐƯỢC QUYỀN BỎ TRỐNG NẾU CỘT CHO PHÉP TRỐNG VALUE 
-- DEFAULT KHI ĐÓNG CÁI TỦ MẶC ĐỊNH LÀ NULL

 INSERT INTO StudentV1 VALUES('SE123457',N'Lê',N'Bình','2003-2-1',NULL)
 INSERT INTO StudentV1 VALUES('SE123458',N'Võ',N'Cường','2003-3-1',N'Null')

 -- CHỈ MUỐN LƯU 1 VÀI INFO KHÔNG ĐỦ SỐ CỘT, MIỄN CỘT CÒN LẠI CHO PHÉP BỎ TRỐNG 
 INSERT INTO StudentV1 (StudentID,LastName,FirstName)
 VALUES('SE123459',N'Trần',N'Dũng')

INSERT INTO StudentV1 VALUES(NULL,NULL,NULL,NULL)
--SIÊU NGUY HIỂM, SV TOÀN INFO BỎ TRỐNG
--GÀI DATA ĐƯA VÀO (RÀNG BUỘC)
--CONSTRAINT TRÊN DATA/CELL/COLUMN
 INSERT INTO StudentV1 (StudentID,LastName,FirstName) VALUES('SE123460',N'Phạm',N'Em')

  INSERT INTO StudentV1 (StudentID,LastName,FirstName)
 VALUES('SE123460',N'Đỗ',N'Giang')

 --TRÙNG MÃ SỐ KHÔNG CHẤP NHẬN ĐƯỢC

 ------------------------------------------------------------
 --HỌC THÊM VỀ CÁI CONSTRAINTS - TRONG ĐÓ PK CONSTRAINT
 --RÀNG BUỘC LÀ CÁCH TA /DB DESIGNER ÉP CELL/CỘT NÀO ĐÓ VALUE PHẢI NTN
 --ĐẶT RA QUY TẮC/RULE CHO VIỆC NHẬP DATA
 --VÌ CÓ NHIỀU QUY TẮC, ĐỂ TRÁNH NHẦM LẪN DỄ KIỂM SOÁT TA CÓ QUYỀN ĐẶT TÊN CHO CÁC QUY TẮC
 --CONSTRAINT NAME
  --DROP TABLE StudentV6
  CREATE TABLE StudentV6
  (
  StudentID char(8) NOT NULL,
  LastName nvarchar(40) NOT NULL,
  FirstName nvarchar(10) NOT NULL,
  DOB datetime NULL,
  Address nvarchar(50) NULL,
  --PRIMARY KEY (StudentID) --tự DB ENGINE ĐẶT TÊN CHO RÀNG BUỘC  
  CONSTRAINT PK_STUDENTV6 PRIMARY KEY (StudentID)
  )

CREATE TABLE StudentV7
  (
  StudentID char(8) NOT NULL,
  LastName nvarchar(40) NOT NULL,
  FirstName nvarchar(10) NOT NULL,
  DOB datetime NULL,
  Address nvarchar(50) NULL,
  --PRIMARY KEY (StudentID) --tự DB ENGINE ĐẶT TÊN CHO RÀNG BUỘC  
  --CONSTRAINT PK_STUDENTV6 PRIMARY KEY (StudentID)
  )

ALTER TABLE StudentV7 ADD CONSTRAINT PK_STUDENTV7 PRIMARY KEY (StudentID)
ALTER TABLE StudentV7 DROP CONSTRAINT PK_STUDENTV7 