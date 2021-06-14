package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoTarefaAluno;
import br.com.mactechnology.controller.dto.input.InputTarefaAluno;
import br.com.mactechnology.model.TarefaAluno;

@Component
public class TarefaAlunoMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoTarefaAluno toDto(TarefaAluno tarefaAluno) {
		return modelMapper.map(tarefaAluno, DtoTarefaAluno.class);
	}
	
	public List<DtoTarefaAluno> toCollectionDto(List<TarefaAluno> tarefaAlunos) {
		return tarefaAlunos.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public TarefaAluno toEntity(InputTarefaAluno input) {
		return modelMapper.map(input, TarefaAluno.class);
	}
}