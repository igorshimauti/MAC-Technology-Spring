package br.com.mactechnology.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mactechnology.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

	List<Materia> findByNome(String nome);
	List<Materia> findByNomeContaining(String nome);
}