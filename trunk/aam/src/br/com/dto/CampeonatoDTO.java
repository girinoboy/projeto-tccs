/**
 * 
 */
package br.com.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="campeonato")
public class CampeonatoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6175523350743924889L;
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Date data;
	private String descricao;
	@OneToMany(targetEntity=LinkDTO.class, mappedBy = "campeonatoDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL,CascadeType.PERSIST, CascadeType.MERGE})
	private List<LinkDTO> listLinkDTO;
	@OneToMany(targetEntity=ResultadoDTO.class, mappedBy = "campeonatoDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL,CascadeType.PERSIST, CascadeType.MERGE})
	private List<ResultadoDTO> listResultadoDTO;

	/**
	 * 
	 */
	public CampeonatoDTO() {
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<LinkDTO> getListLinkDTO() {
		return listLinkDTO;
	}

	public void setListLinkDTO(List<LinkDTO> listLinkDTO) {
		this.listLinkDTO = listLinkDTO;
	}

	public List<ResultadoDTO> getListResultadoDTO() {
		return listResultadoDTO;
	}

	public void setListResultadoDTO(List<ResultadoDTO> listResultadoDTO) {
		this.listResultadoDTO = listResultadoDTO;
	}

}
