/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.VeiculoDAO;
import br.com.dto.VeiculoDTO;
import br.com.ind.IndTipo;
import br.com.ind.IndTipoCombustivel;

/**
 * @author marcleonio
 *
 */
@ManagedBean
@SessionScoped
public class VeiculoMB extends GenericoMB<VeiculoDTO> implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6715654559023119886L;
	private VeiculoDAO veiculoDAO = new VeiculoDAO();
	private VeiculoDTO veiculoDTO = new VeiculoDTO();
	private List<VeiculoDTO> listVeiculoDTO = new ArrayList<VeiculoDTO>();
	private List<IndTipoCombustivel> listIndTipoCombustivel;
	private List<IndTipo> listTipo;

	/**
	 * @throws Exception 
	 * 
	 */
	public VeiculoMB() throws Exception {
		atualiza(null);
		listIndTipoCombustivel = Arrays.asList(IndTipoCombustivel.values());
		listTipo = Arrays.asList(IndTipo.values());
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		listVeiculoDTO = veiculoDAO.list();
		veiculoDTO = new VeiculoDTO();

	}

	@Override
	public void reset(ActionEvent event) {
		veiculoDTO = new VeiculoDTO();
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		veiculoDAO.save(veiculoDTO);
		addMessage(rb.getString("successfullySaved"));
		atualiza(null);
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		abstractDAO.delete(abstractDTO);
		addMessage(rb.getString("successfullyDeleted"));
		atualiza(null);
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

	public List<IndTipoCombustivel> getListIndTipoCombustivel() {
		return listIndTipoCombustivel;
	}

	public void setListIndTipoCombustivel(
			List<IndTipoCombustivel> listIndTipoCombustivel) {
		this.listIndTipoCombustivel = listIndTipoCombustivel;
	}

	public List<IndTipo> getListTipo() {
		return listTipo;
	}

	public void setListTipo(List<IndTipo> listTipo) {
		this.listTipo = listTipo;
	}

}
