package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoAula;
import br.com.mactechnology.controller.dto.input.InputAula;
import br.com.mactechnology.model.Aula;

@Component
public class AulaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoAula toDto(Aula aula) {
		return modelMapper.map(aula, DtoAula.class);
	}
	
	public List<DtoAula> toCollectionDto(List<Aula> aulas) {
		return aulas.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public Aula toEntity(InputAula input) {
		return modelMapper.map(input, Aula.class);
	}
}