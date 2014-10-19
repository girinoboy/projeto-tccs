/**
 * 
 */
package br.com.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author marcleonio
 *
 */
@Entity
@Table(name = "marca")
public class MarcaDTO extends AbstractDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7482479660999297141L;
	private String nome;

	/**
	 * 
	 */
	public MarcaDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
