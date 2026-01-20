--liquibase formatted sql

--changeset AlexNiunko:1
create table if not exists public.company
(
    id   serial primary key,
    name varchar(255) not null unique
);
--rollback DROP table public.company


--changeset AlexNiunko:2
create table if not exists public.company_locale
(
    company_id  integer references company,
    lang        varchar(255) not null,
    description varchar(255),
    primary key (company_id, lang)
);

--changeset AlexNiunko:3
create table if not exists public.users
(
    id         bigserial primary key,
    username   varchar(255) not null unique ,
    birth_date date,
    firstname  varchar(255),
    lastname   varchar(255),
    role       varchar(255),
    company_id integer references company(id)
);

--changeset AlexNiunko:4
create table if not exists public.chat
(
    id    bigserial primary key,
    name  varchar(255) not null unique
);

--changeset AlexNiunko:5
create table if not exists public.payment
(
    id bigserial primary key,
    amount      integer not null,
    receiver_id bigint references users(id)
);

--changeset AlexNiunko:6
create table if not exists public.users_chats
(
    id bigserial primary key ,
    user_id bigint references users(id),
    chat_id bigint references chat(id)
)
