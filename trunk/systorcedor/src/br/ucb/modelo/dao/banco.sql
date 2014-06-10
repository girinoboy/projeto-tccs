DROP DATABASE sysTorcedor;

CREATE DATABASE sysTorcedor;

USE sysTorcedor;

CREATE TABLE clube (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  nomeTime varchar(50) NOT NULL,
  dtCriacao int(4) NOT NULL,
  uf char(2) NOT NULL,
  descricao varchar(500) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE torcedor (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  estCivil varchar(12) NOT NULL,
  email varchar(40) DEFAULT NULL,
  dtaNasc datetime NOT NULL,
  uf char(2) NOT NULL,
  idClube int(10) unsigned NOT NULL,
  idCamiseta int(10) unsigned NOT NULL,
  PRIMARY KEY (id),
  KEY fk_torcedor_clube (idClube),
  CONSTRAINT fk_torcedor_clube FOREIGN KEY (idClube) REFERENCES clube (id)
);
CREATE  TABLE `systorcedor`.`camiseta` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `nomeCamiseta` VARCHAR(45) NULL ,

  `dtCriacao` INT NULL ,

  `nomeTime` VARCHAR(45) NULL ,

  `descricao` VARCHAR(45) NULL ,

  PRIMARY KEY (`id`) );
  
  ALTER TABLE `systorcedor`.`torcedor` ADD COLUMN `idCamiseta` INT NULL  AFTER `idClube` , 

  ADD CONSTRAINT `fk_camiseta`

  FOREIGN KEY (`idCamiseta` )

  REFERENCES `systorcedor`.`camiseta` (`id` )

  ON DELETE NO ACTION

  ON UPDATE NO ACTION

, ADD INDEX `fk_camiseta_idx` (`idCamiseta` ASC) ;