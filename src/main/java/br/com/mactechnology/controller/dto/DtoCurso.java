package br.com.mactechnology.controller.dto;

import java.util.List;

public class DtoCurso {

	private String nomeCurso;
	private List<DtoMateria> materias;

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public List<DtoMateria> getMaterias() {
		return materias;
	}

	public void setMaterias(List<DtoMateria> materias) {
		this.materias = materias;
	}
}