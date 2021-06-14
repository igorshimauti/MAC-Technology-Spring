package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoCurso;
import br.com.mactechnology.controller.dto.input.InputCurso;
import br.com.mactechnology.model.Curso;

@Component
public class CursoMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoCurso toDto(Curso curso) {
		return modelMapper.map(curso, DtoCurso.class);
	}
	
	public List<DtoCurso> toCollectionDto(List<Curso> cursos) {
		return cursos.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public Curso toEntity(InputCurso input) {
		return modelMapper.map(input, Curso.class);
	}
}