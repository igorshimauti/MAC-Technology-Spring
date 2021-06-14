package br.com.mactechnology.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mactechnology.model.Tarefa;
import br.com.mactechnology.model.TarefaAluno;

@Repository
public interface TarefaAlunoRepository extends JpaRepository<TarefaAluno, Long>{

	List<TarefaAluno> findByTarefa(Tarefa tarefa);
}