/**
 * 
 */
package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="resultado")
public class ResultadoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7483319953382704519L;

//	@Id 
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Integer id;
	private String posicao;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@Cascade({org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;
	@OneToOne(fetch = FetchType.EAGER, cascade ={CascadeType.PERSIST,CascadeType.MERGE} )
	@Cascade({org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(name = "campeonato_id", insertable = true, updatable = true, nullable = true)
	private CampeonatoDTO campeonatoDTO;
	
	/**
	 * 
	 */
	public ResultadoDTO() {
		// TODO Auto-generated constructor stub
	}

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public UsuarioDTO getUsuarioDTO() {
		if(usuarioDTO==null)
			usuarioDTO = new UsuarioDTO();
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public CampeonatoDTO getCampeonatoDTO() {
		return campeonatoDTO;
	}

	public void setCampeonatoDTO(CampeonatoDTO campeonatoDTO) {
		this.campeonatoDTO = campeonatoDTO;
	}

}
