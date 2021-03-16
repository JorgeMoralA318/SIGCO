/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.27-log : Database - sigcodb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sigcodb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sigcodb`;

/*Table structure for table `acceso` */

DROP TABLE IF EXISTS `acceso`;

CREATE TABLE `acceso` (
  `idAcceso` int(11) NOT NULL AUTO_INCREMENT,
  `codRol` varchar(5) DEFAULT NULL,
  `codModulo` varchar(5) DEFAULT NULL,
  `codVentana` varchar(5) DEFAULT NULL,
  `acceso` int(1) DEFAULT '0',
  `nuevo` int(1) DEFAULT '0',
  `actualizar` int(1) DEFAULT '0',
  `borrar` int(1) DEFAULT '0',
  PRIMARY KEY (`idAcceso`),
  KEY `fk_acceso_rol_idx` (`codRol`),
  KEY `fk_acceso_modulo_idx` (`codModulo`),
  KEY `fk_acceso_ventana_idx` (`codVentana`),
  CONSTRAINT `fk_acceso_modulo` FOREIGN KEY (`codModulo`) REFERENCES `modulo` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_acceso_rol` FOREIGN KEY (`codRol`) REFERENCES `rol` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_acceso_ventana` FOREIGN KEY (`codVentana`) REFERENCES `ventana` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `acceso` */

insert  into `acceso`(`idAcceso`,`codRol`,`codModulo`,`codVentana`,`acceso`,`nuevo`,`actualizar`,`borrar`) values 
(8,'00001','00001','00001',1,9,1,1),
(9,'00001','00001','00002',1,1,1,1),
(10,'00001','00001','00003',1,1,1,1),
(11,'00001','00001','00004',1,1,1,1),
(12,'00001','00001','00005',1,1,1,1),
(13,'00001','00001','00006',1,1,1,1),
(14,'00001','00001','00007',1,1,1,1),
(15,'00002','00001','00001',0,1,1,1),
(16,'00002','00001','00002',0,1,1,1),
(17,'00002','00001','00003',0,1,1,1),
(18,'00002','00001','00004',1,1,1,1),
(19,'00002','00001','00005',1,1,1,1),
(20,'00002','00001','00006',0,1,1,1),
(21,'00002','00001','00007',1,1,1,1);

/*Table structure for table `adelanto` */

DROP TABLE IF EXISTS `adelanto`;

CREATE TABLE `adelanto` (
  `idAdelanto` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `estado` enum('PEN','CAN') DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `obs` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idAdelanto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `adelanto` */

/*Table structure for table `amonestacion` */

DROP TABLE IF EXISTS `amonestacion`;

CREATE TABLE `amonestacion` (
  `idAmonestacion` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `codMotAmo` varchar(5) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idAmonestacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `amonestacion` */

/*Table structure for table `articulo` */

DROP TABLE IF EXISTS `articulo`;

CREATE TABLE `articulo` (
  `codigo` varchar(10) NOT NULL,
  `CIP` varchar(25) DEFAULT NULL COMMENT 'codigo interno del producto',
  `descripcion` varchar(45) DEFAULT NULL,
  `especificacion` text,
  `fechaIngreso` date DEFAULT NULL,
  `precioCosto` decimal(10,2) DEFAULT NULL,
  `precioVenta` decimal(10,2) DEFAULT NULL,
  `codMarca` varchar(5) DEFAULT NULL,
  `codFamilia` varchar(5) DEFAULT NULL COMMENT 'unidad de medida',
  `activo` enum('N','S') DEFAULT NULL COMMENT '1 - Activo\n2 - Inactivo\nhabilitar este producto para venta',
  `foto` longblob,
  `CodEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `articulo` */

/*Table structure for table `asignacionzona` */

DROP TABLE IF EXISTS `asignacionzona`;

CREATE TABLE `asignacionzona` (
  `codZona` varchar(5) NOT NULL,
  `codCiudad` varchar(5) NOT NULL,
  `obs` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`codZona`,`codCiudad`),
  KEY `fk_Zona_has_Ciudad_Ciudad1_idx` (`codCiudad`),
  KEY `fk_Zona_has_Ciudad_Zona1_idx` (`codZona`),
  CONSTRAINT `fk_Zona_has_Ciudad_Ciudad1` FOREIGN KEY (`codCiudad`) REFERENCES `ciudad` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Zona_has_Ciudad_Zona1` FOREIGN KEY (`codZona`) REFERENCES `zona` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `asignacionzona` */

/*Table structure for table `banco` */

DROP TABLE IF EXISTS `banco`;

CREATE TABLE `banco` (
  `codigo` varchar(5) NOT NULL,
  `banco` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `banco` */

/*Table structure for table `barrio` */

DROP TABLE IF EXISTS `barrio`;

CREATE TABLE `barrio` (
  `codigo` varchar(5) NOT NULL,
  `barrio` varchar(45) DEFAULT NULL,
  `codciudad` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_barrrio_ciudad_idx` (`codciudad`),
  CONSTRAINT `fk_barrrio_ciudad` FOREIGN KEY (`codciudad`) REFERENCES `ciudad` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `barrio` */

insert  into `barrio`(`codigo`,`barrio`,`codciudad`) values 
('00001','SANTA ANA','00002'),
('00004','san jose','00001'),
('B-2','san miguel','00001'),
('B-3','santo tomas','00001');

/*Table structure for table `boleta` */

DROP TABLE IF EXISTS `boleta`;

