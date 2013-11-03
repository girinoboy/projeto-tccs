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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@Table(name="usuario")
public class UsuarioDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String usuario;
	private String senha;
	private String nome;
	@Column(name="data_nascimento",columnDefinition="date")
	private Date dataNascimento;
	private String cpf;
	private String email;
	private String tema;
	private Boolean ativoInativo;
	private String sexo;
	private String rg;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "graduacao_id", insertable = true, updatable = true, nullable = true)
	private GraduacaoDTO graduacaoDTO;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "anexos_id", insertable = true, updatable = true, nullable = true)
	private AnexoDTO anexoDTO;
	@OneToMany(targetEntity=AnexoDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY)
	private List<AnexoDTO> listAnexoDTO;
	
	/**
	 * 
	 */
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioDTO other = (UsuarioDTO) obj;
        if (this.id == null){
        	return false;
        }
        if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
	
	public UsuarioDTO(String nome, int i) {
		this.id = i;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Boolean getAtivoInativo() {
		return ativoInativo;
	}

	public void setAtivoInativo(Boolean ativoInativo) {
		this.ativoInativo = ativoInativo;
	}

	public GraduacaoDTO getGraduacaoDTO() {
		if(graduacaoDTO==null)
			graduacaoDTO= new GraduacaoDTO();
		return graduacaoDTO;
	}

	public void setGraduacaoDTO(GraduacaoDTO graduacaoDTO) {
		this.graduacaoDTO = graduacaoDTO;
	}

	public AnexoDTO getAnexoDTO() {
		return anexoDTO;
	}

	public void setAnexoDTO(AnexoDTO anexoDTO) {
		this.anexoDTO = anexoDTO;
	}

	public List<AnexoDTO> getListAnexoDTO() {
		return listAnexoDTO;
	}

	public void setListAnexoDTO(List<AnexoDTO> listAnexoDTO) {
		this.listAnexoDTO = listAnexoDTO;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}



}
