CREATE TABLE matricula (
  curso_id BIGINT NOT NULL,
  aluno_id BIGINT NOT NULL,
  PRIMARY KEY (curso_id, aluno_id)
);

ALTER TABLE matricula ADD CONSTRAINT fk_matricula_curso FOREIGN KEY (curso_id) REFERENCES curso(id);
ALTER TABLE matricula ADD CONSTRAINT fk_matricula_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id);