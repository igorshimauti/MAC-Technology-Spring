package br.com.mactechnology.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mactechnology.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	List<Aluno> findByNome(String nome);
	List<Aluno> findByNomeContaining(String nome);
	List<Aluno> findByCursosId(Long cursoId);
	Optional<Aluno> findByCpf(String cpf);
}