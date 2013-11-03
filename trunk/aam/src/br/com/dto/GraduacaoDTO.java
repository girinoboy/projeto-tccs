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
 * @author Marcleônio
 *
 */
@Entity
@Table(name="graduacao")
public class GraduacaoDTO {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String conhecimentos;

	/**
	 * 
	 */
	public GraduacaoDTO() {
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
        final GraduacaoDTO other = (GraduacaoDTO) obj;
        if (this.id == null){
        	return false;
        }
        if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        return hash;
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

	public String getConhecimentos() {
		return conhecimentos;
	}

	public void setConhecimentos(String conhecimentos) {
		this.conhecimentos = conhecimentos;
	}

}
