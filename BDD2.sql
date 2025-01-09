create database Operadores;
use Operadores;

create table Clientes(
id int auto_increment primary key,
nombre varchar(30) not null,
apellido varchar(30) not null,
correo varchar(30) unique
);

insert into Clientes(nombre,apellido,correo) 
values ('Richard','Padilla','richardpadilla@gmail.com'),
		('Alex','Morales','alexmorales@gmail.com'),
        ('Domenica','Yepez','domeyepez@gmail.com');
        
        
select * from Clientes;