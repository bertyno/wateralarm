
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (1,'keith', 'keith','bjalonmontanes@pivotal.io', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (2,'Berti', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (3,'Cornelia J. Andresen', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (4,'Coral Villareal Betancourt', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (5,'Chad I. Cobbs', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (6,'Michael C. Feller', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (7,'Michael J. Grover', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (8,'John C. Howard', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (9,'Ida Ketterer', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (10,'Laina Ochoa Lucero', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (11,'Wesley M. Mayo', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (12,'Leslie F. Mcclary', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (13,'John D. Mudra', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (14,'Pietronella J. Nielsen', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (15,'John S. Oleary', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (16,'Glenda D. Smith', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (17,'Willemina O. Thygesen', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (18,'Antje Vogt', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (19,'Julia Weber', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (20,'Mark T. Williams', 'keith','tanino@tanino.com', true);
insert into T_USER (ID, NAME, PASSWORD, EMAIL, ENABLED) values (21,'Christine J. Wilson', 'keith','tanino@tanino.com', true);
                                       
insert into T_PLANT (ID, USER_ID, NAME) values (0, 1, 'My plant');
insert into T_PLANT (ID, USER_ID, NAME) values (1, 1, 'My other plant');
insert into T_PLANT (ID, USER_ID, NAME) values (2, 2, 'Fulanito');
insert into T_PLANT (ID, USER_ID, NAME) values (3, 3, 'Menganito');
insert into T_PLANT (ID, USER_ID, NAME) values (4, 4, 'Setanito');
insert into T_PLANT (ID, USER_ID, NAME) values (5, 5, 'Este');
insert into T_PLANT (ID, USER_ID, NAME) values (6, 6, 'El otro');
insert into T_PLANT (ID, USER_ID, NAME) values (7, 7, 'El de la moto');
insert into T_PLANT (ID, USER_ID, NAME) values (8, 8, '');
insert into T_PLANT (ID, USER_ID, NAME) values (9, 9, '');
insert into T_PLANT (ID, USER_ID, NAME) values (10, 10, '1234123412340010');
insert into T_PLANT (ID, USER_ID, NAME) values (11, 11, '1234123412340011');
insert into T_PLANT (ID, USER_ID, NAME) values (12, 12, '1234123412340012');
insert into T_PLANT (ID, USER_ID, NAME) values (13, 13, '1234123412340013');
insert into T_PLANT (ID, USER_ID, NAME) values (14, 14, '1234123412340014');
insert into T_PLANT (ID, USER_ID, NAME) values (15, 15, '1234123412340015');
insert into T_PLANT (ID, USER_ID, NAME) values (16, 16, '1234123412340016');
insert into T_PLANT (ID, USER_ID, NAME) values (17, 17, '1234123412340017');
insert into T_PLANT (ID, USER_ID, NAME) values (18, 18, '1234123412340018');
insert into T_PLANT (ID, USER_ID, NAME) values (19, 19, '1234123412340019');
insert into T_PLANT (ID, USER_ID, NAME) values (20, 20, '1234123412340020');

insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, NOW(),10, 1 );
insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, NOW(),15, 1 );
insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, '2015-01-01',20, 1 );
insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, '2015-01-01',30, 1 );
insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, '2015-01-01',40, 1 );
insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, '9999-12-31',9, 1 );
insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, '2015-01-01',10, 2 );
insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, '2015-01-01',15, 2 );
insert into T_RECORD (ID, R_DATE, HUMIDITY, PLANT_ID) values (NULL, NOW(),20, 2 );

insert into T_AUTHORITY (USERNAME, AUTHORITY) values ('keith', 'ROLE_EDITOR');
insert into T_AUTHORITY (USERNAME, AUTHORITY) values ('keith', 'ROLE_VIEWER');

