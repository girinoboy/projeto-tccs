/**
 * 
 */
package br.com.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import org.hibernate.annotations.Cascade;

/**
 * @author Marcleônio
 *
 */
@Entity
@Table(name = "usuario")
public class UsuarioDTO extends AbstractDTO{


	private String login;
	private String senha;
	private String nome;
	private String tema;
	private String telefone;
	private String endereco;
	private String email;
	private String cpf;
	private String observacao;
	private String sexo;
	@Column(name ="data_nascimento")
	private Date dataNascimento;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "anexos_id", insertable = true, updatable = true, nullable = true)
	private AnexoDTO anexoDTO;
	@OneToMany(targetEntity=AnexoDTO.class, mappedBy = "usuarioDTO", fetch = FetchType.LAZY)
	private List<AnexoDTO> listAnexoDTO;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "perfil_id", insertable = true, updatable = true, nullable = true)
	private PerfilDTO perfilDTO;

	/**
	 * 
	 */
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}


	public UsuarioDTO(String search) {
		this.nome = search;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getTema() {
		return tema;
	}


	public void setTema(String tema) {
		this.tema = tema;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public Integer getIdade(){

		Calendar dateOfBirth = new GregorianCalendar();
		if(dataNascimento!=null){
			dateOfBirth.setTime(dataNascimento);
		}
		// Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		// Obtém a idade baseado no ano
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;

	}

	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public List<AnexoDTO> getListAnexoDTO() {
		return listAnexoDTO;
	}


	public void setListAnexoDTO(List<AnexoDTO> listAnexoDTO) {
		this.listAnexoDTO = listAnexoDTO;
	}


	public PerfilDTO getPerfilDTO() {
		if(perfilDTO == null){
			perfilDTO = new PerfilDTO();
		}
		return perfilDTO;
	}


	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

}
