USE DBDESIGN_PHONEBOOK
CREATE DATABASE DBDESIGN_PHONEBOOK
DROP DATABASE DBDESIGN_PHONEBOOK


CREATE TABLE PersonV5
(
	Nick nvarchar(30) PRIMARY KEY,
	Title nvarchar(30),
	Company nvarchar(40)
)

CREATE TABLE PhoneTypeV5
(
	TypeName nvarchar(20) PRIMARY KEY
)

--DROP TABLE PhoneBookV5

CREATE TABLE PhoneBookV5
(	
	Phone char(11) PRIMARY KEY,	
	TypeName nvarchar(20) REFERENCES PhoneTypeV5(TypeName),
	Nick nvarchar(30) REFERENCES PersonV5(Nick),

)


SELECT * FROM PhoneTypeV5
SELECT * FROM PhoneBookV5
SELECT * FROM PersonV5


INSERT INTO PhoneTypeV5 VALUES(N'Di Động');
INSERT INTO PhoneTypeV5 VALUES(N'Nhà/Để bàn');
INSERT INTO PhoneTypeV5 VALUES(N'Công ty');


INSERT INTO PersonV5 VALUES(N'Hoang NT','Lecturer','FPTU HCMC');
INSERT INTO PersonV5 VALUES(N'An Nguyen','Student','FPTU HCMC');
INSERT INTO PersonV5 VALUES(N'Binh Le','Student','FPTU HCMC');
INSERT INTO PersonV5 VALUES(N'Cuong Vo','Student','FPTU HCMC');

INSERT INTO PhoneBookV5 VALUES('090x',N'Di động',N'Hoang NT');
INSERT INTO PhoneBookV5 VALUES('093x',N'Di động',N'An Nguyen');
INSERT INTO PhoneBookV5 VALUES('091x',N'Nhà/Để bàn',N'An Nguyen');

INSERT INTO PhoneBookV5 VALUES('092x',N'Công ty',N'Binh Le');
INSERT INTO PhoneBookV5 VALUES('094x',N'Di động',N'Binh Le');
INSERT INTO PhoneBookV5 VALUES('095x',N'Di động',N'Binh Le');

INSERT INTO PhoneBookV5 VALUES('096x',N'Di động',N'Cuong Vo');
INSERT INTO PhoneBookV5 VALUES('097x',N'Nhà/Để bàn',N'Cuong Vo');
INSERT INTO PhoneBookV5 VALUES('098x',N'Công ty',N'Cuong Vo');
