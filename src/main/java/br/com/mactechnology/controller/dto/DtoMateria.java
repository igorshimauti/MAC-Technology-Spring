package br.com.mactechnology.controller.dto;

public class DtoMateria {

	private Long id;
	private String nome;
	private Long professorId;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Long getProfessorId() {
		return professorId;
	}
	
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}