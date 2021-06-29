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
import br.com.mactechnology.controller.dto.input.InputCurso;
import br.com.mactechnology.controller.mapper.AlunoMapper;
import br.com.mactechnology.controller.mapper.CursoMapper;
import br.com.mactechnology.model.Curso;
import br.com.mactechnology.repository.CursoRepository;
import br.com.mactechnology.service.CursoService;

@CrossOrigin(origins = "https://mac-courses.netlify.app", maxAge = 7200)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 7200)
@RestController
@RequestMapping(value = "/curso")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private CursoMapper cursoMapper;
	
	@Autowired
	private AlunoMapper alunoMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoCurso>> listar() {
		return ResponseEntity.ok(cursoMapper.toCollectionDto(cursoRepository.findAll()));
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoCurso> buscar(@PathVariable Long id) {
		return cursoRepository.findById(id)
				.map(curso -> ResponseEntity.ok(cursoMapper.toDto(curso)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/{id}/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAluno>> listarAlunosMatriculados(@PathVariable Long id) {
		return ResponseEntity.ok(alunoMapper.toCollectionDto(cursoService.findByCursosId(id)));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoCurso> incluir(@Valid @RequestBody InputCurso input) {
		Curso curso = cursoMapper.toEntity(input);
		return ResponseEntity.ok(cursoMapper.toDto(cursoService.salvar(curso)));
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoCurso> atualizar(@PathVariable Long id, @Valid @RequestBody InputCurso input) {
		if (!cursoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		Curso curso = cursoMapper.toEntity(input);
		curso.setId(id);
		return ResponseEntity.ok(cursoMapper.toDto(cursoService.salvar(curso)));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		if (!cursoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cursoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}