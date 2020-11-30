create database new_database;

create table users(
                      id serial primary key,
                      users_name varchar
);
create table role(
                     id serial primary key,
                     role_name varchar
);

create table rules(
                     id serial primary key,
                     rule_name varchar
);

create table item(
                     id serial primary key,
                     item_name varchar
);

create table comments(
                         id serial primary key,
                         message_text varchar
);

create table attaches(
                         id serial primary key,
                         article varchar
);

create table category(
                         id serial primary key,
                         description varchar
);

create table state(
                      id serial primary key,
                      description varchar
);

-- many-to-one
create table users_role(
                           id serial primary key,
                           name varchar,
                           users_id int references users(id)
);

-- many-to-many
create table role_rules (
                            id serial primary key,
                            role_id int references role(id),
                            rule_id int references rules(id)
);

-- many-to-one
create table item_users(
                           id serial primary key,
                           name varchar,
                           item_id int references item(id)
);

-- one-to-many
create table item_comments(
                              id serial primary key,
                              name varchar,
                              comment_id int references comments(id)
);

-- one-to-many
create table item_attaches(
                              id serial primary key,
                              name varchar,
                              item_id int references item(id)
);

-- many-to-one
create table item_category(
                              id serial primary key,
                              name varchar,
                              category_id int references category(id)
);

-- many-to-one
create table item_state(
                           id serial primary key,
                           name varchar,
                           item_id int references item(id)
);

-- заполнение таблиц данными
insert into users (users_name) values ('Ivan');
insert into users (users_name) values ('Kirill');
insert into users (users_name) values ('Roman');

insert into role (role_name) values ('Developer');
insert into role (role_name) values ('Manager');
insert into role (role_name) values ('Counter');

insert into rules (rule_name) values ('first_rule');
insert into rules (rule_name) values ('second_rule');
insert into rules (rule_name) values ('third_rule');

insert into item (item_name) values ('sphere');
insert into item (item_name) values ('triangle');
insert into item (item_name) values ('square');

insert into comments (message_text) values ('first message');
insert into comments (message_text) values ('second message');
insert into comments (message_text) values ('third message');

insert into attaches (article) values ('first article');
insert into attaches (article) values ('second article');
insert into attaches (article) values ('third article');

insert into category (description) values ('first desc');
insert into category (description) values ('second desc');
insert into category (description) values ('third desc');

insert into state (description) values ('first state desc');
insert into state (description) values ('second state desc');
insert into state (description) values ('third state desc');