USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT
--CÚ PHÁP CHUẨN CỦA LỆNH SELECT
--SELECT * FROM <TABLE> WHERE....
--WHERE CỘT = VALUE NÀO ĐÓ
--WHERE COOJT IN (MỘT TẬP HỢP NÀO ĐÓ)
--VD: CITY IN ('LONDON','BERLIN','NEWYORK') --THAY BẰNG OR OR OR
--NẾU CÓ 1 CÂU SQL MÀ TRẢ VỀ ĐƯỢC 1 CỘT NHIỀU DÒNG THÌ TA CÓ THỂ XEM TƯƠNG ĐƯƠNG LÀ 1 TẬP HỢP


--***CÚ PHÁP:
--WHERE CỘT IN (1 CÂU SELECT TRẢ VỀ 1 CỘT NHIỀU DÒNG -NHIỀU VALUE CÙNG KIỂU)
-------------------------------------------------------------------
--THỰC HÀNH

--1.LIỆT KÊ CÁC NHÓM HÀNG
SELECT * FROM Categories

--2.IN RA CÁC MÓN HÀNG THUỘC NHÓM 1, 6 ,8
SELECT * FROM Products WHERE  CategoryID IN (1,6,8)

--3.IN RA CÁC NHÓM HÀNG THUỘC NHÓM BIA/RƯỢU, THỊT VÀ HẢI SẢN
SELECT * FROM Products WHERE CategoryID IN (SELECT CategoryID FROM Categories WHERE CategoryName IN ('Beverages','Meat/Poultry','Seafood'))

---4.NHÂN VIÊN LONDON PHỤ TRÁCH ĐƠN HÀNG NÀO
SELECT * FROM Orders WHERE EmployeeID IN (SELECT EmployeeID FROM Employees WHERE City ='LONDON')


--BTVN
--1. CÁC NHÀ CUNG CẤP ĐẾN TỪ MỸ CUNG CẤP NHỮNG MẶT HÀNG NÀO?
--2.CÁC NHÀ CUNG CẤP ĐẾN TỪ MỸ CUNG CẤP NHỮNG NHÓM HÀNG NÀO?
--3.CÁC ĐƠN HÀNG VẬN CHUYỂN TỚI THÀNH PHỐ SAO PAYLO ĐC VẬN CHUYỂN BỞI NHỮNG HÃNG NÀO
--CÁC CTY NÀO ĐÃ VC HÀNG ĐẾN SAO PAOLO

--4. KHÁCH HÀNG ĐẾN TỪ THÀNH PHỐ BERLIN,LONDON,MALRID CÓ NHỮNG ĐƠN HÀNG NÀO