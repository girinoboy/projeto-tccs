/**
 * 
 */
package br.com.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import br.com.dao.VeiculoDAO;
import br.com.dto.VeiculoDTO;

/**
 * @author marcleonio
 *
 */
@ManagedBean
public class AlertaMB extends GenericoMB {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5203224718632004160L;
	private VeiculoDAO veiculoDAO;
	
	@PostConstruct
	public void inicio(){
		super.inicio();
		veiculoDAO = new VeiculoDAO();
	}

	/**
	 * 
	 */
	public AlertaMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void verificaTrocaOleo(){
		List<VeiculoDTO> list = veiculoDAO.verificaTrocaOleo();
		if(list != null && !list.isEmpty()){
			addMessage("Exixtem veiculos que precisam troca o óleo.");
		}
	}

}
