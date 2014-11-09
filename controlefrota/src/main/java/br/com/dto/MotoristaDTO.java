/**
 * 
 */
package br.com.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.com.ind.indCategoria;


/**
 * @author Marcleonio
 *
 */
@Entity
@Audited
@Table(name="motorista")
public class MotoristaDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	@Column(name="data_admissao")
	private Date dataAdmissao;
	private String cnh;
	private Date validade;
	@Enumerated(EnumType.STRING)
	private indCategoria categoria;
	/**
	 * 
	 */
	public MotoristaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public indCategoria getCategoria() {
		return categoria;
	}
	public void setCategoria(indCategoria categoria) {
		this.categoria = categoria;
	}

}
