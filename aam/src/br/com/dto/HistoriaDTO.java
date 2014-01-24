package br.com.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="historia")
public class HistoriaDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Boolean categoria;
	private String titulo;
	private String descricao;
	@OneToMany(targetEntity=LinkDTO.class, mappedBy = "historiaDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL,CascadeType.PERSIST, CascadeType.MERGE})
	private List<LinkDTO> listLinkDTO;

	public HistoriaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCategoria() {
		return categoria;
	}

	public void setCategoria(Boolean categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

}
