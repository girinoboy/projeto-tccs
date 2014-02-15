package br.com.dto;

public class StatusGestaoDTO {
	
	private Integer membrosAtivos;
	private Integer membrosInativos;
	private Integer membrosSemPendencia;
	private Integer membrosComPendencia;
	private Integer TotalMembros;
	private Integer TotalArrecadado;

	public StatusGestaoDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getMembrosAtivos() {
		return membrosAtivos;
	}

	public StatusGestaoDTO(Integer membrosAtivos, Integer membrosInativos,
			Integer membrosSemPendencia, Integer membrosComPendencia,
			Integer totalMembros, Integer totalArrecadado) {
		super();
		this.membrosAtivos = membrosAtivos;
		this.membrosInativos = membrosInativos;
		this.membrosSemPendencia = membrosSemPendencia;
		this.membrosComPendencia = membrosComPendencia;
		TotalMembros = totalMembros;
		TotalArrecadado = totalArrecadado;
	}

	public void setMembrosAtivos(Integer membrosAtivos) {
		this.membrosAtivos = membrosAtivos;
	}

	public Integer getMembrosInativos() {
		return membrosInativos;
	}

	public void setMembrosInativos(Integer membrosInativos) {
		this.membrosInativos = membrosInativos;
	}

	public Integer getMembrosSemPendencia() {
		return membrosSemPendencia;
	}

	public void setMembrosSemPendencia(Integer membrosSemPendencia) {
		this.membrosSemPendencia = membrosSemPendencia;
	}

	public Integer getMembrosComPendencia() {
		return membrosComPendencia;
	}

	public void setMembrosComPendencia(Integer membrosComPendencia) {
		this.membrosComPendencia = membrosComPendencia;
	}

	public Integer getTotalMembros() {
		return TotalMembros;
	}

	public void setTotalMembros(Integer totalMembros) {
		TotalMembros = totalMembros;
	}

	public Integer getTotalArrecadado() {
		return TotalArrecadado;
	}

	public void setTotalArrecadado(Integer totalArrecadado) {
		TotalArrecadado = totalArrecadado;
	}

}
