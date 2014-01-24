/**
 * 
 */
package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name = "link")
public class LinkDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5801329818411040951L;
	
	private String nome;
	private String url;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "historia_id", insertable = true, updatable = true, nullable = true)
	private HistoriaDTO historiaDTO;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "noticia_id", insertable = true, updatable = true, nullable = true)
	private NoticiaDTO noticiaDTO;

	/**
	 * 
	 */
	public LinkDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HistoriaDTO getHistoriaDTO() {
		return historiaDTO;
	}

	public void setHistoriaDTO(HistoriaDTO historiaDTO) {
		this.historiaDTO = historiaDTO;
	}

	public NoticiaDTO getNoticiaDTO() {
		return noticiaDTO;
	}

	public void setNoticiaDTO(NoticiaDTO noticiaDTO) {
		this.noticiaDTO = noticiaDTO;
	}

}
