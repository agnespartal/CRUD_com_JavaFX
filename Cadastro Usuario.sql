CREATE DATABASE cadastro;

use cadastro;

CREATE TABLE Usuarios (
cpf int PRIMARY KEY,
nome VARCHAR(100) not NULL,
dataNasc date,
telefone char(15),
email char(20)
);