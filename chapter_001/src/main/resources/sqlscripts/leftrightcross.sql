--Даны две сущности, представленные в таблицах departments и employees.
-- Отношение one-to-many. В таблицах только поле name.
--1. Создать таблицы и заполнить их начальными данными
create table departments(
                            id serial primary key,
                            name varchar
);

create table employee(
                         id serial primary key,
                         name varchar,
                         department_id int references departments(id)
);

insert into departments(name) values ('first department');
insert into departments(name) values ('second department');
insert into departments(name) values ('third department');
insert into departments(name) values ('fourth department');
insert into departments(name) values ('fifth department');

insert into employee(name, department_id) values ('Jack', 1);
insert into employee(name, department_id) values ('Bob', 1);
insert into employee(name, department_id) values ('Emily', 2);
insert into employee(name, department_id) values ('Jhon', 2);
insert into employee(name, department_id) values ('Vladimir', 3);
insert into employee(name, department_id) values ('Antonina', 3);
insert into employee(name, department_id) values ('Oleg', 4);
insert into employee(name, department_id) values ('Olga', 4);
insert into employee(name, department_id) values ('Alex', 5);
insert into employee(name, department_id) values ('Jessica', 5);
insert into employee(name, department_id) values ('Tim', null);
insert into employee(name, department_id) values ('Jane', null);

--2. Выполнить запросы с left, rigth, full, cross соединениями
-- left
select * from employee as e left join departments as d on e.department_id = d.id;
-- right
select * from departments as d right join employee as e on e.department_id = d.id;
-- full
select * from employee e full join departments d on d.id = e.department_id;
-- cross
select * from employee e cross join departments d;

--3. Используя left join найти работников, которые не относятся ни к одну
-- из департаментов
select * from employee as e left join departments as d on e.department_id = d.id
where d.id is null;

--4. Используя left и right join написать запросы, которые давали бы
-- одинаковый результат.
select * from employee e right join departments d on d.id = e.department_id;
select * from departments d left join employee e on d.id = e.department_id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
create table teens(
                      id serial primary key,
                      name varchar,
                      gender varchar
);

insert into teens(name, gender) values ('Bob', 'm');
insert into teens(name, gender) values ('Jessica', 'f');
insert into teens(name, gender) values ('Vladimir', 'm');
insert into teens(name, gender) values ('Olga', 'f');
insert into teens(name, gender) values ('Jack', 'm');
insert into teens(name, gender) values ('Jane', 'f');
insert into teens(name, gender) values ('Tim', 'm');
insert into teens(name, gender) values ('Emily', 'f');

-- Используя cross join составить все возможные разнополые пары
select t1.name, t2.name from teens as t1 cross join teens t2 where t1.gender != t2.gender;


