CREATE TABLE materia (
	id serial NOT NULL,
	curso_id BIGINT NOT NULL,
	professor_id BIGINT NOT NULL,
	nome varchar(50) NOT NULL,
	CONSTRAINT pk_materia PRIMARY KEY (id)
);

ALTER TABLE materia ADD CONSTRAINT fk_materia_curso FOREIGN KEY (curso_id) REFERENCES curso(id);
ALTER TABLE materia ADD CONSTRAINT fk_materia_professor FOREIGN KEY (professor_id) REFERENCES professor(id);