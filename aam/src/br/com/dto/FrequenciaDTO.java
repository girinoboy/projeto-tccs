/**
 * 
 */
package br.com.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Marcleonio
 *
 */
@Entity
@Table(name = "frequencia")
public class FrequenciaDTO extends AbstractDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -795000463364688520L;
//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Integer id;
	@Column(name ="data_entrada")
	private Date dataEntrada;
	@Column(name ="data_completa")
	private Date dataCompleta;
	private Boolean presente;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;
	private Integer mes;
	private Integer ano;
	@Column(name ="total_presenca")
	private Integer totalPresenca;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="data_lancamento")
	private Date dataLancamento;

	/**
	 * 
	 */
	public FrequenciaDTO() {
		if(dataLancamento == null){
			dataLancamento = new Date();
		}
	}

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public Date getDataCompleta() {
		return dataCompleta;
	}

	public void setDataCompleta(Date dataCompleta) {
		this.dataCompleta = dataCompleta;
	}

	public Boolean getPresente() {
		if(presente == null){
			presente = new Boolean(false);
		}
		return presente;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}
	
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getTotalPresenca() {
		return totalPresenca;
	}

	public void setTotalPresenca(Integer totalPresenca) {
		this.totalPresenca = totalPresenca;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

}
