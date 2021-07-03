package br.com.mactechnology.controller.dto.input;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InputAula {
	
	@NotBlank
	private String tema;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate data;
	
	@NotNull
	private List<Long> alunos;
	
	private String url;

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<Long> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Long> alunos) {
		this.alunos = alunos;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}