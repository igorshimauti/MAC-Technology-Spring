CREATE TABLE tarefa_aluno (
	id BIGINT auto_increment NOT NULL,
	tarefa_id BIGINT NOT NULL,
	aluno_id BIGINT NOT NULL,
	data_entrega DATE NOT NULL,
	nota FLOAT NOT NULL,
	observacao varchar(200),
	CONSTRAINT pk_tarefa_aluno PRIMARY KEY (id)
);

ALTER TABLE tarefa_aluno ADD CONSTRAINT fk_tarefa_aluno_tarefa FOREIGN KEY (tarefa_id) REFERENCES tarefa(id);
ALTER TABLE tarefa_aluno ADD CONSTRAINT fk_tarefa_aluno_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id);
ALTER TABLE tarefa_aluno ADD CONSTRAINT uk_tarefa_aluno UNIQUE KEY (tarefa_id, aluno_id);