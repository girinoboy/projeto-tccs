package br.ucb.modelo.bean;

import java.io.Serializable;
import java.util.Date;

import br.ucb.modelo.enumerador.EstadoCivil;
import br.ucb.modelo.enumerador.Uf;

public class PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private EstadoCivil estadoCivil;
	private String email;
	private Date dtaNasc;
	private Uf uf;
	
	public PessoaBean() {
	}
	
	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public final String getNome() {
		return nome;
	}
	
	public final void setNome(String nome) {
		this.nome = nome;
	}
	
	public final EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public final void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final Date getDtaNasc() {
		return dtaNasc;
	}
	
	public final void setDtaNasc(Date dtaNasc) {
		this.dtaNasc = dtaNasc;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}