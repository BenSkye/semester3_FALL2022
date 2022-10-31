Create database Saleman
Drop database Saleman

USE Saleman

--drop table Salesman
create table Salesman
(salesman_id int primary key,
name varchar(20) not null,
city varchar(20) not null,
commission float  not null);
insert into salesman values(11,'JamesHoog','NewYork',0.15);
insert into salesman values(12,'NailKnite','Paris',0.13);
insert into salesman values(15,'PitAlex','London',0.11);
insert into salesman values(16,'McLyon','Paris',0.14);
insert into salesman values(5007,'PaulAdam','Rome',0.13);
insert into salesman values(5003,'LausonHen','SanJose',0.12);

--drop table customer
create table Customer
(customer_id int primary key,
cust_name varchar(20) not null,
city varchar(20) not null,
grade int not null,
salesman_id int references salesman(salesman_id) on delete set null);
insert into customer values	(3002,'NickRimando','NewYork',100,11);
insert into customer values (3007,'BradDavis','NewYork',200,12);
insert into customer values (3005,'GrahamZusi','California',200,15);
insert into customer values (3008,'JulianGreen','London',300,16);
insert into customer values (3004,'FabianJohnson','Paris',300,5007);
insert into customer values (3009,'GeoffCameron','Berlin',100,5003);

--drop table orders
create table Orders
(ord_no int primary key,
pur_amt int not null,
ord_date varchar(20) not null,
customer_id int references customer(customer_id) on delete set null,
salesman_id int references salesman(salesman_id) on delete set null);

insert into orders values(70001,150.5,'2012-10-05',3002,11);
insert into orders values(70009,270.65,'2012-09-10',3007,12);
insert into orders values(70002,65.26,'2012-10-05',3005,5006);
insert into orders values(70004,110.5,'2012-08-17',3008,5007);
insert into orders values(70007,948.5,'2012-09-10',3004,5003);
insert into orders values(70005,2400.6,'2012-07-27',3009,16);

select * from salesman;
select * from orders;
select * from customer;

delete from salesman where salesman_id = 39;

select * from salesman;
select * from orders;