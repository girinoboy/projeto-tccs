/**
 * 
 */
package br.com.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="tecnica")
public class TecnicaDTO extends AbstractDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;//usuario que cadastrou a tecnica ou alterou
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "anexos_id", insertable = true, updatable = true, nullable = true)
	private AnexoDTO anexoDTO;
	
	@ManyToMany  (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinTable(name = "graduacao_tecnica", joinColumns = { @JoinColumn(name = "tecnica_id",
	unique = false, nullable = false, insertable = true,updatable=true) },
	inverseJoinColumns = { @JoinColumn(name = "graduacao_id",
	unique = false, nullable = false, insertable = true,updatable=true) })
//	@LazyCollection(value = null)@LazyCollection(LazyCollectionOption.EXTRA)
	private List<GraduacaoDTO> listGraduacaoDTO;

	/**
	 * 
	 */
	public TecnicaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public AnexoDTO getAnexoDTO() {
		return anexoDTO;
	}

	public void setAnexoDTO(AnexoDTO anexoDTO) {
		this.anexoDTO = anexoDTO;
	}

	public List<GraduacaoDTO> getListGraduacaoDTO() {
		return listGraduacaoDTO;
	}

	public void setListGraduacaoDTO(List<GraduacaoDTO> listGraduacaoDTO) {
		this.listGraduacaoDTO = listGraduacaoDTO;
	}

}
