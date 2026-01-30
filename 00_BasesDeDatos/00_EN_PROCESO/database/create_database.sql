/*drop database if exists cine_elorrieta; */
create database cine_elorrieta;

use cine_elorrieta;

create table Cine(
	IDCine int unsigned auto_increment primary key,
	nom varchar (50) not null
);

create table sala(
	numSala int unsigned auto_increment,
	IDCine int unsigned not null,
    aforo int,
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
    descuento decimal (10, 2) not null, 
    total decimal(10, 2) not null,
	DNI varchar(9) not null,
    constraint FK_Cliente_Compra foreign key (DNI) references Cliente (DNI) on update cascade on delete cascade
    
);



create table Entrada(

	IDEntrada int unsigned auto_increment primary key,
    CantPersonas int unsigned not null,
    importe decimal(10,2) not null, 
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
insert into Sala values(1, 1, 30);
insert into Sala values(2, 1, 50);
insert into Sala values(3, 1, 65);
insert into Sala values(4, 1, 30);
insert into Sala values(5, 1, 20);
insert into Sala values(6, 1, 60);
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


/*propuesta de inserts para aumentar volumen de datos*/
insert into sesion values(37, '2026-02-10', '15:30', '17:30', 7.20, 1, 1, 1);
insert into sesion values(38, '2026-02-10', '16:00', '18:10', 6.00, 1, 2, 4);
insert into sesion values(39, '2026-02-10', '17:00', '19:20', 8.50, 1, 3, 5);
insert into sesion values(40, '2026-02-10', '18:30', '20:05', 6.80, 1, 4, 7);
insert into sesion values(41, '2026-02-10', '19:00', '21:15', 7.50, 1, 5, 9);
insert into sesion values(42, '2026-02-10', '21:30', '23:35', 9.00, 1, 6, 8);

insert into sesion values(43, '2026-02-11', '15:30', '17:00', 6.20, 1, 1, 3);
insert into sesion values(44, '2026-02-11', '16:00', '18:10', 7.90, 1, 2, 2);
insert into sesion values(45, '2026-02-11', '18:00', '20:20', 8.70, 1, 3, 5);
insert into sesion values(46, '2026-02-11', '18:30', '20:05', 6.50, 1, 4, 7);
insert into sesion values(47, '2026-02-11', '20:00', '22:05', 8.90, 1, 5, 8);
insert into sesion values(48, '2026-02-11', '21:30', '23:25', 8.00, 1, 6, 6);

insert into sesion values(49, '2026-02-12', '15:30', '17:30', 7.20, 1, 1, 1);
insert into sesion values(50, '2026-02-12', '16:00', '18:15', 7.80, 1, 2, 9);
insert into sesion values(51, '2026-02-12', '18:00', '19:30', 6.10, 1, 3, 3);
insert into sesion values(52, '2026-02-12', '18:30', '20:20', 6.50, 1, 4, 4);
insert into sesion values(53, '2026-02-12', '20:00', '22:20', 8.50, 1, 5, 5);
insert into sesion values(54, '2026-02-12', '21:30', '23:10', 7.90, 1, 6, 7);

insert into sesion values(55, '2026-02-13', '15:30', '17:45', 7.40, 1, 1, 8);
insert into sesion values(56, '2026-02-13', '16:00', '17:55', 6.30, 1, 2, 6);
insert into sesion values(57, '2026-02-13', '18:00', '20:10', 8.20, 1, 3, 2);
insert into sesion values(58, '2026-02-13', '18:30', '20:05', 6.70, 1, 4, 7);
insert into sesion values(59, '2026-02-13', '20:00', '22:20', 9.10, 1, 5, 5);
insert into sesion values(60, '2026-02-13', '21:30', '23:45', 8.80, 1, 6, 9);

insert into sesion values(61, '2026-02-14', '15:30', '17:00', 6.00, 1, 1, 3);
insert into sesion values(62, '2026-02-14', '16:00', '18:10', 7.60, 1, 2, 2);
insert into sesion values(63, '2026-02-14', '18:00', '20:20', 8.50, 1, 3, 5);
insert into sesion values(64, '2026-02-14', '18:30', '20:05', 6.90, 1, 4, 7);
insert into sesion values(65, '2026-02-14', '20:00', '22:05', 8.90, 1, 5, 8);
insert into sesion values(66, '2026-02-14', '21:30', '23:10', 7.80, 1, 6, 6);

insert into sesion values(67, '2026-02-15', '15:30', '17:30', 7.20, 1, 1, 1);
insert into sesion values(68, '2026-02-15', '16:00', '18:15', 7.80, 1, 2, 9);
insert into sesion values(69, '2026-02-15', '18:00', '19:30', 6.10, 1, 3, 3);
insert into sesion values(70, '2026-02-15', '18:30', '20:20', 6.50, 1, 4, 4);
insert into sesion values(71, '2026-02-15', '20:00', '22:20', 8.50, 1, 5, 5);
insert into sesion values(72, '2026-02-15', '21:30', '23:35', 9.00, 1, 6, 8);

insert into sesion values(73, '2026-02-16', '15:30', '17:00', 6.20, 1, 1, 3);
insert into sesion values(74, '2026-02-16', '16:00', '18:10', 7.90, 1, 2, 2);
insert into sesion values(75, '2026-02-16', '18:00', '20:20', 8.70, 1, 3, 5);
insert into sesion values(76, '2026-02-16', '18:30', '20:05', 6.50, 1, 4, 7);
insert into sesion values(77, '2026-02-16', '20:00', '22:05', 8.90, 1, 5, 8);
insert into sesion values(78, '2026-02-16', '21:30', '23:25', 8.00, 1, 6, 6);

insert into sesion values(79, '2026-02-17', '15:30', '17:30', 7.20, 1, 1, 1);
insert into sesion values(80, '2026-02-17', '16:00', '18:15', 7.80, 1, 2, 9);
insert into sesion values(81, '2026-02-17', '18:00', '19:30', 6.10, 1, 3, 3);
insert into sesion values(82, '2026-02-17', '18:30', '20:20', 6.50, 1, 4, 4);
insert into sesion values(83, '2026-02-17', '20:00', '22:20', 8.50, 1, 5, 5);
insert into sesion values(84, '2026-02-17', '21:30', '23:10', 7.90, 1, 6, 7);

insert into sesion values(85, '2026-02-18', '18:30', '20:45', 7.65, 1, 6, 9);
insert into sesion values(86, '2026-02-18', '20:00', '22:05', 9.00, 1, 4, 8);


/*------------------- fin de insert de sesion -----------------*/

/*------------------- insert de cliente -----------------*/
insert into cliente values('12345678A', 'ana', 'rosa', 'ana@gmail.com', md5('1234'));
insert into cliente values('12345678B', 'juan', 'ramirez', 'juan@gmail.com', md5('4321'));
insert into cliente values('12345678C', 'maria', 'hernandez', 'maria@gmail.com', md5('5423'));
insert into cliente values('21321265A', 'luis', 'martinez', 'luis@gmail.com', md5('6666'));
insert into cliente values('54769853Ñ', 'J', 'PG', 'jpg@gmail.com', md5('JPEG'));
/*------------------- fin de insert de cliente -----------------*/

/*----------------- insert de compra -------------------*/
insert into compra values(1, current_timestamp, 'web', 0.00, 6.30, '12345678A');
insert into compra values(2, current_timestamp, 'app', 20.00, 25.6,  '54769853Ñ');
insert into compra values(3, current_timestamp, 'web', 20.00, 11.60, '12345678A');
insert into compra values(4, current_timestamp, 'app', 30.00, 16.31, '12345678B');
insert into compra values(5, current_timestamp, 'web', 0.00, 8.00, '21321265A');
insert into compra values(6, current_timestamp, 'app', 20.00, 13.28, '54769853Ñ');
/*-----------VERIFICAR----------------*/

/*----------------- fin de insert de compra -------------------*/
/*--------------------- insert de entrada ---------------*/
insert into entrada values(1, 1, 6.30, 15, 1);
insert into entrada values(2, 2, 16.00, 21, 2);
insert into entrada values(3, 2, 16.00, 36, 2);

/*-----------VERIFICAR----------------*/


insert into entrada values
(null, 1, 6.30, 3, 3),
(null, 1, 6.90, 7, 3);



insert into entrada values
(null, 1, 7.22, 1, 4),
(null, 1, 8.00, 9, 4),
(null, 1, 6.30, 15, 4);



insert into entrada values
(null, 1, 8.00, 10, 5);



insert into entrada values
(null, 1, 7.65, 8, 6),
(null, 1, 9.00, 11, 6);



insert into entrada values
(null, 2, 18.00, 11, 6);

/*-----------VERIFICAR----------------*/


/*--------------------- fin de insert de entrada ---------------*/
select fec, idsesion, hora_ini, numsala, precio 
from sesion
where idpeli = 3 and fec = '2026-02-12';

/* ---------------- consultas para comprobar la base de datos + consultas de ayuda para program ------------------*/


/*datos que debería contener la entrada*/
select identrada, cantpersonas, importe, numsala, hora_ini, hora_fin, nompeli, nomgen
from entrada natural join sesion natural join pelicula natural join sala natural join genero;

select * from compra;


/* sala, sesion 3, aforo de sala y aforo disponible*/
 select  numsala, aforo, aforo-cantpersonas 'aforo disponible'
 from sala natural join sesion natural join entrada
 where idsesion = 3;
 
 /*--------- SESIONES disponibles según la fecha actual ---------*/
 select * from sesion
where fec > current_date();

/* --------- SESIONES disponibles según la fecha y hora actual ---------*/
select * from sesion
where fec > current_date()
and hora_ini > current_time();

/* ---------------- fin de consultas para comprobar la base de datos ------------------*/






/* *************************************** CONSULTAS DEL RETO ***************************************** */
/* ======================================================================================================== */
/*● El dinero recaudado por cada película con recaudación superior a una cifra
dada.*/
select sum(importe) 'recaudacion €', nompeli, idpeli
from entrada natural join sesion natural join pelicula
group by nompeli, idpeli
HAVING sum(importe) > 10;

/*● La duración media de las películas por género.*/

select round(avg(duracion),1) 'duracion media min\'', nomgen
from pelicula natural join genero
group by nomgen;

/*● El número de sesiones ofrecidas por película. Debe permitir filtrar por género
o por precio.*/

/*filtrado por un genero*/
select count(*) 'numero de sesiones', nompeli
from sesion natural join pelicula p
where p.idgen = (select idgen from genero where nomgen = 'terror')
group by nompeli;

/*numero de sesiones por pelicula*/
select count(*) 'numero de sesiones', nompeli
from sesion natural join pelicula p
group by nompeli;

/*filtrado por precio*/
select count(*) 'numero de sesiones', nompeli
from sesion s natural join pelicula p
where s.precio > 6
group by nompeli;

/*Debe permitir filtrar por género
o por precio.?????*/

/*● El precio medio de las películas por género.*/

select round(avg(precio),2) 'precio medio', nomgen
from pelicula natural join genero natural join sesion
group by nomgen;

/*● Datos de las películas con mayor recaudación.*/

/*son las 3 con más recaudación*/
select IDpeli, nompeli, Duracion, Caratula, sum(importe)'recaudacion'  
from entrada natural join sesion natural join pelicula
group by idpeli
order by recaudacion desc
limit 3;

/* ● Datos de los clientes a los que se les ha aplicado mayores descuentos en sus
compras. */
 
 select DNI, NomCli, Ape, mail, /*sum(descuento) as descuento_total_suma*/ descuento
 from cliente natural join compra;
 /*group by DNI
 order by sum(descuento) desc;*/
 
 /*● Datos de los clientes que han adquirido mayor número de entradas.*/

select DNI, NomCli, Ape, mail, count(*) 'numero de entradas adquiridas'
from cliente natural join compra natural join entrada
group by idcompra
order by count(*) desc
limit 3;

/*● Datos de los clientes que han gastado más dinero. */

select DNI, NomCli, Ape, mail, sum(total) 'total gastado'
from cliente natural join compra
group by DNI
order by sum(total) desc
limit 3;

/*● Datos de las películas con un número de espectadores inferior a una cantidad dada.*/

select IDpeli, NomPeli, Duracion, Caratula, IDGen, sum(cantpersonas) as numero_de_espectadores
from pelicula natural join sesion natural join entrada
group by idpeli
having numero_de_espectadores < 3
order by numero_de_espectadores desc;

/*  ● Datos de los clientes que aún no han comprado ninguna entrada. */

select DNI, NomCli, Ape, mail
from cliente
where dni not in (select dni from compra);

use cine_elorrieta;

/* *************************************** FIN DE CONSULTAS DEL RETO ***************************************** */
/* ======================================================================================================== */





