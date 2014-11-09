package br.com.ind;

public enum indCategoria {
	A("A"),B("B"),C("C"),D("D");
	
	private String descricao;
	
	indCategoria(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
