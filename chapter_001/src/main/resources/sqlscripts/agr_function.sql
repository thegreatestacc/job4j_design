create table devices(
                        id serial primary key,
                        name varchar(255),
                        price float
);

create table people(
                       id serial primary key,
                       name varchar(255)
);

create table devices_people(
                               id serial primary key,
                               device_id int references devices(id),
                               people_id int references people(id)
);

insert into devices (name, price) values ('phone', 10000);
insert into devices (name, price) values ('TV', 5000);
insert into devices (name, price) values ('PS5', 15000);

insert into people (name) values ('Vladimir');
insert into people (name) values ('Antonina');
insert into people (name) values ('Olga');

insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (3, 1);

-- Используя агрегатные функции вывести среднюю цену устройств
select avg(price) from devices;

-- Используя группировку вывести для каждого человека среднюю цену его устройств
select d.name, avg(price) from devices_people as dp join devices as d on dp.device_id = d.id
group by d.name;

--Дополнить запрос п.4. условием, что цена устройства должны быть больше 5000
select d.name, avg(price) from devices_people as dp join devices as d on dp.device_id = d.id
group by d.name having avg(price) > 5000;