/**
 * 
 */
package br.com.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
 * @author Marcleônio
 *
 */
@Entity
@Table(name="escola")
public class EscolaDTO {
	
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cidade_id", insertable = true, updatable = true, nullable = true)
	private CidadeDTO cidadeDTO;
	@OneToMany(targetEntity=CidadeDTO.class, fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private List<CidadeDTO> listCidadeDTO;
	private String telefone;
	private Integer redeEnsino;
	private String fax;
	private Integer planejamentoLiterario;
	private Date data;
	private Integer serie;
	private Integer status;
	private Boolean biblioteca;
	private Integer turno;

	/**
	 * 
	 */
	public EscolaDTO() {
		listCidadeDTO = new ArrayList<CidadeDTO>();
		listCidadeDTO.add(new CidadeDTO(1,"Brasilia"));
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public CidadeDTO getCidadeDTO() {
		return cidadeDTO;
	}

	public void setCidadeDTO(CidadeDTO cidadeDTO) {
		this.cidadeDTO = cidadeDTO;
	}

	public List<CidadeDTO> getListCidadeDTO() {
		return listCidadeDTO;
	}

	public void setListCidadeDTO(List<CidadeDTO> listCidadeDTO) {
		this.listCidadeDTO = listCidadeDTO;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getRedeEnsino() {
		return redeEnsino;
	}

	public void setRedeEnsino(Integer redeEnsino) {
		this.redeEnsino = redeEnsino;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getPlanejamentoLiterario() {
		return planejamentoLiterario;
	}

	public void setPlanejamentoLiterario(Integer planejamentoLiterario) {
		this.planejamentoLiterario = planejamentoLiterario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Boolean biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

	

}
