package br.com.mactechnology.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mactechnology.controller.dto.DtoAluno;
import br.com.mactechnology.controller.dto.DtoCurso;
import br.com.mactechnology.controller.dto.input.InputAluno;
import br.com.mactechnology.controller.mapper.AlunoMapper;
import br.com.mactechnology.controller.mapper.CursoMapper;
import br.com.mactechnology.model.Aluno;
import br.com.mactechnology.repository.AlunoRepository;
import br.com.mactechnology.service.AlunoService;

@CrossOrigin
@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private AlunoMapper alunoMapper;
	
	@Autowired
	private CursoMapper cursoMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAluno>> listar() {
		return ResponseEntity.ok(alunoMapper.toCollectionDto(alunoRepository.findAll()));
	}
	
	@GetMapping(value = "/{alunoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAluno> buscar(@PathVariable Long alunoId) {
		return alunoRepository.findById(alunoId)
				.map(aluno -> ResponseEntity.ok(alunoMapper.toDto(aluno)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/{alunoId}/matricula", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoCurso>> listarMatriculas(@PathVariable Long alunoId) {
		return ResponseEntity.ok(cursoMapper.toCollectionDto(alunoService.listarMatriculas(alunoId)));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoAluno> incluir(@Valid @RequestBody InputAluno input) {
		Aluno aluno = alunoService.salvar(input.getCursos(), alunoMapper.toEntity(input));
		return ResponseEntity.ok(alunoMapper.toDto(aluno));
	}
	
	@PutMapping(value = "/{alunoId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAluno> atualizar(@PathVariable Long alunoId, @Valid @RequestBody InputAluno input) {
		if (!alunoRepository.existsById(alunoId)) {
			return ResponseEntity.notFound().build();
		}
		
		Aluno aluno = alunoMapper.toEntity(input);
		aluno.setId(alunoId);
		return ResponseEntity.ok(alunoMapper.toDto(alunoService.salvar(input.getCursos(), aluno)));
	}
	
	@DeleteMapping(value = "/{alunoId}")
	public ResponseEntity<DtoAluno> excluir(@PathVariable Long alunoId) {
		if (!alunoRepository.existsById(alunoId)) {
			return ResponseEntity.notFound().build();
		}
		
		alunoService.excluir(alunoId);
		return ResponseEntity.noContent().build();
	}
}