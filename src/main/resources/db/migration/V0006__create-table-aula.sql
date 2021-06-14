CREATE TABLE aula (
	id BIGINT auto_increment NOT NULL,
	materia_id BIGINT NOT NULL,
	tema varchar(100) NOT NULL,
	data DATE NOT NULL,
	CONSTRAINT pk_aula PRIMARY KEY (id)
);

ALTER TABLE aula ADD CONSTRAINT fk_aula_materia FOREIGN KEY (materia_id) REFERENCES materia(id);