/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.dto.VeiculoDTO;

/**
 * @author marcleonio
 *
 */
@SuppressWarnings("unchecked")
public class VeiculoDAO extends GenericoDAO<VeiculoDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8663969675148891916L;

	/**
	 * 
	 */
	public VeiculoDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<VeiculoDTO> verificaTrocaOleo() {
		List<VeiculoDTO> list = criteria
				.add(Restrictions.ge("kmLitro", "10000"))
				.list();
		return list;
	}

}
