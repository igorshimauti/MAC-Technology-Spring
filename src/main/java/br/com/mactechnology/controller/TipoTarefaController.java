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

import br.com.mactechnology.controller.dto.DtoTipoTarefa;
import br.com.mactechnology.controller.dto.input.InputTipoTarefa;
import br.com.mactechnology.controller.mapper.TipoTarefaMapper;
import br.com.mactechnology.model.TipoTarefa;
import br.com.mactechnology.repository.TipoTarefaRepository;
import br.com.mactechnology.service.TipoTarefaService;

@CrossOrigin
@RestController
@RequestMapping(value = "/tipo_tarefa")
public class TipoTarefaController {

	@Autowired
	private TipoTarefaService tipoTarefaService;
	
	@Autowired
	private TipoTarefaRepository tipoTarefaRepository;
	
	@Autowired
	private TipoTarefaMapper tipoTarefaMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTipoTarefa>> listar() {
		return ResponseEntity.ok(tipoTarefaMapper.toCollectionDto(tipoTarefaRepository.findAll()));
	}
	
	@GetMapping(value = "/{tipoTarefaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTipoTarefa> buscar(@PathVariable Long tipoTarefaId) {
		return tipoTarefaRepository.findById(tipoTarefaId)
				.map(tipoTarefa -> ResponseEntity.ok(tipoTarefaMapper.toDto(tipoTarefa)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DtoTipoTarefa> incluir(@Valid @RequestBody InputTipoTarefa input) {
		TipoTarefa tipoTarefa = tipoTarefaService.salvar(tipoTarefaMapper.toEntity(input));
		return ResponseEntity.ok(tipoTarefaMapper.toDto(tipoTarefa));
	}
	
	@PutMapping(value = "/{tipoTarefaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTipoTarefa> atualizar(@PathVariable Long tipoTarefaId, @Valid @RequestBody InputTipoTarefa input) {
		if (!tipoTarefaRepository.existsById(tipoTarefaId)) {
			return ResponseEntity.notFound().build();
		}
		
		TipoTarefa tipoTarefa = tipoTarefaMapper.toEntity(input);
		tipoTarefa.setId(tipoTarefaId);
		return ResponseEntity.ok(tipoTarefaMapper.toDto(tipoTarefa));
	}
	
	@DeleteMapping(value = "/{tipoTarefaId}")
	public ResponseEntity<Void> excluir(@PathVariable Long tipoTarefaId) {
		if (!tipoTarefaRepository.existsById(tipoTarefaId)) {
			return ResponseEntity.notFound().build();
		}
		
		tipoTarefaService.excluir(tipoTarefaId);
		return ResponseEntity.noContent().build();
	}
}