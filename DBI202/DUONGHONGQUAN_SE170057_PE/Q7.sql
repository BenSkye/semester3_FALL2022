SELECT e.EmployeeID,e.FirstName,e.LastName,e.Salary
FROM Employees e
WHERE e.Salary>
	(SELECT e.Salary
	FROM Employees e
	WHERE e.FirstName='Janette' AND e.LastName='King')
