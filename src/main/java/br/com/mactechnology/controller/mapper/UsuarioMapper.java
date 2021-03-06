package br.com.mactechnology.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mactechnology.controller.dto.DtoUsuario;
import br.com.mactechnology.controller.dto.input.InputUsuario;
import br.com.mactechnology.model.Usuario;

@Component
public class UsuarioMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DtoUsuario toDto(Usuario usuario) {
		return modelMapper.map(usuario, DtoUsuario.class);
	}
	
	public List<DtoUsuario> toCollectionDto(List<Usuario> usuarios) {
		return usuarios.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public Usuario toEntity(InputUsuario input) {
		return modelMapper.map(input, Usuario.class);
	}
}
