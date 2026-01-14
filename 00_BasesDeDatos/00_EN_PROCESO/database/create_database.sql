/*create database cine_elorrieta;
drop database cine_elorrieta;*/
use cine_elorrieta;

create table Cine(
	IDCine int unsigned auto_increment primary key,
	nom varchar (50) not null
);

create table sala(
	numSala int unsigned auto_increment,
	IDCine int unsigned not null,
	constraint PK_sala primary key (numSala, IDcine),
	constraint FK_Cine_sala foreign key (IDCine) references Cine (IDCine) on update cascade on delete cascade
);

create table genero(
	IDGen int unsigned auto_increment primary key,
    NomGen varchar(20) not null
);

create table pelicula(
	IDpeli int unsigned auto_increment primary key,
    NomPeli varchar(50) not null,
    Duracion int unsigned not null, /*minutos*/
   /* precio_inicial decimal (4,2) not null ,*/  /* RECOMENDADO POR ARANTZA TAMBIÉN  */ 
    IDGen int unsigned not null,
    constraint FK_genero_pelicula foreign key (IDGen) references Genero (IDGen) on update cascade
);

create table sesion(
	IDsesion int unsigned auto_increment primary key, 
    fec date not null,
    hora_ini time not null,
    hora_fin time not null,
    precio decimal(4,2) not null,
    IDCine int unsigned not null,
	NumSala int unsigned not null,
    IDPeli int unsigned not null,
    constraint FK_Sala_Sesion foreign key (IDCine, NumSala) references sala (IDCine, NumSala) on update cascade on delete cascade,
    constraint FK_Pelicula_Sesion foreign key (IDPeli) references Pelicula (IDPeli) on update cascade on delete cascade
);

create table Cliente(

	DNI varchar(9) primary key,
    NomCli varchar (20) not null,
    Ape varchar (20) not null,
    mail varchar(100) unique not null,
    password varchar(255) not null

);

create table Compra(

	IDCompra int unsigned auto_increment primary key,
    FecCompra date not null,
    plataforma enum ('web','app') not null, 
    DNI varchar(9) not null,
    descuento decimal (5,2) not null, /******  PORCENTAJE *****/   
    total decimal(4,2) not null,
    constraint FK_Cliente_Compra foreign key (DNI) references Cliente (DNI) on update cascade on delete cascade
    
);

create table Entrada(

	IDEntrada int unsigned auto_increment primary key,
    CantPersonas int unsigned not null,
    importe decimal(4,2) not null, /* importe ? arantza sugirió ponerlo pero idk*/
    IDSesion int unsigned not null,
    IDCompra int unsigned not null,
    constraint FK_Sesion_Entrada foreign key (IDSesion) references Sesion(IDSesion) on update cascade on delete cascade,
    constraint FK_Compra_Entrada foreign key (IDCompra) references Compra(IDCompra) on update cascade on delete cascade
    
);

insert into Cine values (1, 'Elorrieta Cines');
insert into Salas values(1, 'Elorrieta Cines');
insert into Salas values(2, 'Elorrieta Cines');
insert into Salas values(3, 'Elorrieta Cines');
insert into Salas values(4, 'Elorrieta Cines');
insert into Salas values(5, 'Elorrieta Cines');
insert into Salas values(6, 'Elorrieta Cines');

insert into genero values(1, 'Terror');
insert into genero values(2, 'Accion');
insert into genero values(3, 'Comedia');
insert into genero values(4, 'Romance');
insert into genero values(5, 'Musical');

insert into pelicula values(1, 'Pinky Pink', 120, 5);
insert into pelicula values(2, 'Salvando al Presidente', 130, 2);
insert into pelicula values(3,'Tú, ella, él y yo', 90, 4);
insert into pelicula values(4, 'Un pobre de derechas', 110, 3);
insert into pelicula values(5, 'Sweet dreams', 140, 1);
insert into pelicula values(6, 'El llanto maldito', 115, 1);
insert into pelicula values(7, 'Mi madre y yo', 100, 3);
insert into pelicula values(8, 'The F*k end of the world', 125, 2);
insert into pelicula values(9, 'El sillón', 135, 1);
