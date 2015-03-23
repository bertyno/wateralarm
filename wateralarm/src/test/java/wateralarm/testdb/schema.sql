drop table T_USER if exists;
drop table T_PLANT if exists;
drop table T_RECORD if exists;


create table T_USER (ID integer identity primary key, NAME varchar(50) not null);
create table T_PLANT (ID integer identity primary key, NAME varchar(50), USER_ID integer);
create table T_RECORD (R_DATE date not null, TEMPERATURE integer);

       
alter table T_PLANT add constraint FK_T_USER foreign key (USER_ID) references T_USER(ID) on delete cascade;
