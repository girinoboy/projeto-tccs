package br.ucb.modelo.bean;

import java.io.Serializable;

public class TorcedorBean extends PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ClubeBean clube;
	
	public TorcedorBean() {
		this.clube = new ClubeBean();
	}

	public void setClube(ClubeBean clube) {
		this.clube = clube;
	}

	public ClubeBean getClube() {
		return clube;
	}
	
}