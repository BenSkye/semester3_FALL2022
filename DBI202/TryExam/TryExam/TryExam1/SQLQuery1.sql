	USE PE_DBI202_F2019
	--2
SELECT * FROM dbo.Locations WHERE CountryID IN ('US','UK')
--3.
SELECT e.EmployeeID,e.FirstName,e.LastName,e.Salary,e.Commission_pct,e.HireDate FROM dbo.Employees e WHERE e.Salary BETWEEN 4000 AND 10000
--4.
SELECT  e.EmployeeID,e.FirstName,e.LastName,e.HireDate,j.JobID,j.JobTitle,e.DepartmentID,d.DepartmentName 
FROM Employees e , Jobs j,Departments d 
WHERE e.JobID=j.JobID AND e.DepartmentID=d.DepartmentID  
AND JobTitle = 'Stock Clerk'

--5.
SELECT j.JobID,j.JobTitle, COUNT(*) AS [NO] FROM Employees e,Jobs j WHERE e.JobID=j.JobID  GROUP BY j.JobID,j.JobTitle ORDER BY [NO] DESC
--6.
Select * from Countries
where CountryID not in 
(Select distinct  l.CountryID from Locations l, Departments d 
where l.LocationID = d.LocationID);
