INSERT INTO public.company (id,name)
VALUES (1,'Microsoft'),
       (2,'Apple'),
       (3,'Google');

SELECT SETVAL('company_id_seq',(SELECT MAX(id) from public.company));

INSERT INTO public.company_locale (company_id, lang, description)
VALUES (
           (SELECT id FROM public.company WHERE name = 'Microsoft'),
           'en',
           'Leading software and cloud computing company'
       ),
       (
           (SELECT id FROM public.company WHERE name = 'Microsoft'),
           'ru',
           'Ведущая компания в области ПО и облачных вычислений'
       ),
       (
           (SELECT id FROM public.company WHERE name = 'Apple'),
           'de',
           'Führendes Software- und Cloud-Computing-Unternehmen'
       ),
       (
           (SELECT id FROM public.company WHERE name = 'Apple'),
           'fr',
           'Entreprise leader en logiciels et informatique cloud'
       ),
       (
           (SELECT id FROM public.company WHERE name = 'Google'),
           'es',
           'Empresa líder en software y computación en la nube'
       );

INSERT INTO public.users (id,birth_date, firstname, lastname, role, username, company_id)
VALUES (
           1,'1955-10-28', 'Bill', 'Gates', 'USER', 'bill@mail.ru',
           (SELECT id FROM public.company WHERE name = 'Microsoft')
       ),
       (
           2,'1955-02-24', 'Steve', 'Jobs', 'USER', 'jobs@gmai.com',
           (SELECT id FROM public.company WHERE name = 'Apple')
       ),
       (
           3,'1973-08-21', 'Sergey', 'Brin', 'USER', 'brin@tut.by',
           (SELECT id FROM public.company WHERE name = 'Google')
       ),
       (
           4,'1960-11-01', 'Tim', 'Cook', 'USER', 'cook@yandex.ru',
           (SELECT id FROM public.company WHERE name = 'Apple')
       ),
       (
           5,'1955-01-01', 'Dianne', 'Greene', 'USER', 'creene@gmail.com',
           (SELECT id FROM public.company WHERE name = 'Google')
       );

SELECT SETVAL('users_id_seq',(SELECT MAX(id) from public.users));

INSERT INTO public.payment (amount, receiver_id)
VALUES (
           100,
           (SELECT id FROM public.users WHERE username = 'bill@mail.ru')
       ),
       (
           300,
           (SELECT id FROM public.users WHERE username = 'bill@mail.ru')
       ),
       (
           500,
           (SELECT id FROM public.users WHERE username = 'bill@mail.ru')
       ),
       (
           250,
           (SELECT id FROM public.users WHERE username = 'jobs@gmai.com')
       ),
       (
           600,
           (SELECT id FROM public.users WHERE username = 'jobs@gmai.com')
       ),
       (
           500,
           (SELECT id FROM public.users WHERE username = 'jobs@gmai.com')
       ),
       (
           400,
           (SELECT id FROM public.users WHERE username = 'brin@tut.by')
       ),
       (
           300,
           (SELECT id FROM public.users WHERE username = 'brin@tut.by')
       ),
       (
           500,
           (SELECT id FROM public.users WHERE username = 'jobs@gmai.com')
       ),
       (
           500,
           (SELECT id FROM public.users WHERE username = 'brin@tut.by')
       );

INSERT INTO public.chat (name)
VALUES ('dmdev'),
       ('java'),
       ('youtube-members');

INSERT INTO public.users_chats (user_id, chat_id)
VALUES (
           (SELECT id FROM public.users WHERE username = 'bill@mail.ru'),
           (SELECT id FROM public.chat WHERE name = 'java')
       ),
       (
           (SELECT id FROM public.users WHERE username = 'jobs@gmai.com'),
           (SELECT id FROM public.chat WHERE name = 'java')
       ),
       (
           (SELECT id FROM public.users WHERE username = 'brin@tut.by'),
           (SELECT id FROM public.chat WHERE name = 'java')
       ),
       (
           (SELECT id FROM public.users WHERE username = 'brin@tut.by'),
           (SELECT id FROM public.chat WHERE name = 'java')
       ),
       (
           (SELECT id FROM public.users WHERE username = 'bill@mail.ru'),
           (SELECT id FROM public.chat WHERE name = 'youtube-members')
       ),
       (
           (SELECT id FROM public.users WHERE username = 'jobs@gmai.com'),
           (SELECT id FROM public.chat WHERE name = 'youtube-members')
       ),
       (
           (SELECT id FROM public.users WHERE username = 'brin@tut.by'),
           (SELECT id FROM public.chat WHERE name = 'dmdev')
       ),
       (
           (SELECT id FROM public.users WHERE username = 'brin@tut.by'),
           (SELECT id FROM public.chat WHERE name = 'java')
       );