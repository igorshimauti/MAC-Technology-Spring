package br.com.mactechnology.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.Aluno;
import br.com.mactechnology.model.Tarefa;
import br.com.mactechnology.model.TarefaAluno;
import br.com.mactechnology.repository.TarefaAlunoRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class TarefaAlunoService {

	@Autowired
	private TarefaAlunoRepository tarefaAlunoRepository;
	
	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Transactional
	public TarefaAluno salvar(Long tarefaId, Long alunoId, TarefaAluno tarefaAluno) {
		Tarefa tarefa = tarefaService.buscar(tarefaId);
		Aluno aluno = alunoService.buscar(alunoId);
		
		tarefaAluno.setTarefa(tarefa);
		tarefaAluno.setAluno(aluno);
		
		return tarefaAlunoRepository.save(tarefaAluno);
	}
	
	@Transactional
	public void excluir(Long tarefaAlunoId) {
		tarefaAlunoRepository.deleteById(tarefaAlunoId);
	}
	
	@Transactional(readOnly = true)
	public TarefaAluno buscar(Long tarefaAlunoId) {
		return tarefaAlunoRepository.findById(tarefaAlunoId).orElseThrow(() -> new BusinessRulesException("Tarefa do aluno não encontrada."));
	}
	
	@Transactional(readOnly = true)
	public List<TarefaAluno> listar(Long tarefaId) {
		Tarefa tarefa = tarefaService.buscar(tarefaId);
		return tarefaAlunoRepository.findByTarefa(tarefa);
	}
}