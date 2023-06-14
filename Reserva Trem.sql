CREATE DATABASE Trem;

use Trem;

CREATE TABLE reservaTrem (
cpf int PRIMARY KEY,
partida char(100),
destino char(100),
dataPartida date,
horaPartida char(6),
qtdPassageiro int
);	