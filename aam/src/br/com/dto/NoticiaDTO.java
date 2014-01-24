/**
 * 
 */
package br.com.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="noticia")
public class NoticiaDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	@Column(name="data_criacao")
	private Date dataCriacao;
	@Column(name="sem_data")
	private Boolean semData;
	@Column(name="marcado_para")
	private Date marcadoPara;
	private String descricao;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;//usuario marcador, ou o que registrou a noticia
	@OneToMany(targetEntity=LinkDTO.class, mappedBy = "noticiaDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL,CascadeType.PERSIST, CascadeType.MERGE})
	private List<LinkDTO> listLinkDTO;

	/**
	 * 
	 */
	public NoticiaDTO() {
		dataCriacao = new Date();
		setDataCriacao(dataCriacao);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Boolean getSemData() {
		return semData;
	}

	public void setSemData(Boolean semData) {
		this.semData = semData;
	}

	public Date getMarcadoPara() {
		return marcadoPara;
	}

	public void setMarcadoPara(Date marcadoPara) {
		this.marcadoPara = marcadoPara;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public List<LinkDTO> getListLinkDTO() {
		return listLinkDTO;
	}

	public void setListLinkDTO(List<LinkDTO> listLinkDTO) {
		this.listLinkDTO = listLinkDTO;
	}


}
