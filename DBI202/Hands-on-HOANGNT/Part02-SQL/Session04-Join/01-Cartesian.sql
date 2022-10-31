CREATE DATABASE Cartesian

--database tương đương 1 cái thùng chứa data bên trong

CREATE TABLE EnDict		--DDL Data defintion language
(
	Nmbr int,
	EnDesc varchar(30)


)
--tu dien tieng anh so dem
INSERT INTO EnDict VALUES(1,'One')
INSERT INTO EnDict VALUES(2,'Two')
INSERT INTO EnDict VALUES(3,'Three')
--PHẦN NÀY THÊM CHO OUTER JOIN
INSERT INTO EnDict VALUES(4,'Four')


CREATE TABLE VNDict		--DDL Data defintion language
(
	Nmbr int,
	VnDesc Nvarchar(30)

	
)

--DROP TABLE EnDict
--DROP TABLE VNDict

INSERT INTO VnDict VALUES(1,N'Một')
INSERT INTO VnDict VALUES(2,N'Hai')
INSERT INTO VnDict VALUES(3,N'Ba')
INSERT INTO VnDict VALUES(5,N'Năm')

SELECT * FROM VNDict
SELECT * FROM EnDict		--Data Manipulation Language

-- BÔI ĐEN CẢ 2 LỆNH NÀY CHẠY THÌ NÓ KHÔNG PHẢI JOIN 
--JOIN LÀ GỘP CHUNG 1 THÀNH 1 BẢNG TẠM TRONG RAM, KHÔNG ẢNH HƯỞNG DỮ LIỆU GỐC CỦA MỖI TABLE
--JOIN LÀ SELECT CÙNG LÚC NHÌU TABLE
SELECT * FROM VNDict ,EnDict	--SINH TABLE MỚI, TẠM THỜI LUCS CHẠY HOI
								-- SỐ CỘT BẰNG TỔNG 2 BÊN
								--SỐ HÀNG = TÍCH 2 BÊN

SELECT * FROM VNDict ,EnDict ORDER BY Endesc
SELECT * FROM VNDict ,EnDict ORDER BY Nmbr


--GHÉP TABLE, JOIN BỊ TRÙNG TÊN CỘT, DÙNG ALIAS(AS),ĐẶT TÊN GIẢ ĐỂ THAM CHIẾU CHỈ ĐỊNH CỘT THUỘC TABLE TRÁNH NHẦM

SELECT* FROM VNDict ,EnDict ORDER BY VnDict.Nmbr
SELECT* FROM VNDict vn,EnDict en ORDER BY en.Nmbr

SELECT vn.Nmbr,vn.VnDesc,en.EnDesc FROM VNDict vn, EnDict en ORDER BY en.Nmbr
SELECT vn.*,vn.VnDesc,en.EnDesc FROM VNDict vn, EnDict en ORDER BY en.Nmbr
SELECT vn.*,en.* FROM VNDict vn, EnDict en ORDER BY en.Nmbr


--cú pháp 2:
SELECT vn.*,en.* FROM VNDict vn CROSS JOIN  EnDict en ORDER BY en.Nmbr

--TUI BIẾT RẰNG CÓ CẶP GHÉP XÀO ĐƯỢC VÌ CÓ TƯƠNG HỢP TRONG CELL NÀO ĐOÁ, HERE NMBR
SELECT vn.*,en.* FROM VNDict vn, EnDict en WHERE vn.Nmbr = en.Nmbr


--GHÉP CÓ CHỌN LỌC KHI TÌM TƯƠNG QUAN CỘT/CELL ĐỂ GHÉP: INNER JOIN /OUTER
														--EQUI JOIN
														--ĐA PHẦN TƯƠNG GHÉP THEO TOÁN TỬ =
														--CÒN CÓ THỂ GHÉP THEO > >= < <=

