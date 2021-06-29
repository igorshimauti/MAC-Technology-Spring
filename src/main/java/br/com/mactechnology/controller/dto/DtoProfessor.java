package br.com.mactechnology.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DtoProfessor {

	private Long id;
	private String nome;
	private String cpf;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate dataNascimento;
	
	private String email;
	private String celular;
	private DtoEnderecoResidencial enderecoResidencial;

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public DtoEnderecoResidencial getEnderecoResidencial() {
		return enderecoResidencial;
	}

	public void setEnderecoResidencial(DtoEnderecoResidencial enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
}