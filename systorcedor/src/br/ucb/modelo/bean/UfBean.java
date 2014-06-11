package br.ucb.modelo.bean;

import java.io.Serializable;

public class UfBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String sigla;

	public UfBean() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
