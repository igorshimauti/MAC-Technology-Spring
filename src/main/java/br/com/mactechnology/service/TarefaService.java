package br.com.mactechnology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.Materia;
import br.com.mactechnology.model.Tarefa;
import br.com.mactechnology.repository.TarefaRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	MateriaService materiaService;
	
	@Transactional
	public Tarefa salvar(Long materiaId, Tarefa tarefa) {
		Materia materia = materiaService.buscar(materiaId);
		tarefa.setMateria(materia);
		return tarefaRepository.save(tarefa);
	}
	
	@Transactional
	public void excluir(Long tarefaId) {
		tarefaRepository.deleteById(tarefaId);
	}
	
	@Transactional(readOnly = true)
	public Tarefa buscar(Long tarefaId) {
		return tarefaRepository.findById(tarefaId).orElseThrow(() -> new BusinessRulesException("Tarefa não encontrada."));
	}
}