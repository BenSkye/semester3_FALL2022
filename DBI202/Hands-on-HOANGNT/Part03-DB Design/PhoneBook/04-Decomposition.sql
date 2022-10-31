USE DBDESIGN_PHONEBOOK


CREATE TABLE PhoneBookV3_2
(
	NickName nvarchar(30),
	Phone char(11),-- CHỈ 1 SỐ PHONE THUI, NHƯNG CẦN GIẢI NGHĨA THÊM SỐ NÀY LÀ SỐ GÌ
	PhoneType nvarchar(20)
	
)

INSERT INTO PhoneBookV3_2 VALUES(N'Hoang NT','090x','Cell');
INSERT INTO PhoneBookV3_2 VALUES(N'An Nguyen','090x','Cell');
INSERT INTO PhoneBookV3_2 VALUES(N'An Nguyen','091x','Home');

INSERT INTO PhoneBookV3_2 VALUES(N'Binh Le','090x','Work');
INSERT INTO PhoneBookV3_2 VALUES(N'Binh Le','091x','Cell');
INSERT INTO PhoneBookV3_2 VALUES(N'Binh Le','092x','Cell');

INSERT INTO PhoneBookV3_2 VALUES(N'Cuong Vo','090x','Cell');
INSERT INTO PhoneBookV3_2 VALUES(N'Cuong Vo','091x','Home');
INSERT INTO PhoneBookV3_2 VALUES(N'Cuong Vo','092x','Work');

SELECT * FROM PhoneBookV3_2
SELECT * FROM PhoneBookV3_2 WHERE PhoneType='Cell'

/*
Ưu điểm:
COUNT NGON, GROUP BY THEO NICK, THEO LOẠI PHONE
WHERE NGON 

NHƯỢC ĐIỂM:
REDUNDANCY TRÊN INFO CỦA NICK
*/

--MỘT KHI BỊ TRÙNG LẶP INFO, REDUNDANCY, CHỈ CÓ 1 SOLUTION KHÔNG CHO TRÙNG NỮA
--TỨC LÀ XUẤT HIỆN 1 LẦN TỨC LÀ RA BẢNG KHÁC
--DECOMPOSITION

---------------------------------------------

/*
TÁCH BẢNG
INFO BỊ PHÂN MẢNH, NẰM NHIỀU NƠI, PHẢI JOIN
NHẬP DATA COI CHỪNG BỊ VÊNH, XOÁ DATA COI CHỪNG LẠC TRÔI
PHÂN MẢNH PHẢI CÓ LÚC TÁI NHẬP

*/

CREATE TABLE PersonV4_1
(
	Nick nvarchar(30),
	Title nvarchar(30),
	Company nvarchar(40)
)

CREATE TABLE PhoneBookV4_1
(	
	Phone char(11),
	PhoneType nvarchar(20),
	Nick nvarchar(30)
	
)
SELECT * FROM PhoneBookV4_1
SELECT * FROM PersonV4_1

SELECT * FROM PersonV4_1 p2 INNER JOIN PhoneBookV4_1 p1  ON p1.Nick = p2.Nick
INSERT INTO PersonV4_1 VALUES(N'Hoang NT','Lecturer','FPTU HCMC');
INSERT INTO PersonV4_1 VALUES(N'An Nguyen','Student','FPTU HCMC');
INSERT INTO PersonV4_1 VALUES(N'Binh Le','Student','FPTU HCMC');
INSERT INTO PersonV4_1 VALUES(N'Cuong Vo','Student','FPTU HCMC');



INSERT INTO PhoneBookV4_1 VALUES('090x','Cell',N'Hoang NT');
INSERT INTO PhoneBookV4_1 VALUES('090x','Cell',N'An Nguyen');
INSERT INTO PhoneBookV4_1 VALUES('091x','Home',N'An Nguyen');

INSERT INTO PhoneBookV4_1 VALUES('090x','Work',N'Binh Le');
INSERT INTO PhoneBookV4_1 VALUES('091x','Cell',N'Binh Le');
INSERT INTO PhoneBookV4_1 VALUES('092x','Cell',N'Binh Le');

INSERT INTO PhoneBookV4_1 VALUES('090x','Cell',N'Cuong Vo');
INSERT INTO PhoneBookV4_1 VALUES('091x','Home',N'Cuong Vo');
INSERT INTO PhoneBookV4_1 VALUES('092x','Work',N'Cuong Vo');
/*
Ưu điểm:
COUNT NGON, GROUP BY THEO NICK, THEO LOẠI PHONE
WHERE NGON 
REDUNDANCY TRÊN INFO CỦA NICK--GIẢI QUYẾT XONG

NHƯỢC ĐIỂM:
--TÍNH KHÔNG NHẤT QUÁN(INCONSISTANCY) CỦA LOẠI PHONE CÓ NGƯỜI GÕ CELL, cell,Cell
										gõ thêm:Di động
--QUERY LIỆT KÊ SỐ DI ĐỘNG CỦA TẤT CẢ MỌI NGƯỜI !! TOANG KHI WHERE
*/

/*
Có những loại dữ liệu biết trước là nhiều nhưng hữu hạn values để nhập
tỉnh thành nhiều nhưng chỉ 63

*/

---------------------------------------------

