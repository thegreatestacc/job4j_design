create database People;

create table customers(
    id serial primary key,
    first_name varchar,
    visit_data date,
    sex boolean
);

insert into customers (first_name, visit_data, sex)
    values
('supername', (TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss')), true);

select * from customers;