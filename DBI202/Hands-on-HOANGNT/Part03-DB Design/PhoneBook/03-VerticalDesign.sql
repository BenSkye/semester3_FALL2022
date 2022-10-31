USE DBDESIGN_PHONEBOOK

CREATE TABLE PhoneBookV3_1
(
	NickName nvarchar(30),
	Phone char(11)
	--MỞ RỘNG TABLE THEO CHIỀU DỌC, AI CÓ NHIỀU SIM THÌ THÊM NHIỀU DÒNG!!!
)

INSERT INTO PhoneBookV3_1 VALUES(N'Hoang NT','090x');
INSERT INTO PhoneBookV3_1 VALUES(N'An Nguyen','090x');
INSERT INTO PhoneBookV3_1 VALUES(N'An Nguyen','091x');

INSERT INTO PhoneBookV3_1 VALUES(N'Binh Le','090x');
INSERT INTO PhoneBookV3_1 VALUES(N'Binh Le','091x');
INSERT INTO PhoneBookV3_1 VALUES(N'Binh Le','092x');

INSERT INTO PhoneBookV3_1 VALUES(N'Cuong Vo','090x');
INSERT INTO PhoneBookV3_1 VALUES(N'Cuong Vo','091x');
INSERT INTO PhoneBookV3_1 VALUES(N'Cuong Vo','092x');



SELECT * FROM PhoneBookV3_1

--PHÂN TÍCH:
/*
NHƯỢC ĐIỂM:
KHÔNG BIẾT SỐ PHONE X NÀO ĐÓ THUỘC LOẠI NÀO
CHO TUI BIẾT SỐ ĐỂ BÀN, Ở NHÀ CỦA ANH BÌNH LÊ? TOANG !!!
VI PHẠM PK, REDUNDANCY LẶP LẠI NHIỀU LẦN

 ƯU ĐIỂM:
SELECT PHONE LÀ RA ĐƯỢC TẤT CẢ CÁC SỐ DI ĐỘNG
THỐNG KÊ SỐ LƯỢNG SDT MỖI NGƯỜI XÀI, MẤY SIM 
2 CHIỀU FIX
CHIỀU NGANG(THÊM CỘT),CHIỀU DỌC(THÊM DÒNG)**
***

DATA BỊ NULL PHÍ KHÔNG GIAN LƯU TRỮ

TRIẾT LÍ THIẾT KẾ: CỐ GẮNG GIỮ NGUYÊN CẤU TRÚC, KHÔNG THÊM CỘT TABLE,CHỈ CẦN THÊM DÒNG NẾU CÓ BIẾN ĐỘNG


--TRÁNH BỊ REDUNDANCY, PK, TÁCH BẢNG, PHẦN LẶP LẠI RA 1 CHỖ KHÁC


*/
SELECT * FROM PhoneBookV3_1
SELECT p.NickName, COUNT(*) AS [NO SIM] FROM PhoneBookV3_1 p GROUP BY p.NickName
------------------------------
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

