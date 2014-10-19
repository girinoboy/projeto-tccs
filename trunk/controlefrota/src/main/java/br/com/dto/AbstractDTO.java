/**
 * 
 */
package br.com.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author marcleonio.medeiros
 *
 */
@MappedSuperclass 
public abstract class AbstractDTO implements Serializable { 

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	public Integer getId() { 
		return id; 
	} 

	public void setId(Integer id) { 
		this.id = id; 
	} 

	@Override 
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		result = prime * result + ((id == null) ? 0 : id.hashCode()); 
		return result; 
	} 

	@Override 
	public boolean equals(Object obj) { 
		if (this == obj) 
			return true; 
		if (obj == null) 
			return false; 
		if (getClass() != obj.getClass()) 
			return false; 

		return (obj instanceof AbstractDTO) ? (this.getId() == null ? this == obj 
				: this.getId().equals(((AbstractDTO) obj).getId())) 
				: false; 
	} 

} 
