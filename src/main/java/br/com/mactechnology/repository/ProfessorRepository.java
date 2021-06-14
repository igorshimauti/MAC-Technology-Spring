package br.com.mactechnology.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mactechnology.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	List<Professor> findByNome(String nome);
	List<Professor> findByNomeContaining(String nome);
	List<Professor> findByCpf(String cpf);
}