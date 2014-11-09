/**
 * 
 */
package br.com.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.ind.indCategoria;

/**
 * @author marcleonio
 *
 */
@Entity
@Table(name = "categoria")
public class CategoriaDTO extends AbstractDTO{
	
	
	@Enumerated(EnumType.STRING)
	private indCategoria categoria;

	/**
	 * 
	 */
	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
	}

}
