# ITSTEP-JAVA-HW20221109
homework from 2022/11/09 - Hibernate, RestApi

Создать REST API для телефонного справочника.
У каждого контакта может быть несколько телефонов

CREATE DATABASE my_db_phone;
USE my_db_phone;

CREATE TABLE contacts(
	id int primary key not null auto_increment,
    	first_name varchar(25),
last_name varchar(25)
);

CREATE TABLE phones(
id int primary key not null auto_increment,
    	contact_id int,
    	phone_number text,
	foreign key(contact_id) references contacts(id)
);

insert into contacts (first_name, last_name) value('Petro', 'Petrenko');
insert into contacts (first_name, last_name) value('Anton', 'Antonenko');
insert into contacts (first_name, last_name) value('Ivan', 'Ivanenko');
insert into phones (contact_id, phone_number) value('20', '+380-77-111-11-11');
insert into phones (contact_id, phone_number) value('21', '+380-77-222-22-22');
insert into phones (contact_id, phone_number) value('21', '+380-77-333-33-33');
insert into phones (contact_id, phone_number) value('21', '+380-77-444-44-44');
insert into phones (contact_id, phone_number) value('22', '+380-77-555-55-55');
insert into phones (contact_id, phone_number) value('22', '+380-77-667-67-67');

select * from phones;
select * from contacts;

![image](https://user-images.githubusercontent.com/88108788/212482844-04923fcb-1495-4c2b-be8f-59aa29ab3f77.png)
![image](https://user-images.githubusercontent.com/88108788/212482852-b05b0942-c4eb-4652-a924-df3cac9fd7a5.png)
![image](https://user-images.githubusercontent.com/88108788/212482865-c9852234-ccf7-44ea-a0ab-ec567387b16e.png)
![image](https://user-images.githubusercontent.com/88108788/212482874-16261712-b8a1-4664-9958-1f7caa7d5fa0.png)
![image](https://user-images.githubusercontent.com/88108788/212482885-37d216f5-f2d5-437c-bb9a-eea5f8a77a09.png)
