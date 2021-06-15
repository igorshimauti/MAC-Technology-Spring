CREATE TABLE professor (
	id serial NOT NULL,
	nome varchar(150) NOT NULL,
	cpf varchar(14) NOT NULL,
	data_nascimento DATE NOT NULL,
	email varchar(150) NOT NULL,
	celular varchar(12) NOT NULL,
	cep varchar(8),
	uf varchar(2),
	cidade varchar(50),
	bairro varchar(50),
	logradouro varchar(100),
	numero varchar(20),
	complemento varchar(50),
	CONSTRAINT pk_professor PRIMARY KEY (id)
);