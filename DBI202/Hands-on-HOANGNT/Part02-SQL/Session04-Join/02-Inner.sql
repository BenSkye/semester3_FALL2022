SELECT * FROM VNDict, EnDict --tích đề các
SELECT * FROM VNDict CROSS JOIN EnDict --tích đề các
SELECT * FROM VNDict vn, EnDict en WHERE vn.Nmbr = en.Nmbr --tích đề các

SELECT * FROM VNDict , EnDict  WHERE VNDict.Nmbr = EnDict.Nmbr --Nên đặt alias thì giúp ngắn câu lệnh

--CÁCH VIẾT CHUẨN
SELECT * FROM VNDict INNER JOIN EnDict  ON VNDict.Nmbr = EnDict.Nmbr --NHÌN SÂU TABLE RỒI GHÉP, KHÔNG GHÉP BỪA BÃI GHÉP CÓ TƯƠNG QUAN BÊN TRONG
																		--KHÔNG GHÉP BỪA BÃI

SELECT * FROM VNDict  JOIN EnDict  ON VNDict.Nmbr = EnDict.Nmbr 
SELECT * FROM VNDict vn, EnDict en WHERE en.Nmbr > vn.Nmbr --GHÉP CÓ CHỌN LỌC HONG XÀI DẤU = MÀ DÙNG > < >= <= !=
															--NON EQUI JON (KHÔNG GHÉP BỪA BÃI)

SELECT * FROM VNDict vn, EnDict en WHERE en.Nmbr != vn.Nmbr 
SELECT * FROM VNDict vn JOIN EnDict en ON en.Nmbr != vn.Nmbr --CHUẨN 