USE DBDESIGN_PHONEBOOK

CREATE TABLE PhoneBookV2
(
NickName nvarchar(30),
--Phone varchar(50) --cấm đa trị, cấm gộp nhiều số phone trongh 1 cell
	HomePhone char(11),
	WorkPhone char(11),
	CellPhone char(11)
)

INSERT INTO PhoneBookV2 VALUES(N'Hoang NT',NULL,NULL,'090x');

--AN CÓ HAI SỐ PHONE LÀM SAO
INSERT INTO PhoneBookV2 VALUES(N'An Nguyen','090x','091x',NULL);
INSERT INTO PhoneBookV2 VALUES(N'Binh Le','090x','091x','092x');
INSERT INTO PhoneBookV2 VALUES(N'Cuong Vo','090x','091x','092x');


SELECT * FROM PhoneBookV2

--PHÂN TÍCH:
/*
 ƯU ĐIỂM:
SELECT PHONE LÀ RA ĐƯỢC TẤT CẢ CÁC SỐ DI ĐỘNG

CHO TUI BIẾT SỐ ĐỂ BÀN, Ở NHÀ CỦA ANH BÌNH LÊ? TOANG !!!
QUY ƯỚC SỐ ĐẦU TIÊN LÀ SỐ ĐT BÀN, SỐ 2 LÀ DI ĐỘNG, SỐ 3 LÀ WORK

KHỐN NẠN VÌ QUY ƯỚC NGẦM,SỐ NÀO LÀ SỐ LOẠI NÀO, KHÓ NHỚ CHO NGƯỜI NHẬP LIỆU
TIÊU CHÍ NÀO ĐỂ CẲT CHUỖI

MỘT CELL MÀ CHỨA NHIỀU VALUES CÙNG KIỂU, ĐƯỢC GỌI LÀ CỘT ĐA TRỊ
MULTIVALUED COLUMN -> TIỀM ẨN NHIỀU KHÓ KHĂN CHO VIỆC XỬ LÍ DATA

NẾU 1 TALBLE CÓ 1 ĐA TRỊ NGƯỜI TA NÓI RẰNG NÓ KHÔNG ĐẠT CHUẨN 1 LEVEL THIẾT KẾ
1ST NORMALIZATION

CHUẨN 1, CHẤT LƯỢNG THIẾT KẾ TÍNH TỪ 1, 2, 3

THIẾT KẾ KÉM THÌ PHẢI NÂNG CẤP, KHÔNG CHƠI ĐA TRỊ
KHÔNG CHƠI GOM VALUE TRONG 1 CELL
2 CHIỀU FIX
CHIỀU NGANG(THÊM CỘT),CHIỀU DỌC(THÊM DÒNG)*****

*/

/*
THỐNG KÊ SỐ LƯỢNG SDT MỖI NGƯỜI XÀI, MẤY SIM ??
DATA BỊ NULL PHÍ KHÔNG GIAN LƯU TRỮ
TRIẾT LÍ THIẾT KẾ: CỐ GẮNG GIỮ NGUYÊN CẤU TRÚC, KHÔNG THÊM CỘT TABLE,CHỈ CẦN THÊM DÒNG NẾU CÓ BIẾN ĐỘNG
*/

--1.CHO TUI BIẾT CÁC SỐ DI ĐỘNG CỦA MỌI NGƯỜI
SELECT NickName,CellPhone FROM PhoneBookV2

--PHIÊN BẢN 3: PHIÊN BẢN NGON BẮT ĐẦU, AI NHIỀU PHONE THÌ NHIỀU DÒNG,NHIỀU CELL THEO CHIỀU DỌC, THÊM DÒNG
--			   COUNT NGON LÀNH, TRẢ LỜI ĐƯỢC CÂU CÓ BAO NHIÊU SIM


