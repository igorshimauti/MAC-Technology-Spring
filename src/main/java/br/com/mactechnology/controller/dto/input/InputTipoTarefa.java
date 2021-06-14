package br.com.mactechnology.controller.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class InputTipoTarefa {

	@Valid
	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}