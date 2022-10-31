--DROP DATABASE ClubManagement

CREATE DATABASE ClubManagement

USE ClubManagement

--DROP TABLE Club
CREATE TABLE Club
(
	ClubID char(5) PRIMARY KEY,         -- PK Primary Key - Khóa chính
	ClubName nvarchar(50),
	Hotline varchar(11)
)

INSERT INTO Club VALUES('SiTi', N'Cộng đồng Sinh viên Tình nguyện', '090x')
INSERT INTO Club VALUES('SkllC', N'Skillcetera', '091x')
INSERT INTO Club VALUES('CSG', N'CLB Truyền thông Cóc Sài Gòn', '092x')
INSERT INTO Club VALUES('FEV', N'FPT Event Club', '093x')
INSERT INTO Club VALUES('FCode', N'FPT Code', '094x')

SELECT * FROM Club

--DROP TABLE Student
CREATE TABLE Student
(
	StudentID char(8) PRIMARY KEY,          -- PK Primary Key - Khóa chính
	LastName nvarchar(30),
	FirstName nvarchar(10),
	DOB date,
	Address nvarchar(50)	
)

INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE1', N'Nguyễn', N'Một');
INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE2', N'Lê', N'Hai');
INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE3', N'Trần', N'Ba');
INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE4', N'Phạm', N'Bốn');
INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE5', N'Lý', N'Năm');

SELECT * FROM Student

--DROP TABLE Registration
CREATE TABLE Registration
(
	RegID int IDENTITY(1, 1) PRIMARY KEY,   -- PK Primary Key - Khóa chính - Tăng tự động từ 1	      
	StudentID char(8),
	ClubID char(5),    
	JoinedDate date,
	LeavedDate date
	CONSTRAINT FK_Reg_Club FOREIGN KEY (ClubID) REFERENCES Club(ClubID),                -- FK Foreign Key - Khóa ngoại
	CONSTRAINT FK_Reg_Student FOREIGN KEY (StudentID) REFERENCES Student(StudentID)     -- FK Foreign Key - Khóa ngoại
)


-- SiTi 3, SkllC 2, CSG 2, FEV 0, FCODE 0
-- SE1 3, SE2 3, SE3 1, SE4 0, SE5 0
INSERT INTO Registration(StudentID, ClubID) VALUES('SE1', 'SiTi')
INSERT INTO Registration(StudentID, ClubID) VALUES('SE1', 'SkllC')
INSERT INTO Registration(StudentID, ClubID) VALUES('SE1', 'CSG')


INSERT INTO Registration(StudentID, ClubID) VALUES('SE2', 'SiTi')
INSERT INTO Registration(StudentID, ClubID) VALUES('SE2', 'SkllC')
INSERT INTO Registration(StudentID, ClubID) VALUES('SE2', 'CSG')

INSERT INTO Registration(StudentID, ClubID) VALUES('SE3', 'SiTi')

SELECT * FROM Registration


--Thực hành
--1.LIỆT KÊ THÔNG TIN SINH VIÊN ĐANG THEO HỌC
SELECT * FROM Student
--2.LIỆT KÊ THÔNG TIN SINH VIÊN ĐANG THEO HỌC KÈM THEO CLB BẠN ĐÓ ĐANG THAM GIA
--OUTPUT 1: MÃ SV, TÊN SV, MÃ CLB
--OUTPUT 2: MÃ SV ,TÊN SV, MÃ CLB, TÊN CLB

SELECT s.StudentID,s.FirstName+' ' +s.LastName AS FULLNAME,r.ClubID 
FROM Student s  JOIN Registration r ON s.StudentID =r.StudentID 
--MẤT SINH VIÊN 4,5

SELECT s.StudentID,s.FirstName+' ' +s.LastName AS FULLNAME,r.ClubID 
FROM Student s LEFT JOIN Registration r ON s.StudentID =r.StudentID 
 
SELECT s.StudentID,s.FirstName+' ' +s.LastName AS FULLNAME,r.ClubID ,c.ClubName
FROM Student s  JOIN Registration r ON s.StudentID = r.StudentID
JOIN Club c ON r.ClubID = c.ClubID
--PHÁT SINH VẤN ĐỀ LỚN : MẤT MẸ NÓ 2 SV 4,5 MẤT LUÔN CLB FCODE FEV

SELECT s.StudentID,s.FirstName+' ' +s.LastName AS FULLNAME,r.ClubID ,c.ClubName
FROM Student s, Registration r,Club c WHERE s.StudentID = r.StudentID AND r.ClubID = c.ClubID
--KHÔNG LẤY ĐƯỢC PHẦN HỤT VÌ NÓ CHỈ ĐI TÌM ĐÁM BẰNG NHAU RỒI IN RA

--JOIN SẼ GIÚP, VÌ NÓ NHÌN LUÔN PHẦN CHUNG PHẦN HỤT
SELECT s.StudentID,s.FirstName+' ' +s.LastName AS FULLNAME,r.ClubID ,c.ClubName
FROM Student s FULL JOIN Registration r ON s.StudentID = r.StudentID
FULL JOIN Club c ON r.ClubID = c.ClubID
