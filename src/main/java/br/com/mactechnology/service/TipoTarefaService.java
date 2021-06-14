package br.com.mactechnology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.TipoTarefa;
import br.com.mactechnology.repository.TipoTarefaRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class TipoTarefaService {

	@Autowired
	private TipoTarefaRepository tipoTarefaRepository;
	
	@Transactional
	public TipoTarefa salvar(TipoTarefa tipoTarefa) {
		return tipoTarefaRepository.save(tipoTarefa);
	}
	
	@Transactional
	public void excluir(Long tipoTarefaId) {
		tipoTarefaRepository.deleteById(tipoTarefaId);
	}
	
	@Transactional(readOnly = true)
	public TipoTarefa buscar(Long tipoTarefaId) {
		return tipoTarefaRepository.findById(tipoTarefaId).orElseThrow(() -> new BusinessRulesException("Tipo de tarefa não encontrada."));
	}
}