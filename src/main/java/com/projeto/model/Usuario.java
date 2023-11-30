package com.projeto.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "usuario")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USU_CODIGO", nullable = false, precision = 10, scale = 0)
	private int USU_CODIGO;

	@NotEmpty
	@Column(name = "USU_NOME", nullable = false, length = 30)
	private String USU_NOME;

	@NotEmpty
	@Column(name = "USU_LOGIN", nullable = false, length = 10)
	private String USU_LOGIN;

	@NotEmpty
	@Column(name = "USU_SENHA", nullable = false, length = 10)
	private String USU_SENHA;

	@NotNull
	@Column(name = "USU_ATIVO", precision = 1)
	private int USU_ATIVO;

	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "USU_CADASTRO", nullable = true)
	private Date USU_DTCADASTRO;

	public Date getUSU_DTCADASTRO() {
		return USU_DTCADASTRO;
	}

	public void setUSU_DTCADASTRO(Date USU_DTCADASTRO) {
		this.USU_DTCADASTRO = USU_DTCADASTRO;
	}

	public int getUSU_CODIGO() {
		return this.USU_CODIGO;
	}

	public void setUSU_CODIGO(int USU_CODIGO) {
		this.USU_CODIGO = USU_CODIGO;
	}

	public String getUSU_NOME() {
		return this.USU_NOME;
	}

	public void setUSU_NOME(String USU_NOME) {
		this.USU_NOME = USU_NOME;
	}

	public String getUSU_LOGIN() {
		return this.USU_LOGIN;
	}

	public void setUSU_LOGIN(String USU_LOGIN) {
		this.USU_LOGIN = USU_LOGIN;
	}

	public String getUSU_SENHA() {
		return this.USU_SENHA;
	}

	public void setUSU_SENHA(String USU_SENHA) {
		this.USU_SENHA = USU_SENHA;
	}

	public int getUSU_ATIVO() {
		return this.USU_ATIVO;
	}
	
	public Usuario() {
	
	}
	public void setUSU_ATIVO(int USU_ATIVO) {
		this.USU_ATIVO = USU_ATIVO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(USU_CODIGO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return USU_CODIGO == other.USU_CODIGO;
	}

	@Override
	public String toString() {
		return "UsuarioModel [USU_CODIGO=" + USU_CODIGO + "]";
	}

}
