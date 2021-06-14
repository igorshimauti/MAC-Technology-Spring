package br.com.mactechnology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.Curso;
import br.com.mactechnology.model.Materia;
import br.com.mactechnology.repository.MateriaRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class MateriaService {

	@Autowired
	private MateriaRepository materiaRepository;
	
	@Autowired
	private CursoService cursoService; 
	
	@Transactional
	public Materia salvar(Long cursoId, Materia materia) {
		boolean materiaJaCadastrada = materiaRepository.findByNome(materia.getNome()).stream().anyMatch(materiaExistente -> !materiaExistente.equals(materia));

		if (materiaJaCadastrada) {
			throw new BusinessRulesException("Matéria com o nome '" + materia.getNome() + "' já foi cadastrada anteriormente.");
		}
		
		Curso curso = cursoService.buscar(cursoId);
		materia.setCurso(curso);
		return materiaRepository.save(materia);
	}

	@Transactional
	public void Excluir(Long id) {
		materiaRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Materia buscar(Long materiaId) {
		return materiaRepository.findById(materiaId).orElseThrow(() -> new BusinessRulesException("Materia não encontrada."));
	}
	
	public boolean materiaNaoLocalizada(Long materiaId) {
		return !materiaRepository.existsById(materiaId);
	}
}