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
  PRIMARY KEY (id),
  KEY fk_torcedor_clube (idClube),
  CONSTRAINT fk_torcedor_clube FOREIGN KEY (idClube) REFERENCES clube (id)
);
