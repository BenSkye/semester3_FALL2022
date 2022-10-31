CREATE DATABASE LAB4
DROP DATABASE LAB4
USE LAB4

create table Movie(mID int, title varchar(30), year int, director varchar(30));
create table Reviewer(rID int, name varchar(30));
create table Rating(rID int, mID int, stars int, ratingDate date);

/* Populate the tables with our data */
insert into Movie values(101, 'Gone with the Wind', 1939, 'Victor Fleming');
insert into Movie values(102, 'Star Wars', 1977, 'George Lucas');
insert into Movie values(103, 'The Sound of Music', 1965, 'Robert Wise');
insert into Movie values(104, 'E.T.', 1982, 'Steven Spielberg');
insert into Movie values(105, 'Titanic', 1997, 'James Cameron');
insert into Movie values(106, 'Snow White', 1937, null);
insert into Movie values(107, 'Avatar', 2009, 'James Cameron');
insert into Movie values(108, 'Raiders of the Lost Ark', 1981, 'Steven Spielberg');

insert into Reviewer values(201, 'Sarah Martinez');
insert into Reviewer values(202, 'Daniel Lewis');
insert into Reviewer values(203, 'Brittany Harris');
insert into Reviewer values(204, 'Mike Anderson');
insert into Reviewer values(205, 'Chris Jackson');
insert into Reviewer values(206, 'Elizabeth Thomas');
insert into Reviewer values(207, 'James Cameron');
insert into Reviewer values(208, 'Ashley White');

insert into Rating values(201, 101, 2, '2011-01-22');
insert into Rating values(201, 101, 4, '2011-01-27');
insert into Rating values(202, 106, 4, null);
insert into Rating values(203, 103, 2, '2011-01-20');
insert into Rating values(203, 108, 4, '2011-01-12');
insert into Rating values(203, 108, 2, '2011-01-30');
insert into Rating values(204, 101, 3, '2011-01-09');
insert into Rating values(205, 103, 3, '2011-01-27');
insert into Rating values(205, 104, 2, '2011-01-22');
insert into Rating values(205, 108, 4, null);
insert into Rating values(206, 107, 3, '2011-01-15');
insert into Rating values(206, 106, 5, '2011-01-19');
insert into Rating values(207, 107, 5, '2011-01-20');
insert into Rating values(208, 104, 3, '2011-01-02');
-- 1. Find the titles of all movies directed by Steven Spielberg.

SELECT title
FROM Movie
WHERE director = 'Steven Spielberg';


-- 2. Find all years that have a movie that received a rating of 4 or 5, and sort them in increasing order.

SELECT DISTINCT year
FROM Movie, Rating
WHERE Movie.mId = Rating.mId AND stars IN (4, 5)
ORDER BY year;



-- 3. Find the titles of all movies that have no ratings. 

SELECT title
FROM Movie
WHERE mId NOT IN (SELECT mID FROM Rating);


-- 4. Some reviewers didn't provide a date with their rating. Find the names of all reviewers who have ratings with a NULL value for the date. 

SELECT name
FROM Reviewer
INNER JOIN Rating ON Reviewer.rID = Rating.rID
WHERE ratingDate IS NULL;


-- 5. Write a query to return the ratings data in a more readable format: reviewer name, movie title, stars, and ratingDate. Also, sort the data, first by reviewer name, then by movie title, and lastly by number of stars. 

SELECT name, title, stars, ratingDate
FROM Movie, Rating, Reviewer
WHERE Movie.mId = Rating.mId AND Reviewer.rId = Rating.rId
ORDER BY name, title, stars;



-- 6. For all cases where the same reviewer rated the same movie twice and gave it a higher rating the second time, return the reviewer's name and the title of the movie. 

