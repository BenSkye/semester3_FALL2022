	USE Northwind

--Q1
CREATE VIEW CustomerInfo AS
SELECT C.CustomerID, C.CompanyName, C.ContactName, C.ContactTitle, C.Address,
C.City,C.Country, C.Phone, O.OrderDate, O.RequiredDate, O.ShippedDate
FROM Customers C INNER JOIN Orders O 
ON C.CustomerID = O.CustomerID;


SELECT * FROM CustomerInfo
--Q2:
sp_rename @objname = 'dbo.CustomerInfo', @newname = 'CustomerDetails'

SELECT * FROM CustomerDetails

--Q2.2:
CREATE VIEW ProductDetails AS
SELECT P.ProductID, S.CompanyName, P.ProductName, C.CategoryName, 
C.Description, P.QuantityPerUnit, P.UnitPrice, P.UnitsInStock, P.UnitsOnOrder, P.ReorderLevel,P.Discontinued 
FROM Products P INNER JOIN Suppliers S ON S.SupplierId = P.SupplierId
INNER JOIN Categories C ON C.CategoryID = P.CategoryID

SELECT * FROM ProductDetails
--Q3:
DROP VIEW dbo.customerDetails;

--Q4:
SELECT SUBSTRING(Description, 1, 5) AS ShortInfo FROM Categories;

--Q5:
SELECT S.CompanyName, P.ProductName
FROM Products P INNER JOIN Categories C ON C.CategoryID = P.CategoryID
INNER JOIN Suppliers S ON S.SupplierID = P.SupplierID
WHERE C.CategoryName = 'Seafood';

--Q6:
SELECT c.CategoryID,p.ProductName,s.CompanyName 
FROM Categories c FULL JOIN Products p ON c.CategoryID = p.CategoryID  
FULL JOIN Suppliers s ON p.SupplierID = s.SupplierID WHERE p.CategoryID = '5'
		
	