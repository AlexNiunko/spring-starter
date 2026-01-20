INSERT INTO public.company (name)
VALUES ('Microsoft'),
       ('Apple'),
       ('Google');

INSERT INTO public.company_locale (company_id, lang, description)
VALUES (1, 'en', 'Leading software and cloud computing company'),
       (1, 'ru', 'Ведущая компания в области ПО и облачных вычислений'),
       (2, 'de', 'Führendes Software- und Cloud-Computing-Unternehmen'),
       (2, 'fr', 'Entreprise leader en logiciels et informatique cloud'),
       (3, 'es', 'Empresa líder en software y computación en la nube');

INSERT INTO public.user (birth_date, firstname, lastname, role, username, company_id)
VALUES ('1955-10-28', 'Bill', 'Gates', 'USER', 'bill@mail.ru', 1),
       ('1955-02-24', 'Steve', 'Jobs', 'USER', 'jobs@gmai.com', 2),
       ('1973-08-21', 'Sergey', 'Brin', 'USER', 'brin@tut.by', 3),
       ('1960-11-01', 'Tim', 'Cook', 'USER', 'cook@yandex.ru', 2),
       ('1955-01-01', 'Dianne', 'Greene', 'USER', 'creene@gmail.com', 3);


INSERT INTO public.payment (amount, receiver_id)
VALUES (100, 1),
       (300, 1),
       (500, 1),
       (250, 2),
       (600, 2),
       (500, 2),
       (400, 3),
       (300, 3),
       (500, 2),
       (500, 3);

INSERT INTO public.chat ( name)
VALUES ('dmdev'),
       ('java'),
       ('youtube-members');

INSERT INTO public.users_chats (user_id, chat_id)
VALUES (1, 2),
       (2, 2),
       (3, 2),
       (3, 2),
       (1, 3),
       (2, 3),
       (3, 1),
       (3, 2);
