package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoMateria;
import br.com.mactechnology.controller.dto.input.InputMateria;
import br.com.mactechnology.model.Materia;

@Component
public class MateriaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoMateria toDto(Materia materia) {
		return modelMapper.map(materia, DtoMateria.class);
	}
	
	public List<DtoMateria> toCollectionDto(List<Materia> materias) {
		return materias.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public Materia toEntity(InputMateria input) {
		return modelMapper.map(input, Materia.class);
	}
}