SELECT name, title
FROM Movie
INNER JOIN Rating R1 ON Movie.mID = R1.mID
INNER JOIN Rating R2 ON R1.rID = R2.rID
INNER JOIN Reviewer ON Reviewer.rID = R2.rID
WHERE R1.mId = R2.mId AND R1.ratingDate < R2.ratingDate AND R1.stars < R2.stars;



-- 7. For each movie that has at least one rating, find the highest number of stars that movie received. Return the movie title and number of stars. Sort by movie title. 


SELECT title, max(stars)
FROM Movie
JOIN Rating ON Rating.mID = Movie.mID
GROUP BY Rating.mID, title
ORDER BY title ASC;


-- 8. For each movie, return the title and the 'rating spread', that is, the difference between highest and lowest ratings given to that movie. Sort by rating spread from highest to lowest, then by movie title. 

SELECT title, max(stars)-min(stars) AS rating_spread
FROM Movie
JOIN Rating ON Rating.mID = Movie.mID
GROUP BY Rating.mID, title
ORDER BY rating_spread DESC, title ASC;

-- 9. Find the difference between the average rating of movies released before 1980 and the average rating of movies released after 1980. (Make sure to calculate the average rating for each movie, then the average of those averages for movies before 1980 and movies after. Don't just calculate the overall average rating before and after 1980.) 

SELECT AVG(Before1980.avg) - AVG(After1980.avg)
FROM (
  SELECT AVG(stars) AS avg
  FROM Movie
  INNER JOIN Rating ON Movie.mID = Rating.mID
  WHERE year < 1980
  GROUP BY Movie.mID
) AS Before1980, (
  SELECT AVG(stars) AS avg
  FROM Movie
  INNER JOIN Rating ON Movie.mID = Rating.mID
  WHERE year > 1980
  GROUP BY Movie.mID
) AS After1980;


-- 10. Find the names of all reviewers who rated Gone with the Wind. 

SELECT DISTINCT name
FROM Rating
INNER JOIN Movie
ON Movie.mID = Rating.mID
INNER JOIN Reviewer
ON Reviewer.rID = Rating.rID
WHERE title = 'Gone with the Wind';

-- 11. For any rating where the reviewer is the same as the director of the movie, return the reviewer name, movie title, and number of stars. 

SELECT name, title, stars
FROM Rating
INNER JOIN Movie
ON Movie.mID = Rating.mID
INNER JOIN Reviewer
ON Reviewer.rID = Rating.rID
WHERE name = director;


-- 12. Return all reviewer names and movie names together in a single list, alphabetized. (Sorting by the first name of the reviewer and first word in the title is fine; no need for special processing on last names or removing "The".) 

SELECT name as cname from Reviewer
union all
select title as cname from Movie
ORDER BY cname ASC;


-- 13. Find the titles of all movies not reviewed by Chris Jackson. 

SELECT title
FROM Movie
WHERE mID NOT IN ( 
SELECT mID FROM Rating INNER JOIN REVIEWER ON rating.rID = reviewer.rid 
WHERE name = 'Chris Jackson'
);


-- 14. For all pairs of reviewers such that both reviewers gave a rating to the same movie, return the names of both reviewers. Eliminate duplicates, don't pair reviewers with themselves, and include each pair only once. For each pair, return the names in the pair in alphabetical order.

SELECT DISTINCT Re1.name, Re2.name
FROM Rating R1, Rating R2, Reviewer Re1, Reviewer Re2
WHERE R1.mID = R2.mID
AND R1.rID = Re1.rID
AND R2.rID = Re2.rID
AND Re1.name < Re2.name
ORDER BY Re1.name, Re2.name;


-- 15. For each rating that is the lowest (fewest stars) currently in the database, return the reviewer name, movie title, and number of stars.

SELECT name, title, stars
FROM MOVIE
INNER JOIN RATING ON Movie.mID = Rating.mID
INNER JOIN REVIEWER ON Reviewer.rID = Rating.rID
WHERE stars = (SELECT MIN(stars) FROM RATING);


-- 16. List movie titles and average ratings, from highest-rated to lowest-rated. If two or more movies have the same average rating, list them in alphabetical order. 

SELECT title, AVG(Stars)
FROM MOVIE
INNER JOIN RATING ON Movie.mID = Rating.mID
INNER JOIN REVIEWER ON Reviewer.rID = Rating.rID
GROUP BY rating.mid, title
ORDER BY AVG(STARS) DESC, title ASC;


-- 17. Find the names of all reviewers who have contributed three or more ratings.

SELECT name
FROM Reviewer
WHERE (SELECT COUNT(*) FROM Rating WHERE Rating.rId = Reviewer.rId) >= 3;


-- At least 3 ratings to different movies (Remainder to myself)

SELECT name
FROM Reviewer
WHERE (SELECT COUNT(DISTINCT mId) FROM Rating WHERE Rating.rId = Reviewer.rId) >= 3;


-- 18. Some directors directed more than one movie. For all such directors, return the titles of all movies directed by them, along with the director name. Sort by director name, then movie title.

SELECT title, director
FROM Movie M1
WHERE (SELECT COUNT(*) FROM Movie M2 WHERE M1.director = M2.director) > 1
ORDER BY director, title;




-- 19. Find the movie(s) with the highest average rating. Return the movie title(s) and average rating.

SELECT title, AVG(stars) as average
FROM MOVIE
INNER JOIN RATING ON Movie.mID = Rating.mID
INNER JOIN REVIEWER ON Reviewer.rID = Rating.rID
GROUP BY movie.mID, title
HAVING  AVG(stars) = (
SELECT MAX(avg_stars)
FROM (
SELECT title, AVG(stars) AS avg_stars
FROM MOVIE
INNER JOIN RATING ON Rating.mID = Movie.mID
GROUP BY movie.mID, title
) I
);

-- 20. Find the movie(s) with the lowest average rating. Return the movie title(s) and average rating.

SELECT title, AVG(stars) as average
FROM MOVIE
INNER JOIN RATING ON Movie.mID = Rating.mID
INNER JOIN REVIEWER ON Reviewer.rID = Rating.rID
GROUP BY movie.mID, title
HAVING  AVG(stars) = (
SELECT MIN(avg_stars)
FROM (
SELECT title, AVG(stars) AS avg_stars
FROM MOVIE
INNER JOIN RATING ON Rating.mID = Movie.mID
GROUP BY movie.mID, title
) I
);

-- 21. For each director, return the director's name together with the title(s) of the movie(s) they directed that received the highest rating among all of their movies, and the value of that rating. Ignore movies whose director is NULL. 

SELECT director, title, MAX(stars)
FROM Movie
INNER JOIN RATING ON rating.mID = movie.mID
WHERE DIRECTOR is NOT NULL
GROUP BY DIRECTOR, title;


-- 22. Add the reviewer Roger Ebert to your database, with an rID of 209.

INSERT INTO Reviewer
VALUES (209, 'Roger Ebert');


-- 23. Insert 5-star ratings by James Cameron for all movies in the database. Leave the review date as NULL.

INSERT INTO Rating
SELECT (SELECT rId FROM Reviewer WHERE name = 'James Cameron'), mId, 5, NULL
FROM Movie;


-- 24. For all movies that have an average rating of 4 stars or higher, add 25 to the release year. (Update the existing tuples; don't insert new tuples.)

UPDATE Movie
SET year = year + 25
WHERE Movie.mID  IN (
  SELECT Movie.mID
  FROM Movie
  INNER JOIN Rating ON Movie.mID=Rating.mID
  GROUP BY Movie.mID
  HAVING AVG(stars) >= 4
);


-- 25. Remove all ratings where the movie's year is before 1970 or after 2000, and the rating is fewer than 4 stars.

DELETE FROM Rating
WHERE mId IN (
  SELECT mId
  FROM Movie
  WHERE year < 1970 OR year > 2000
) AND stars < 4;