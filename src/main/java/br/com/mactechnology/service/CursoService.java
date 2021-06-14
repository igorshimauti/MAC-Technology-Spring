package br.com.mactechnology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.Curso;
import br.com.mactechnology.repository.CursoRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Transactional
	public Curso salvar(Curso curso) {
		boolean cursoJaCadastrado = cursoRepository.findByNome(curso.getNome()).stream().anyMatch(cursoExistente -> !cursoExistente.equals(curso));
		
		if (cursoJaCadastrado) {
			throw new BusinessRulesException("Curso com o nome '" + curso.getNome() + "' já foi cadastrado anteriormente.");
		}
		
		return cursoRepository.save(curso);
	}
	
	@Transactional
	public void excluir(Long cursoId) {
		cursoRepository.deleteById(cursoId);
	}
	
	@Transactional(readOnly = true)
	public Curso buscar(Long cursoId) {
		return cursoRepository.findById(cursoId).orElseThrow(() -> new BusinessRulesException("Curso não encontrado."));
	}
}