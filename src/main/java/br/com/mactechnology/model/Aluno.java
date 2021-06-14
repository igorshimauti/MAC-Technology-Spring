package br.com.mactechnology.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150)
	@Size(max = 150)
	@NotBlank
	private String nome;
	
	@Column(length = 14)
	@Size(max = 14)
	@NotBlank
	private String cpf;
	
	@NotNull
	private LocalDate dataNascimento;
	
	@Column(length = 150)
	@Size(max = 150)
	private String email;
	
	@Column(length = 12)
	@Size(max = 12)
	private String celular;
	
	@Embedded
	private EnderecoResidencial enderecoResidencial;
	
	@ManyToMany
    @JoinTable(name="matricula", joinColumns = @JoinColumn(name="aluno_id"), inverseJoinColumns = @JoinColumn(name="curso_id"))
	private List<Curso> cursos;
	
	@ManyToMany(mappedBy = "alunos")
	private List<Aula> aulas;
	
	@OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
	private TarefaAluno tarefaAluno;
	
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

	public EnderecoResidencial getEnderecoResidencial() {
		return enderecoResidencial;
	}
	public void setEnderecoResidencial(EnderecoResidencial enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}