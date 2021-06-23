package br.com.mactechnology.controller.dto;

public class DtoToken {

	private String token;
	private String tipo;

	public DtoToken(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}	
}