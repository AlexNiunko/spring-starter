--liquibase formatted sql

--changeset AlexNiunko:1
create table revision (
                          id serial not null,
                          timestamp bigint,
                          primary key (id)
);

--changeset AlexNiunko:2
create table users_aud (
                           id bigint not null,
                           rev integer not null,
                           revtype smallint,
                           birth_date date,
                           firstname varchar(255),
                           lastname varchar(255),
                           role varchar(255) check (role in ('USER','ADMIN')),
                           username varchar(255),
                           company_id integer,
                           primary key (rev, id)
);

