INSERT INTO persone (id_persone, user_name, email) VALUES (1, 'anazena', 'anazena@climb.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (2, 'cofkocof', 'cofkocof@climb.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (3, 'medvedbojan', 'medvedbojan@climb.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (4, 'nodi', 'nodi@climb.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (5, 'jelenčekrudolf', 'rudolf@climb.com');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (11, 'rope', 'climbing rope', 10, 1, 'climbing');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (12, 'belt', 'climbing belt', 5, 2, 'climbing');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (13, 'shoes', 'climbing shoes', 5, 3, 'climbing');
INSERT INTO event (id_event, title, description, start_date, end_date, id_persone, tag) VALUES (21, 'Climbing school for beginners', 'Basics of climbing', '2021-05-05', '2021-06-06', 1, 'climbing');
INSERT INTO event (id_event, title, description, start_date, end_date, id_persone, tag) VALUES (22, 'Climbing school 2', 'Climbing school for those who know basics', '2021-07-01', '2021-09-01', 4, 'climbing');
INSERT INTO event (id_event, title, description, start_date, end_date, id_persone, tag) VALUES (23, 'Climbing seminar', 'Seminar of climbing in America', '2021-08-05', '2021-08-06', 5, 'climbing');
INSERT INTO registration (id_registration, registration_date, id_persone, id_event) VALUES (31, '2021-03-05', 1, 21)
INSERT INTO registration (id_registration, registration_date, id_persone, id_event) VALUES (32, '2021-09-05', 1, 22)
INSERT INTO rent (id_rent, registration_date, id_persone, id_item) VALUES (41, '2021-03-05', 1, 11)
INSERT INTO rent (id_rent, registration_date, id_persone, id_item) VALUES (42, '2021-06-05', 1, 12)
