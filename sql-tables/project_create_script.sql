create database bankingapp;
use bankingapp;

CREATE TABLE Persona(
Dpi BIGINT PRIMARY KEY NOT NULL,
PrimerNombre VARCHAR(40) NOT NULL,
SegundoNombre VARCHAR(40) NULL,
TercerNombre VARCHAR(40) NULL,
Apellido VARCHAR(40) NOT NULL,
SegundoApellido VARCHAR(40) NULL,
Direccion VARCHAR(256) NOT NULL,
TelefonoMovil INT NULL,
TelefonoResidencial INT NULL,
CorreoElectronico VARCHAR(128) NULL
);

CREATE TABLE Moneda(
IdMoneda INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Descripcion VARCHAR(16) NOT NULL
);

CREATE TABLE CategoriaTransaccion(
IdCategoriaTransaccion INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Descripcion VARCHAR(64) NOT NULL
);


CREATE TABLE Banco(
IdBanco INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(128) NOT NULL,
Direccion VARCHAR(256) NOT NULL,
EfectivoDisponible  DECIMAL(9,2) NOT NULL
);

CREATE TABLE MonedaBanco(
IdBanco INT NOT NULL,
IdMoneda INT NOT NULL,

CONSTRAINT pk_idbanco_idmoneda PRIMARY KEY(IdBanco, IdMoneda),
CONSTRAINT fk_monedabanco_banco FOREIGN KEY(IdBanco) REFERENCES Banco(IdBanco),
CONSTRAINT fk_monedabanco_moneda FOREIGN KEY(IdMoneda) REFERENCES Moneda(IdMoneda)
);

CREATE TABLE CuentaBancaria(
IdCuentaBancaria INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
IdBanco INT NOT NULL,
Saldo DECIMAL(6,2),
CONSTRAINT fk_cuentabancaria_banco FOREIGN KEY(IdBanco) REFERENCES Banco(IdBanco)
) AUTO_INCREMENT = 1111;

CREATE TABLE Usuario(
Dpi BIGINT NOT NULL PRIMARY KEY,
NombreUsuario VARCHAR(64) NOT NULL,
Password VARCHAR(128) NOT NULL,
IdCuentaBancaria INT NOT NULL,

CONSTRAINT fk_usuario_cuentabancaria FOREIGN KEY(IdCuentaBancaria) REFERENCES CuentaBancaria(IdCuentaBancaria),
CONSTRAINT fk_usuario_persona FOREIGN KEY(Dpi) REFERENCES Persona(Dpi)
);

CREATE TABLE Cajero(
IdCajero INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
IdBanco INT NOT NULL,
Direccion VARCHAR(256) NOT NULL,
EfectivoDisponible DECIMAL(4,2) NOT NULL,

CONSTRAINT fk_cajero_banco FOREIGN KEY(IdBanco) REFERENCES Banco(IdBanco)
);

CREATE TABLE TipoTransaccion(
IdTipoTransaccion INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Descripcion VARCHAR(128) NOT NULL,
IdCategoriaTransaccion INT NOT NULL,

CONSTRAINT fk_tipotransaccion_categoriatransaccion FOREIGN KEY(IdCategoriaTransaccion) 
REFERENCES CategoriaTransaccion(IdCategoriaTransaccion)
);

CREATE TABLE Transaccion(
IdTransaccion INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
IdTipoTransaccion INT NOT NULL,
Monto DECIMAL(6,2) NULL,
IdCajero INT NULL,
IdBanco INT NULL,
IdCuentaBancaria INT NULL,

CONSTRAINT fk_transaccion_cajero FOREIGN KEY(IdCajero) REFERENCES Cajero(IdCajero),
CONSTRAINT fk_transaccion_banco FOREIGN KEY(IdBanco) REFERENCES Banco(IdBanco),
CONSTRAINT fk_transaccion_cuentabancaria FOREIGN KEY(IdCuentaBancaria) REFERENCES CuentaBancaria(IdCuentaBancaria)
);

CREATE TABLE HistorialTransacciones(
IdHistorialTransacciones INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
IdTransaccion INT NOT NULL,

CONSTRAINT fk_historialtransacciones_transaccion FOREIGN KEY(IdTransaccion) REFERENCES Transaccion(IdTransaccion)
);

#ALTER TABLE 'solucionestecnologicas'.'monedabanco'
#CHANGE COLUMN 'IdMonedaBanco' 'IdMonedaBanco' INT NOT NULL ,
#DROP PRIMARY KEY,
#ADD PRIMARY KEY ('IdMonedaBanco', 'IdMoneda');
#;

ALTER TABLE persona CHANGE `primerapellido` `PrimerApellido` varchar(40);

ALTER TABLE usuario
DROP FOREIGN KEY fk_usuario_cuentabancaria;

alter table cuentabancaria 
add column dpi bigint not null

alter table cuentabancaria
add constraint fk_cuentabancaria_usuario foreign key(dpi) references usuario(dpi);

ALTER TABLE CuentaBancaria DROP FOREIGN KEY fl_cuentabancaria_usuario;
ALTER TABLE cuentabancaria CHANGE dpi Dpi BIGINT;

ALTER TABLE Usuario
ADD COLUMN FechaCreacionUsuario timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;




