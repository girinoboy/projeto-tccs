/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.FrequenciaDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.FrequenciaDTO;
import br.com.dto.UsuarioDTO;

/**
 * @author marcleonio
 *
 */
@ManagedBean
@ViewScoped
public class FrequenciaMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9007826101364461566L;
	
	
	private FrequenciaDTO frequenciaDTO;
	private FrequenciaDAO frequenciaDAO;

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
		
	}

	@Override
	public void reset(ActionEvent event) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	public FrequenciaDTO getFrequenciaDTO() {
		return frequenciaDTO;
	}

	public void setFrequenciaDTO(FrequenciaDTO frequenciaDTO) {
		this.frequenciaDTO = frequenciaDTO;
	}

}
