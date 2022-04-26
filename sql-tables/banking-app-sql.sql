CREATE TABLE `banco` (
  `IdBanco` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(128) NOT NULL,
  `Direccion` varchar(256) NOT NULL,
  `EfectivoDisponible` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`IdBanco`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cajero` (
  `IdCajero` int NOT NULL AUTO_INCREMENT,
  `IdBanco` int NOT NULL,
  `Direccion` varchar(256) NOT NULL,
  `EfectivoDisponible` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`IdCajero`),
  KEY `fk_cajero_banco` (`IdBanco`),
  CONSTRAINT `fk_cajero_banco` FOREIGN KEY (`IdBanco`) REFERENCES `banco` (`IdBanco`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `categoriatransaccion` (
  `IdCategoriaTransaccion` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(64) NOT NULL,
  PRIMARY KEY (`IdCategoriaTransaccion`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cuentabancaria` (
  `IdCuentaBancaria` int NOT NULL AUTO_INCREMENT,
  `IdBanco` int NOT NULL,
  `saldo` decimal(10,2) NOT NULL,
  `Dpi` bigint DEFAULT NULL,
  PRIMARY KEY (`IdCuentaBancaria`),
  KEY `fk_cuentabancaria_banco` (`IdBanco`),
  KEY `fk_cuentabancaria_usuario` (`Dpi`),
  CONSTRAINT `fk_cuentabancaria_banco` FOREIGN KEY (`IdBanco`) REFERENCES `banco` (`IdBanco`),
  CONSTRAINT `fk_cuentabancaria_usuario` FOREIGN KEY (`Dpi`) REFERENCES `usuario` (`Dpi`)
) ENGINE=InnoDB AUTO_INCREMENT=1136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `moneda` (
  `IdMoneda` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(16) NOT NULL,
  PRIMARY KEY (`IdMoneda`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `monedabanco` (
  `IdMonedaBanco` int NOT NULL AUTO_INCREMENT,
  `IdBanco` int NOT NULL,
  `IdMoneda` int NOT NULL,
  PRIMARY KEY (`IdMonedaBanco`),
  KEY `fk_monedabanco_banco` (`IdBanco`),
  KEY `fk_monedabanco_moneda` (`IdMoneda`),
  CONSTRAINT `fk_monedabanco_banco` FOREIGN KEY (`IdBanco`) REFERENCES `banco` (`IdBanco`),
  CONSTRAINT `fk_monedabanco_moneda` FOREIGN KEY (`IdMoneda`) REFERENCES `moneda` (`IdMoneda`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `persona` (
  `Dpi` bigint NOT NULL,
  `PrimerNombre` varchar(40) NOT NULL,
  `SegundoNombre` varchar(40) DEFAULT NULL,
  `TercerNombre` varchar(40) DEFAULT NULL,
  `PrimerApellido` varchar(40) DEFAULT NULL,
  `SegundoApellido` varchar(40) DEFAULT NULL,
  `Direccion` varchar(256) NOT NULL,
  `TelefonoMovil` bigint DEFAULT NULL,
  `TelefonoResidencial` bigint DEFAULT NULL,
  `CorreoElectronico` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`Dpi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tipotransaccion` (
  `IdTipoTransaccion` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(128) NOT NULL,
  `IdCategoriaTransaccion` int NOT NULL,
  PRIMARY KEY (`IdTipoTransaccion`),
  KEY `fk_tipotransaccion_categoriatransaccion` (`IdCategoriaTransaccion`),
  CONSTRAINT `fk_tipotransaccion_categoriatransaccion` FOREIGN KEY (`IdCategoriaTransaccion`) REFERENCES `categoriatransaccion` (`IdCategoriaTransaccion`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transaccion` (
  `IdTransaccion` int NOT NULL AUTO_INCREMENT,
  `IdTipoTransaccion` int NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `IdCajero` int DEFAULT NULL,
  `IdBanco` int DEFAULT NULL,
  `IdCuentaBancaria` int DEFAULT NULL,
  `FechaTransaccion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IdMoneda` int DEFAULT NULL,
  PRIMARY KEY (`IdTransaccion`),
  KEY `fk_transaccion_cajero` (`IdCajero`),
  KEY `fk_transaccion_banco` (`IdBanco`),
  KEY `fk_transaccion_cuentabancaria` (`IdCuentaBancaria`),
  CONSTRAINT `fk_transaccion_banco` FOREIGN KEY (`IdBanco`) REFERENCES `banco` (`IdBanco`),
  CONSTRAINT `fk_transaccion_cajero` FOREIGN KEY (`IdCajero`) REFERENCES `cajero` (`IdCajero`),
  CONSTRAINT `fk_transaccion_cuentabancaria` FOREIGN KEY (`IdCuentaBancaria`) REFERENCES `cuentabancaria` (`IdCuentaBancaria`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario` (
  `Dpi` bigint NOT NULL,
  `NombreUsuario` varchar(64) NOT NULL,
  `Password` varchar(128) NOT NULL,
  `FechaCreacionUsuario` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Dpi`),
  CONSTRAINT `fk_usuario_persona` FOREIGN KEY (`Dpi`) REFERENCES `persona` (`Dpi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
