USE AdventureWorksLT2012

--Q1:
SELECT ProductID, Name, ListPrice from SalesLT.Product
WHERE ListPrice >
(SELECT AVG(UnitPrice) FROM SalesLT.SalesOrderDetail)
ORDER BY ProductID;
--Q2:
SELECT ProductID, Name, ListPrice FROM SalesLT.Product
WHERE ProductID IN
(SELECT ProductID from SalesLT.SalesOrderDetail
 WHERE UnitPrice < 100.00)
AND ListPrice >= 100.00
ORDER BY ProductID;
--Q3:
SELECT ProductID, Name, StandardCost, ListPrice,
    (SELECT AVG(UnitPrice)
     FROM SalesLT.SalesOrderDetail AS SODetails
     WHERE P.ProductID = SODetails.ProductID)
AS AvgSellingPrice
FROM SalesLT.Product AS P
ORDER BY P.ProductID;

--Q4:
SELECT ProductID, Name, StandardCost, ListPrice,
(SELECT AVG(UnitPrice)
FROM SalesLT.SalesOrderDetail AS SODetails	
WHERE Pd.ProductID = SODetails.ProductID) AS AvgSellingPrice
FROM SalesLT.Product AS Pd
WHERE StandardCost >
(SELECT AVG(UnitPrice)
	FROM SalesLT.SalesOrderDetail AS SODetails
	 WHERE Pd.ProductID = SODetails.ProductID)
ORDER BY Pd.ProductID;

--Q5:
SELECT SOH.SalesOrderID, SOH.CustomerID, CI.FirstName, CI.LastName, SOH.TotalDue
FROM SalesLT.SalesOrderHeader AS SOH
CROSS APPLY dbo.ufnGetCustomerInformation(SOH.CustomerID) AS CI
ORDER BY SOH.SalesOrderID;

--Q6:
	SELECT CA.CustomerID, CI.FirstName, CI.LastName, A.AddressLine1, A.City
	FROM SalesLT.Address AS A
	JOIN SalesLT.CustomerAddress AS CA
	ON A.AddressID = CA.AddressID
	CROSS APPLY dbo.ufnGetCustomerInformation(CA.CustomerID) AS CI
	ORDER BY CA.CustomerID;
