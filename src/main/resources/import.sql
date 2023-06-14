/* table  pruebaapp */


create database pruebaapp;

/* Creamos algunos usuarios estudiantes  */
create table empleados (id bigint not null auto_increment, nombre varchar(255) not null, apellido varchar(255) not null, email varchar(255) not null, fecha_nacimiento datetime not null, primary key (id)) engine=InnoDB;


INSERT INTO students (  nombre,apellido, email,fecha_nacimiento) VALUES('rafael','hernandez','rafael@gmail','1996-01-01');
INSERT INTO students (  nombre,apellido, email,fecha_nacimiento)  VALUES('emanuel','quinto','emanuel@gmail','1997-01-01');
INSERT INTO students (  nombre,apellido, email,fecha_nacimiento)  VALUES('josue','flores','josue@gmail','1998-01-01');
INSERT INTO students (  nombre,apellido, email,fecha_nacimiento)  VALUES('elena','garcia','elena@gmail','1999-01-01');
INSERT INTO students (  nombre,apellido, email,fecha_nacimiento)  VALUES('adrian','patiño','adrian@gmail','1994-01-01');
INSERT INTO students (  nombre,apellido, email,fecha_nacimiento)  VALUES('jose','salgado','jose@gmail','1993-01-01');
INSERT INTO students (  nombre,apellido, email,fecha_nacimiento)  VALUES('vela','martines','vela@gmail','1992-01-01');
INSERT INTO students (  nombre,apellido, email,fecha_nacimiento)  VALUES('pelon','lopez','pelon@gmail','1991-01-01');










