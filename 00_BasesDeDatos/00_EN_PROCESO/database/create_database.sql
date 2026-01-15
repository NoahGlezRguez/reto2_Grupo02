/*drop database cine_elorrieta;*/
create database cine_elorrieta;

use cine_elorrieta;

create table Cine(
	IDCine int unsigned auto_increment primary key,
	nom varchar (50) not null
);

create table sala(
	numSala int unsigned auto_increment,
	IDCine int unsigned not null,
	primary key (numSala, IDcine),
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
    Caratula varchar(50),
    IDGen int unsigned not null,
    constraint FK_genero_pelicula foreign key (IDGen) references Genero (IDGen) on update cascade
);
 /* pendiente de revisart si creamos el atributo aforo disponible */
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
    userpassword varchar(255) not null

);



create table Compra(

	IDCompra int unsigned auto_increment primary key,
    FecCompra timestamp default current_timestamp not null,
    plataforma enum ('web','app') not null, 
    descuento decimal (5,2) not null, 
    total decimal(4,2) not null,
	DNI varchar(9) not null,
    constraint FK_Cliente_Compra foreign key (DNI) references Cliente (DNI) on update cascade on delete cascade
    
);



create table Entrada(

	IDEntrada int unsigned auto_increment primary key,
    CantPersonas int unsigned not null,
    importe decimal(4,2) not null, 
    IDSesion int unsigned not null,
    IDCompra int unsigned not null,
    constraint FK_Sesion_Entrada foreign key (IDSesion) references Sesion(IDSesion) on update cascade on delete cascade,
    constraint FK_Compra_Entrada foreign key (IDCompra) references Compra(IDCompra) on update cascade on delete cascade
    
);

/* si en los inserts quieres insertar un id que no sabes si existe puedes poner null y automaticamente le asigna el número que le correspondería 
porque tiene el auto increment*/



/*------------------- insert de cine -----------------*/
insert into Cine values (1, 'Elorrieta Cines');
/*------------------- fin de insert de cine -----------------*/

/* ------------------- insert se salas -----------------*/
insert into Sala values(1, 1);
insert into Sala values(2, 1);
insert into Sala values(3, 1);
insert into Sala values(4, 1);
insert into Sala values(5, 1);
insert into Sala values(6, 1);
/* ------------------- fin de insert se salas -----------------*/

/*------------------- insert de genero -----------------*/
insert into genero values(1, 'Terror');
insert into genero values(2, 'Accion');
insert into genero values(3, 'Comedia');
insert into genero values(4, 'Romance');
insert into genero values(5, 'Musical');
/*------------------- fin de insert de genero -----------------*/

/*------------------- insert de pleicula -----------------*/
insert into pelicula values(1, 'Pinky Pink', 120,'img/film1.png', 5);
insert into pelicula values(2, 'Salvando al Presidente', 130,'img/film2.png', 2);
insert into pelicula values(3,'Tú, ella, él y yo', 90,'img/film3.png', 4);
insert into pelicula values(4, 'Un pobre de derechas', 110,'img/film4.png',  3);
insert into pelicula values(5, 'Sweet dreams', 140,'img/film5.png', 1);
insert into pelicula values(6, 'El llanto maldito', 115,'img/film6.png', 1);
insert into pelicula values(7, 'Mi madre y yo', 100,'img/film7.png', 3);
insert into pelicula values(8, 'The F*k end of the world', 125,'img/film8.png', 2);
insert into pelicula values(9, 'El sillón', 135,'img/film9.png', 1);
/*------------------- fin de insert de pleicula -----------------*/

