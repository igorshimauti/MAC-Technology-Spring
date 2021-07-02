package br.com.mactechnology.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mactechnology.controller.dto.DtoTarefaAluno;
import br.com.mactechnology.controller.dto.input.InputTarefaAluno;
import br.com.mactechnology.controller.mapper.TarefaAlunoMapper;
import br.com.mactechnology.model.TarefaAluno;
import br.com.mactechnology.service.TarefaAlunoService;

@CrossOrigin(origins = "https://mac-cursos.netlify.app", maxAge = 7200)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 7200)
@RestController
@RequestMapping(value = "/curso/{cursoId}/materia/{materiaId}/tarefa/{tarefaId}/entrega")
public class TarefaAlunoController {

	@Autowired
	private TarefaAlunoService tarefaAlunoService;
	
	@Autowired
	private TarefaAlunoMapper tarefaAlunoMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTarefaAluno>> listar(@PathVariable Long tarefaId) {
		return ResponseEntity.ok(tarefaAlunoMapper.toCollectionDto(tarefaAlunoService.listar(tarefaId)));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoTarefaAluno> entregar(@PathVariable Long tarefaId, @Valid @RequestBody InputTarefaAluno input) {
		TarefaAluno tarefaAluno = tarefaAlunoService.salvar(tarefaId, input.getAlunoId(), tarefaAlunoMapper.toEntity(input));
		return ResponseEntity.ok(tarefaAlunoMapper.toDto(tarefaAluno));
	}
}