package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoTarefa;
import br.com.mactechnology.controller.dto.input.InputTarefa;
import br.com.mactechnology.model.Tarefa;

@Component
public class TarefaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoTarefa toDto(Tarefa tarefa) {
		return modelMapper.map(tarefa, DtoTarefa.class);
	}
	
	public List<DtoTarefa> toCollectionDto(List<Tarefa> tarefas) {
		return tarefas.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public Tarefa toEntity(InputTarefa input) {
		return modelMapper.map(input, Tarefa.class);
	}
}