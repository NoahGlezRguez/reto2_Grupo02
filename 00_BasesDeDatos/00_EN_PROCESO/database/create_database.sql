create database cine_Elorrieta
collate utf8mb4_spanish_ci;

/* cuales valores poner not null */

create table Cine(
	IDCine int unsigned auto_increment primary key,
	nom varchar (50) not null
);

create table sala(
	IDCine int unsigned not null,
	numSala int unsigned auto_increment,
	constraint PK_sala primary key (IDcine, numSala),
	constraint FK_Cine_sala foreign key (IDCine) references Cine (IDCine) on update cascade on delete cascade
);

create table genero(
	IDGen int unsigned auto_increment primary key,
    NomGen varchar(20) not null
);

create table pelicula(
	IDpeli int unsigned auto_increment primary key,
    NomPeli varchar(50) not null,
    Duracion int unsigned not null,
    IDGen int unsigned not null,
    constraint FK_genero_pelicula foreign key (IDGen) references Genero (IDGen) on update cascade
);

create table sesion(
	IDsesion int unsigned auto_increment primary key, 
    fec date not null,
    hora_ini time not null,
    hora_fin time not null,
    precio decimal(4,2) not null, /* float? */
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
    constraint FK_Cliente_Compra foreign key (DNI) references Cliente (DNI) on update cascade on delete cascade
);

create table Entrada(

	IDEntrada int unsigned auto_increment primary key,
    CantPersonas int unsigned not null,
    IDSesion int unsigned not null,
    IDCompra int unsigned not null,
    constraint FK_Sesion_Entrada foreign key (IDSesion) references Sesion(IDSesion) on update cascade on delete cascade,
    constraint FK_Compra_Entrada foreign key (IDCompra) references Compra(IDCompra) on update cascade on delete cascade
    
);
/*
drop database cine_Elorrieta;
/*