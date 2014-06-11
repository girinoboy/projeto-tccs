DROP DATABASE systorcedor;

CREATE DATABASE systorcedor;

USE systorcedor;

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


CREATE  TABLE `systorcedor`.`uf` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `sigla` VARCHAR(45) NULL ,

  PRIMARY KEY (`id`) );
  
  
  
insert into systorcedor.uf (sigla) values('AC');
insert into systorcedor.uf (sigla) values('AL');
insert into systorcedor.uf (sigla) values('AP');
insert into systorcedor.uf (sigla) values('AM');
insert into systorcedor.uf (sigla) values('BA');
insert into systorcedor.uf (sigla) values('CE');
insert into systorcedor.uf (sigla) values('DF');
insert into systorcedor.uf (sigla) values('ES');
insert into systorcedor.uf (sigla) values('GO');
insert into systorcedor.uf (sigla) values('MA');
insert into systorcedor.uf (sigla) values('MT');
insert into systorcedor.uf (sigla) values('MS');
insert into systorcedor.uf (sigla) values('MG');
insert into systorcedor.uf (sigla) values('PA');
insert into systorcedor.uf (sigla) values('PB');
insert into systorcedor.uf (sigla) values('PR');
insert into systorcedor.uf (sigla) values('PE');
insert into systorcedor.uf (sigla) values('PI');
insert into systorcedor.uf (sigla) values('RJ');
insert into systorcedor.uf (sigla) values('RN');
insert into systorcedor.uf (sigla) values('RS');
insert into systorcedor.uf (sigla) values('RO');
insert into systorcedor.uf (sigla) values('RR');
insert into systorcedor.uf (sigla) values('SC');
insert into systorcedor.uf (sigla) values('SP');
insert into systorcedor.uf (sigla) values('SE');
insert into systorcedor.uf (sigla) values('TO');
