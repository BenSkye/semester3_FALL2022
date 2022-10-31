USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT


--CÚ PHÁP MỞ RỘNG: 
--MỆNH ĐỀ WHERE: DÙNG LÀM BỘ LỌC/FILTER/ LỌC RA NHỮNG DỮ LIỆU TA CẦN THEO 1 TIÊU CHÍ NÀO ĐÓ
-- VÍ DỤ: LỌC RA NHỮNG SINH VIÊN Ở QUÊ BÌNH DƯƠNG
-- VÍ DỤ: LỌC RA NHỮNG SINH VIÊN Ở QUÊ KIÊN GIANG VÀ ĐIỂM TRUNG BÌNH >= 8

--CÚ PHÁP DÙNG BỘ LỌC
-- SELECT * (CỘT BẠN MUỐN IN RA) FROM <TÊN TABLE> WHERE <ĐIỀU KIỆN LỌC>
--ĐIỀU KIỆN LỌC: TÌM TỪNG DÒNG VỚI CỘT CÓ GIÁ TRỊ CẦN LỌC
--				LỌC THEO TÊN CỘT VỚI VALUE THẾ NÀO, LẤY TÊN CỘT, XEM VALUE TRONG CELL CÓ THOẢ ĐIỀU KIỆN LỌC KHÔNG?

--ĐỂ VIẾT ĐIỀU KIỆN LỌC TA CẦN:
-- TÊN CỘT
--VALUE CỦA CỘT (CELL)
--TOÁN TỬ (OPERATOR) > >= < <= = ( 1 DẤU  BẰNG) !=, <> (!+ HOẶC <> CÙNG NGHĨA)
--NHIỀU ĐIỀU KIỆN LỌC ĐI KÈM NHAU DÙNG THÊM TOÁN TỬ LOGIC (LOGIC OPERATOR) AND,OR,NOT (&&, || , !)
--VÍ DỤ: WHERE CITY = N'BÌNH DƯƠNG'
--		 WHERE CITY = N'BÌNH DƯƠNG' AND Gpa >= 8

--LỌC LIÊN QUAN ĐẾN GIÁ TRỊ, VALUE/CELL CHƯA GÌ, TA PHẢI QUAN TÂM ĐẾN DATA TYPE
-- SỐ NGUYÊN/THỰC, GHI SỐ RA NHƯ TRUYỀN THỐNG
--CHUỖI KÍ TỰ: 'A','AHIHI'
--NGÀY THÁNG: '2013-01-01...'

-------------------------------------------------------------------

--THỰC HÀNH

--1.IN RA DANH SÁCH CÁC KHÁCH HÀNG 
SELECT * FROM Customers --92

--2.IN RA DANH SÁCH KHÁCH HÀNG ĐẾN TỪ Ý

SELECT *FROM Customers WHERE Country = 'Italy' --3

--3.IN RA DANH SÁCH KHÁCH HÀNG ĐẾN TỪ MỸ
SELECT *FROM Customers WHERE Country = 'USA' --13

--4. IN RA NHỮNG KHÁCH HÀNG ĐẾN TỪ MỸ, Ý
SELECT *FROM Customers WHERE Country = 'Italy' AND Country ='USA' --0
SELECT *FROM Customers WHERE Country = 'Italy' OR Country ='USA' --16

SELECT *FROM Customers WHERE Country = 'Italy' OR Country ='USA' ORDER BY Country

--5.IN RA KHÁCH HÀNG ĐẾN TỪ THỦ ĐÔ NƯỚC ĐỨC
SELECT * FROM Customers WHERE Country = 'Germany' AND City ='Berlin'

--6.in ra thông tin của nhân viên
SELECT * FROM Employees

--7.IN RA THÔNG TIN NHÂN VIÊN CÓ NĂM SINH TỪ 1960 TRỞ LẠI GẦN ĐÂY/ ĐỔ LẠI
SELECT * FROM Employees WHERE YEAR(BirthDate) >=1960 ORDER BY YEAR(BirthDate)

