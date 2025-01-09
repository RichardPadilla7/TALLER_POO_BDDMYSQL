create database Concursos;
use Concursos;

create table Usuarios(
id int auto_increment primary key,
nombre varchar(30),
correo varchar(30), 
pass varchar(30)
);

create table Inscripciones(
cod int auto_increment primary key,
nombre varchar(30),
apellido varchar(30),
edad int,
correo varchar(30)
);

insert into Usuarios (nombre, correo, pass)
values
('Carlos Pérez', 'carlos.perez@example.com', 'password123'),
('Ana Gómez', 'ana.gomez@example.com', 'abcde123'),
('Luis Rodríguez', 'luis.rodriguez@example.com', 'mypassword');

        
insert into Inscripciones (nombre, apellido, edad, correo)
values
('Juan', 'Martínez', 25, 'juan.martinez@example.com'),
('María', 'Sánchez', 32, 'maria.sanchez@example.com'),
('Pedro', 'López', 28, 'pedro.lopez@example.com');

select * from Usuarios;
select * from Inscripciones;


