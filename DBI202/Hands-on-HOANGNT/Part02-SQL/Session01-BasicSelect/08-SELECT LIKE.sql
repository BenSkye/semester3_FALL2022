USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT


--CÚ PHÁP MỞ RỘNG SELECT:
--TRONG THỰC TẾ CÓ NHỮNG LÚC TA MUỐN TÌM DỮ LIỆU/ FILTER THEO KIỂU GẦN ĐÚNG
--GẦN ĐÚNG TRÊN KIỂU CHUỖI, VÍ DỤ, LIỆT KÊ AI ĐÓ CÓ TÊN LÀ AN, KHÁC CÂU LIỆT KÊU AI ĐÓ BẮT ĐẦU TÊN BẰNG CHỮ A
--TÌM ĐÚNG, TOÁN TỬ =
--TÌM GẦN ĐÚNG, TÌM  CÓ SỰ XUẤT HIỆN, KHÔNG DÙNG =, DÙNG TOÁN TỬ LIKE
--														LIKE 'A'....
--ĐỂ SỬ DỤNG TOÁN TỬ LIKE, TA CẦN THÊM 2 THỨ TRỢ GIÚP, DẤU % VÀ DẤU _
-- DẤU % ĐẠI DIỆN CHO 1 CHUỖI BẤT KÌ NÀO ĐÓ
-- DẤU _ ĐẠI DIỆN CHO 1 KÍ TỰ BẤT KÌ NÀO ĐÓ

--VÍ DỤ: NAME LIKE 'A%' BẤT KÌ AI CÓ  TÊN XUẤT HIỆN BẮT ĐẦU BẰNG CHỮ A, PHẦN CÒN LẠI KHÔNG CARE
--		 NAME LIKE 'A_', BẤT KÌ AI CÓ TÊN 2 KÍ TỰ TRONG ĐÓ KÍ TỰ ĐẦU PHẢI LÀ CHỮ A
-------------------------------------------------------------------
--THỰC HÀNH

--1. IN RA DANH SÁCH NHÂN VIÊN
SELECT * FROM Employees

--2. NHÂN VIÊN NÀO CÓ TÊN BẮT ĐẦU BẰNG CHỮ A
SELECT EmployeeID, FirstName + ' ' +LastName AS [FullName],Title FROM Employees WHERE FirstName LIKE 'A%'
SELECT EmployeeID, CONCAT(FirstName , ' ' ,LastName) AS [FullName],Title FROM Employees WHERE FirstName LIKE 'A%'

--3. NHÂN VIÊN NÀO CÓ TÊN KẾT THÚC BẰNG CHỮ A
SELECT EmployeeID, FirstName + ' ' +LastName AS [FullName],Title FROM Employees WHERE FirstName LIKE '%A'

--4.NHÂN VIÊN NÀO CÓ 4 KÝ TỰ
SELECT EmployeeID, FirstName + ' ' +LastName AS [FullName],Title FROM Employees WHERE FirstName LIKE '____'

--5.XEM DANH SÁCH SẢN PHẨM ĐANG CÓ
SELECT * FROM Products

--6.NHỮNG SẢN PHẨM TÊN BẮT ĐẦU BẰNG CH
SELECT * FROM Products WHERE ProductName LIKE 'CH%'

--7.NHỮNG SẢN PHẨM TÊN CÓ 5 KÍ TỰ
SELECT * FROM Products WHERE ProductName LIKE '_____'

--8. NHỮNG SẢN PHẨM TRONG TÊN SẨN PHẨM MÀ TỪ CUỐI CÙNG LÀ 5 KÍ TỰ
SELECT * FROM Products WHERE ProductName LIKE '% _____' OR ProductName LIKE '_____'
