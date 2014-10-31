/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.ArrayList;
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
		List<String> listString = new ArrayList<String>();
		listString.add("10000");
		listString.add("20000");
		listString.add("30000");
		listString.add("40000");
		listString.add("50000");
		listString.add("60000");
		listString.add("70000");
		listString.add("80000");
		listString.add("90000");
		List<VeiculoDTO> list = criteria
				.add(Restrictions.in("kmOleo", listString))
				.list();
		return list;
	}

}
