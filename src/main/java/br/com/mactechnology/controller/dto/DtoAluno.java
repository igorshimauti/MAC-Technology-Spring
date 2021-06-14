package br.com.mactechnology.controller.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.mactechnology.controller.dto.input.InputEnderecoResidencial;

public class DtoAluno {


	private String nome;
	private String cpf;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private List<DtoCurso> cursos;
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

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<DtoCurso> getCursos() {
		return cursos;
	}

	public void setCursos(List<DtoCurso> cursos) {
		this.cursos = cursos;
	}

	public InputEnderecoResidencial getEnderecoResidencial() {
		return enderecoResidencial;
	}
	
	public void setEnderecoResidencial(InputEnderecoResidencial enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
}