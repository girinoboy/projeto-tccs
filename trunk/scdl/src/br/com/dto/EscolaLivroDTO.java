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
@Table(name = "escola_livro")
public class EscolaLivroDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column(name="quantidade_aluno")
	private Integer quantidadeAluno;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "escola_id", insertable = true, updatable = true, nullable = true)
	private EscolaDTO escolaDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "livro_id", insertable = true, updatable = true, nullable = true)
	private LivroDTO livroDTO;
	@Column(name="data_adocao")
	private Date dataAdocao;

	public EscolaLivroDTO() {
		dataAdocao = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidadeAluno() {
		return quantidadeAluno;
	}

	public void setQuantidadeAluno(Integer quantidadeAluno) {
		this.quantidadeAluno = quantidadeAluno;
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

	public Date getDataAdocao() {
		if(dataAdocao==null)
			dataAdocao = new Date();
		return dataAdocao;
	}

	public void setDataAdocao(Date dataAdocao) {
		this.dataAdocao = dataAdocao;
	}

}
