--liquibase formatted sql

--changeset AlexNiunko:1
ALTER table users
ADD COLUMN image VARCHAR(64);

--changeset AlexNiunko:2
ALTER table users_aud
ADD COLUMN image VARCHAR(64);

