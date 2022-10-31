USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT


--CÚ PHÁP MỞ RỘNG SELECT:
--KHI BẠN CẦN LỌC DỮ LIỆU TRONG 1 ĐOẠN CHO TRƯỚC, THAY VÌ DÙNG >= ... AND <=, TA CÓ THỂ THAY THẾ 
--BẰNG MỆNH ĐỀ BETWEEN, IN

--*CÚ PHÁP: CỘT BETWEEN VALUE1 AND VALUE2
--						>>>>>>>BETWEEN THAY THẾ CHO 2 MỆNH ĐỀ >= <= AND
--*CÚ PHÁP: CỘT IN(MỘT TẬP CÁC GIÁ TRỊ ĐƯỢC LIỆT KÊ CÁCH NHAU BỞI DẤU PHẨY)
--				>>>>>>>>>>>>>>>IN THAY THẾ CHO MỘT LOẠT OR
-------------------------------------------------------------------

--THỰC HÀNH

--1.LIỆT KÊ DANH SÁCH NHÂN VIÊN SINH TRONG NĂM 1960 -1970
SELECT * FROM Employees WHERE  YEAR(BirthDate) BETWEEN 1960 AND 1970 ORDER BY BirthDate DESC

--2.LIỆT KÊ CÁC ĐƠN HÀNG CÓ TRỌNG LƯỢNG 100-500
SELECT * FROM Orders WHERE Freight BETWEEN 100 AND 500 ORDER BY Freight DESC

--3.LIỆT KÊ CÁC ĐƠN HÀNG GỬI TỚI ANH PHÁP MỸ
SELECT * FROM Orders WHERE ShipCountry = 'UK' OR ShipCountry = 'FRANCE' OR ShipCountry = 'USA'
SELECT * FROM Orders WHERE ShipCountry IN ('USA','UK','FRANCE')

--4.LIỆT KÊ CÁC ĐƠN HÀNG KHÔNG GỬI TỚI ANH PHÁP MỸ
SELECT * FROM Orders WHERE ShipCountry NOT IN ('USA','UK','FRANCE')

--5.LIỆT KÊ CÁC ĐƠN HÀNG TRONG NĂM 1996 NGOẠI TRỪ THÁNG  6 7 8 9
SELECT * FROM Orders WHERE YEAR(OrderDate) = 1996 AND MONTH(OrderDate) NOT IN (6 , 7, 8, 9)

