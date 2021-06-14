CREATE TABLE tipo_tarefa (
	id BIGINT auto_increment NOT NULL,
	descricao varchar(200) NOT NULL,
	CONSTRAINT pk_tipo_tarefa PRIMARY KEY (id)
);