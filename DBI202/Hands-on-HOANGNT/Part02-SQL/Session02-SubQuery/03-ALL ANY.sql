USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT
--CÚ PHÁP CHUẨN CỦA LỆNH SELECT
--SELECT * FROM <TABLE> WHERE....
--WHERE CỘT TOÁN-TỬ-SO-SÁNH VỚI-VALUE-CẦN-LỌC
--		CỘT > < >= <= = != VALUE
--						DÙNG CÂU SUB QUERY TUỲ NGỮ CẢNH
--		CỘT				=(SUB CHỈ CÓ 1 VALUE)
--		CỘT				IN(SUB CHỈ CÓ 1 CỘT NHƯNG NHIỀU VALUE)
--		CỘT             > >= < <= ALL(1 CÂU SUB 1 CỘT NHIỀU VALUE)
--		CỘT						  ANY(1 CÂU SUB 1 CỘT NHIỀU VALUE)
-------------------------------------------------------------------
--THỰC HÀNH

CREATE TABLE Num
(
Numbr int
)

SELECT * FROM Num

INSERT INTO Num VALUES (1)
INSERT INTO Num VALUES (1)
INSERT INTO Num VALUES (2)
INSERT INTO Num VALUES (9)
INSERT INTO Num VALUES (5)
INSERT INTO Num VALUES (100)
INSERT INTO Num VALUES (101)

SELECT * FROM Num WHERE Numbr >5

--2.TÌM SỐ LỚN NHẤT
SELECT * FROM Num WHERE Numbr >= ALL (SELECT * FROM Num)

--4.NHÂN VIÊN NÀO LỚN TUỔI NHẤT
SELECT * FROM Employees WHERE YEAR(BirthDate) <= ALL(SELECT YEAR(BirthDate) FROM Employees)

--5.ĐƠN HÀNG  CÓ TRỌNG LƯỢNG LỚN NHẤT
SELECT * FROM Orders WHERE Freight >= ALL (SELECT Freight FROM Orders)