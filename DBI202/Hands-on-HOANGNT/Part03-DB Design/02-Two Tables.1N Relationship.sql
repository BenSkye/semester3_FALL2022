CREATE DATABASE DBDESIGN_ONEMANY
--DROP DATABASE DBDESIGN_ONEMANY

USE DBDESIGN_ONEMANY
--TABLE 1 TẠO TRƯỚC, TABLE N TẠO SAU

CREATE TABLE MajorV1
(
MajorID char(2) PRIMARY KEY, --MẶC ĐỊNH DB ENGINE ĐẶT TÊN
MajorName nvarchar(40) NOT NULL

)
--CHÈN DATA
INSERT INTO MajorV1 VALUES ('SE',N'Kỹ thuật phần mềm')
INSERT INTO MajorV1 VALUES ('IA',N'An toàn thông tin')


--DROP TABLE StudentV1
CREATE TABLE StudentV1
  (
  StudentID char(8) NOT NULL,
  LastName nvarchar(40) NOT NULL,
  FirstName nvarchar(10) NOT NULL,
  DOB datetime NULL,
  Address nvarchar(50) NULL,
  MajorID char(2) --tên cột khoá ngoại không cần trùng bên 1-K nhưng bắt buộc trùng kiểu dữ liệu
  --PRIMARY KEY (StudentID) --tự DB ENGINE ĐẶT TÊN CHO RÀNG BUỘC  
  CONSTRAINT PK_STUDENTV1 PRIMARY KEY (StudentID)
  )
  SELECT * FROM StudentV1
  INSERT INTO StudentV1 VALUES('SE1',N'NGUYỄN',N'AN',NULL,NULL,'SE')

  --THIẾT KẾ SAI

  CREATE TABLE MajorV2
(
MajorID char(2) PRIMARY KEY, --MẶC ĐỊNH DB ENGINE ĐẶT TÊN
MajorName nvarchar(40) NOT NULL

)
INSERT INTO MajorV2 VALUES ('SE',N'Kỹ thuật phần mềm')
INSERT INTO MajorV2 VALUES ('IA',N'An toàn thông tin')
--DROP TABLE MajorV2
--DROP TABLE StudentV2
--NẾU CÓ MỐI QUAN HỆ 1-N XOÁ N TRƯỚC RỒI XOÁ 1 SAU
CREATE TABLE StudentV2
  (
  StudentID char(8) PRIMARY KEY,
  LastName nvarchar(40) NOT NULL,
  FirstName nvarchar(10) NOT NULL,
  DOB datetime NULL,
  Address nvarchar(50) NULL,
 -- MajorID char(2) REFERENCES MajorV2(MajorID)
 MajorID char(2) FOREIGN KEY REFERENCES MajorV2(MajorID)
  )
  INSERT INTO StudentV2 VALUES('SE1',N'NGUYỄN',N'AN',NULL,NULL,'SE')
  INSERT INTO StudentV2 VALUES('SE1',N'NGUYỄN',N'AN',NULL,NULL,'AH')

  --------------------------------------------------------------------------------
   CREATE TABLE MajorV3
(
MajorID char(2) PRIMARY KEY, --MẶC ĐỊNH DB ENGINE ĐẶT TÊN
MajorName nvarchar(40) NOT NULL

)
INSERT INTO MajorV3 VALUES ('SE',N'Kỹ thuật phần mềm')
INSERT INTO MajorV3 VALUES ('IA',N'An toàn thông tin')
--DROP TABLE MajorV3
--DROP TABLE StudentV3
--NẾU CÓ MỐI QUAN HỆ 1-N XOÁ N TRƯỚC RỒI XOÁ 1 SAU
CREATE TABLE StudentV3
  (
  StudentID char(8) PRIMARY KEY,
  LastName nvarchar(40) NOT NULL,
  FirstName nvarchar(10) NOT NULL,
  DOB datetime NULL,
  Address nvarchar(50) NULL,
 -- MajorID char(2) REFERENCES MajorV2(MajorID)
  MajorID char(2), 
  CONSTRAINT FK_StudentV3_MajorV3  FOREIGN KEY(MajorID) REFERENCES MajorV3(MajorID)
  )
  INSERT INTO StudentV3 VALUES('SE1',N'NGUYỄN',N'AN',NULL,NULL,'SE')
  INSERT INTO StudentV3 VALUES('SE1',N'NGUYỄN',N'AN',NULL,NULL,'AH')

 ALTER TABLE StudentV3 ADD CONSTRAINT FK_StudentV3_MajorV3 FOREIGN KEY (MajorID) REFERENCES  MajorV3(MajorID)
						ON DELETE CASCADE
						ON UPDATE CASCADE
DELETE FROM StudentV3 WHERE StudentID = 'ah1'
DELETE FROM StudentV3 WHERE MajorID ='AH'

--CHỐT HẠ:
--XOÁ BÊN 1 TỨC LÀ MẤT BÊN 1 KHÔNG LẼ SỤP ĐỔ ĐÁM BÊN N, KHÔNG HAY NÊN CHỌN ĐƯA BÊN N VỀ NULL
--UPDATE BÊN 1 VẪN CÒN GIỮ DÒNG/ROW BÊN N NÊN ĐỒNG BỘ THEO,ĂN THEO,CASCADE
 ALTER TABLE StudentV3 ADD CONSTRAINT FK_StudentV3_MajorV3 FOREIGN KEY (MajorID) REFERENCES  MajorV3(MajorID)
						ON DELETE SET NULL
						ON UPDATE CASCADE

UPDATE MajorV3 SET MajorID ='AK' WHERE MajorID='AH'