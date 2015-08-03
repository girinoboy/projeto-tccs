package br.com.enumeration;

public enum MedalhaE {
	z("Participação"),
	p("1º Lugar"),
	s("2º Lugar"),
	t("3º Lugar");
	
	private String descricao;
	
	MedalhaE(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
