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

import br.com.mactechnology.controller.dto.DtoMateria;
import br.com.mactechnology.controller.dto.input.InputMateria;
import br.com.mactechnology.controller.mapper.MateriaMapper;
import br.com.mactechnology.model.Curso;
import br.com.mactechnology.model.Materia;
import br.com.mactechnology.model.Professor;
import br.com.mactechnology.repository.MateriaRepository;
import br.com.mactechnology.service.CursoService;
import br.com.mactechnology.service.MateriaService;
import br.com.mactechnology.service.ProfessorService;

@CrossOrigin(origins = "https://mac-courses.netlify.app", maxAge = 7200)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 7200)
@RestController
@RequestMapping(value = "/curso/{cursoId}/materia")
public class MateriaController {

	@Autowired
	private MateriaService materiaService;
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Autowired
	private CursoService cursoService;

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private MateriaMapper materiaMapper;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoMateria>> listar(@PathVariable Long cursoId) {
		Curso curso = cursoService.buscar(cursoId);
		return ResponseEntity.ok(materiaMapper.toCollectionDto(curso.getMaterias()));
	}
	
	@GetMapping(value = "/{materiaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoMateria> buscar(@PathVariable Long materiaId) {
		return materiaRepository.findById(materiaId)
				.map(materia -> ResponseEntity.ok(materiaMapper.toDto(materia)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoMateria> incluir(@PathVariable Long cursoId, @Valid @RequestBody InputMateria input) {
		Materia materia = materiaMapper.toEntity(input);
		Professor professor = professorService.buscar(input.getProfessorId());
		
		materia.setId(null);
		materia.setProfessor(professor);
		
		return ResponseEntity.ok(materiaMapper.toDto(materiaService.salvar(cursoId, materia)));
	}
	
	@PutMapping(value = "/{materiaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoMateria> atualizar(@PathVariable Long cursoId, @PathVariable Long materiaId, @Valid @RequestBody InputMateria input) {
		if (materiaService.materiaNaoLocalizada(materiaId)) {
			return ResponseEntity.notFound().build();
		}
		
		Materia materia = materiaMapper.toEntity(input);
		materia.setId(materiaId);
		return ResponseEntity.ok(materiaMapper.toDto(materiaService.salvar(cursoId, materia)));
	}
	
	@DeleteMapping(value = "/{materiaId}")
	public ResponseEntity<Void> excluir(@PathVariable Long materiaId) {
		if (materiaService.materiaNaoLocalizada(materiaId)) {
			return ResponseEntity.notFound().build();
		}
		
		materiaService.Excluir(materiaId);
		return ResponseEntity.noContent().build();
	}
}