package br.com.ind;

public enum indSituacao {
	AGUARDANDO("Aguardando"),FINALIZADO("Finalizado"),EM_ANDAMENTO("Em andamento");

	private String descricao;
	
	private indSituacao(String descricao) {
		this.descricao= descricao;
	}

	public String toString() {
		return this.descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
