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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String telefone;
	private Integer redeEnsino;
	private String fax;
	private Integer planejamentoLiterario;
	private Date data;
	private Integer serie;
	private String anotacoes;
	private Boolean biblioteca;/*
	@OneToMany(targetEntity=TurnoDTO.class, fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	@JoinColumn(name = "escola_id", insertable = true, updatable = true, nullable = true)*/
	/*
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "IDS_SV_CHECK_HAS_TYPE",
                joinColumns={@JoinColumn(name="check_id")},
                inverseJoinColumns={@JoinColumn(name="checktype_id")})
	*/
	
	 @ManyToMany  (cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	    @JoinTable(name = "escola_turno", joinColumns = { @JoinColumn(name = "escola_id",   
	    unique = false, nullable = false, insertable = true) },   
	    inverseJoinColumns = { @JoinColumn(name = "turno_id",   
	    unique = false, nullable = false, insertable = true) })  
	
	private List<TurnoDTO> listTurnoDTO;

	/**
	 * 
	 */
	public EscolaDTO() {
		
	}
	
	@Override  
    public boolean equals(Object obj) {  
        if (obj == null) {  
            return false;  
        }  
        if (getClass() != obj.getClass()) {  
            return false;  
        }  
        final EscolaDTO other = (EscolaDTO) obj;
        if (id != null && !this.id.equals(other.id)) {  
            return false;
        }  
        return true;  
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

	public Boolean getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Boolean biblioteca) {
		this.biblioteca = biblioteca;
	}

	public List<TurnoDTO> getListTurnoDTO() {
		return listTurnoDTO;
	}

	public void setListTurnoDTO(List<TurnoDTO> listTurnoDTO) {
		this.listTurnoDTO = listTurnoDTO;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

}
