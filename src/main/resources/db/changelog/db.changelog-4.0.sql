--liquibase formatted sql

--changeset AlexNiunko:1
ALTER table users
    ADD COLUMN password VARCHAR(128) DEFAULT '{noop}123';

--changeset AlexNiunko:2
ALTER table users_aud
    ADD COLUMN password VARCHAR(128);

