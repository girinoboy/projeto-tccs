package br.com.ind;

public enum IndTipoCombustivel {
	GASOLINA("Gasolina"),ETANOL("Etanol"),DIESEL("Diesel");
	
	private String descricao;
	
	IndTipoCombustivel(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}

}
