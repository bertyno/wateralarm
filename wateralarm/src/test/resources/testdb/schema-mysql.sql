
drop table T_RECORD ;
drop table T_AUTHORITY;
drop table T_PLANT;
drop table T_USER ;

create table T_USER (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME varchar(50) not null, PASSWORD  varchar(50) not null, EMAIL  varchar(50) not null, ENABLED boolean not null );
create table T_PLANT (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME varchar(50), USER_ID INT);
create table T_RECORD (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, R_DATE date not null, HUMIDITY INT, PLANT_ID INT);
create table T_AUTHORITY (USERNAME varchar(50) not null, AUTHORITY varchar(50) not null);



ALTER TABLE T_USER AUTO_INCREMENT=22;
ALTER table T_PLANT  AUTO_INCREMENT=21;