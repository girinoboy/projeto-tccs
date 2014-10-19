package br.com.ind;

public enum IndTipo {
	PASSAGEIRO(1L,"Passageiro"),CARGA(2L,"Carga");

	private Long id;
	private String descricao;

	IndTipo(Long id,String descricao){
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
}
