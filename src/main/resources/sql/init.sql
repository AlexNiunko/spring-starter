create table if not exists public.company
(
    id   serial primary key,
    name varchar(255) not null unique
);


create table if not exists public.company_locale
(
    company_id  integer references company,
    lang        varchar(255) not null,
    description varchar(255),
    primary key (company_id, lang)
);

create table if not exists public.user
(
    id         bigserial primary key,
    username   varchar(255) not null unique ,
    birth_date date,
    firstname  varchar(255),
    lastname   varchar(255),
    role       varchar(255),
    company_id integer references company(id)
);

create table if not exists public.chat
(
    id    bigserial primary key,
    name  varchar(255) not null unique
);

create table if not exists public.payment
(
    id bigserial primary key,
    amount      integer not null,
    receiver_id bigint references user(id)
);

create table if not exists public.users_chats
(
    id bigserial primary key ,
    user_id bigint references user(id),
    chat_id bigint references chat(id)
)
