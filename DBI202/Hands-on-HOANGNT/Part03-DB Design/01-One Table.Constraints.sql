--PHẦN NÀY THÍ NGHIỆM CÁC LOẠI RÀNG BUỘC !!

--1.RÀNG BUỘC PRIMARY KEY
/*
TẠM THỜI CHẤP NHẬN PK LÀ 1 CỘT(TƯƠNG LAI CÓ THỂ CÓ NHIỀU CỘT) MÀ GIÁ TRỊ CỦA NÓ
TRÊN MỌI DÒNG/ MỌI CELL CỦA CỘT NÀY KHÔNG TRÙNG LẠI, MỤC ĐÍCH DÙNG ĐỂ WHERE ĐƯỢC 1 DÒNG DUY NHẤT
VALUE CỦA KEY CÓ THỂ ĐƯỢC TẠO THEO 2 CÁCH

CÁCH 1: TỰ NHẬP = TAY,DB ENGINE SẼ TỰ KIỂM TRA GIÙM MÌNH CÓ TRÙNG HAY KHÔNG?
CÁCH 2: DB ENGINE TỰ GENERATE CHO MÌNH 1 CON SỐ KHÔNG TRÙNG LẠI
*/

--THỰC HÀNH
--TABLE LƯU THÔNG TIN ĐĂNG KÍ EVENT NÀO ĐOÁ
--THÔNG TIN CẦN LƯU TRỮ: STT.FULL NAME,EMAIL
--NGÀY GIỜ ĐĂNG KÍ,SỐ DI ĐỘNG...
/*
PHÂN TÍCH:
NGÀY GIỜ ĐĂNG KÍ: KHÔNG BẮT NHẬP DEFAULT
STT: TỰ GÁN
EMAIL,PHONE:KHÔNG CHO TRÙNG, 1EMAIL 1 LẦN ĐĂNG KÍ
*/

/*
USE DBDESIGN_ONETABLE
CREATE TABLE Registration (
Seq int PRIMARY KEY,		--PHẢI TỰ NHẬP STT
FirstName nvarchar(10),
LastName nvarchar(30),
Email varchar(50),
Phone varchar(11),
RegDate	datetime DEFAULT GetDate()	
)
*/
DROP TABLE REGISTRATION
CREATE TABLE Registration (
Seq int PRIMARY KEY IDENTITY,		--MẶC ĐỊNH ĐI TỪ 1 VÀ NHẢY ++ CHO NGƯỜI SAU
FirstName nvarchar(10),
LastName nvarchar(30),
Email varchar(50),
Phone varchar(11),
RegDate	datetime DEFAULT GetDate()	
)

--ĐĂNG KÍ 
INSERT INTO Registration(FirstName,LastName,Email,Phone) VALUES(N'An',N'Nguyễn','An@gmail.com','090x')
INSERT INTO Registration(FirstName,LastName,Email,Phone) VALUES(N'Bình',N'Lê','Binh@gmail.com','091x')

SELECT * FROM Registration