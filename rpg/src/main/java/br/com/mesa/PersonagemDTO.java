package br.com.mesa;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


public class PersonagemDTO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String top;
	private String left;
	private String nome;
	private boolean novo;

	public PersonagemDTO() {
		// TODO Auto-generated constructor stub
	}

	public PersonagemDTO(String id, String top, String left, String nome) {
		super();
		this.id = id;
		this.top = top;
		this.left = left;
		this.nome = nome;
		this.novo = true;
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

	public PersonagemDTO setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}
}
