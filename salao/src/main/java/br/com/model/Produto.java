package br.com.model;

import javax.persistence.Entity;

@Entity
public class Produto extends AbstractModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3193331642931354967L;
	private String nome;
	private String descricao;
	private Double preco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
