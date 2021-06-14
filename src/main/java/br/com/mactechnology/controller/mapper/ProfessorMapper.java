package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoProfessor;
import br.com.mactechnology.controller.dto.input.InputProfessor;
import br.com.mactechnology.model.Professor;

@Component
public class ProfessorMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoProfessor toDto(Professor professor) {
		return modelMapper.map(professor, DtoProfessor.class);
	}
	
	public List<DtoProfessor> toCollectionDto(List<Professor> professores) {
		return professores.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public Professor toEntity(InputProfessor input) {
		return modelMapper.map(input, Professor.class);
	}
}