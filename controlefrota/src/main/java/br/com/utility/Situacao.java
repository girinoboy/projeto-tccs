package br.com.utility;

public enum Situacao {
	AGUARDANDO("Aguardando"),FINALIZADO("Finalizado"),EM_ANDAMENTO("Em andamento");

	private String descricao;
	
	private Situacao(String descricao) {
		this.descricao= descricao;
	}

	public String toString() {
		return this.descricao;
	}

}
