package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="solicitacao")
public class SolicitacaoDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private Integer quantidade;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "livro_id", insertable = true, updatable = true, nullable = true)
	private LivroDTO livroDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "editora_id", insertable = true, updatable = true, nullable = true)
	private EditoraDTO editoraDTO;
	
	public SolicitacaoDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LivroDTO getLivroDTO() {
		return livroDTO;
	}

	public void setLivroDTO(LivroDTO livroDTO) {
		this.livroDTO = livroDTO;
	}

	public EditoraDTO getEditoraDTO() {
		return editoraDTO;
	}

	public void setEditoraDTO(EditoraDTO editoraDTO) {
		this.editoraDTO = editoraDTO;
	}

}
