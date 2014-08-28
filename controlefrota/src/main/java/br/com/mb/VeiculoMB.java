/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.VeiculoDAO;
import br.com.dto.VeiculoDTO;

/**
 * @author marcleonio
 *
 */
@ManagedBean
@SessionScoped
public class VeiculoMB extends GenericoMB implements ModeloMB{
	
	VeiculoDAO veiculoDAO = new VeiculoDAO();

	/**
	 * 
	 */
	private static final long serialVersionUID = -6715654559023119886L;
	
	private VeiculoDTO veiculoDTO = new VeiculoDTO();

	/**
	 * 
	 */
	public VeiculoMB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(ActionEvent event) {
		veiculoDTO = new VeiculoDTO();
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		veiculoDAO.save(veiculoDTO);
		addMessage("Salvo.");
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public VeiculoDTO getVeiculoDTO() {
		return veiculoDTO;
	}

	public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
		this.veiculoDTO = veiculoDTO;
	}

}
