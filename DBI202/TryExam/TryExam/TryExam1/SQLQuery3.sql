USE PE_DBI202_F2021

--2.
SELECT * FROM ProductSubcategory WHERE Category='Accessories'
--3.
SELECT ProductID,Name,Color,Cost,Price,SellEndDate FROM Product WHERE Cost < 100 AND SellEndDate IS NOT NULL
--4.
SELECT p.ProductID,p.Name,p.Price,p.Color,m.Name,s.Name,s.Category 
FROM Product p LEFT JOIN ProductModel m ON p.ModelID =m.ModelID FULL JOIN ProductSubcategory s
 ON p.SubcategoryID=s.SubcategoryID WHERE p.Price <100 AND P.Color ='Black'
 --5.
 SELECT s.SubcategoryID,s.Name,s.Category,COUNT(*) AS[NO] 
 FROM ProductSubcategory s, Product p WHERE S.SubcategoryID= p.SubcategoryID 
 GROUP BY s.SubcategoryID,s.Category,s.Name 
 ORDER BY s.Category ASC , NO DESC,S.Name ASC
 --6.
 select l.LocationID,l.Name,COUNT(*) AS numbercount FROM Location l, ProductInventory p 
 WHERE l.LocationID = p.LocationID 
 GROUP BY l.LocationID,l.Name

 select min(number) AS numbermin FROM 
 (select l.LocationID,l.Name,COUNT(*) AS number FROM Location l, ProductInventory p 
 WHERE l.LocationID = p.LocationID  
 GROUP BY l.LocationID,l.Name ) AS A

  select l.LocationID,l.Name,COUNT(*) AS numbercount FROM Location l, ProductInventory p 
 WHERE l.LocationID = p.LocationID GROUP BY l.LocationID,l.Name  HAVING count(*) = ( select min(number) AS numbermin FROM 
 (select l.LocationID,l.Name,COUNT(*) AS number FROM Location l, ProductInventory p 
 WHERE l.LocationID = p.LocationID  
 GROUP BY l.LocationID,l.Name ) AS A)





 --7.
 SELECT S.Category,S.Name ,count(*) AS [NO] FROM ProductSubcategory s INNER JOIN Product p ON s.SubcategoryID=p.SubcategoryID  GROUP BY S.Name,S.Category
  
  SELECT S.Category,S.Name , count(*) AS [NO] 
  FROM ProductSubcategory s INNER JOIN Product p 
  ON s.SubcategoryID=p.SubcategoryID 
  GROUP BY S.Name,S.Category



 --8.
 SELECT M.ModelID,M.Name,COUNT(*) FROM Product P, ProductModel M WHERE P.ModelID = M.ModelID GROUP BY M.Name,M.ModelID
 GO
 CREATE PROC proc_product_model 
 @modelID int,
 @numberOfProduct int output
 AS
BEGIN
	 SELECT @numberOfProduct= COUNT(*) FROM Product P, ProductModel M WHERE P.ModelID = M.ModelID AND M.ModelID=@modelID GROUP BY M.Name,M.ModelID
END


DECLARE @x int
EXEC proc_product_model 9,@x output
SELECT @x AS NUMBER
GO



CREATE TRIGGER tr_insert_Product ON PRODUCT FOR INSERT
AS
BEGIN
 SELECT i.ProductID,i.Name,i.ModelID FROM INSERTED i
END

INSERT [dbo].[Product] ([ProductID], [Name], [Color], [Cost], [Price], [SubcategoryID], [ModelID], [SellStartDate], [SellEndDate]) VALUES (1111111, N'Seat Lug', NULL, 0.0000, 0.0000, NULL, NULL, CAST(N'1998-06-01T00:00:00.000' AS DateTime), NULL)

