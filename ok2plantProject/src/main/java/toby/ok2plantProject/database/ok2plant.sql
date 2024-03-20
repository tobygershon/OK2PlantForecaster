begin transaction;

drop table if exists users, search_data, zip_map;

create table users (
    user_id serial primary key,
    email varchar(50) NOT NULL unique
);

create table search_data (
    search_id serial primary key,
    user_id integer check (user_id > 0) references users (user_id),
    zip varchar(10) NOT NULL,
    request_follow_up boolean,
    search_date date,
    ok_2_plant_date date
);

create table zip_map (
    zip varchar(10) primary key,
    first_frost_date date NOT NULL
);

commit transaction;

