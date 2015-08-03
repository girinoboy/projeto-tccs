package br.com.enumeration;

public enum ConceitoE {

	C("Competente"),
	EVC("Em vias de Competência");
	
	private String descricao;
	
	ConceitoE(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
