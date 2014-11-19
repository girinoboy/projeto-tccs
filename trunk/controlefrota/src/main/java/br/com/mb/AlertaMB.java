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
	
	public void verificarAlertaQuilometragem(){
		List<VeiculoDTO> list = veiculoDAO.verificarAlertaQuilometragem();
		list.addAll(veiculoDAO.verificarAlertaGasto());
		for (VeiculoDTO veiculoDTO : list) {
			if(veiculoDTO.getId() == null){
				addMessage("Exixtem veiculos que estão abastecendo demais.","Placa: "+veiculoDTO.getPlaca());
			}
			if(veiculoDTO.getKmAtual().equals(veiculoDTO.getKmOleo())){
				addMessage("Exixtem veiculos que precisam trocar o óleo.","Placa: "+veiculoDTO.getPlaca());
			}
			if(veiculoDTO.getKmAtual().equals(veiculoDTO.getKmPneu())){
				addMessage("Exixtem veiculos que precisam trocar o Pneu.","Placa: "+veiculoDTO.getPlaca());
			}
			if(veiculoDTO.getKmAtual().equals(veiculoDTO.getKmRevisao())){
				addMessage("Exixtem veiculos que precisam de revisão.","Placa: "+veiculoDTO.getPlaca());
			}
		}
	}

}
