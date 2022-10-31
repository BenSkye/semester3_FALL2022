select l.LocationID, l.StreetAddress, l.City, l.StateProvince, l.CountryID, count(d.LocationID) as NumberOfDepartments
from Departments d
right join Locations l
on d.LocationID = l.LocationID
group by l.LocationID, l.StreetAddress, l.City, l.StateProvince, l.CountryID
order by NumberOfDepartments ASC, l.LocationID DESC
