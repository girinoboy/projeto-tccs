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
 * @author Marcleônio
 *
 */
@Entity
@Table(name="graduacao")
public class GraduacaoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1034105644688150471L;
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String conhecimentos;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;//usuario que cadastrou a tecnica ou alterou
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "anexos_id", insertable = true, updatable = true, nullable = true)
	private AnexoDTO anexoDTO;
	
	@ManyToMany  (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinTable(name = "graduacao_tecnica", joinColumns = { @JoinColumn(name = "graduacao_id",
	unique = false, nullable = false, insertable = true,updatable=true) },
	inverseJoinColumns = { @JoinColumn(name = "tecnica_id",
	unique = false, nullable = false, insertable = true,updatable=true) })
//	@LazyCollection(value = null)@LazyCollection(LazyCollectionOption.EXTRA)
	private List<TecnicaDTO> listTecnica;

	/**
	 * 
	 */
	public GraduacaoDTO() {
		// TODO Auto-generated constructor stub
	}

//	@Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final GraduacaoDTO other = (GraduacaoDTO) obj;
//        if (this.id == null){
//        	return false;
//        }
//        if (!this.id.equals(other.id)) {
//            return false;
//        }
//        return true;
//    }
// 
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 31 * hash + this.id;
//        return hash;
//    }

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

	public String getConhecimentos() {
		return conhecimentos;
	}

	public void setConhecimentos(String conhecimentos) {
		this.conhecimentos = conhecimentos;
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

	public List<TecnicaDTO> getListTecnica() {
		return listTecnica;
	}

	public void setListTecnica(List<TecnicaDTO> listTecnica) {
		this.listTecnica = listTecnica;
	}

}
