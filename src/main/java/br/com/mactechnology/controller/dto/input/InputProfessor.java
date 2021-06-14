package br.com.mactechnology.controller.dto.input;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InputProfessor {

	@Valid
	@NotBlank
	private String nome;
	
	@Valid
	@NotBlank
	private String cpf;
	
	@Valid
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@Valid
	@NotBlank
	private String email;
	
	@Valid
	@NotBlank
	private String celular;
	
	private InputEnderecoResidencial enderecoResidencial;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public InputEnderecoResidencial getEnderecoResidencial() {
		return enderecoResidencial;
	}

	public void setEnderecoResidencial(InputEnderecoResidencial enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
}