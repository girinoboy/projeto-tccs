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
@Table(name = "veiculo")
public class VeiculoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6037278747810425918L;

	public VeiculoDTO(){

	}

}
