package br.com.mactechnology.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.mactechnology.controller.dto.input.InputEnderecoResidencial;

public class DtoAluno {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String celular;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate dataNascimento;
	
	private InputEnderecoResidencial enderecoResidencial;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
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