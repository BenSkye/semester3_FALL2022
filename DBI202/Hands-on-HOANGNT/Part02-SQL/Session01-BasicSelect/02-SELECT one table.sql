USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT
--MỘT DB là nơi chứa data (bán hàng, thư viện, quản lí sinh viên, ...)
--Data được lưu dưới dạng TABLE, tách thành nhiều TABLE (nghệ thuật design db, NF)
--Dùng lệnh SELECT để xem /in dữ liệu cũng hiển thị dưới dạng table
--CÚ PHÁP CHUẨN: SELECT * FROM <TÊN-TABLE>
--						* đại diện cho việc tui muốn lấy all of columns
--CÚ PHÁP MỞ RỘNG: SELECT TÊN-CÁC-CỘT-MUỐN-LẤY, CÁCH-NHAU-DẤU-PHẨY FROM <TÊN-TABLE>
--				   SELECT CÓ THỂ DÙNG CÁC HÀM XỬ LÍ CÁC CỘT ĐỂ ĐỘ LẠI THÔNG TIN HIỂN THỊ 
--				   FROM < TÊN TABLE>



--Data trả về có cell/ô có NULL, hiểu rằng chưa xác định VALUE/ GIÁ TRỊ/ CHƯA BIẾT từ từ cập nhật sau

-------------------------------------------------------------------
--1.Xem thông tin của tất cả các khách hàng đang giao dịch với mình
SELECT * FROM Customers
INSERT INTO Customers(CustomerID, CompanyName, ContactName) VALUES('ALFKI','FPT UNIVERSITY','Thanh Nguyen Khac')

--TRUNG PRIMARY KEY
INSERT INTO Customers(CustomerID, CompanyName, ContactName) VALUES('FPTU','FPT UNIVERSITY','Thanh Nguyen Khac')
SELECT * FROM Employees

SELECT * FROM Products

SELECT * FROM Shippers
INSERT INTO Shippers (CompanyName,Phone) VALUES ('FEDEX VN','0909')
--6.XEM CHI TIẾT MUA HÀNG
SELECT * FROM Orders

SELECT * FROM [Order Details]

--7.IN RA THÔNG TIN KHÁCH HÀNG, CHỈ GỒM CỘT ID,ComName,ContactName,Country
SELECT CustomerID,CompanyName,ContactName,Country FROM Customers

--8.In ra thông tin nhân viên
--Tên người tách thành Last & First: dành cho mục tiêu sort theo tiêu chí tên, họ. In ra t
--tên theo quy ước mỗi quốc gia

SELECT * FROM Employees
SELECT EmployeeID,LastName,FirstName,Title,BirthDate FROM Employees

--9.IN THÔNG TIN NHÂN VIÊN, GHÉP TÊN CHO ĐẸP/ GỘP CỘT, TÍNH LUÔN TUỔI
SELECT EmployeeID,LastName +' ' + FirstName AS [Full Name],Title,BirthDate FROM Employees
SELECT EmployeeID,LastName +' ' + FirstName AS [Full Name],Title,YEAR(BirthDate) FROM Employees
SELECT EmployeeID,LastName +' ' + FirstName AS [Full Name],Title,(YEAR(GETDATE())- YEAR(BirthDate)) AS AGE FROM Employees


--10. I n ra thông tin chi tiết mua hàng
SELECT *FROM [Order Details]
SELECT *, UnitPrice * Quantity FROM [Order Details]

--CÔNG THỨC TÍNH TỔNG TIỀN PHẢI TRẢ TỪNG MÓN, CÓ TRỪ ĐI GIẢM GIÁ
-- SL * DG - TIỀN GIẢM GIÁ ==> PHẢI TRẢ
-- SL* DG - SL*DG*DISCOUNT ==> PHẢI TRẢ
-- SL*DG (1-DISCOUNT)
SELECT *,UnitPrice*Quantity FROM [Order Details]
SELECT *,UnitPrice*Quantity*(1-Discount)  AS [Subtotal] FROM [Order Details] 