/*------------------- insert de sesion -----------------*/
insert into sesion values(1, '2026-01-20', '15:30', '17:30', 7.22, 1, 1, 1); /* 'Pinky Pink', 120,*/
insert into sesion values(2, '2026-01-20', '15:30', '17:40', 5.25, 1, 2, 2); /* 'Salvando al Presidente', 130 */
insert into sesion values(3, '2026-01-20', '15:30', '17:00', 6.30, 1, 3, 3); /* Tú, ella, él y yo', 90 */
insert into sesion values(4, '2026-01-20', '16:00', '17:50', 5.20, 1, 4, 4); /* Un pobre de derechas', 110 */ 
insert into sesion values(5, '2026-01-20', '18:00', '19:50', 6.10, 1, 4, 4); /* Un pobre de derechas', 110 */ 
insert into sesion values(6, '2026-01-20', '18:10', '20:10', 8.50, 1, 1, 2); /* 'Salvando al Presidente', 130 */
insert into sesion values(7, '2026-01-20', '18:10', '19:50', 6.90, 1, 5, 7); /*'Mi madre y yo', 100 */
insert into sesion values(8, '2026-01-20', '18:30', '20:45', 7.65, 1, 6, 9); /* El sillón', 135 */
insert into sesion values(9, '2026-01-20', '18:30', '20:25', 8.00, 1, 3, 6); /* El llanto maldito', 115 */
insert into sesion values(10, '2026-01-20', '19:00', '21:20', 8.50, 1, 2, 5); /* 'Sweet dreams', 140 */
insert into sesion values(11, '2026-01-20', '20:00', '22:05', 9.00, 1, 4, 8); /* 'The F*k end of the world', 125 */
insert into sesion values(12, '2026-01-20', '21:30', '23:10', 8.00, 1, 6, 7); /* 'Mi madre y yo', 100 */
/**/
insert into sesion values(13, '2026-01-21', '15:30', '17:30', 7.22, 1, 1, 1); /* 'Pinky Pink', 120,*/
insert into sesion values(14, '2026-01-21', '15:30', '17:40', 5.25, 1, 2, 2); /* 'Salvando al Presidente', 130 */
insert into sesion values(15, '2026-01-21', '15:30', '17:00', 6.30, 1, 3, 3); /* Tú, ella, él y yo', 90 */
insert into sesion values(16, '2026-01-21', '16:00', '17:50', 5.20, 1, 4, 4); /* Un pobre de derechas', 110 */ 
insert into sesion values(17, '2026-01-21', '18:00', '19:50', 6.10, 1, 4, 4); /* Un pobre de derechas', 110 */ 
insert into sesion values(18, '2026-01-21', '18:10', '20:10', 8.50, 1, 1, 2); /* 'Salvando al Presidente', 130 */
insert into sesion values(19, '2026-01-21', '18:10', '19:50', 6.90, 1, 5, 7); /*'Mi madre y yo', 100 */
insert into sesion values(20, '2026-01-21', '18:30', '20:45', 7.65, 1, 6, 9); /* El sillón', 135 */
insert into sesion values(21, '2026-01-21', '18:30', '20:25', 8.00, 1, 3, 6); /* El llanto maldito', 115 */
insert into sesion values(22, '2026-01-21', '19:00', '21:20', 8.50, 1, 2, 5); /* 'Sweet dreams', 140 */
insert into sesion values(23, '2026-01-21', '20:00', '22:05', 9.00, 1, 4, 8); /* 'The F*k end of the world', 125 */
insert into sesion values(24, '2026-01-21', '21:30', '23:10', 8.00, 1, 6, 7); /* 'Mi madre y yo', 100 */
/**/
insert into sesion values(25, '2026-01-22', '15:30', '17:30', 7.22, 1, 1, 1); /* 'Pinky Pink', 120,*/
insert into sesion values(26, '2026-01-22', '15:30', '17:40', 5.25, 1, 2, 2); /* 'Salvando al Presidente', 130 */
insert into sesion values(27, '2026-01-22', '15:30', '17:00', 6.30, 1, 3, 3); /* Tú, ella, él y yo', 90 */
insert into sesion values(28, '2026-01-22', '16:00', '17:50', 5.20, 1, 4, 4); /* Un pobre de derechas', 110 */ 
insert into sesion values(29, '2026-01-22', '18:00', '19:50', 6.10, 1, 4, 4); /* Un pobre de derechas', 110 */ 
insert into sesion values(30, '2026-01-22', '18:10', '20:10', 8.50, 1, 1, 2); /* 'Salvando al Presidente', 130 */
insert into sesion values(31, '2026-01-22', '18:10', '19:50', 6.90, 1, 5, 7); /*'Mi madre y yo', 100 */
insert into sesion values(32, '2026-01-22', '18:30', '20:45', 7.65, 1, 6, 9); /* El sillón', 135 */
insert into sesion values(33, '2026-01-22', '18:30', '20:25', 8.00, 1, 3, 6); /* El llanto maldito', 115 */
insert into sesion values(34, '2026-01-22', '19:00', '21:20', 8.50, 1, 2, 5); /* 'Sweet dreams', 140 */
insert into sesion values(35, '2026-01-22', '20:00', '22:05', 9.00, 1, 4, 8); /* 'The F*k end of the world', 125 */
insert into sesion values(36, '2026-01-22', '21:30', '23:10', 8.00, 1, 6, 7); /* 'Mi madre y yo', 100 */
/*------------------- fin de insert de sesion -----------------*/

/*------------------- insert de cliente -----------------*/
insert into cliente values('12345678A', 'ana', 'rosa', 'ana@gmail.com', '1234');
insert into cliente values('12345678B', 'juan', 'ramirez', 'juan@gmail.com', '4321');
insert into cliente values('12345678C', 'maria', 'hernandez', 'maria@gmail.com', '5423');
insert into cliente values('21321265A', 'luis', 'martinez', 'luis@gmail.com', '6666');
insert into cliente values('54769853Ñ', 'J', 'PG', 'jpg@gmail.com', 'JPEG');
/*------------------- fin de insert de cliente -----------------*/

/*----------------- insert de compra -------------------*/
insert into compra values(1, current_timestamp, 'web', 0.00, 6.30, '12345678A');
insert into compra values(2, current_timestamp, 'app', 20.00, 25.6,  '54769853Ñ');
/*----------------- fin de insert de compra -------------------*/
/*--------------------- insert de entrada ---------------*/
insert into entrada values(1, 1, 6.30, 15, 1);
insert into entrada values(2, 2, 16.00, 21, 2);
insert into entrada values(3, 2, 16.00, 36, 2);

/*--------------------- fin de insert de entrada ---------------*/
