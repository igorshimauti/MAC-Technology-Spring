package br.com.mactechnology.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mactechnology.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

	List<Curso> findByNome(String nome);
	List<Curso> findByNomeContaining(String nome);
	List<Curso> findByAlunosId(Long id);
}