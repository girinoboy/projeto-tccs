package br.ucb.modelo.bean;

import java.io.Serializable;

public class TorcedorBean extends PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ClubeBean clube;
	private CamisetaBean camiseta;
	
	public TorcedorBean() {
		this.clube = new ClubeBean();
		this.camiseta = new CamisetaBean();
	}

	public void setClube(ClubeBean clube) {
		this.clube = clube;
	}

	public ClubeBean getClube() {
		return clube;
	}

	public CamisetaBean getCamiseta() {
		return camiseta;
	}

	public void setCamiseta(CamisetaBean camiseta) {
		this.camiseta = camiseta;
	}
	
}