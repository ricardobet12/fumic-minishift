/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  cristiancg
 * Created: 2/03/2020
 */

CREATE TABLE usuarios(
id_usuario serial,
nombres varchar(50),
apellidos varchar(50),
cedula varchar(50),
direccion varchar(50),
usuario varchar(50),
clave varchar(50),
rol varchar(50),
estado boolean,
primary key(id_usuario)
);


CREATE TABLE empresa(
id_empresa serial,
nombre varchar(50),
nit varchar(50),
direccion varchar(50),
telefono varchar(50),
id_usuario integer null,
estado boolean,
ruta_logo varchar(200),
lat double,
longi double,
primary key(id_empresa),
constraint FK_EMPLEADO foreign key(id_usuario) REFERENCES usuarios(id_usuario)
);


CREATE TABLE rutas (   
    id_ruta serial,
    id_usuario int,
    id_empresa int,
    estado varchar(15),
    primary key(id_ruta),
    constraint FK_USUARIO foreign key(id_usuario) REFERENCES usuarios(id_usuario),
    constraint FK_EMPRESA_RUTA foreign key(id_empresa) REFERENCES empresa(id_empresa)
);

CREATE TABLE formulario(
    id_formulario serial,
    fecha_visita date,
    hora_llegada time,
    hora_salida time,
    id_empresa int,
    tipo_control varchar(15),
    areas_tratadas varchar(200),
    observaciones varchar(200),
    servicio varchar(15),
    primary key(id_formulario),
    constraint FK_EMPRESA_FORMULARIO foreign key(id_empresa) REFERENCES empresa(id_empresa)
);

CREATE TABLE visitas(
    id_visita serial,
    id_empresa int,
    fecha date,
    id_formulario int,
    estado boolean,
    primary key(id_visita),
    constraint FK_EMPRESA_VISITA foreign key(id_empresa) REFERENCES empresa(id_empresa),
    constraint FK_FORMULARIO_VISITA foreign key(id_formulario) REFERENCES formulario(id_formulario)
);

CREATE TABLE tipoPlaga(
    id_plaga serial,
    nombre varchar(20),
    nivel_infestacion varchar(1),
    servicio_ejecutado varchar(20),
    producto_aplicado varchar(20),
    dosis int,
    numero_registro int,
    id_formulario int,
    primary key(id_plaga),
    constraint FK_FORMULARIO foreign key(id_formulario) REFERENCES formulario(id_formulario)
);

