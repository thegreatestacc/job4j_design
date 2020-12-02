--Нужно написать SQL скрипты:
--Создать структур данных в базе.
--Таблицы.
--   Кузов. Двигатель, Коробка передач.
create table carcase(
                        id serial primary key,
                        name varchar
);

create table engine(
                       id serial primary key,
                       name varchar
);

create table transmission(
                             id serial primary key,
                             name varchar
);

--Создать структуру Машина.
create table car(
                    id serial primary key,
                    name varchar,
                    carcase_id int references carcase(id),
                    engine_id int references engine(id),
                    transmission_id int references transmission(id)
);

--Машина не может существовать без данных из п.1.
--Заполнить таблицы через insert.
insert into carcase (name) values ('carcase1');
insert into carcase (name) values ('carcase2');
insert into carcase (name) values ('carcase3');

insert into engine (name) values ('engine1');
insert into engine (name) values ('engine2');
insert into engine (name) values ('engine3');

insert into transmission (name) values ('transmission1');
insert into transmission (name) values ('transmission2');
insert into transmission (name) values ('transmission3');

insert into car (name, carcase_id, engine_id, transmission_id) values ('mercedes', 1, 2, 3);
insert into car (name, carcase_id, engine_id, transmission_id) values ('bmw', 2, 3, 1);
insert into car (name, carcase_id, engine_id, transmission_id) values ('audi', 3, 1, 2);

insert into car (name, carcase_id, engine_id, transmission_id) values ('lada', null, null, null);
insert into car (name, carcase_id, engine_id, transmission_id) values ('kopeyka', 2, null, 3);

--Создать SQL запросы:
-- 1. Вывести список всех машин и все привязанные к ним детали.
select * from car;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.