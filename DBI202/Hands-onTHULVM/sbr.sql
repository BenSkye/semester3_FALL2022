CREATE DATABASE SBR
USE SBR

CREATE TABLE s(
sid int,
sname varchar(20) not null,
rating varchar(20) not null,
age int not null

)
insert into s values(22,'Dustin',7,45)
insert into s values(29,'Brutus',1,33)
insert into s values(31,'Lubber',8,55.5)
insert into s values(32,'Andy',8,25.5)
insert into s values(58,'Rusty',10,35)
insert into s values(64,'Horatio',7,355)
insert into s values(71,'Zorba',10,16)
insert into s values(74,'Horatio',9,40)
insert into s values(85,'Art',3,25.5)
insert into s values(88,'Albert',3,25.5)
CREATE TABLE b(
bid int not null,
bname varchar(20)not null,
color varchar(20) not null

)
insert into b values(101,'Interlake','blue')
insert into b values(102,'Interlake','red')
insert into b values(103,'Clipper','green')
insert into b values(104,'Marine','red')

CREATE TABLE r(
sid int not null,
bid int not null,
date datetime not null
)
insert into r values(22,101,'1998-10-10')
insert into r values(22,102,'1998-10-10')
insert into r values(22,103,'1998-10-8')
insert into r values(22,104,'1998-10-7')
insert into r values(31,102,'1998-11-10')
insert into r values(31,103,'1998-11-6')
insert into r values(31,104,'1998-11-12')
insert into r values(64,101,'1998-9-5')
insert into r values(64,102,'1998-9-8')
insert into r values(74,103,'1998-9-8')
insert into r values(88,104,'1998-11-8')
insert into r values(88,102,'1998-10-10')



--1
SELECT color
FROM s, b, r
WHERE r.sid=s.sid AND r.bid=b.bid AND
sname='Albert'

--2.
(SELECT sid
FROM s
WHERE rating>=8)
UNION
(SELECT sid
FROM r
WHERE bid=103)

--3.
SELECT sname
FROM s 
WHERE sid NOT IN
(SELECT s.sid
FROM r, s
WHERE r.sid=s.sid AND sname LIKE ('%storm%'))
ORDER BY s.sname

--4.
SELECT sid
FROM s
WHERE age>20 AND sid NOT IN
(SELECT sid
FROM r, b
WHERE r.bid=b.bid AND bname LIKE ('%thunder%'))

--5.
SELECT sname
FROM s, r r1, r r2
WHERE s.sid=r1.sid AND s.sid=r2.sid AND
r1.bid<>r2.bid

--6.
SELECT sname
FROM s
WHERE NOT EXISTS (
SELECT *
FROM b
WHERE NOT EXISTS (
SELECT *
FROM r
WHERE r.sid=s.sid AND r.bid=b.bid))

--7.
SELECT sname
FROM s
WHERE NOT EXISTS (
SELECT *
FROM b
WHERE bname LIKE 'typhoon%' AND NOT EXISTS (
SELECT *
FROM r
WHERE r.sid=s.sid AND r.bid=b.bid))

--8.
SELECT s1.sid
FROM s s1, s s2
WHERE s1.rating>s2.rating AND s2.sname='Bob'

--9.
SELECT sid
FROM s
WHERE rating > all (
SELECT rating
FROM s s2
WHERE s2.sname='Bob')
--10.
SELECT sid
FROM s s1
WHERE s1.rating >= all (
SELECT rating
FROM s)
--11.
SELECT s1.sname, s1.age
FROM s s1
WHERE NOT EXISTS (
SELECT *
FROM s s2
WHERE s2.age>s1.age)
--12.
SELECT s1.sname
FROM s s1
WHERE NOT EXISTS (
SELECT *
FROM b, r, s s2
WHERE s2.sid=r.sid AND s2.sid =b.bid AND s2.rating<s1.rating AND
NOT EXISTS (
SELECT *
FROM r r2
WHERE s1.sid=r2.sid AND r2.bid=b.bid))

--13.
SELECT rating, AVG(age)
FROM s
GROUP BY rating
--14.
SELECT b.bid, AVG(age)
FROM b, r, s
WHERE b.bid=r.bid AND r.sid=s.sid
GROUP BY b.bid
HAVING 5<=COUNT(DISTINCT r.sid)
--15.
SELECT b.bid, AVG(age)
FROM b, r, s
WHERE s.age>=40 AND b.bid=r.bid AND s.sid=r.sid
GROUP BY b.bid
HAVING 5<=COUNT(DISTINCT s.sid)
--16.
SELECT b1.bid, AVG(s.age)
FROM r, s (SELECT bid
FROM b, r r2, s s2
WHERE b.bid=r2.bid AND s2.sid=r.sid AND s.age>=40
GROUP BY b.bid
HAVING 5<=COUNT(DISTINCT r2.sid)) b1
WHERE b1.bid=r.bid AND r.sid=s.sid
GROUP BY b1.bid

---------------------






