package br.ucb.modelo.bean;

import java.io.Serializable;

public class CamisetaBean  implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String nomeCamiseta;
	private int dtCriacao;
	private String nomeTime;
	private String descricao;

	public CamisetaBean() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeCamiseta() {
		return nomeCamiseta;
	}

	public void setNomeCamiseta(String nomeCamiseta) {
		this.nomeCamiseta = nomeCamiseta;
	}

	public int getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(int dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
