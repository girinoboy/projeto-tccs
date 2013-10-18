/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.EscolaDAO;
import br.com.dto.EscolaDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
public class EscolaMB extends GenericoMB implements ModeloMB{
	
	EscolaDTO escolaDTO = new EscolaDTO();
	EscolaDAO escolaDAO = new EscolaDAO();
	List<EscolaDTO> listEscola = new ArrayList<EscolaDTO>();

	/**
	 * 
	 */
	public EscolaMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void add(ActionEvent actionEvent) throws Exception {
		escolaDTO = escolaDAO.save(escolaDTO);
		
		addMessage("Salvo.");
		listEscola = escolaDAO.list();
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void del(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
	}
	
	public EscolaDTO getEscolaDTO() {
		return escolaDTO;
	}

	public void setEscolaDTO(EscolaDTO escolaDTO) {
		this.escolaDTO = escolaDTO;
	}

	public List<EscolaDTO> getListEscola() {
		return listEscola;
	}

	public void setListEscola(List<EscolaDTO> listEscola) {
		this.listEscola = listEscola;
	}

}
