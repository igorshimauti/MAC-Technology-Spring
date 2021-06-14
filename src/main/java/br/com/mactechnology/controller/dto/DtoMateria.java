package br.com.mactechnology.controller.dto;

public class DtoMateria {

	private String nomeMateria;
	private DtoProfessor professor;
	
	public String getNomeMateria() {
		return nomeMateria;
	}
	
	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}
	
	public DtoProfessor getProfessor() {
		return professor;
	}
	
	public void setProfessor(DtoProfessor professor) {
		this.professor = professor;
	}
}