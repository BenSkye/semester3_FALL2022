USE Northwind --Chọn để chơi với thùng chứa data nào đó
			  --tại 1 thời điểm chơi với 1 thùng chứa data

SELECT * FROM Customers

SELECT * FROM Employees

-------------------------------------------------------------------

--LÝ THUYẾT
--1.DBE cung cấp câu lệnh SELECT dùng để
--in ra màn hình 1 cái gì đó ~~sout()
--in ra dữ liệu có trong table (hàng/cột)!!!
--dùng cho mục đích nào thì kết quả hiển thị luôn là table

-------------------------------------------------------------------

-- 1.Hôm nay là ngày bao nhiêu
SELECT GETDATE()

SELECT GETDATE() AS [Hôm nay là ngày]

--2.Bây giờ là tháng mấy
SELECT MONTH(GETDATE()) AS [Bây giờ là tháng]

SELECT YEAR(GETDATE())

--3.Trị tuyệt đối
SELECT ABS(-5) AS [Trị tuyệt đối của -5]

-- 5+5 LÀ MẤY
SELECT SUM(5+5) AS [5 + 5 là ]

--In ra tên của mình <3
SELECT N'MonDev- Dương Hồng Quân' AS [My full name is]

SELECT N'MonDev- Dương ' + N' Hồng' + N' Quân' AS [My full name is]

SELECT (YEAR(GETDATE()) - 2002)

--SELECT N'MonDev- Dương ' + N' Hồng' + N' Quân' +(YEAR(GETDATE()) -   2002) N'years old' --LỖI KHI GHÉP SỐ VÔ CHUỖI

SELECT N'MonDev- Dương ' + N' Hồng' + N' Quân' + CONVERT(VARCHAR,YEAR(GETDATE()) - 2002) + N' years old' AS [My Profile]

SELECT CONVERT(VARCHAR,YEAR(GETDATE()) - 2002)

SELECT N'MonDev- Dương ' + N' Hồng' + N' Quân' + CAST(YEAR(GETDATE()) - 2002 AS VARCHAR) + N' years old' AS MyProfile

