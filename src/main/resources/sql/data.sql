INSERT INTO public.company (id, name)
VALUES (1, 'Microsoft'),
       (2, 'Apple'),
       (3, 'Google');

INSERT INTO public.company_locale (company_id, lang, description)
VALUES (1, 'en', 'Leading software and cloud computing company'),
       (1, 'ru', 'Ведущая компания в области ПО и облачных вычислений'),
       (1, 'de', 'Führendes Software- und Cloud-Computing-Unternehmen'),
       (1, 'fr', 'Entreprise leader en logiciels et informatique cloud'),
       (1, 'es', 'Empresa líder en software y computación en la nube');

INSERT INTO public.users (id, birth_date, firstname, lastname, role, username, company_id)
VALUES (1, '1955-10-28', 'Bill', 'Gates', 'USER', 'bill@mail.ru', 1),
       (2, '1955-02-24', 'Steve', 'Jobs', 'USER', 'jobs@gmai.com', 2),
       (3, '1973-08-21', 'Sergey', 'Brin', 'USER', 'brin@tut.by', 3),
       (4, '1960-11-01', 'Tim', 'Cook', 'USER', 'cook@yandex.ru', 2),
       (5, '1955-01-01', 'Dianne', 'Greene', 'USER', 'creene@gmail.com', 3);


INSERT INTO public.payment (id, amount, receiver_id)
VALUES (1, 100, 1),
       (2, 300, 1),
       (3, 500, 1),
       (4, 250, 2),
       (5, 600, 2),
       (6, 500, 2),
       (7, 400, 4),
       (8, 300, 4),
       (9, 500, 3),
       (10, 500, 3);

INSERT INTO public.chat (id, name)
VALUES (1, 'dmdev'),
       (2, 'java'),
       (3, 'youtube-members');

INSERT INTO public.users_chats (id, user_id, chat_id) VALUES
                                                          (1, 1, 1),
                                                          (2, 2, 2),
                                                          (3, 3, 2),
                                                          (4, 4, 2),
                                                          (5, 1, 3),
                                                          (6, 2, 3),
                                                          (7, 3, 1),
                                                          (8, 5, 2);
