SELECT j.JobID,j.JobTitle,j.max_salary
FROM Jobs j
WHERE j.jobTitle LIKE '%Manager%'
ORDER BY j.max_salary asc, j.JobID desc
