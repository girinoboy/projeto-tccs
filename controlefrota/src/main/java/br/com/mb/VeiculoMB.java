/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715654559023119886L;
	private VeiculoDAO veiculoDAO = new VeiculoDAO();
	private VeiculoDTO veiculoDTO = new VeiculoDTO();
	private List<VeiculoDTO> listVeiculoDTO = new ArrayList<VeiculoDTO>();

	/**
	 * @throws Exception 
	 * 
	 */
	public VeiculoMB() throws Exception {
		listVeiculoDTO = veiculoDAO.list();
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
		addMessage(rb.getString("successfullySaved"));
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

	public List<VeiculoDTO> getListVeiculoDTO() {
		return listVeiculoDTO;
	}

	public void setListVeiculoDTO(List<VeiculoDTO> listVeiculoDTO) {
		this.listVeiculoDTO = listVeiculoDTO;
	}

}
