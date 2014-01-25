package br.com.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="resultado_avaliacao")
public class ResultadoAvaliacaoDTO extends AbstractDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6207979261320831785L;
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Date data;
	private Double tecnica;
	private Double luta;
	private Double conhecimentos;
	private String comentarios;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;

	public ResultadoAvaliacaoDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Double getTecnica() {
		return tecnica;
	}

	public void setTecnica(Double tecnica) {
		this.tecnica = tecnica;
	}

	public Double getLuta() {
		return luta;
	}

	public void setLuta(Double luta) {
		this.luta = luta;
	}

	public Double getConhecimentos() {
		return conhecimentos;
	}

	public void setConhecimentos(Double conhecimentos) {
		this.conhecimentos = conhecimentos;
	}

}
