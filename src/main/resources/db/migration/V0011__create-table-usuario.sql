CREATE TABLE usuario (
	id serial NOT NULL,
	nome varchar(150) NOT NULL,
	cpf varchar(14) NOT NULL,
	email varchar(200) NOT NULL,
	senha varchar(100) NOT NULL,
	autorizado boolean NOT NULL,
	CONSTRAINT pk_usuario PRIMARY KEY (id)
);