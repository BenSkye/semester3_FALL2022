SELECT e.EmployeeID, e.FirstName,e.LastName, j.JobTitle, d.DepartmentName,year(e.HireDate) as yearHireDate
FROM Jobs j JOIN Employees e ON j.JobID=e.JobID
			JOIN Departments d ON e.DepartmentID=d.DepartmentID
WHERE year(e.HireDate)=2004
