create table type(
                     id serial primary key,
                     name varchar
);

create table product(
                        id serial primary key,
                        name varchar,
                        type_id int references type(id),
                        expired_date date,
                        price int
);

insert into type (name) values ('сыр');
insert into type (name) values ('мороженное');
insert into type (name) values ('молоко');

-- Написать запрос, получение всех продуктов с типом "СЫР"
select * from product where type_id = 1;

-- Написать запрос, получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like '%мороженное%';

-- Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where extract(month from expired_date) = extract(month from current_date + interval '1' month);

-- Написать запрос, который выводит самый дорогой продукт.
select max(price) from product;

-- Написать запрос, который выводит количество всех продуктов определенного типа.
select count(*) from product where type_id = 2;

-- Написать запрос, получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product where type_id in (1, 3);

-- Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select product.type_id from product having count(*) < 10;

-- Вывести все продукты и их тип.
select * from product as p join type as t on p.type_id = t.id;