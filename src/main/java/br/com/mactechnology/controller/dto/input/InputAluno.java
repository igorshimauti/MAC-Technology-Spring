package br.com.mactechnology.controller.dto.input;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InputAluno {
	
	@Valid
	@NotBlank
	private String nome;
	
	@Valid
	@NotBlank
	private String cpf;
	
	@Valid
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate dataNascimento;
	
	private String email;
	private String celular;

	@NotNull
	private List<Long> cursos;

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

	public List<Long> getCursos() {
		return cursos;
	}

	public void setCursos(List<Long> cursos) {
		this.cursos = cursos;
	}

	public InputEnderecoResidencial getEnderecoResidencial() {
		return enderecoResidencial;
	}

	public void setEnderecoResidencial(InputEnderecoResidencial enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
}