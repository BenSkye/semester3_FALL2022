CREATE OR ALTER PROC PR1 @departmentID int, @numberOfEmployees int output
AS
BEGIN
	DECLARE @num int
	SELECT @num = COUNT(*) FROM Employees e WHERE e.DepartmentID = @departmentID
	SET @numberOfEmployees = @num
END
