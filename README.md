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
![image](https://user-images.githubusercontent.com/88108788/212482909-8ad7e389-a270-458a-a5f0-a3bcb0bd940d.png)
![image](https://user-images.githubusercontent.com/88108788/212482916-be468f16-50e5-443d-8873-25de072fe4d0.png)
![image](https://user-images.githubusercontent.com/88108788/212482923-dc0cf4cf-4c52-4b61-b1bc-84029bd2398f.png)
![image](https://user-images.githubusercontent.com/88108788/212482927-6b647426-0b0a-4ecf-9251-0ffeb70d28fb.png)

