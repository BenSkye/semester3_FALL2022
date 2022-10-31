USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT


--CÚ PHÁP MỞ RỘNG SELECT:
--TRONG THỰC TẾ CÓ NHỮNG LÚC DỮ LIỆU/INFO CHƯA XÁC ĐỊNH ĐƯỢC NÓ LÀ GÌ??
--KÍ TÊN DANH SÁCH THI, DANH SÁCH CÓ CỘT ĐIỂM, ĐIỂM NGAY LÚC KÍ TÊN CHƯA XÁC ĐỊNH ĐƯỢC. TỪ TỪ SẼ UPDATE SAU
--HIỆN TƯỢNG DỮ LIỆU CHƯA XÁC ĐỊNH, CHƯA BIẾT, TỪ TỪ VÀO SAU, NHƯNG HIỆN NAY NHÌN VÀO CHƯA THẤY CÓ DATA
-- =====> THÌ TA GỌI GIÁ TRỊ CHƯA XÁC ĐỊNH NÀY LÀ NULL
--NULL ĐẠI ĐIỆN CHO THỨ CHƯA XÁC ĐỊNH, CHƯA XÁC ĐỊNH ==> KHÔNG CÓ GIÁ TRỊ==> KHÔNG THỂ SO SÁNH > >= < <= = != <>
--CẤM TUYỆT ĐỐI XÀI CÁC GIÁ TRỊ SO SÁNH KÈM VỚI GIÁ TRỊ NULL
--TA DÙNG TOÁN TỬ, IS NULL, IS NOT NULL, NOT (IS NULL) ĐỂ FILTER CELL CÓ GIÁ TRỊ NULL
-------------------------------------------------------------------
--THỰC HÀNH

--1.IN RA DANH SÁCH NHÂN VIÊN
SELECT * FROM Employees

--2.AI CHƯA XÁC ĐỊNH KHU VỰC Ở, REGION NULL
SELECT * FROM Employees WHERE Region IS NULL

--3.NHỮNG AI ĐÃ XÁC ĐỊNH ĐƯỢC KHU VỰC LƯU TRÚ
SELECT * FROM Employees WHERE Region IS NOT NULL
SELECT * FROM Employees WHERE NOT (Region IS NULL)

--4.NHỮNG NHÂN VIÊN ĐẠI DIỆN KINH DOANH XÀ XÁC ĐỊNH NƠI CƯ TRÚ
SELECT * FROM Employees WHERE Title = 'Sales Representative' AND Region IS NOT NULL
SELECT * FROM Employees WHERE Title = 'Sales Representative' AND NOT (Region IS NULL)

--5.LIỆT KÊ DANH SÁCH KHÁCH HÀNG ĐẾN TỪ ANH PHÁP MỸ, CÓ CẢ THÔNG TIN SỐ FAX VÀ REGION
SELECT * FROM Customers WHERE Country IN ('UK','USA','FRANCE') AND Fax IS NOT NULL AND REGION IS NOT NULL 

