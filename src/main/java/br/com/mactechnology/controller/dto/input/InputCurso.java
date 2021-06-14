package br.com.mactechnology.controller.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class InputCurso {

	@Valid
	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}