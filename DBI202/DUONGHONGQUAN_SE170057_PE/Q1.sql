
CREATE TABLE Student
(
	StudentID int PRIMARY KEY,
	DOB datetime,
	StudentName Varchar (20),
	Gender Varchar (10),
	CourseID int,
	CONSTRAINT Fore1 FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
)
CREATE TABLE Course
(
	CourseID int PRIMARY KEY,
	CourseName Varchar (20),
	LecturerID int,
	 CONSTRAINT Fore2 FOREIGN KEY (LecturerID) REFERENCES Lecturer(LecturerID)

)
CREATE TABLE Lecturer
(
	LecturerID int PRIMARY KEY,
	LecturerName Varchar (20),
)