CREATE TABLE `boleta` (
  `idBoleta` int(11) NOT NULL,
  `nroBoleta` varchar(10) DEFAULT NULL,
  `codBanco` varchar(5) DEFAULT NULL,
  `nroCuenta` varchar(20) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `serie` char(1) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `tipoCuenta` enum('CC','CA') DEFAULT NULL,
  `tipoBoleta` enum('DEP','EXT') DEFAULT NULL COMMENT 'DEP - deposito\nEXT - Extraccion\n',
  `totalImporte` decimal(10,2) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idBoleta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `boleta` */

/*Table structure for table `cargo` */

DROP TABLE IF EXISTS `cargo`;

CREATE TABLE `cargo` (
  `codigo` varchar(5) NOT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cargo` */

insert  into `cargo`(`codigo`,`cargo`) values 
('00001','COBRADOR'),
('00002','VENDEDOR');

/*Table structure for table `cargo_salario` */

DROP TABLE IF EXISTS `cargo_salario`;

CREATE TABLE `cargo_salario` (
  `codigo` varchar(5) NOT NULL,
  `codCargo` varchar(5) DEFAULT NULL,
  `codPersonal` varchar(10) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL,
  `codMotivo` varchar(5) DEFAULT NULL,
  `comVenta` int(2) DEFAULT NULL,
  `comCobro` int(2) DEFAULT NULL,
  `estado` enum('A','I') DEFAULT 'A' COMMENT 'A = activo\nb= inactivo',
  PRIMARY KEY (`codigo`),
  KEY `fk_Cargo_has_Personal_Personal1_idx` (`codPersonal`),
  KEY `fk_Cargo_has_Personal_Cargo1_idx` (`codCargo`),
  KEY `fk_cargo_motivBaja_idx` (`codMotivo`),
  CONSTRAINT `fk_Cargo_has_Personal_Cargo1` FOREIGN KEY (`codCargo`) REFERENCES `cargo` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cargo_has_Personal_Personal1` FOREIGN KEY (`codPersonal`) REFERENCES `personal` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cargo_motivBaja` FOREIGN KEY (`codMotivo`) REFERENCES `motivobaja` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cargo_salario` */

insert  into `cargo_salario`(`codigo`,`codCargo`,`codPersonal`,`fecha_alta`,`salario`,`fecha_baja`,`codMotivo`,`comVenta`,`comCobro`,`estado`) values 
('00001','00001','00001','2020-10-03',2300000.00,NULL,NULL,0,6,'A'),
('00002','00002','00001','2020-10-03',2300000.00,NULL,NULL,2,3,'A');

/*Table structure for table `cheque` */

DROP TABLE IF EXISTS `cheque`;

CREATE TABLE `cheque` (
  `idCheque` int(11) NOT NULL AUTO_INCREMENT,
  `nroCheque` varchar(10) DEFAULT NULL,
  `codbeneficiario` varchar(5) DEFAULT NULL,
  `codBanco` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `fechaEmision` date DEFAULT NULL,
  `fechaRecepcion` date DEFAULT NULL,
  `origen` enum('VNT','CMP','OTR') DEFAULT NULL COMMENT 'VNT - venta\nCMP - compra\nOTR  otros',
  `monto` decimal(10,2) DEFAULT NULL,
  `estado` enum('PEN','COB','DEP','ANU') DEFAULT NULL COMMENT 'PEN - PENDIENTE\nCOB- COBRADOR\nDEP - DEPOSITADO\nANU -ANULADO\n',
  `detalle` varchar(45) DEFAULT NULL,
  `tipo` enum('CB','PG') DEFAULT NULL COMMENT 'CB - Cobro\nPG - Pago',
  `tipoBenef` enum('CLI','PRO','PER','EMP') DEFAULT NULL COMMENT 'CLI - Cliente\nPRO - Proveedor\nPER - Personal\nEMP - Empresa',
  PRIMARY KEY (`idCheque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cheque` */

/*Table structure for table `ciudad` */

DROP TABLE IF EXISTS `ciudad`;

CREATE TABLE `ciudad` (
  `codigo` varchar(5) NOT NULL,
  `ciudad` varchar(45) DEFAULT NULL,
  `codDepartamento` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_ciudad_dpto_idx` (`codDepartamento`),
  CONSTRAINT `fk_ciudad_dpto` FOREIGN KEY (`codDepartamento`) REFERENCES `departamento` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ciudad` */

insert  into `ciudad`(`codigo`,`ciudad`,`codDepartamento`) values 
('00001','CHORE','00001'),
('00002','Liberacion','00001'),
('00003','askmdflk','00001');

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `codigo` varchar(10) NOT NULL,
  `ruc_ci` varchar(12) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fechaNac` date DEFAULT NULL,
  `fechaIng` date DEFAULT NULL COMMENT 'fecha de ingreso',
  `sexo` enum('M','F') DEFAULT NULL,
  `estadoCivil` enum('S','C','D','V') DEFAULT NULL,
  `telefono` varchar(25) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `codProfesion` varchar(5) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `codPais` varchar(5) DEFAULT NULL,
  `codDepartamento` varchar(5) DEFAULT NULL,
  `codCiudad` varchar(5) DEFAULT NULL,
  `codBarrio` varchar(5) DEFAULT NULL,
  `nroCasa` varchar(10) DEFAULT NULL,
  `codCobrador` varchar(5) DEFAULT NULL,
  `codVendedor` varchar(5) DEFAULT NULL,
  `limiteCredito` decimal(10,2) DEFAULT NULL,
  `isdeuda` int(11) DEFAULT '0' COMMENT 'Indica si un cliente tiene deuda pendiente o cancelado\n0 - no tiene deuda pendiente\n1 - tiene deuda pendiente',
  `isSolicitud` int(1) DEFAULT '0' COMMENT 'Este campo sirve para distingir si un cliente tiene o no solicitudes pendientes\nvalores:\n0 sin solicitud pendiente\n1 solicitud pendiente',
  `obs` text,
  PRIMARY KEY (`codigo`),
  KEY `fk_cliente_cobrador_idx` (`codVendedor`),
  KEY `fk_cliente_pais_idx` (`codPais`),
  KEY `fk_cliente_dpto_idx` (`codDepartamento`),
  KEY `fk_cliente_ciudad_idx` (`codCiudad`),
  KEY `fk_cliente_barrio_idx` (`codBarrio`),
  KEY `fk_clinte_sucursal_idx` (`codSucursal`),
  KEY `fk_cliente_profesion_idx` (`codProfesion`),
  KEY `fk_cliente_vendedor` (`codCobrador`),
  CONSTRAINT `fk_cliente_barrio` FOREIGN KEY (`codBarrio`) REFERENCES `barrio` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_ciudad` FOREIGN KEY (`codCiudad`) REFERENCES `ciudad` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_cobrador` FOREIGN KEY (`codVendedor`) REFERENCES `cargo_salario` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_dpto` FOREIGN KEY (`codDepartamento`) REFERENCES `departamento` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_pais` FOREIGN KEY (`codPais`) REFERENCES `pais` (`Codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_profesion` FOREIGN KEY (`codProfesion`) REFERENCES `profesion` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_vendedor` FOREIGN KEY (`codCobrador`) REFERENCES `cargo_salario` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_clinte_sucursal` FOREIGN KEY (`codSucursal`) REFERENCES `sucursal` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

insert  into `cliente`(`codigo`,`ruc_ci`,`nombre`,`apellido`,`fechaNac`,`fechaIng`,`sexo`,`estadoCivil`,`telefono`,`codSucursal`,`codProfesion`,`email`,`codPais`,`codDepartamento`,`codCiudad`,`codBarrio`,`nroCasa`,`codCobrador`,`codVendedor`,`limiteCredito`,`isdeuda`,`isSolicitud`,`obs`) values 
('00001','5652925','JORGE RAMON','MORAL ACOSTA','1995-09-30',NULL,'M','S','0983785318','00001','00001','jorgemoral@gmail.com','00001','00001','00001','00001','32442','00002','00001',3000000.00,0,0,'no tiene ninguna observacion'),
('00002','78788779','JUAN ','PEREZ','2020-10-25',NULL,'M','D','09876776688','00001','00001','-','00001','00001','00001','00001','878','00001','00001',68787888.00,0,0,'-'),
('00003','687677','PEDRO','GONZALEZ','2018-08-18',NULL,'F','S','-','00001','00001','-','00001','00001','00001','00001','9899','00002','00001',9797998.00,0,0,'-'),
('00004','345524','Cesar','Caballero','2020-10-25',NULL,'F','S','09898707','00001','00001','-','00001','00001','00001','00001','98989','00001','00001',9898989.00,0,0,'-'),
('00005','6766676','CESAR DAVID','GODOY AYALA','2020-10-10',NULL,'M','S','098767766','00001','00001','-','00001','00001','00001','00001','2','00001','00002',870000.00,0,0,'-');

/*Table structure for table `combo` */

DROP TABLE IF EXISTS `combo`;

CREATE TABLE `combo` (
  `idCombo` int(11) NOT NULL AUTO_INCREMENT,
  `nroCombo` varchar(5) DEFAULT NULL,
  `Decripcion` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `fechaDesde` date DEFAULT NULL,
  `fechaHasta` date DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idCombo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `combo` */

/*Table structure for table `compra` */

DROP TABLE IF EXISTS `compra`;

CREATE TABLE `compra` (
  `idCompra` int(11) NOT NULL AUTO_INCREMENT,
  `nroComprobante` varchar(15) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` varchar(15) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `codProveedor` varchar(5) DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `codDeposito` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `tipoCompra` enum('CON','CRE') DEFAULT NULL,
  PRIMARY KEY (`idCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `compra` */

/*Table structure for table `concepto_cargo` */

DROP TABLE IF EXISTS `concepto_cargo`;

CREATE TABLE `concepto_cargo` (
  `idconcepto_cargo` int(11) NOT NULL,
  `codConceptoRetri` varchar(5) DEFAULT NULL,
  `codCargo` varchar(45) DEFAULT NULL,
  `importe` decimal(10,2) DEFAULT NULL,
  `porcentaje` decimal(10,2) DEFAULT NULL,
  `calcPorcentaje` int(11) DEFAULT '0' COMMENT 'campo para establecer que se utilizara un porcentaje.\n1 usar calculo en base a porcentajes.',
  PRIMARY KEY (`idconcepto_cargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `concepto_cargo` */

/*Table structure for table `conceptoretribucion` */

DROP TABLE IF EXISTS `conceptoretribucion`;

CREATE TABLE `conceptoretribucion` (
  `codigo` varchar(5) NOT NULL,
  `concepto` varchar(45) DEFAULT NULL,
  `conceptode` enum('A','D') DEFAULT NULL COMMENT 'A- Abono\nD- Descuento',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `conceptoretribucion` */

/*Table structure for table `contrato` */

DROP TABLE IF EXISTS `contrato`;

CREATE TABLE `contrato` (
  `idContrato` int(11) NOT NULL AUTO_INCREMENT,
  `codCargo` varchar(5) DEFAULT NULL,
  `fechainicio` date DEFAULT NULL,
  `fechavto` date DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `tipoContrato` enum('D','I') DEFAULT NULL COMMENT 'tipo de contrato.\nD - contrato definido\nI - contrato indefinido',
  `tipoJornada` enum('D','M','S') DEFAULT NULL COMMENT 'D-diaria\nM-Mensual\nS-Semanal',
  `horaJornada` int(11) DEFAULT NULL,
  `duracionDia` int(11) DEFAULT NULL COMMENT 'duracion de contrato en dias.',
  `codMotivoFinali` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `estado` enum('V','F') DEFAULT NULL COMMENT 'V - vigente\nF - finalizado\nS - Suspendido',
  PRIMARY KEY (`idContrato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contrato` */

/*Table structure for table `credito` */

DROP TABLE IF EXISTS `credito`;

CREATE TABLE `credito` (
  `idCredito` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `primerVto` date DEFAULT NULL,
  `entregaInicial` varchar(45) DEFAULT NULL,
  `cantCouta` int(11) DEFAULT NULL,
  `origen` enum('CLI','PRO','PER') DEFAULT NULL COMMENT 'CLI -cliente\nPRO - proveedor\nPER - Personal.',
  `idAdelanto` int(11) DEFAULT NULL COMMENT 'FK de adelanto',
  `total` decimal(10,2) DEFAULT NULL,
  `saldo` decimal(10,2) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `estado` enum('PEN','CAN','ANU') DEFAULT NULL COMMENT 'PEN - pendiente\nCAN - cancelado\nANU - anulado',
  PRIMARY KEY (`idCredito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `credito` */

/*Table structure for table `cuenta` */

DROP TABLE IF EXISTS `cuenta`;

CREATE TABLE `cuenta` (
  `idCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `nroCuenta` varchar(10) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucusal` varchar(5) DEFAULT NULL,
  `fechaApe` date DEFAULT NULL,
  `saldoActual` decimal(10,2) DEFAULT NULL,
  `saldoMinimo` decimal(10,2) DEFAULT NULL,
  `codBanco` varchar(5) DEFAULT NULL,
  `descripcioncta` varchar(45) DEFAULT NULL,
  `tipo` enum('CC','CA') DEFAULT NULL COMMENT 'CC - CUENTA CORRIENTE\nCA - CAJA AHORRO',
  PRIMARY KEY (`idCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cuenta` */

/*Table structure for table `cuentatercero` */

DROP TABLE IF EXISTS `cuentatercero`;

CREATE TABLE `cuentatercero` (
  `idcuentaTercero` int(11) NOT NULL AUTO_INCREMENT,
  `nroCuenta` varchar(10) DEFAULT NULL,
  `titular` varchar(45) DEFAULT NULL,
  `ciRuc` varchar(10) DEFAULT NULL,
  `codBanco` varchar(5) DEFAULT NULL,
  `tipoCuenta` enum('PRO','CLI','EMP') DEFAULT NULL,
  `codTitular` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idcuentaTercero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cuentatercero` */

/*Table structure for table `departamento` */

DROP TABLE IF EXISTS `departamento`;

CREATE TABLE `departamento` (
  `codigo` varchar(5) NOT NULL,
  `departamento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `departamento` */

insert  into `departamento`(`codigo`,`departamento`) values 
('00001','SAN PEDRO');

/*Table structure for table `deposito` */

DROP TABLE IF EXISTS `deposito`;

CREATE TABLE `deposito` (
  `codigo` varchar(5) NOT NULL,
  `deposito` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `deposito` */

/*Table structure for table `detboleta` */

DROP TABLE IF EXISTS `detboleta`;

CREATE TABLE `detboleta` (
  `idDetBoleta` int(11) NOT NULL AUTO_INCREMENT,
  `nroChequre` varchar(10) DEFAULT NULL,
  `importe` decimal(10,2) DEFAULT NULL,
  `tipo` enum('EFE','CHQ') DEFAULT NULL COMMENT 'EFE -EFECTIVO\nCHQ -CHEQUE',
  `idBoleta` int(11) NOT NULL,
  PRIMARY KEY (`idDetBoleta`),
  KEY `fk_DetBoleta_Boleta1_idx` (`idBoleta`),
  CONSTRAINT `fk_DetBoleta_Boleta1` FOREIGN KEY (`idBoleta`) REFERENCES `boleta` (`idBoleta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detboleta` */

/*Table structure for table `detcombo` */

DROP TABLE IF EXISTS `detcombo`;

CREATE TABLE `detcombo` (
  `idDetCombo` int(11) NOT NULL AUTO_INCREMENT,
  `idCombo` int(11) NOT NULL,
  `CodArticulo` varchar(5) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDetCombo`),
  KEY `fk_DetCombo_Combo1_idx` (`idCombo`),
  CONSTRAINT `fk_DetCombo_Combo1` FOREIGN KEY (`idCombo`) REFERENCES `combo` (`idCombo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detcombo` */

/*Table structure for table `detcompra` */

DROP TABLE IF EXISTS `detcompra`;

CREATE TABLE `detcompra` (
  `idDetCompra` int(11) NOT NULL AUTO_INCREMENT,
  `idCompra` int(11) NOT NULL,
  `codArticulo` varchar(10) DEFAULT NULL,
  `precioCosto` decimal(10,2) DEFAULT NULL,
  `iva` int(11) DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idDetCompra`),
  KEY `fk_DetCompra_Compra1_idx` (`idCompra`),
  CONSTRAINT `fk_DetCompra_Compra1` FOREIGN KEY (`idCompra`) REFERENCES `compra` (`idCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detcompra` */

/*Table structure for table `detcredito` */

DROP TABLE IF EXISTS `detcredito`;

CREATE TABLE `detcredito` (
  `idDetCredito` int(11) NOT NULL AUTO_INCREMENT,
  `idCredito` int(11) NOT NULL,
  `orden` int(11) DEFAULT NULL,
  `fecvto` date DEFAULT NULL,
  `diaAtraso` int(11) DEFAULT NULL,
  `fechaPago` date DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `importe` decimal(10,2) DEFAULT NULL,
  `saldo` decimal(10,2) DEFAULT NULL,
  `estado` enum('PEN','CAN') DEFAULT NULL,
  PRIMARY KEY (`idDetCredito`),
  KEY `fk_DetCredito_Credito1_idx` (`idCredito`),
  CONSTRAINT `fk_DetCredito_Credito1` FOREIGN KEY (`idCredito`) REFERENCES `credito` (`idCredito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detcredito` */

/*Table structure for table `detinventariomov` */

DROP TABLE IF EXISTS `detinventariomov`;

CREATE TABLE `detinventariomov` (
  `idDetInventarioMov` int(11) NOT NULL AUTO_INCREMENT,
  `idInventarioMov` int(11) NOT NULL,
  `codArticulo` varchar(5) DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `precioCosto` decimal(10,2) DEFAULT NULL COMMENT 'precio de costo.',
  PRIMARY KEY (`idDetInventarioMov`),
  KEY `fk_DetInventarioMov_InventarioMov1_idx` (`idInventarioMov`),
  CONSTRAINT `fk_DetInventarioMov_InventarioMov1` FOREIGN KEY (`idInventarioMov`) REFERENCES `inventariomov` (`idInventarioMov`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detinventariomov` */

/*Table structure for table `detnomina` */

DROP TABLE IF EXISTS `detnomina`;

CREATE TABLE `detnomina` (
  `idDetNomina` int(11) NOT NULL AUTO_INCREMENT,
  `idNomina` int(11) NOT NULL,
  `CodConceptoRetri` varchar(5) DEFAULT NULL,
  `conceptoDe` enum('A','D') DEFAULT NULL,
  `porcentaje` decimal(10,2) DEFAULT NULL,
  `importe` decimal(10,2) DEFAULT NULL,
  `pagado` int(11) DEFAULT '0' COMMENT '1',
  `orden` int(11) DEFAULT NULL COMMENT 'orden de cuota en adelantos',
  PRIMARY KEY (`idDetNomina`),
  KEY `fk_DetNomina_Nomina1_idx` (`idNomina`),
  CONSTRAINT `fk_DetNomina_Nomina1` FOREIGN KEY (`idNomina`) REFERENCES `nomina` (`idNomina`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detnomina` */

/*Table structure for table `detoferta` */

DROP TABLE IF EXISTS `detoferta`;

CREATE TABLE `detoferta` (
  `idDetOferta` int(11) NOT NULL AUTO_INCREMENT,
  `codArticulo` varchar(5) DEFAULT NULL,
  `descuento` decimal(10,2) DEFAULT NULL,
  `idOferta` int(11) NOT NULL,
  PRIMARY KEY (`idDetOferta`),
  KEY `fk_DetOferta_Oferta1_idx` (`idOferta`),
  CONSTRAINT `fk_DetOferta_Oferta1` FOREIGN KEY (`idOferta`) REFERENCES `oferta` (`idOferta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detoferta` */

/*Table structure for table `detordencompra` */

DROP TABLE IF EXISTS `detordencompra`;

CREATE TABLE `detordencompra` (
  `idDetOrdenCompra` int(11) NOT NULL AUTO_INCREMENT,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `precioCosto` decimal(10,2) DEFAULT NULL,
  `iva` int(11) DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `codArticulo` varchar(10) DEFAULT NULL,
  `idOrdenCompra` int(11) NOT NULL,
  PRIMARY KEY (`idDetOrdenCompra`),
  KEY `fk_DetOrdenCompra_OrdenCompra1_idx` (`idOrdenCompra`),
  CONSTRAINT `fk_DetOrdenCompra_OrdenCompra1` FOREIGN KEY (`idOrdenCompra`) REFERENCES `ordencompra` (`idOrdenCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detordencompra` */

/*Table structure for table `detordenpago` */

DROP TABLE IF EXISTS `detordenpago`;

CREATE TABLE `detordenpago` (
  `idDetOrdenPago` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdenPago` int(11) NOT NULL,
  `idCredito` int(11) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `codConcepto` varchar(5) DEFAULT NULL COMMENT 'Si Origen en la tabla OrdenPago es ''PER'', entonces este campo contendra los codigo del concepto de retribucion tablas ''ConceptoRetribucion''.',
  `monto` decimal(10,2) DEFAULT NULL,
  `porcent` decimal(10,2) DEFAULT NULL COMMENT 'contendra valores de porcentaje que puedan venir adjuntado de cualquier indole.',
  PRIMARY KEY (`idDetOrdenPago`),
  KEY `fk_DetOrdenPago_OrdenPago1_idx` (`idOrdenPago`),
  CONSTRAINT `fk_DetOrdenPago_OrdenPago1` FOREIGN KEY (`idOrdenPago`) REFERENCES `ordenpago` (`idOrdenPago`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detordenpago` */

/*Table structure for table `detpagocob` */

DROP TABLE IF EXISTS `detpagocob`;

CREATE TABLE `detpagocob` (
  `idDetPagoCob` int(11) NOT NULL AUTO_INCREMENT,
  `idPagoCob` int(11) NOT NULL,
  `idCredito` int(11) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `codConcepto` varchar(5) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `interes` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idDetPagoCob`),
  KEY `fk_DetPagoCob_PagoCob1_idx` (`idPagoCob`),
  CONSTRAINT `fk_DetPagoCob_PagoCob1` FOREIGN KEY (`idPagoCob`) REFERENCES `pagocob` (`idPagoCob`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detpagocob` */

/*Table structure for table `detprestamoarticulo` */

DROP TABLE IF EXISTS `detprestamoarticulo`;

CREATE TABLE `detprestamoarticulo` (
  `idDetPrestamoArticulo` int(11) NOT NULL AUTO_INCREMENT,
  `idPrestamoAriculo` int(11) NOT NULL,
  `codArticulo` varchar(5) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idDetPrestamoArticulo`),
  KEY `fk_DetPrestamoArticulo_PrestamoAriculo1_idx` (`idPrestamoAriculo`),
  CONSTRAINT `fk_DetPrestamoArticulo_PrestamoAriculo1` FOREIGN KEY (`idPrestamoAriculo`) REFERENCES `prestamoariculo` (`idPrestamoAriculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detprestamoarticulo` */

/*Table structure for table `detrend` */

DROP TABLE IF EXISTS `detrend`;

CREATE TABLE `detrend` (
  `idDetRendRecibo` int(11) NOT NULL AUTO_INCREMENT,
  `idRendRecibo` int(11) NOT NULL,
  `idRendicion` int(11) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `codConcepto` varchar(5) DEFAULT NULL,
  `monto` varchar(45) DEFAULT NULL,
  `interes` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idDetRendRecibo`),
  KEY `fk_DetRend_RendRecibo1_idx` (`idRendRecibo`),
  CONSTRAINT `fk_DetRend_RendRecibo1` FOREIGN KEY (`idRendRecibo`) REFERENCES `rendrecibo` (`idRendRecibo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detrend` */

/*Table structure for table `detretribucion` */

DROP TABLE IF EXISTS `detretribucion`;

CREATE TABLE `detretribucion` (
  `idDetRetribucion` int(11) NOT NULL AUTO_INCREMENT,
  `idRetribucion` int(11) NOT NULL,
  `codConceptoRetri` varchar(5) DEFAULT NULL,
  `importe` decimal(10,2) DEFAULT NULL,
  `porcentaje` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idDetRetribucion`),
  KEY `fk_DetRetribucion_Retribucion1_idx` (`idRetribucion`),
  CONSTRAINT `fk_DetRetribucion_Retribucion1` FOREIGN KEY (`idRetribucion`) REFERENCES `retribucion` (`idRetribucion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detretribucion` */

/*Table structure for table `detsolicitudcredito` */

DROP TABLE IF EXISTS `detsolicitudcredito`;

CREATE TABLE `detsolicitudcredito` (
  `idDetSolicitudCredito` int(11) NOT NULL AUTO_INCREMENT,
  `SidSolicitudCredito` int(11) NOT NULL,
  `codArticulo` varchar(10) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idDetSolicitudCredito`),
  KEY `fk_DetSolicitudCredito_SolicitudCredito1_idx` (`SidSolicitudCredito`),
  CONSTRAINT `fk_DetSolicitudCredito_SolicitudCredito1` FOREIGN KEY (`SidSolicitudCredito`) REFERENCES `solicitudcredito` (`idSolicitudCredito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detsolicitudcredito` */

/*Table structure for table `detventa` */

DROP TABLE IF EXISTS `detventa`;

CREATE TABLE `detventa` (
  `idDetVenta` int(11) NOT NULL AUTO_INCREMENT,
  `codArticulo` varchar(10) DEFAULT NULL,
  `cantidad` decimal(10,2) DEFAULT NULL,
  `precioCosto` decimal(10,2) DEFAULT NULL,
  `precioVenta` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `idVenta` int(11) NOT NULL,
  PRIMARY KEY (`idDetVenta`),
  KEY `fk_DetVenta_Venta1_idx` (`idVenta`),
  CONSTRAINT `fk_DetVenta_Venta1` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`idVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detventa` */

/*Table structure for table `empresa` */

DROP TABLE IF EXISTS `empresa`;

CREATE TABLE `empresa` (
  `codigo` varchar(5) NOT NULL,
  `empresa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `empresa` */

insert  into `empresa`(`codigo`,`empresa`) values 
('00001','RODY COMERCIAL');

/*Table structure for table `esticontrato` */

DROP TABLE IF EXISTS `esticontrato`;

CREATE TABLE `esticontrato` (
  `idEstiContrato` int(11) NOT NULL AUTO_INCREMENT,
  `idContrato` int(11) NOT NULL,
  `codEstipulacion` varchar(5) DEFAULT NULL,
  `textoadicional` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idEstiContrato`),
  KEY `fk_EstiContrato_Contrato1_idx` (`idContrato`),
  CONSTRAINT `fk_EstiContrato_Contrato1` FOREIGN KEY (`idContrato`) REFERENCES `contrato` (`idContrato`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `esticontrato` */

/*Table structure for table `estipulacion` */

DROP TABLE IF EXISTS `estipulacion`;

CREATE TABLE `estipulacion` (
  `codigo` varchar(5) NOT NULL,
  `Estipulacion` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `estipulacion` */

/*Table structure for table `existencia` */

DROP TABLE IF EXISTS `existencia`;

CREATE TABLE `existencia` (
  `idexistencia` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) DEFAULT NULL,
  `stockMinimo` int(11) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codArticulo` varchar(5) DEFAULT NULL,
  `codDeposito` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idexistencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `existencia` */

/*Table structure for table `familia` */

DROP TABLE IF EXISTS `familia`;

CREATE TABLE `familia` (
  `codigo` varchar(5) NOT NULL,
  `familia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `familia` */

/*Table structure for table `inventariomov` */

DROP TABLE IF EXISTS `inventariomov`;

CREATE TABLE `inventariomov` (
  `idInventarioMov` int(11) NOT NULL AUTO_INCREMENT,
  `nroMovimiento` varchar(10) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` varchar(20) DEFAULT NULL,
  `tipo` enum('E','S') DEFAULT NULL,
  `codMotivoMov` varchar(5) DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `codDeposito` varchar(5) DEFAULT NULL,
  `CodSucursal` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idInventarioMov`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `inventariomov` */

/*Table structure for table `marca` */

DROP TABLE IF EXISTS `marca`;

CREATE TABLE `marca` (
  `codigo` varchar(5) NOT NULL,
  `marca` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `marca` */

/*Table structure for table `modulo` */

DROP TABLE IF EXISTS `modulo`;

CREATE TABLE `modulo` (
  `codigo` varchar(5) NOT NULL,
  `modulo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `modulo` */

insert  into `modulo`(`codigo`,`modulo`) values 
('00001','Datos Generales'),
('00002','Acceso'),
('00003','Venta'),
('00004','Compra'),
('00005','Iventario'),
('00006','Tesoreria'),
('00007','Servicio Tecnico'),
('00008','Recursos Humanos'),
('00009','Credito'),
('00010','Banco'),
('00011','Ofertas_Combos');

/*Table structure for table `motivoamonetacion` */

DROP TABLE IF EXISTS `motivoamonetacion`;

CREATE TABLE `motivoamonetacion` (
  `codigo` varchar(5) NOT NULL,
  `motivo` varchar(45) DEFAULT NULL,
  `codConceptoRetri` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `motivoamonetacion` */

/*Table structure for table `motivobaja` */

DROP TABLE IF EXISTS `motivobaja`;

CREATE TABLE `motivobaja` (
  `codigo` varchar(5) NOT NULL,
  `motivo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `motivobaja` */

/*Table structure for table `motivomovinventario` */

DROP TABLE IF EXISTS `motivomovinventario`;

CREATE TABLE `motivomovinventario` (
  `codigo` varchar(5) NOT NULL,
  `motivoMovInventario` varchar(45) DEFAULT NULL,
  `tipo` enum('E','S') DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `motivomovinventario` */

/*Table structure for table `movbancario` */

DROP TABLE IF EXISTS `movbancario`;

CREATE TABLE `movbancario` (
  `idMovBancario` int(11) NOT NULL,
  `nroMovBancario` varchar(5) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `codBanco` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `nroCuenta` varchar(10) DEFAULT NULL,
  `nroComprobante` varchar(10) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL COMMENT 'personal que realizo la operacion',
  `codConcepto` varchar(5) DEFAULT NULL,
  `importe` decimal(10,2) DEFAULT NULL,
  `tipoComp` enum('BOL','CHQ') DEFAULT NULL COMMENT 'tipo de comprobante\nBOL - BOLETA\nCHQ - CHEQUE\n',
  `tipoMov` enum('D','C') DEFAULT NULL COMMENT 'D- Debito(-)\nC- Credito(+)',
  PRIMARY KEY (`idMovBancario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `movbancario` */

/*Table structure for table `movcaja` */

DROP TABLE IF EXISTS `movcaja`;

CREATE TABLE `movcaja` (
  `idMovCaja` int(11) NOT NULL AUTO_INCREMENT,
  `operacion` enum('VNT','CMP','DEVC','DEVV','OTR') DEFAULT NULL COMMENT 'VNT - Venta.\nCMP - Compra.\nCOB - Cobros.\nPAG - Pagos.\nDEVC- Devolucion compra.\nDEVV- Devolucion venta.\nOTR - Otros.',
  `fecha` date DEFAULT NULL,
  `hora` varchar(15) DEFAULT NULL,
  `tipo` enum('I','E') DEFAULT NULL COMMENT 'E - Egreso\nI -Ingreso',
  `codConcepto` varchar(5) DEFAULT NULL,
  `nroCheque` varchar(20) DEFAULT NULL,
  `CodUsuario` varchar(5) DEFAULT NULL,
  `codTipopago` varchar(5) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `detalles` varchar(250) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idMovCaja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `movcaja` */

/*Table structure for table `nomina` */

DROP TABLE IF EXISTS `nomina`;

CREATE TABLE `nomina` (
  `idNomina` int(11) NOT NULL AUTO_INCREMENT,
  `idContrato` int(11) NOT NULL,
  `fechaEmision` date DEFAULT NULL,
  `mes` int(11) DEFAULT NULL,
  `periodo` int(11) DEFAULT NULL COMMENT 'año de la nomina',
  `codPersonal` varchar(5) DEFAULT NULL,
  `codCargo` varchar(5) DEFAULT NULL,
  `devengo` decimal(10,2) DEFAULT NULL COMMENT 'la suma de todos los salarios',
  `deduccion` decimal(10,2) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `cerrado` int(11) DEFAULT '0' COMMENT '1 cerrado ya no se podra utilizar',
  PRIMARY KEY (`idNomina`),
  KEY `fk_Nomina_Contrato1_idx` (`idContrato`),
  CONSTRAINT `fk_Nomina_Contrato1` FOREIGN KEY (`idContrato`) REFERENCES `contrato` (`idContrato`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `nomina` */

/*Table structure for table `oferta` */

DROP TABLE IF EXISTS `oferta`;

CREATE TABLE `oferta` (
  `idOferta` int(11) NOT NULL AUTO_INCREMENT,
  `nroOferta` varchar(5) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fechaDesde` date DEFAULT NULL,
  `fechaHasta` date DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idOferta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oferta` */

/*Table structure for table `ordencompra` */

DROP TABLE IF EXISTS `ordencompra`;

CREATE TABLE `ordencompra` (
  `idOrdenCompra` int(11) NOT NULL AUTO_INCREMENT,
  `nroOrden` varchar(5) DEFAULT NULL,
  `fechaEmision` date DEFAULT NULL,
  `fechaApoba` date DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `codProveedor` varchar(5) DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `gravadas5` decimal(10,2) DEFAULT NULL,
  `gravadas10` decimal(10,2) DEFAULT NULL,
  `iva5` decimal(10,2) DEFAULT NULL,
  `iva10` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `estado` enum('PEN','APR') DEFAULT NULL,
  PRIMARY KEY (`idOrdenCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordencompra` */

/*Table structure for table `ordenpago` */

DROP TABLE IF EXISTS `ordenpago`;

CREATE TABLE `ordenpago` (
  `idOrdenPago` int(11) NOT NULL AUTO_INCREMENT,
  `nroOrden` varchar(5) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `codProveedor` varchar(5) DEFAULT NULL,
  `codCliente` varchar(10) DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `codTipopago` varchar(5) DEFAULT NULL,
  `codBanco` varchar(5) DEFAULT NULL COMMENT 'en caso de que el pago se a cheque',
  `nroCheque` varchar(20) DEFAULT NULL COMMENT 'numero de cheque en caso de que tipo de pago se a cheque',
  `origen` enum('CLI','PRO','PER') DEFAULT NULL COMMENT 'CLI -  clientes\nPRO - proveedor\nPER -  personal',
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `idNomina` int(11) DEFAULT NULL,
  `idCredito` int(11) DEFAULT NULL,
  `estado` enum('PEN','PAG','ANU') DEFAULT 'PEN' COMMENT 'PEN - pendiente\nPAG - pagado\nANU -anulado',
  PRIMARY KEY (`idOrdenPago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordenpago` */

/*Table structure for table `pagocob` */

DROP TABLE IF EXISTS `pagocob`;

CREATE TABLE `pagocob` (
  `idPagoCob` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `nroRecibo` varchar(15) DEFAULT NULL,
  `tipo` enum('CB','PG') DEFAULT NULL COMMENT 'CB - cobro\nPG - pago',
  `codCliente` varchar(10) DEFAULT NULL,
  `codProveedor` varchar(5) DEFAULT NULL,
  `codCobrador` varchar(5) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `codTipopago` varchar(5) DEFAULT NULL,
  `nroCheque` varchar(20) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `origen` enum('CLI','PRO','PER') DEFAULT NULL COMMENT 'origen de pago.\nCLI- Cliente.\nPRO- Proveedor.\nPER. - Personal.',
  `idRendicion` int(11) DEFAULT NULL COMMENT 'referencia a la rendicion de cobrador',
  `idCompra` int(11) DEFAULT NULL COMMENT 'referencia a la compra.',
  `idVenta` int(11) DEFAULT NULL,
  `idCredito` int(11) DEFAULT NULL,
  `idOrdenPago` int(11) DEFAULT NULL,
  `idNomina` int(11) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `estado` enum('PAG','ANU') DEFAULT NULL COMMENT 'PAG - Pagado\nANU - Anulado',
  PRIMARY KEY (`idPagoCob`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pagocob` */

/*Table structure for table `pais` */

DROP TABLE IF EXISTS `pais`;

CREATE TABLE `pais` (
  `Codigo` varchar(5) NOT NULL,
  `Pais` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pais` */

insert  into `pais`(`Codigo`,`Pais`) values 
('00001','PARAGUAY');

/*Table structure for table `parametrorrhh` */

DROP TABLE IF EXISTS `parametrorrhh`;

CREATE TABLE `parametrorrhh` (
  `idParametro` int(11) NOT NULL AUTO_INCREMENT,
  `concepAdelanto` varchar(5) DEFAULT NULL,
  `concepVacacion` varchar(5) DEFAULT NULL,
  `porcAdelMax` decimal(10,2) DEFAULT NULL COMMENT 'porcentaje maximo de adelantos sobre salario',
  `porcComiVen` decimal(10,2) DEFAULT NULL COMMENT 'comision base de venta',
  `porcComiCob` decimal(10,2) DEFAULT NULL COMMENT 'comision base cobranza',
  `salarioBase` decimal(10,2) DEFAULT NULL COMMENT 'salario base vigente.',
  PRIMARY KEY (`idParametro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `parametrorrhh` */

/*Table structure for table `parametroventa` */

DROP TABLE IF EXISTS `parametroventa`;

CREATE TABLE `parametroventa` (
  `idParametroVenta` int(11) NOT NULL AUTO_INCREMENT,
  `concepVenta` varchar(5) DEFAULT NULL,
  `concepCobro` varchar(5) DEFAULT NULL,
  `concepPago` varchar(5) DEFAULT NULL,
  `codClienteDefault` varchar(5) DEFAULT NULL COMMENT 'cliente por defecto en venta',
  `codDepositoDefault` varchar(5) DEFAULT NULL COMMENT 'codDeposot',
  PRIMARY KEY (`idParametroVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `parametroventa` */

/*Table structure for table `personal` */

DROP TABLE IF EXISTS `personal`;

CREATE TABLE `personal` (
  `codigo` varchar(10) NOT NULL,
  `fechaIngreso` date DEFAULT NULL,
  `ciRuc` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fechaNac` date DEFAULT NULL,
  `sexo` enum('M','F') DEFAULT NULL,
  `estadoCivil` enum('C','S','D','V') DEFAULT NULL,
  `codPais` varchar(5) DEFAULT NULL,
  `codDepartamento` varchar(5) DEFAULT NULL,
  `codCiudad` varchar(5) DEFAULT NULL,
  `codBarrio` varchar(5) DEFAULT NULL,
  `casaNro` varchar(10) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `codProfesion` varchar(5) DEFAULT NULL,
  `telefono` varchar(25) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `referencia` varchar(60) DEFAULT NULL,
  `estado` enum('A','B') DEFAULT 'A' COMMENT 'A - Activo\nB- Dado de baja',
  PRIMARY KEY (`codigo`),
  KEY `fk_personal_pais_idx` (`codPais`),
  KEY `fk_personal_dpto_idx` (`codDepartamento`),
  KEY `fk_personal_ciudad_idx` (`codCiudad`),
  KEY `fk_personal_barrio_idx` (`codBarrio`),
  KEY `fk_personal_profesion_idx` (`codProfesion`),
  CONSTRAINT `fk_personal_barrio` FOREIGN KEY (`codBarrio`) REFERENCES `barrio` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_ciudad` FOREIGN KEY (`codCiudad`) REFERENCES `ciudad` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_dpto` FOREIGN KEY (`codDepartamento`) REFERENCES `departamento` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_pais` FOREIGN KEY (`codPais`) REFERENCES `pais` (`Codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_profesion` FOREIGN KEY (`codProfesion`) REFERENCES `profesion` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `personal` */

insert  into `personal`(`codigo`,`fechaIngreso`,`ciRuc`,`nombre`,`apellido`,`fechaNac`,`sexo`,`estadoCivil`,`codPais`,`codDepartamento`,`codCiudad`,`codBarrio`,`casaNro`,`codSucursal`,`codProfesion`,`telefono`,`email`,`referencia`,`estado`) values 
('00001','2020-10-03','12345','JORGE','MORAL','2020-10-03','M','C','00001','00001','00001','00001','3000','00001','00001','0983785318',NULL,NULL,'A');

/*Table structure for table `preaviso` */

DROP TABLE IF EXISTS `preaviso`;

CREATE TABLE `preaviso` (
  `idPreaviso` int(11) NOT NULL AUTO_INCREMENT,
  `fechaEmision` date DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `origen` int(11) DEFAULT NULL COMMENT '1 - empleado 2 - empleado',
  `codPersonal` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `antiguedad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPreaviso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `preaviso` */

/*Table structure for table `premios` */

DROP TABLE IF EXISTS `premios`;

CREATE TABLE `premios` (
  `idpremios` int(11) NOT NULL AUTO_INCREMENT,
  `idSorteo` int(11) NOT NULL,
  `tipoPremio` enum('ART','EFE') DEFAULT NULL,
  `codArticulo` varchar(5) DEFAULT NULL,
  `cantArticulo` int(11) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idpremios`),
  KEY `fk_premios_Sorteo1_idx` (`idSorteo`),
  CONSTRAINT `fk_premios_Sorteo1` FOREIGN KEY (`idSorteo`) REFERENCES `sorteo` (`idSorteo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `premios` */

/*Table structure for table `prestamoariculo` */

DROP TABLE IF EXISTS `prestamoariculo`;

CREATE TABLE `prestamoariculo` (
  `idPrestamoAriculo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `codCliente` varchar(10) DEFAULT NULL,
  `estado` enum('PEN','CAN') DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `tipo` enum('REC','OTR') DEFAULT NULL COMMENT 'REC - RECIBIDO\nOTR - OTORGADO',
  `obs` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idPrestamoAriculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prestamoariculo` */

/*Table structure for table `profesion` */

DROP TABLE IF EXISTS `profesion`;

CREATE TABLE `profesion` (
  `codigo` varchar(5) NOT NULL,
  `Profesion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `profesion` */

insert  into `profesion`(`codigo`,`Profesion`) values 
('00001','INGENIERO');

/*Table structure for table `promesapago` */

DROP TABLE IF EXISTS `promesapago`;

CREATE TABLE `promesapago` (
  `idPromesa` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `codCliente` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `obs` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idPromesa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `promesapago` */

/*Table structure for table `proveedor` */

DROP TABLE IF EXISTS `proveedor`;

CREATE TABLE `proveedor` (
  `codigo` varchar(5) NOT NULL,
  `ciRuc` varchar(10) DEFAULT NULL,
  `proveedor` varchar(45) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `codSucursal` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `proveedor` */

/*Table structure for table `referencia` */

DROP TABLE IF EXISTS `referencia`;

CREATE TABLE `referencia` (
  `codigo` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codCliente` varchar(10) DEFAULT NULL,
  `ci_ruc` varchar(15) DEFAULT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `contacto` varchar(45) DEFAULT NULL,
  `obs` text,
  `tipoRef` enum('P','C') DEFAULT NULL COMMENT 'P: REFERENCIA PERSONAL\nC: REFERENCIA COMERCIAL',
  PRIMARY KEY (`codigo`),
  KEY `fk_referencia_cliente_idx` (`codCliente`),
  CONSTRAINT `fk_referencia_cliente` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `referencia` */

insert  into `referencia`(`codigo`,`codCliente`,`ci_ruc`,`nombre`,`direccion`,`contacto`,`obs`,`tipoRef`) values 
(00001,'00001','12345','Cesar Caballero','Chore','09836645773','Cliente no tiene trabajo\nEs un vago de la calle','P'),
(00002,'00001','545566','Cesar Alvarez','Hugua Poti','098887887','1- cliente vive en casa de su abuela\n2- no tiene empleo\n3- no quiere trabajar\n4- no posee capacidad de pago','P'),
(00003,'00005','878787','JORGE MORAL','CHORE','0983785318','1-CLIENTE NO QUIERE TRABAJAR\n2-CLIENTE TIENE DEUDA EN 2 PARTES\n3-APE HA PEPE ODEBE\n4-VIVE CON SU ABUELA','P'),
(00004,'00005','78687687','CESAR AYALA','CHORE','098487766','NO TIENE','P');

/*Table structure for table `rendrecibo` */

DROP TABLE IF EXISTS `rendrecibo`;

CREATE TABLE `rendrecibo` (
  `idRendRecibo` int(11) NOT NULL AUTO_INCREMENT,
  `nroRendicion` varchar(10) DEFAULT NULL,
  `nroRecibo` varchar(15) DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  `fechaProc` date DEFAULT NULL COMMENT 'fecha de procesado.',
  `codCobrador` varchar(5) DEFAULT NULL,
  `codCliente` varchar(10) DEFAULT NULL,
  `codTipopago` varchar(5) DEFAULT NULL,
  `nroCheque` varchar(20) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `idCredito` int(11) DEFAULT NULL,
  `estado` enum('PEN','PRO','ANU') DEFAULT NULL COMMENT 'PEN -pendiente\nPRO -procesado\nANU -anulado ',
  PRIMARY KEY (`idRendRecibo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rendrecibo` */

/*Table structure for table `retribucion` */

DROP TABLE IF EXISTS `retribucion`;

CREATE TABLE `retribucion` (
  `idRetribucion` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `mes` int(11) DEFAULT NULL,
  `periodo` int(11) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `codConceptoRetri` varchar(5) DEFAULT NULL,
  `fDevengoDesde` date DEFAULT NULL,
  `fDevengoHasta` date DEFAULT NULL,
  `devengaFecha` int(11) DEFAULT NULL,
  `tipoRetri` enum('NOR','ADE','VAC') DEFAULT NULL COMMENT 'NOR - Normal\nADE - Adelanto\nVAC - Vacacion',
  `codEmpesa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `idRef` int(11) DEFAULT NULL COMMENT 'id de referencia segun el tipo de retribucion: adelantos, vacaciones,amonestaciones.',
  `obs` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idRetribucion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `retribucion` */

/*Table structure for table `rol` */

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `codigo` varchar(5) NOT NULL,
  `rol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rol` */

insert  into `rol`(`codigo`,`rol`) values 
('00001','adm'),
('00002','cajero');

/*Table structure for table `servicio` */

DROP TABLE IF EXISTS `servicio`;

CREATE TABLE `servicio` (
  `idServicio` int(11) NOT NULL,
  `nroServicion` varchar(5) DEFAULT NULL,
  `idVenta` int(11) DEFAULT NULL,
  `fechaIngreso` date DEFAULT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `nroServicio` varchar(5) DEFAULT NULL,
  `codTecnico` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `codArticulo` varchar(45) DEFAULT NULL,
  `costo` decimal(10,2) DEFAULT NULL,
  `entegado` int(11) DEFAULT '0' COMMENT '1 - entregado',
  `estadoEntrega` varchar(250) DEFAULT NULL COMMENT 'descripcion de como fue entregado el articulo.',
  `diagnProblema` varchar(250) DEFAULT NULL COMMENT 'descripcion de problema encontrado.',
  PRIMARY KEY (`idServicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `servicio` */

/*Table structure for table `solicitudcredito` */

DROP TABLE IF EXISTS `solicitudcredito`;

CREATE TABLE `solicitudcredito` (
  `idSolicitudCredito` int(11) NOT NULL AUTO_INCREMENT,
  `nroSolicitud` varchar(10) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fechaapro` date DEFAULT NULL COMMENT 'fecha de aprobacion.',
  `codCliente` varchar(10) DEFAULT NULL,
  `codVendedor` varchar(5) DEFAULT NULL COMMENT 'codigo del vendedor solicitante.',
  `codAprovador` varchar(5) DEFAULT NULL COMMENT 'codigo de encargado de venta que aprobo la solicitud.',
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `combo` int(11) DEFAULT NULL COMMENT 'codigo de combo.',
  `cantCuota` int(11) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `tipoSolicCred` enum('N','C') DEFAULT NULL COMMENT 'N- NORMAL\nC- COMBO',
  `nombreCliente` varchar(45) DEFAULT NULL,
  `apellidoCliente` varchar(45) DEFAULT NULL,
  `fechaNac` date DEFAULT NULL COMMENT 'fecha de nacimiento del cliente',
  `sexo` enum('M','F') DEFAULT NULL,
  `telefonoCliente` varchar(45) DEFAULT NULL,
  `emailCliente` varchar(45) DEFAULT NULL,
  `codPais` varchar(5) DEFAULT NULL,
  `codDpartamento` varchar(5) DEFAULT NULL,
  `codciudad` varchar(5) DEFAULT NULL,
  `codBarrio` varchar(5) DEFAULT NULL,
  `codProfesion` varchar(5) DEFAULT NULL,
  `nroCasa` varchar(5) DEFAULT NULL,
  `referencia_per_nomb1` varchar(45) DEFAULT NULL,
  `referencia_per_nomb2` varchar(45) DEFAULT NULL,
  `referencia_per_nomb3` varchar(45) DEFAULT NULL,
  `referencia_per_tel1` varchar(45) DEFAULT NULL,
  `referencia_per_tel2` varchar(45) DEFAULT NULL,
  `referencia_per_tel3` varchar(45) DEFAULT NULL,
  `estado` enum('PEN','APR','ANU') DEFAULT NULL COMMENT 'PEN- pendiente\nAPR -aprobado\nANU - anulado',
  PRIMARY KEY (`idSolicitudCredito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `solicitudcredito` */

/*Table structure for table `sorteo` */

DROP TABLE IF EXISTS `sorteo`;

CREATE TABLE `sorteo` (
  `idSorteo` int(11) NOT NULL,
  `fechaSorteo` date DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `fechaDesde` date DEFAULT NULL,
  `fechaHasta` date DEFAULT NULL,
  `codcliente` varchar(5) DEFAULT NULL,
  `retirado` int(11) DEFAULT NULL COMMENT '1 retirado',
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idSorteo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sorteo` */

/*Table structure for table `sucursal` */

DROP TABLE IF EXISTS `sucursal`;

CREATE TABLE `sucursal` (
  `codigo` varchar(5) NOT NULL,
  `ruc` varchar(45) DEFAULT NULL,
  `sucursal` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_sucursal_empresa_idx` (`codEmpresa`),
  CONSTRAINT `fk_sucursal_empresa` FOREIGN KEY (`codEmpresa`) REFERENCES `empresa` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sucursal` */

insert  into `sucursal`(`codigo`,`ruc`,`sucursal`,`direccion`,`Telefono`,`codEmpresa`) values 
('00001','1405147-8','CENTRAL','CHORE','0983785318','00001');

/*Table structure for table `timbrado` */

DROP TABLE IF EXISTS `timbrado`;

CREATE TABLE `timbrado` (
  `timbrado` varchar(8) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `vto` date DEFAULT NULL,
  `nroCompInicial` varchar(15) DEFAULT NULL,
  `nroCompFinal` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `timbrado` */

/*Table structure for table `tipopago` */

DROP TABLE IF EXISTS `tipopago`;

CREATE TABLE `tipopago` (
  `Codigo` varchar(5) NOT NULL,
  `Tipopago` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipopago` */

/*Table structure for table `transferencia` */

DROP TABLE IF EXISTS `transferencia`;

CREATE TABLE `transferencia` (
  `idTransferencia` int(11) NOT NULL AUTO_INCREMENT,
  `nroTransferecia` varchar(5) DEFAULT NULL,
  `codConcepto` varchar(5) DEFAULT NULL,
  `nroCuenta` varchar(10) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `importe` decimal(10,2) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `obs` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTransferencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `transferencia` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `codigo` varchar(5) NOT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `codRol` varchar(5) DEFAULT NULL COMMENT 'nivel de acceso',
  `Login` varchar(20) DEFAULT NULL,
  `Clave` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_usuario_rol_idx` (`codRol`),
  KEY `fk_usuario_personal_idx` (`codPersonal`),
  CONSTRAINT `fk_usuario_personal` FOREIGN KEY (`codPersonal`) REFERENCES `personal` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`codRol`) REFERENCES `rol` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

insert  into `usuario`(`codigo`,`codPersonal`,`codRol`,`Login`,`Clave`) values 
('00006','00001','00001','admin','d033e22ae348aeb5660fc2140aec35850c4da997');

/*Table structure for table `vacacion` */

DROP TABLE IF EXISTS `vacacion`;

CREATE TABLE `vacacion` (
  `idVacacion` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `fechaAbono` date DEFAULT NULL,
  `periodo` int(11) DEFAULT NULL,
  `codPersonal` varchar(5) DEFAULT NULL,
  `jornales` decimal(10,2) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL COMMENT 'monto pagado por vacacion jornales * cantidad de dias trabajadas',
  `diasVaca` int(11) DEFAULT NULL COMMENT 'dias de vacasiones',
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `usado` int(11) DEFAULT '0' COMMENT '1 usado',
  PRIMARY KEY (`idVacacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vacacion` */

/*Table structure for table `venta` */

DROP TABLE IF EXISTS `venta`;

CREATE TABLE `venta` (
  `idVenta` int(11) NOT NULL AUTO_INCREMENT,
  `nroComprobante` varchar(10) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `tipoVenta` enum('CON','CRE') DEFAULT NULL COMMENT ' CON - contado\nCRE - credito',
  `codCliente` varchar(10) DEFAULT NULL,
  `codVendedor` varchar(5) DEFAULT NULL,
  `codUsuario` varchar(5) DEFAULT NULL,
  `codEmpresa` varchar(5) DEFAULT NULL,
  `codSucursal` varchar(5) DEFAULT NULL,
  `codDeposito` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `venta` */

/*Table structure for table `ventana` */

DROP TABLE IF EXISTS `ventana`;

CREATE TABLE `ventana` (
  `codigo` varchar(5) NOT NULL,
  `ventana` varchar(45) DEFAULT NULL,
  `codModulo` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_ventana_modulo_idx` (`codModulo`),
  CONSTRAINT `fk_ventana_modulo` FOREIGN KEY (`codModulo`) REFERENCES `modulo` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ventana` */

insert  into `ventana`(`codigo`,`ventana`,`codModulo`) values 
('00001','Empresa','00001'),
('00002','Sucursal','00001'),
('00003','Pais','00001'),
('00004','Departamento','00001'),
('00005','Ciudad','00001'),
('00006','Barrio','00001'),
('00007','Profesión','00001');

/*Table structure for table `zona` */

DROP TABLE IF EXISTS `zona`;

CREATE TABLE `zona` (
  `codigo` varchar(5) NOT NULL,
  `zona` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `zona` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
