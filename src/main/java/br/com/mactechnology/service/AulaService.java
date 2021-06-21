package br.com.mactechnology.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.Aluno;
import br.com.mactechnology.model.Aula;
import br.com.mactechnology.model.Materia;
import br.com.mactechnology.repository.AulaRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class AulaService {

	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private MateriaService materiaService;
	
	@Autowired
	private AlunoService alunoService;
		
	@Transactional
	public Aula salvar(Long materiaId, List<Long> alunos, Aula aula) {
		boolean aulaJaCadastrada = aulaRepository.findByTema(aula.getTema()).stream().anyMatch(aulaExistente -> !aulaExistente.equals(aula));
		
		if (aulaJaCadastrada) {
			throw new BusinessRulesException("Aula com o tema '" + aula.getTema() + "' já foi cadastrada anteriormente.");
		}
		
		for (Long alunoId : alunos) {
			Aluno aluno = alunoService.buscar(alunoId);
			aula.getAlunos().add(aluno);
		}
		
		Materia materia = materiaService.buscar(materiaId);
		aula.setMateria(materia);
		return aulaRepository.save(aula);
	}
	
	@Transactional
	public void excluir(Long aulaId) {
		aulaRepository.deleteById(aulaId);
	}
	
	@Transactional(readOnly = true)
	public Aula buscar(Long aulaId) {
		return aulaRepository.findById(aulaId).orElseThrow(() -> new BusinessRulesException("Aula não encontrada."));
	}
	
	@Transactional(readOnly = true)
	public List<Aula> listar(Long materiaId) {
		return aulaRepository.findByMateriaId(materiaId);
	}
	
	@Transactional(readOnly = true)
	public List<Aluno> findByAulasId(Long aulaId) {
		return alunoService.findByAulasId(aulaId);
	}
}