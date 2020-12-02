create table person(
                       id serial primary key,
                       person_name varchar
);

create table pet(
                    id serial primary key,
                    pet_type varchar,
                    person_id int references person(id)
);

insert into person(person_name) values ('Vladimir');
insert into person(person_name) values ('Antonina');
insert into person(person_name) values ('George');
insert into person(person_name) values ('Olga');

insert into pet(pet_type, person_id) values ('Dog', 1);
insert into pet(pet_type, person_id) values ('Cat', 2);
insert into pet(pet_type, person_id) values ('Rat', 2);
insert into pet(pet_type, person_id) values ('Parrot', 4);

-- вывод всех данных о людях и животных
select * from pet join person as p on pet.person_id = p.id;

-- вывод людей, у кого есть петомцы
select person_name from person join pet as p on p.person_id = person.id;

-- вывод людей, у кого есть петомцы с измененным названием колонки
select person_name as Owner_Name from pet join person as p on pet.person_id = p.id;