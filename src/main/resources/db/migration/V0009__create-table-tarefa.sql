CREATE TABLE tarefa (
	id serial NOT NULL,
	tipo_tarefa_id BIGINT NOT NULL,
	materia_id BIGINT NOT NULL,
	descricao varchar(200) NOT NULL,
	data DATE NOT NULL,
	data_entrega DATE NOT NULL,
	CONSTRAINT pk_tarefa PRIMARY KEY (id)
);

ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_tipo_tarefa FOREIGN KEY (tipo_tarefa_id) REFERENCES tipo_tarefa(id);
ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_materia FOREIGN KEY (materia_id) REFERENCES materia(id);