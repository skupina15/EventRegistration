INSERT INTO persone (id_persone, user_name, email) VALUES (1, 'anazena', 'anazena@climb.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (2, 'cofkocof', 'cofkocof@climb.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (3, 'medvedbojan', 'medvedbojan@climb.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (4, 'nodi', 'nodi@climb.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (5, 'jelenčekrudolf', 'rudolf@climb.com');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (11, 'rope', 'climbing rope', 10, 1, 'rock climbing');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (14, 'rope', 'climbing rope', 12, 1, 'rock climbing');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (12, 'belt', 'climbing belt', 5, 2, 'rock climbing');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (13, 'ice screws', 'ice climbing ice screws', 5, 3, 'ice climbing');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (15, 'chalk bag', 'climbing chalk bag', 3, 2, 'rock climbing');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (16, 'helmet', 'climbing helmet', 5, 3, 'climbing');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (17, 'bag', 'hiking bag', 8, 4, 'hiking');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (18, 'hiking sticks', 'hiking sticks', 5, 3, 'hiking');
INSERT INTO event (id_event, title, description, start_date, end_date, id_persone, tag) VALUES (21, 'Climbing school for beginners', 'Basics of climbing', '2022-05-05', '2022-06-06', 1, 'climbing');
INSERT INTO event (id_event, title, description, start_date, end_date, id_persone, tag) VALUES (22, 'Climbing school 2', 'Climbing school for those who know basics', '2022-07-01', '2022-09-01', 4, 'climbing');
INSERT INTO event (id_event, title, description, start_date, end_date, id_persone, tag) VALUES (23, 'Climbing seminar', 'Seminar of climbing in America', '2022-08-05', '2022-08-06', 5, 'climbing');
INSERT INTO event (id_event, title, description, start_date, end_date, id_persone, tag) VALUES (24, 'Rodica hike', 'Tour on Rodica Mountain', '2022-08-05', '2022-08-06', 5, 'hiking');
INSERT INTO registration (id_registration, registration_date, id_persone, id_event) VALUES (31, '2021-03-05', 1, 23)
INSERT INTO registration (id_registration, registration_date, id_persone, id_event) VALUES (32, '2021-09-05', 1, 22)
INSERT INTO registration (id_registration, registration_date, id_persone, id_event) VALUES (33, '2021-09-05', 1, 24)
INSERT INTO rent (id_rent, registration_date, id_persone, id_item) VALUES (41, '2021-03-05', 1, 13)
INSERT INTO rent (id_rent, registration_date, id_persone, id_item) VALUES (42, '2021-06-05', 1, 12)
INSERT INTO rent (id_rent, registration_date, id_persone, id_item) VALUES (43, '2021-06-07', 1, 16)
