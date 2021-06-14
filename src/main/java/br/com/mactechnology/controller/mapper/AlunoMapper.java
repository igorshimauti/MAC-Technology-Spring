package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoAluno;
import br.com.mactechnology.controller.dto.input.InputAluno;
import br.com.mactechnology.model.Aluno;

@Component
public class AlunoMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoAluno toDto(Aluno aluno) {
		return modelMapper.map(aluno, DtoAluno.class);
	}
	
	public List<DtoAluno> toCollectionDto(List<Aluno> alunos) {
		return alunos.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public Aluno toEntity(InputAluno input) {
		return modelMapper.map(input, Aluno.class);
	}
}