/**
 * 
 */
package br.com.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Marcleônio
 *
 */
public class RelatorioGestaoMensalDTO {
	

	private Date startDate;
	private Date endDate;
	private String mesAno;
	private List<StatusGestaoDTO> statusGestaoDTO;

	/**
	 * 
	 */
	public RelatorioGestaoMensalDTO() {	}
	
	public RelatorioGestaoMensalDTO(Object object, Object object2) {
		
		mesAno = obterNomeMes((Integer) object)+"/"+object2;
	}
	
    public String obterNomeMes(int mes){  
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",   
                          "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};  
        return meses[mes];  
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
	
	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public List<StatusGestaoDTO> getStatusGestaoDTO() {
		if(statusGestaoDTO == null){
			statusGestaoDTO = new ArrayList<StatusGestaoDTO>();
		}
		return statusGestaoDTO;
	}

	public void setStatusGestaoDTO(List<StatusGestaoDTO> statusGestaoDTO) {
		this.statusGestaoDTO = statusGestaoDTO;
	}



}
