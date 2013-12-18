/**
 * 
 */
package br.com.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="agenda")
public class AgendaDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Date dataHoraI;
	private Date dataHoraF;
	@Column(name="data_agendamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAgendamento;
	@Column(name="all_day")
	private Boolean allDay;
	private String obs;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "unidade_id", insertable = true, updatable = true, nullable = true)
	private UnidadeDTO unidadeDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "local_id", insertable = true, updatable = true, nullable = true)
	private LocalDTO localDTO;

	/**
	 * 
	 */
	public AgendaDTO() {
		dataAgendamento = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraI() {
		return dataHoraI;
	}

	public void setDataHoraI(Date dataHoraI) {
		this.dataHoraI = dataHoraI;
	}

	public Date getDataHoraF() {
		return dataHoraF;
	}

	public void setDataHoraF(Date dataHoraF) {
		this.dataHoraF = dataHoraF;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public UnidadeDTO getUnidadeDTO() {
		return unidadeDTO;
	}

	public void setUnidadeDTO(UnidadeDTO unidadeDTO) {
		this.unidadeDTO = unidadeDTO;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public LocalDTO getLocalDTO() {
		return localDTO;
	}

	public void setLocalDTO(LocalDTO localDTO) {
		this.localDTO = localDTO;
	}

}
