USE Northwind
-------------------------------------------------------------------

--LÝ THUYẾT

--CÚ PHÁP CHUẨN: SELECT * FROM <TÊN-TABLE>
--						* đại diện cho việc tui muốn lấy all of columns
--CÚ PHÁP MỞ RỘNG: SELECT TÊN-CÁC-CỘT-MUỐN-LẤY, CÁCH-NHAU-DẤU-PHẨY FROM <TÊN-TABLE>
--				   SELECT CÓ THỂ DÙNG CÁC HÀM XỬ LÍ CÁC CỘT ĐỂ ĐỘ LẠI THÔNG TIN HIỂN THỊ 
--				   FROM < TÊN TABLE>

--Khi ta SELECT ít cột từ table gốc thì có nguy cơ dữ liệu sẽ bị trùng lại
--Không phải ta bị sai, không phải người thiết kế và người nhập liệu bị sai
--Do chúng ta có nhiều info trùng nhau / đặc điểm trùng nhau, nếu chỉ tập trung vào data này thì
--trùng nhau là chắc chắn xảy ra
--Ví dụ:
--100 triệu người dân VN được quản lí info bao gồm: ID, tên, DOB,... Tỉnh thành sinh sống
--													<>					63
--Lệnh SELECT HỖ TRỢ LOẠI TRỪ DÒNG TRÙNG NHAU/ TRÙNG 100%
--SELECT DISTINCT TÊN-CÁC-CỘT FROM <TÊN TABLE>


-------------------------------------------------------------------
--1. Liệt kê danh sách nhân viên
SELECT * FROM Employees			
-- PHÂN TÍCH: 9 NGƯỜI NHƯNG CHỈ CÓ 4 DANH XƯNG
SELECT TitleOfCourtesy FROM Employees			--9 DANH XƯNG
SELECT DISTINCT TitleOfCourtesy FROM Employees -- CHỈ LÀ 4

SELECT DISTINCT EmployeeID,TitleOfCourtesy FROM Employees -- 9
--NẾU DISTINCT ĐI KÈM VỚI ID/KEY NÓ VÔ DỤNG, KEY SAO TRÙNG ĐƯỢC MÀ LOẠI TRỪ

--2.IN RA THÔNG TIN KHÁCH HÀNG
SELECT * FROM  Customers

--3. Có bao nhiêu quốc gia xuất hiện trong thông tin khách hàng, in ra
SELECT Country FROM  Customers				--92
SELECT DISTINCT Country FROM  Customers     --22


