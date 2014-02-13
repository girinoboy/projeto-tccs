/**
 * 
 */
package br.com.dto;

import java.util.Date;

/**
 * @author Marcleônio
 *
 */
public class RelatorioGestaoMensal {
	
	private Date startDate;
	private Date endDate;
	private Integer membrosAtivos;
	private Integer membrosInativos;
	private Integer membrosSemPendencia;
	private Integer membrosComPendencia;
	private Integer TotalMembros;
	private Integer TotalArrecadado;

	/**
	 * 
	 */
	public RelatorioGestaoMensal() {
		// TODO Auto-generated constructor stub
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getMembrosAtivos() {
		return membrosAtivos;
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
