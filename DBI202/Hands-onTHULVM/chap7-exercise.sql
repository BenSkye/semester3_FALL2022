USE AdventureWorks2019

create view vw_Products1
as
select p.ProductID, Name, Color, Size, Style, p.StandardCost,
EndDate, StartDate
from [Production].[Product] p join
[Production].[ProductCostHistory] c
on p.ProductID=c.ProductID
go
select*from [dbo].[vw_Products1]
sp_helptext [vw_Products1]


create view List_Product_view
as
select p.[ProductID], [Name] as Product_name, countofOrderID=
count(*), Subtotal=sum([OrderQty]*[UnitPrice])
from [Production].[Product] p join [Sales].[SalesOrderDetail] o
on p.ProductID=o.ProductID
join [Sales].[SalesOrderHeader] h on
o.SalesOrderID=h.SalesOrderID
where datepart(q, [OrderDate])=1 and YEAR([OrderDate])=2008
group by p.[ProductID], [Name]
having sum([OrderQty]*[UnitPrice])>10000 and count(*)>500


create view vw_CustomerTotals
as
select CustomerID, YEAR(OrderDate) AS OrderYear,
MONTH(OrderDate) AS OrderMonth, sumofTotal=SUM(TotalDue)
from [Sales].[SalesOrderHeader]
group by CustomerID, YEAR(OrderDate), MONTH(OrderDate)

create view view_SumofQty
as
select SalesPersonID, OrderYear=year([OrderDate]),
sumOfOrderQty=sum([OrderQty])
from [Sales].[SalesOrderHeader] h join
[Sales].[SalesOrderDetail] d on
h.SalesOrderID=d.SalesOrderID
group by SalesPersonID, year([OrderDate])


create view ListCustomer_view
as
select [CustomerID], FirstName +' '+ LastName as
fullname
from [Sales].[SalesOrderHeader] h join
[Person].[Person] p on h.CustomerID=p.BusinessEntityID
where year([OrderDate])>=2007 AND
year([OrderDate])<=2008
GROUP BY [CustomerID], FirstName +' '+ LastName
HAVING count(*)>25


create view ListProduct_view
as
select p.ProductID, Name, SumofOrderQty=sum([OrderQty]),
year([OrderDate]) as yearofOrder
from [Production].[Product] p join [Sales].[SalesOrderDetail]
d on p.ProductID=d.ProductID
join[Sales].[SalesOrderHeader] h
on d.SalesOrderID=h.SalesOrderID
where Name like 'Bike%' or Name like 'Sport%'
group by p.ProductID, Name, year([OrderDate])
having sum([OrderQty])>500

create view List_department_View
as
select d.DepartmentID, name, avgofRate=avg(Rate)
from [HumanResources].[Department] d join
[HumanResources].[EmployeeDepartmentHistory] e on
d.DepartmentID=e.DepartmentID
join
[HumanResources].[EmployeePayHistory] h on
e.BusinessEntityID=h.BusinessEntityID
group by d.DepartmentID, name
having avg(Rate)>30

create view vw_OrderSummary WITH ENCRYPTION
as
select year([OrderDate]) as orderYear,
month([OrderDate]) as OrderMonth,
OrderTotal=sum([OrderQty]*[UnitPrice])
from [Sales].[SalesOrderHeader] h join
[Sales].[SalesOrderDetail] d on
h.SalesOrderID=h.SalesOrderID
group by year([OrderDate]), month([OrderDate])
go
sp_helptext [List_Product_view]
sp_helptext vw_OrderSummary

create view vwProducts WITH SCHEMABINDING
as
select p.ProductID, Name, StartDate,EndDate,ListPrice
from [Production].[Product] p join
[Production].[ProductCostHistory] h on
p.ProductID=h.ProductID

create view view_Department
as
select DepartmentID, Name, GroupName
from [HumanResources].[Department]
where GroupName='Manufacturing' or GroupName='Quality Assurance'
WITH CHECK OPTION

insert view_Department values( 'nhan su', 'a')
select *from [HumanResources].[Department]
insert view_Department values( 'nhan su', 'Manufacturing')