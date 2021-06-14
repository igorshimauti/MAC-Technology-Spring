package br.com.mactechnology.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class EnderecoResidencial {

	@Column(length = 8)
	@Size(max = 8)
	private String cep;
	
	@Column(length = 2)
	@Size(max = 2)
	private String uf;
	
	@Column(length = 50)
	@Size(max = 50)
	private String cidade;
	
	@Column(length = 50)
	@Size(max = 50)
	private String bairro;
	
	@Column(length = 100)
	@Size(max = 100)
	private String logradouro;
	
	@Column(length = 20)
	@Size(max = 20)
	private String numero;
	
	@Column(length = 50)
	@Size(max = 50)
	private String complemento;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}