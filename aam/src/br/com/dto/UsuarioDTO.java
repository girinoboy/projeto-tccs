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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

/**
 * @author marcleonio.medeiros
 *
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true, dynamicInsert=true)
//@DynamicUpdate
@Table(name="usuario")
public class UsuarioDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7036085825781563231L;
//	@Id 
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Integer id;
	private String usuario;
	private String senha;
	private String nome;
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;
	private String cpf;
	private String email;
	private String tema;
	@Column(name="ativo_inativo")
	private Boolean ativoInativo;
	private String sexo;
	private String rg;
	private String endereco;
	private String telefone;
	private Double desconto;
	private Boolean excluido;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@Cascade({org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(name = "graduacao_id", insertable = true, updatable = true, nullable = true)
	private GraduacaoDTO graduacaoDTO;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "anexos_id", insertable = true, updatable = true, nullable = true)
	private AnexoDTO anexoDTO;
	@OneToMany(targetEntity=AnexoDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<AnexoDTO> listAnexoDTO;
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)//uma pessoa so tem um pagamento por mes
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name="financeiro_id", referencedColumnName = "id", insertable = true, updatable = true, nullable = true)
	private FinanceiroDTO financeiroDTO;
	@OneToMany(targetEntity=FinanceiroDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<FinanceiroDTO> listFinanceiroDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@Cascade({org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(name = "perfil_id", insertable = true, updatable = true, nullable = true)
	private PerfilDTO perfilDTO;
	@OneToMany(targetEntity=ResultadoDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<ResultadoDTO> listResultadoDTO;
	@OneToMany(targetEntity=ResultadoAvaliacaoDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY, cascade= {CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<ResultadoAvaliacaoDTO> listResultadoAvaliacaoDTO;
	
	
	/**
	 * 
	 */
	public UsuarioDTO() {
		setExcluido(false);
	}
	
//	@Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final UsuarioDTO other = (UsuarioDTO) obj;
//        if (this.id == null){
//        	return false;
//        }
//        if (!this.id.equals(other.id)) {
//            return false;
//        }
//        return true;
//    }
//	
//	public UsuarioDTO(String nome, int i) {
//		this.id = i;
//		this.nome = nome;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

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
		if(tema==null){
			tema ="flick";
		}
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
		return graduacaoDTO;
	}

	public void setGraduacaoDTO(GraduacaoDTO graduacaoDTO) {
		this.graduacaoDTO = graduacaoDTO;
	}

	public AnexoDTO getAnexoDTO() {
		if(anexoDTO==null){
			anexoDTO = new AnexoDTO();
		}
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

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public FinanceiroDTO getFinanceiroDTO() {
		if(financeiroDTO ==null){
			financeiroDTO = new FinanceiroDTO();
		}
		return financeiroDTO;
	}

	public void setFinanceiroDTO(FinanceiroDTO financeiroDTO) {
		this.financeiroDTO = financeiroDTO;
	}

	public List<FinanceiroDTO> getListFinanceiroDTO() {
		return listFinanceiroDTO;
	}

	public void setListFinanceiroDTO(List<FinanceiroDTO> listFinanceiroDTO) {
		this.listFinanceiroDTO = listFinanceiroDTO;
	}

	public PerfilDTO getPerfilDTO() {
		return perfilDTO;
	}

	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public Boolean getExcluido() {
		if(excluido == null){
			excluido= false;
		}
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

	public List<ResultadoDTO> getListResultadoDTO() {
		return listResultadoDTO;
	}

	public void setListResultadoDTO(List<ResultadoDTO> listResultadoDTO) {
		this.listResultadoDTO = listResultadoDTO;
	}

	public List<ResultadoAvaliacaoDTO> getListResultadoAvaliacaoDTO() {
		return listResultadoAvaliacaoDTO;
	}

	public void setListResultadoAvaliacaoDTO(
			List<ResultadoAvaliacaoDTO> listResultadoAvaliacaoDTO) {
		this.listResultadoAvaliacaoDTO = listResultadoAvaliacaoDTO;
	}



}
