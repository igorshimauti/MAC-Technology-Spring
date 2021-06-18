package br.com.mactechnology.controller.dto;

public class DtoMateria {

	private Long id;
	private String nome;
	private DtoProfessor professor;

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
	
	public DtoProfessor getProfessor() {
		return professor;
	}
	
	public void setProfessor(DtoProfessor professor) {
		this.professor = professor;
	}
}