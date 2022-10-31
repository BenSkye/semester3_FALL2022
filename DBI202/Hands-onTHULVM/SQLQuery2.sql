USE Saleman

SELECT salesman_id,name,city,commission FROM Salesman
SELECT DISTINCT ord_no,pur_amt,ord_date,customer_id,salesman_id  FROM Orders
SELECT name,city FROM Salesman WHERE city ='Paris'
SELECT * FROM Customer WHERE grade =200
SELECT ord_no,ord_date,pur_amt FROM Orders WHERE salesman_id = 5003

-------------------
SELECT *FROM Customer WHERE city = 'NewYork' OR NOT GRADE > 100
SELECT * FROM Salesman WHERE commission BETWEEN 0.1 AND 0.12
SELECT * FROM Customer WHERE cust_name LIKE '%n'
SELECT * FROM Salesman WHERE name LIKE 'N__I%'
SELECT * FROM  Customer WHERE grade IS NULL
SELECT SUM (pur_amt) AS [Total puNrchases] FROM Orders
SELECT COUNT(salesman_id) AS [No Saleman] FROM  Orders
SELECT COUNT(*) AS [No date] FROM Orders WHERE ord_date = '2012-10-05'
SELECT city, MAX(grade) AS[Highest grade] FROM Customer GROUP BY city
SELECT customer_id,MAX(pur_amt) FROM Orders GROUP BY customer_id
SELECT customer_id,ord_date,MAX(pur_amt) FROM Orders GROUP BY customer_id, ord_date
SELECT salesman_id,MAX(pur_amt) FROM Orders WHERE  ord_date = '2012-10-05'GROUP BY salesman_id
SELECT customer_id,ord_date,MAX(pur_amt) FROM Orders GROUP BY customer_id,ord_date HAVING MAX (pur_amt)>2000
