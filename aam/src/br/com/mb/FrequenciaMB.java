/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.FrequenciaDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.CampeonatoDTO;
import br.com.dto.FrequenciaDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.AbstractDataModel;

/**
 * @author marcleonio
 *
 */
@ManagedBean
@SessionScoped
public class FrequenciaMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9007826101364461566L;
	
	
	private FrequenciaDTO frequenciaDTO = new FrequenciaDTO();
	private FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
	private AbstractDataModel<FrequenciaDTO> frequenciaDataModel;
	private FrequenciaDTO[] listSelectedFrequenciaDTO;
	

	/**
	 * 
	 */
	public FrequenciaMB() {
		try {
			frequenciaDAO = new FrequenciaDAO();
			atualiza(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		frequenciaDTO = new FrequenciaDTO();
		frequenciaDataModel = new AbstractDataModel<FrequenciaDTO>(frequenciaDAO.list());
		
	}

	@Override
	public void reset(ActionEvent event) {
		frequenciaDTO = new FrequenciaDTO();
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		frequenciaDAO.save(frequenciaDTO);
		addMessage("Operação realizada com sucesso!");
		reset(null);
		atualiza(null);
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		frequenciaDAO.delete(frequenciaDTO);
		
	}

	public FrequenciaDTO getFrequenciaDTO() {
		return frequenciaDTO;
	}

	public void setFrequenciaDTO(FrequenciaDTO frequenciaDTO) {
		this.frequenciaDTO = frequenciaDTO;
	}

	public AbstractDataModel<FrequenciaDTO> getFrequenciaDataModel() {
		return frequenciaDataModel;
	}

	public void setFrequenciaDataModel(
			AbstractDataModel<FrequenciaDTO> frequenciaDataModel) {
		this.frequenciaDataModel = frequenciaDataModel;
	}

	public FrequenciaDTO[] getListSelectedFrequenciaDTO() {
		return listSelectedFrequenciaDTO;
	}

	public void setListSelectedFrequenciaDTO(
			FrequenciaDTO[] listSelectedFrequenciaDTO) {
		this.listSelectedFrequenciaDTO = listSelectedFrequenciaDTO;
	}

}
