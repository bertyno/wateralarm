

drop table T_RECORD if exists;
drop table T_AUTHORITY if exists;
drop table T_PLANT if exists;
drop table T_USER if exists;
drop sequence S_USER_ID if exists;
drop sequence S_PLANT_ID if exists;
drop sequence S_RECORD_ID if exists;

create table T_USER (ID integer identity primary key, NAME varchar(50) not null, PASSWORD  varchar(50) not null, EMAIL  varchar(50) not null, ENABLED boolean not null );
create table T_PLANT (ID integer identity primary key, NAME varchar(50), USER_ID integer);
create table T_RECORD (ID integer identity primary key, R_DATE date not null, HUMIDITY integer, PLANT_ID integer);
create table T_AUTHORITY (USERNAME varchar(50) not null, AUTHORITY varchar(50) not null);
      
alter table T_USER add constraint UNIQUE_NAME_T_USER unique (NAME);
alter table T_PLANT add constraint FK_T_USER foreign key (USER_ID) references T_USER(ID) on delete cascade;
alter table T_RECORD add constraint FK_T_RECORD foreign key (PLANT_ID) references T_PLANT(ID) on delete cascade;
alter table T_AUTHORITY add constraint FK_T_AUTHORITY foreign key (USERNAME) references T_USER(NAME) on delete cascade on update cascade;

create sequence S_USER_ID start with 22;
create sequence S_PLANT_ID start with 21;
create sequence S_RECORD_ID;

