package br.com.enumeration;

public enum ClassificacaoDesempenhoE {
	
	BOM("Bom"),
	REGULAR("Regular"),
	OTIMO("Ótimo");
	
	private String descricao;
	
	ClassificacaoDesempenhoE(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
