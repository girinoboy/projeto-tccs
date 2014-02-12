/**
 * 
 */
package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Marcleônio
 *
 */

@Entity
@Table(name = "graduacao_tecnica")
public class GraduacaoTecnicaDTO extends AbstractDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7279977281530334394L;
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "graduacao_id", insertable = true, updatable = true, nullable = true)
	private GraduacaoDTO graduacaoDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "tecnica_id", insertable = true, updatable = true, nullable = true)
	private TecnicaDTO tecnicaDTO;

	/**
	 * 
	 */
	public GraduacaoTecnicaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GraduacaoDTO getGraduacaoDTO() {
		return graduacaoDTO;
	}

	public void setGraduacaoDTO(GraduacaoDTO graduacaoDTO) {
		this.graduacaoDTO = graduacaoDTO;
	}

	public TecnicaDTO getTecnicaDTO() {
		return tecnicaDTO;
	}

	public void setTecnicaDTO(TecnicaDTO tecnicaDTO) {
		this.tecnicaDTO = tecnicaDTO;
	}

}
