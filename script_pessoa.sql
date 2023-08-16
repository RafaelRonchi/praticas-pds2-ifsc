CREATE DATABASE IF NOT EXISTS banco_pessoa;

USE banco_pessoa;

CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `primeiro_nome` VARCHAR(45) NOT NULL,
  `idade` INT NOT NULL,
  PRIMARY KEY (`id`)
);

select * from pessoa;