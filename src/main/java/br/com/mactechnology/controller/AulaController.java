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
import br.com.mactechnology.controller.dto.DtoAula;
import br.com.mactechnology.controller.dto.input.InputAula;
import br.com.mactechnology.controller.mapper.AlunoMapper;
import br.com.mactechnology.controller.mapper.AulaMapper;
import br.com.mactechnology.model.Aula;
import br.com.mactechnology.repository.AulaRepository;
import br.com.mactechnology.service.AulaService;

@CrossOrigin(origins = "https://mac-courses.netlify.app", maxAge = 7200)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 7200)
@RestController
@RequestMapping(value = "/curso/{cursoId}/materia/{materiaId}/aula")
public class AulaController {

	@Autowired
	private AulaService aulaService;
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private AulaMapper aulaMapper;
	
	@Autowired
	private AlunoMapper alunoMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAula>> listar(@PathVariable Long materiaId) {
		return ResponseEntity.ok(aulaMapper.toCollectionDto(aulaService.listar(materiaId)));
	}
	
	@GetMapping(value = "/{aulaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAula> buscar(@PathVariable Long aulaId) {
		return aulaRepository.findById(aulaId)
				.map(aula -> ResponseEntity.ok(aulaMapper.toDto(aula)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/{aulaId}/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAluno>> listarAlunosPresentes(@PathVariable Long aulaId) {
		return ResponseEntity.ok(alunoMapper.toCollectionDto(aulaService.findByAulasId(aulaId)));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoAula> incluir(@PathVariable Long materiaId, @Valid @RequestBody InputAula input) {
		Aula aula = aulaService.salvar(materiaId, input.getAlunos(), aulaMapper.toEntity(input));
		return ResponseEntity.ok(aulaMapper.toDto(aula));
	}
	
	@PutMapping(value = "/{aulaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAula> atualizar(@PathVariable Long materiaId, @PathVariable Long aulaId, @Valid @RequestBody InputAula input) {
		if (!aulaRepository.existsById(aulaId)) {
			return ResponseEntity.notFound().build();
		}
		
		Aula aula = aulaService.buscar(aulaId);
		aula.setId(aulaId);
		return ResponseEntity.ok(aulaMapper.toDto(aulaService.salvar(materiaId, input.getAlunos(), aula)));
	}
	
	@DeleteMapping(value = "/{aulaId}")
	public ResponseEntity<Void> excluir(@PathVariable Long aulaId) {
		if (!aulaRepository.existsById(aulaId)) {
			return ResponseEntity.notFound().build();
		}
		
		aulaService.excluir(aulaId);
		return ResponseEntity.noContent().build();
	}
}