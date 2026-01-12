create database cine_Elorrieta
collate utf8mb4_spanish_ci; /* is collate ok ? */

/* poner auto increment en los id ? */
/* poner update on cascade ? */
/* cuales valores poner not null */

create table Cine(
	IDCine int primary key,
	nom varchar (50) not null
);

create table sala(
	IDCine int,
	numSala int,
	constraint PK_sala primary key (IDcine, numSala),
	constraint FK_Cine_sala foreign key (IDCine) references Cine (IDCine) on delete cascade /* ODC ?? */
);

create table genero(
	IDGen int primary key,
    NomGen varchar(20) not null
);

create table pelicula(
	IDpeli int primary key,
    NomPeli varchar(50) not null,
    Duracion int not null, /* time???? */
    IDGen int,
    constraint FK_genero_pelicula foreign key (IDGen) references Genero (IDGen)
);

create table sesion(
	IDsesion int primary key, 
    fec date not null,
    hora_ini time not null,
    hora_fin time not null,
    precio float not null, /* float? */
    IDCine int,
	NumSala int,
    IDPeli int,
    constraint FK_Sala_Sesion foreign key (IDCine, NumSala) references sala (IDCine, NumSala),
    constraint FK_Pelicula_Sesion foreign key (IDPeli) references Pelicula (IDPeli)
);

create table Cliente(
	DNI varchar(9) primary key,
    NomCli varchar (20) not null,
    Ape varchar (20) not null,
    mail varchar(100) unique not null,
    password varchar(25) not null /* is this right ? idk how to save the password and I also dk why it is blued */

);

create table Compra(

	IDCompra int primary key,
    FecCompra date not null,
    plataforma enum ('web','app') not null, 
    DNI varchar(9) not null,
    constraint FK_Cliente_Compra foreign key (DNI) references Cliente (DNI)
    
);

create table Entrada(

	IDEntrada int primary key,
    CantPersonas int not null,
    IDSesion int not null,
    IDCompra int not null,
    constraint FK_Sesion_Entrada foreign key (IDSesion) references Sesion(IDSesion),
    constraint FK_Compra_Entrada foreign key (IDCompra) references Compra(IDCompra)
    
);

drop database cine_Elorrieta;