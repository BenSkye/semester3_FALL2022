USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT
--CÚ PHÁP CHUẨN CỦA LỆNH SELECT
--SELECT * FROM <TABLE> WHERE....
--WHERE CỘT = VALUE NÀO ĐÓ
--WHERE CỘT LIKE PATTERN NÀO ĐÓ  e.g.'_____'
--WHERE CỘT BETWEEN RANGE
--WHERE CỘT IN(TẬP HỢP GIÁ TRỊ ĐƯỢC LIỆT KÊ)

--1 CÂU SELECT TUỲ CÁCH VIẾT CÓ THỂ TRẢ VỀ ĐÚNG 1 VALUE/ 1 CELL
--1 CÂU SELECT TUỲ CÁCH VIẾT CÓ THỂ TRẢ VỀ 1 TẬP VALUE/CELL
--TẬP KẾT QUẢ ĐỒNG NHẤT(CÁC  GIÁ TRỊ KHÁC NHAU CỦA 1 BIẾN)

--*******
--WHERE CỘT = VALUE NÀO ĐÓ
--			= THAY VALUE NÀY = 1 CÂU SQL KHÁC MIỄN TRẢ VỀ 1 CELL
--KĨ THUẬT VIẾT CÂU SQL  HỎI KIỂU GIÁN TIẾP, LỒNG NHAU, TRONG CÂU SQL CHỨA CÂU SQL KHÁC
-------------------------------------------------------------------
--THỰC HÀNH

--1. IN RA DANH SÁCH NHÂN VIÊN
SELECT * FROM Employees
SELECT FirstName FROM Employees WHERE EmployeeID = 1

--2.LIỆT KÊ CÁC NHÂN VIÊN Ở LONDON
SELECT * FROM Employees WHERE City = 'LONDON'

--3.LIỆT KÊ CÁC NHÂN VIÊN CÙNG QUÊ VỚI KING ROBERT 
SELECT * FROM Employees WHERE City = (SELECT CITY FROM Employees WHERE FirstName = 'Robert') AND FirstName <> 'Robert'

--4.LIỆT KÊ CÁC ĐƠN HÀNG
SELECT * FROM Orders ORDER BY Freight DESC

--4.1 LIỆT KÊ CÁC ĐƠN HÀNG TRỌNG LƯỢNG LỚN HƠN 252KG
SELECT * FROM Orders WHERE Freight > 252  ORDER BY Freight DESC

--4.2 LIỆT KÊ CÁC ĐƠN HÀNG TRỌNG LƯỢNG LỚN HƠN BẰNG TRỌNG LƯỢNG ĐƠN HÀNG 10555
SELECT * FROM Orders WHERE FREIGHT > (SELECT Freight FROM Orders WHERE OrderID = 10555) AND OrderID <> 10555 ORDER BY Freight DESC

---------------
--1.
SELECT * FROM Shippers
--2.
SELECT * FROM Customers
--3.
SELECT * FROM Orders WHERE ShipVia = (SELECT ShipperID FROM Shippers WHERE CompanyName = 'Speedy Express' )

--4.
SELECT * FROM Orders WHERE ShipVia = (SELECT ShipperID FROM Shippers WHERE CompanyName = 'Speedy Express' ) AND Freight BETWEEN 50 AND 100

--5.
SELECT * FROM Products WHERE CategoryID = (SELECT CategoryID FROM Products WHERE ProductName = 'Filo Mix')

--6.
SELECT * FROM Employees WHERE YEAR(BirthDate) > ( SELECT YEAR(BirthDate) FROM Employees WHERE FirstName = 'Janet')
