package br.com.mactechnology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.Professor;
import br.com.mactechnology.repository.ProfessorRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Transactional
	public Professor salvar(Professor professor) {
		boolean professorJaCadastrado = professorRepository.findByCpf(professor.getCpf()).stream().anyMatch(professorExistente -> !professorExistente.equals(professor));
		
		if (professorJaCadastrado) {
			throw new BusinessRulesException("Professor com o cpf '" + professor.getCpf() + "' já foi cadastrado anteriormente.");
		}
		
		return professorRepository.save(professor);
	}
	
	@Transactional
	public void excluir(Long professorId) {
		professorRepository.deleteById(professorId);
	}
	
	@Transactional(readOnly = true)
	public Professor buscar(Long professorId) {
		return professorRepository.findById(professorId).orElseThrow(() -> new BusinessRulesException("Professor não encontrado."));
	}
}