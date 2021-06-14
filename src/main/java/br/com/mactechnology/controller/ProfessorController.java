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

import br.com.mactechnology.controller.dto.DtoProfessor;
import br.com.mactechnology.controller.dto.input.InputProfessor;
import br.com.mactechnology.controller.mapper.ProfessorMapper;
import br.com.mactechnology.model.Professor;
import br.com.mactechnology.repository.ProfessorRepository;
import br.com.mactechnology.service.ProfessorService;

@CrossOrigin
@RestController
@RequestMapping(value = "/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private ProfessorMapper professorMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoProfessor>> listar() {
		return ResponseEntity.ok(professorMapper.toCollectionDto(professorRepository.findAll()));
	}
	
	@GetMapping(value = "/{professorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoProfessor> buscar(@PathVariable Long professorId) {
		return professorRepository.findById(professorId)
				.map(professor -> ResponseEntity.ok(professorMapper.toDto(professor)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoProfessor> incluir(@Valid @RequestBody InputProfessor input) {
		Professor professor = professorService.salvar(professorMapper.toEntity(input));
		return ResponseEntity.ok(professorMapper.toDto(professor));
	}
	
	@PutMapping(value = "/{professorId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoProfessor> atualizar(@PathVariable Long professorId, @Valid @RequestBody InputProfessor input) {
		if (!professorRepository.existsById(professorId)) {
			return ResponseEntity.notFound().build();
		}
		
		Professor professor = professorMapper.toEntity(input);
		professor.setId(professorId);
		return ResponseEntity.ok(professorMapper.toDto(professorService.salvar(professor)));
	}
	
	@DeleteMapping(value = "/{professorId}")
	public ResponseEntity<Void> excluir(@PathVariable Long professorId) {
		if (!professorRepository.existsById(professorId)) {
			return ResponseEntity.notFound().build();
		}
		
		professorService.excluir(professorId);
		return ResponseEntity.noContent().build();
	}
}