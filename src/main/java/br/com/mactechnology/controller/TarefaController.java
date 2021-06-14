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

import br.com.mactechnology.controller.dto.DtoTarefa;
import br.com.mactechnology.controller.dto.input.InputTarefa;
import br.com.mactechnology.controller.mapper.TarefaMapper;
import br.com.mactechnology.model.Materia;
import br.com.mactechnology.model.Tarefa;
import br.com.mactechnology.repository.TarefaRepository;
import br.com.mactechnology.service.MateriaService;
import br.com.mactechnology.service.TarefaService;

@CrossOrigin
@RestController
@RequestMapping(value = "/curso/{cursoId}/materia/{materiaId}/tarefa")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private MateriaService materiaService;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TarefaMapper tarefaMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTarefa>> listar(@PathVariable Long materiaId) {
		Materia materia = materiaService.buscar(materiaId);
		return ResponseEntity.ok(tarefaMapper.toCollectionDto(materia.getTarefas()));
	}
	
	@GetMapping(value = "/{tarefaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTarefa> buscar(@PathVariable Long tarefaId) {
		return tarefaRepository.findById(tarefaId)
				.map(tarefa -> ResponseEntity.ok(tarefaMapper.toDto(tarefa)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoTarefa> incluir(@PathVariable Long materiaId, @Valid @RequestBody InputTarefa input) {
		Tarefa tarefa = tarefaService.salvar(materiaId, tarefaMapper.toEntity(input));
		return ResponseEntity.ok(tarefaMapper.toDto(tarefa));
	}
	
	@PutMapping(value = "/{tarefaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTarefa> atualizar(@PathVariable Long materiaId, @PathVariable Long tarefaId, @Valid @RequestBody InputTarefa input) {
		if (!tarefaRepository.existsById(tarefaId)) {
			return ResponseEntity.notFound().build();
		}
		
		Tarefa tarefa = tarefaMapper.toEntity(input);
		tarefa.setId(tarefaId);
		return ResponseEntity.ok(tarefaMapper.toDto(tarefaService.salvar(materiaId, tarefa)));
	}
	
	@DeleteMapping(value = "/{tarefaId}")
	public ResponseEntity<Void> excluir(@PathVariable Long tarefaId) {
		if (!tarefaRepository.existsById(tarefaId)) {
			return ResponseEntity.notFound().build();
		}
		
		tarefaRepository.deleteById(tarefaId);
		return ResponseEntity.noContent().build();
	}
}