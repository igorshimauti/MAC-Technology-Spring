package br.com.mactechnology.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DtoTarefa {

	private Long id;
	private Long tipoTarefaId;
	private String descricao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate data;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate dataEntrega;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getTipoTarefaId() {
		return tipoTarefaId;
	}

	public void setTipoTarefaId(Long tipoTarefaId) {
		this.tipoTarefaId = tipoTarefaId;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public LocalDate getDataEntrega() {
		return dataEntrega;
	}
	
	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
}