--8.IN RA THÔNG TIN NHÂN VIÊN CÓ TUỔI TỪ 60 TRỞ LÊN
SELECT * FROM Employees WHERE (YEAR(GETDATE()) - YEAR(BirthDate)) >= 60

--9.IN RA NHỮNG AI Ở LONDON
SELECT * FROM Employees WHERE City = 'LONDON'

--10.IN RA NHỮNG AI KHÔNG Ở LONDON
SELECT * FROM Employees WHERE City != 'LONDON'

--ĐẢO MỆNH ĐỀ !!!!!!!
SELECT * FROM Employees WHERE NOT (City = 'LONDON')

--11.IN RA HỒ SƠ NHÂN VIÊN CÓ MÃ SỐ 1
SELECT * FROM Employees WHERE EmployeeID = 1 ORDER BY EmployeeID
--WHERE MÀ CÓ KEY CHỈ CÓ 1 MÀ THUI
--SELECT MÀ CÓ WHERE KEY CHỈ CÓ 1 DÒNG TRẢ VỀ, DISTINCT LÀ VÔ NGHĨA

--CÔNG THỨC FULL SELECT
--SELECT ...FROM...WHERE....GROUP BY...HAVING...ORDER BY
--		Distinct		1,N table
--		Hàm
--		Nested Query/sub query


--12.Xem thông tin bên đơn hàng
SELECT * FROM Orders

--13.IN RA ĐƠN HÀNG SẮP XẾP GIẢM DẦN THEO TRỌNG LƯỢNG
SELECT * FROM Orders ORDER BY Freight DESC

--14.IN RA ĐƠN HÀNG GIẢM DẦN THEO TRỌNG LƯỢNG, TRỌNG LƯỢNG >= 500KG
SELECT * FROM Orders WHERE Freight >=500 ORDER BY Freight DESC

--14.IN RA ĐƠN HÀNG GIẢM DẦN THEO TRỌNG LƯỢNG, TRỌNG LƯỢNG NẰM TRONG KHOẢNG 100 - 500KG
SELECT * FROM Orders WHERE Freight >= 100 AND Freight <= 500 ORDER BY Freight DESC

--15.IN RA ĐƠN HÀNG GIẢM DẦN THEO TRỌNG LƯỢNG, TRỌNG LƯỢNG NẰM TRONG KHOẢNG 100 - 500KG VÀ SHIP BỞI CTY GIAO VẬN SỐ 1
SELECT * FROM Orders WHERE Freight >= 100 AND Freight <= 500 AND ShipVia = 1

--16.IN RA ĐƠN HÀNG GIẢM DẦN THEO TRỌNG LƯỢNG, TRỌNG LƯỢNG NẰM TRONG KHOẢNG 100 - 500KG VÀ SHIP BỞI CTY GIAO VẬN SỐ 1 VÀ KHÔNG SHIP TỚI LONDON
--TA PHẢI XÀI THÊM () ĐỂ PHÂN TÁCH THỨ TỰ FILTER
SELECT * FROM Orders WHERE Freight >= 100 AND Freight <= 500 AND ShipVia = 1 AND NOT ShipCity = 'LONDON'

--17.LIỆT KÊ DANH SÁCH KHÁCH HÀNG ĐẾN TỪ MỸ HOẶC MEXICO
SELECT * FROM Customers WHERE Country = 'USA' OR Country = 'MEXICO' ORDER BY Country

--18.LIỆT KÊ DANH SÁCH KHÁCH HÀNG KHÔNG ĐẾN TỪ MỸ HOẶC MEXICO
SELECT * FROM Customers WHERE  NOT (Country = 'USA' OR Country = 'MEXICO') ORDER BY Country

--19.LIỆT KÊ CÁC NHÂN VIÊN SINH RA TRONG ĐOẠN 1960-1970
SELECT * FROM Employees WHERE YEAR(BirthDate) <= 1970 AND YEAR(BirthDate) >=1960  ORDER BY BirthDate DESC






