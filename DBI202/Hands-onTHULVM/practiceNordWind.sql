USE Northwind

--1
SELECT CategoryName, Description
FROM categories
ORDER BY CategoryName;

--2
SELECT ContactName,
CompanyName, ContactTitle, Phone
FROM customers
ORDER BY Phone;

--3
SELECT UPPER(FirstName) AS
FirstName, UPPER( LastName) AS
LastName, HireDate
FROM employees
ORDER BY HireDate;

--4
SELECT TOP(10) OrderID, OrderDate,
ShippedDate, CustomerID, Freight
FROM orders
ORDER BY Freight Desc 

--5
SELECT lower(CustomerID) AS ID
FROM customers;

--6
SELECT CompanyName, Fax, Phone, Country,
HomePage
FROM suppliers
ORDER BY Country DESC, CompanyName;

--7
SELECT CompanyName,ContactName
FROM customers
WHERE City = 'Buenos Aires';

--8
SELECT ProductName, UnitPrice,
QuantityPerUnit
FROM products
WHERE Discontinued = 1;

--9
SELECT ContactName, Address, City
FROM customers
WHERE Country NOT IN ('Germany','Mexico',
'Spain');

--10
SELECT OrderDate, ShippedDate, CustomerID, Freight
FROM orders
WHERE OrderDate = '1996-05-21 ';

--11
SELECT FirstName,LastName,Country
FROM employees
WHERE Country != 'USA';

--12
SELECT EmployeeID, OrderID, CustomerID,
RequiredDate, ShippedDate
FROM orders
WHERE ShippedDate > RequiredDate;


--------------------

--13
SELECT City, CompanyName, ContactName
FROM customers
WHERE City LIKE 'A%' OR City LIKE 'B%';

--14
SELECT OrderID
FROM orders
WHERE OrderID%2= 0;

--15
SELECT *
FROM orders
WHERE Freight > 500;

--16
SELECT ProductName,
UnitsInStock,UnitsOnOrder,ReorderLevel
FROM products
WHERE ReorderLevel != 0;

--17
SELECT CompanyName, ContactName, Fax
FROM customers
WHERE Fax IS NOT NULL;

--18.
SELECT FirstName, LastName
FROM employees
WHERE ReportsTo IS NULL;

--19
SELECT OrderID
FROM orders
WHERE
OrderID % 2!=0;

--20
SELECT CompanyName, ContactName, Fax
FROM customers
WHERE Fax IS NOT NULL
ORDER BY ContactName;

--21
SELECT City, CompanyName, ContactName
FROM customers
WHERE City LIKE '%L%'
ORDER BY ContactName;

--22
SELECT FirstName, LastName,BirthDate
FROM employees
WHERE BirthDate Between '1950-01-01' AND
'1959-12-31';

--23
SELECT e.FirstName,e.LastName,YEAR(e.BirthDate) AS[Year BirthDate] FROM Employees e
--24
SELECT o.OrderID, COUNT(*) AS[No orders] 
FROM [Order Details] o 
GROUP BY o.OrderID 
ORDER BY [No orders] DESC

--25.
SELECT s.SupplierID, p.ProductName, S.CompanyName
FROM suppliers s JOIN products p ON
s.SupplierID = p.SupplierID
WHERE s.CompanyName IN ('Exotic Liquids','Specialty
Biscuits, Ltd.','Escargots Nouveaux')
ORDER BY s.SupplierID;

--26.
SELECT ShipPostalCode, OrderID,
OrderDate, RequiredDate,
ShippedDate,ShipAddress
FROM orders
WHERE ShipPostalCode = '98124';

--27.
SELECT ContactName, ContactTitle,
CompanyName
FROM customers
WHERE ContactTitle NOT LIKE '%Sales%'

--28.
SELECT LastName, FirstName, City
FROM employees
WHERE City != 'Seattle';

--29.
SELECT CompanyName, ContactTitle, City, Country
FROM customers
WHERE Country IN ('Mexico','Spain') AND City !=
'Madrid';

--30.
SELECT ContactName
FROM customers
WHERE ContactName NOT LIKE '_A%';

--31.
SELECT round (avg (UnitPrice),0)
AS AveragePrice,
SUM(UnitsInStock) AS TotalStock,
MAX(UnitsOnOrder) AS MaxOrder
FROM products;

--32.
SELECT s.SupplierID, s.CompanyName,
c.CategoryName, p.ProductName, p.UnitPrice
FROM products p JOIN
suppliers s ON s.SupplierID =
p.SupplierID JOIN categories C ON
c.CategoryID = p.CategoryID;
--33.
SELECT CustomerID, sum(Freight)
FROM orders
GROUP BY CustomerID
HAVING sum(Freight) > '200';

--34.
SELECT od.OrderID,
c.ContactName,od.UnitPrice,od.Quantity,od.Discount
FROM [Order Details] od
JOIN orders o ON od.OrderID = o.OrderID
JOIN customers c ON c.CustomerID =
o.CustomerID
WHERE od.Discount != '0';

--35
SELECT od.OrderID,
c.ContactName,od.UnitPrice,od.Quantity,od.Discount
FROM [Order Details] od
JOIN orders o ON od.OrderID = o.OrderID
JOIN customers c ON c.CustomerID =
o.CustomerID
WHERE od.Discount != '0';

--36.
SELECT avg(UnitPrice) AS AveragePrice,
min(UnitPrice)AS MinimumPrice,
max(UnitPrice)AS MaximumPrice
FROM products;