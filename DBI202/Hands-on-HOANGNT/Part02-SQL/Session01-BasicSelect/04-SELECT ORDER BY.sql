USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT


--CÚ PHÁP MỞ RỘNG: 
--TA MUỐN SẮP XẾP DỮ LIỆU/ SORT THEO TIÊU CHÍ NÀO ĐÓ, THƯỜNG LÀ TĂNG DẦN ASCENDING/ASC
--																GIẢM DẦN DESCENDING/DESC
--MẶC ĐỊNH KHÔNG NÓI GÌ CẢ THÌ LÀ SORT TĂNG DẦN
-- A < B < C
-- 1 < 2 < 3
-- TA CÓ THỂ SORT TRÊN NHIỀU CỘT, LOGIC NÀY TỪ TỪ TÍNH

--SELECT...FROM <TÊN TABLE> ORDER BY TÊN-CỘT-MUỐN-SORT <KIỂU SORT>
-------------------------------------------------------------------

--1.IN RA DANH SÁCH NHÂN VIÊN
SELECT * FROM Employees

--2.IN RA DANH SÁCH NHÂN VIÊN TĂNG DẦN THEO NĂM SINH
SELECT * FROM Employees ORDER BY BirthDate ASC
SELECT * FROM Employees ORDER BY BirthDate --mặc định tăng dần

--3.IN RA DANH SÁCH NHÂN VIÊN TĂNG DẦN THEO NĂM SINH
SELECT * FROM Employees ORDER BY BirthDate DESC

--4.TÍNH TIỀN CHI TIẾT MUA HÀNG
SELECT * FROM [Order Details]
SELECT *, UnitPrice * UnitPrice * (1- Discount) AS SubTotal FROM [Order Details]

--5.Tính tiền chi tiết mua hàng, sắp xếp giảm dần theo số tiền
SELECT *FROM [Order Details]
SELECT *, UnitPrice * Quantity * (1 - Discount) AS SubTotal FROM [Order Details]
							ORDER BY SubTotal DESC


--6.In ra danh sách nhân viên giảm dần theo tuổi
SELECT * FROM Employees
SELECT EmployeeID,FirstName,BirthDate,YEAR(GETDATE())- YEAR(BirthDate) AS AGE 
												FROM Employees
												ORDER BY AGE DESC