/**
 * 
 */
package br.com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Marcle�nio
 *
 */
@Entity
@Table(name="cidade")
public class CidadeDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	/**
	 * 
	 */
	public CidadeDTO() {
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
        final CidadeDTO other = (CidadeDTO) obj;
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

	public CidadeDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

}
