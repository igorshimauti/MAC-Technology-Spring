package br.com.mactechnology.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mactechnology.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

	List<Aula> findByTema(String tema);
	List<Aula> findByMateriaId(Long materiaId);
}