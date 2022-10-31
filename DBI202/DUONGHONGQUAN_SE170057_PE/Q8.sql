select e.JobID,j.JobTitle ,count(e.JobID) as NumberOfEmployees,AVG(e.Salary) As AverageOfEmployees
from Employees e, Jobs j
where j.JobID = e.JobID
group by e.JobID,j.JobTitle
having count(e.JobID) >= 20
Order by AVG(e.Salary) asc
