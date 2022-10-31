USE Northwind

--------------------------------------------------------------------------------
--LÝ THUYẾT
--DB ENGINE HỖ TRỢ 1 LOẠT NHÓM HÀM DÙNG THAO TÁC TRÊN NHÓM DÒNG/CỘT, GOM DATA TÍNH TOÁN
--TRÊN ĐÁM DATA GOM NÀY  --NHÓM HÀM GOM NHÓM == AGGREGATE FUNCTIONS, AGGREGATION
--COUNT() SUM() MIN() MAX()

--****CÚ PHÁP CHUẨN:
--SELECT CỘT..., HÀM GOM NHÓM (),... FROM <TABLE>
--SELECT CỘT..., HÀM GOM NHÓM (),... FROM <TABLE> WHERE....GROUP BY...HAVING(WHERE THỨ 2)


---------------------------------------------------------------------------------
--1.LIỆT KÊ DANH SÁCH SINH VIÊN
SELECT * FROM Employees

--2.NĂM SINH NÀO BÉ NHẤT
SELECT MIN(BirthDate) FROM Employees

--3.AI SINH NĂM  BÉ NHẤT
SELECT * FROM Employees WHERE BirthDate <= ALL(
SELECT MIN(BirthDate) FROM Employees			
)

SELECT * FROM Employees WHERE BirthDate <= ALL (SELECT BirthDate FROM Employees)

--4. TRONG CÁC ĐƠN HÀNG ĐƠN HÀNG NÀO CÓ TRỌNG LƯỢNG LỚN NHẤT
SELECT * FROM Orders WHERE FREIGHT = (SELECT MAX(Freight) FROM Orders) 

--5.TÍNH TỔNG KHỐI LƯỢNG
SELECT SUM(Freight) AS [FREIGHT IN TOTAL] FROM Orders

--6.TÍNH TRUNG BÌNH KHỐI LƯỢNG 
SELECT AVG(Freight) AS [FREIGHT IN AVG] FROM Orders

--7.LIỆT KÊ CÁC ĐƠN HÀNG CÓ TRỌNG LƯỢNG NẶNG HƠN TRỌNG LƯỢNG TRUNG BÌNH CỦA TẤT CẢ
SELECT * FROM Orders WHERE Freight >= (SELECT AVG(Freight) FROM Orders)

--8. CÓ BAO NHIÊU ĐƠN HÀNG CÓ TRỌNG LƯỢNG NẶNG HƠN TRỌNG LƯỢNG TRUNG BÌNH CỦA TẤT CẢ
SELECT COUNT(*) FROM (
				SELECT * FROM Orders WHERE Freight >= 
				(
				SELECT AVG(Freight) FROM Orders
				)
)  AS[AVG]


SELECT COUNT(*) FROM Orders WHERE Freight >= ( (SELECT AVG(Freight) FROM Orders))

--2.Đếm xem mỗi khu vực có bao nhiêu nv
SELECT COUNT(*) FROM Employees 
SELECT Region, COUNT(*) FROM Employees GROUP BY Region 
SELECT COUNT(Region) FROM Employees GROUP BY Region  --SAI DO ĐẾM NULL

--3. KHẢO SÁT ĐƠN HÀNG
SELECT * FROM Orders
SELECT ShipCountry, COUNT(ShipCountry) FROM Orders GROUP BY ShipCountry

--4. QUỐC GIA NÀO CÓ TỪ 100 ĐƠN HÀNG TRỞ LÊN
SELECT ShipCountry, COUNT(ShipCountry) FROM Orders GROUP BY ShipCountry HAVING COUNT(*) >100

--5.QUỐC GIA NÀO CÓ NHIỀU ĐƠN HÀNG NHẤT


SELECT MAX([No Order]) FROM (SELECT ShipCountry, COUNT(*) AS [No Order] FROM Orders GROUP BY ShipCountry) AS Cntry