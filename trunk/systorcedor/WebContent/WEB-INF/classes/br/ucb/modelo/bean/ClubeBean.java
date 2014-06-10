package br.ucb.modelo.bean;

import java.io.Serializable;


import br.ucb.modelo.enumerador.Uf;

public class ClubeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nomeTime;
	private int dtCriacao;
	private Uf uf;
	private String descricao;
	
	public ClubeBean() {
	}
	
	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public final String getNomeTime() {
		return nomeTime;
	}
	
	public final void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

	public int getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(int dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}