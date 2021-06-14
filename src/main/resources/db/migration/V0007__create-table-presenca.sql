CREATE TABLE presenca (
	id BIGINT auto_increment NOT NULL,
	aula_id BIGINT NOT NULL,
	aluno_id BIGINT NOT NULL,
	CONSTRAINT pk_presenca PRIMARY KEY (id)
);

ALTER TABLE presenca ADD CONSTRAINT fk_aula_presenca FOREIGN KEY (aula_id) REFERENCES aula(id);
ALTER TABLE presenca ADD CONSTRAINT fk_aluno_presenca FOREIGN KEY (aluno_id) REFERENCES aluno(id);