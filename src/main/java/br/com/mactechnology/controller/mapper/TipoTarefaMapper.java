package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoTipoTarefa;
import br.com.mactechnology.controller.dto.input.InputTipoTarefa;
import br.com.mactechnology.model.TipoTarefa;

@Component
public class TipoTarefaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoTipoTarefa toDto(TipoTarefa tipoTarefa) {
		return modelMapper.map(tipoTarefa, DtoTipoTarefa.class);
	}
	
	public List<DtoTipoTarefa> toCollectionDto(List<TipoTarefa> tipoTarefas) {
		return tipoTarefas.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public TipoTarefa toEntity(InputTipoTarefa input) {
		return modelMapper.map(input, TipoTarefa.class);
	}
}