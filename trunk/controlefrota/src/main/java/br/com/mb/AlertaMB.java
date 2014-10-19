/**
 * 
 */
package br.com.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.dao.VeiculoDAO;
import br.com.dto.VeiculoDTO;

/**
 * @author marcleonio
 *
 */
@ManagedBean
public class AlertaMB extends GenericoMB<VeiculoDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5203224718632004160L;
	private VeiculoDAO veiculoDAO;
	
	@PostConstruct
	public void inicio() throws Exception{
		super.inicio();
	}

	/**
	 * 
	 */
	public AlertaMB() {
		veiculoDAO = new VeiculoDAO();
	}
	
	public void verificaTrocaOleo(){
		List<VeiculoDTO> list = veiculoDAO.verificaTrocaOleo();
		for (VeiculoDTO veiculoDTO : list) {
			addMessage("Exixtem veiculos que precisam trocar o óleo.","Placa: "+veiculoDTO.getPlaca());
		}
	}

}
