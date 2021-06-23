package br.com.mactechnology.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.Aluno;
import br.com.mactechnology.model.Curso;
import br.com.mactechnology.repository.AlunoRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CursoService cursoService;
	
	@Transactional
	public Aluno salvar(List<Long> cursos, Aluno aluno) {
		Aluno alunoJaCadastrado = alunoRepository.findByCpf(aluno.getCpf()).orElse(aluno);
		
		if (alunoJaCadastrado.getId() != aluno.getId()) {
			throw new BusinessRulesException("Aluno com o CPF '" + aluno.getCpf() + "' já foi cadastrado anteriormente.");
		}
		
		for (Long cursoId : cursos) {
			Curso curso = cursoService.buscar(cursoId);
			aluno.getCursos().add(curso);
		} 
		
		return alunoRepository.save(aluno);
	}
	
	@Transactional
	public void excluir(Long alunoId) {
		alunoRepository.deleteById(alunoId);
	}
	
	@Transactional(readOnly = true)
	public Aluno buscar(Long alunoId) {
		return alunoRepository.findById(alunoId).orElseThrow(() -> new BusinessRulesException("Aluno não encontrado."));
	}
	
	@Transactional(readOnly = true)
	public Aluno buscarCpf(String cpf) {
		return alunoRepository.findByCpf(cpf).orElseThrow(() -> new BusinessRulesException("Aluno não encontrado."));
	}
	
	@Transactional(readOnly = true)
	public List<Curso> listarMatriculas(Long alunoId) {
		return cursoService.findByAlunosId(alunoId);
	}
	
	@Transactional(readOnly = true)
	public List<Aluno> findByCursosId(Long cursoId) {
		return alunoRepository.findByCursosId(cursoId);
	};
	
	@Transactional(readOnly = true)
	public List<Aluno> findByAulasId(Long aulaId) {
		return alunoRepository.findByAulasId(aulaId);
	};
}