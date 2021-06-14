CREATE TABLE aluno (
	id BIGINT auto_increment NOT NULL,
	nome varchar(150) NOT NULL,
	cpf varchar(14) NOT NULL,
	data_nascimento DATE NOT NULL,
	email varchar(150),
	celular varchar(12),
	cep varchar(8),
	uf varchar(2),
	cidade varchar(50),
	bairro varchar(50),
	logradouro varchar(100),
	numero varchar(20),
	complemento varchar(50),
	CONSTRAINT pk_professor PRIMARY KEY (id)
);