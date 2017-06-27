package br.com.rpg.dto;

import java.io.Serializable;

public class PersonagemDTO implements Serializable{

	private String id;
	private String top;
	private String left;
	private String nome;

	public PersonagemDTO() {
		// TODO Auto-generated constructor stub
	}

	public PersonagemDTO(String id, String top, String left, String nome) {
		super();
		this.id = id;
		this.top = top;
		this.left = left;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
