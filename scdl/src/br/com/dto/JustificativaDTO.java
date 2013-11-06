package br.com.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="justificativa")
public class JustificativaDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Column(name="data_justificativa")
	private Date dataJustificativa;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "escola_id", insertable = true, updatable = true, nullable = true)
	private EscolaDTO escolaDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "livro_id", insertable = true, updatable = true, nullable = true)
	private LivroDTO livroDTO;

	/**
	 * 
	 */
	public JustificativaDTO() {
		dataJustificativa = new Date();
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JustificativaDTO other = (JustificativaDTO) obj;
        if (this.id == null){
        	return false;
        }
        if (!this.id.equals(other.id)) {
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


	public EscolaDTO getEscolaDTO() {
		return escolaDTO;
	}


	public void setEscolaDTO(EscolaDTO escolaDTO) {
		this.escolaDTO = escolaDTO;
	}


	public LivroDTO getLivroDTO() {
		return livroDTO;
	}


	public void setLivroDTO(LivroDTO livroDTO) {
		this.livroDTO = livroDTO;
	}


	public Date getDataJustificativa() {
		return dataJustificativa;
	}


	public void setDataJustificativa(Date dataJustificativa) {
		this.dataJustificativa = dataJustificativa;
	}
}
