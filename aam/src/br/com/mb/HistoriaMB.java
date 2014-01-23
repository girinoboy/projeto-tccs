/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.com.dao.HistoriaDAO;
import br.com.dto.HistoriaDTO;
import br.com.utility.HistoriaDataModel;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@SessionScoped
public class HistoriaMB extends GenericoMB implements ModeloMB{
	
	private HistoriaDAO historiaDAO = new HistoriaDAO();
	private HistoriaDTO historiaDTO = new HistoriaDTO();
	private List<HistoriaDTO> listHistoriaDTO = new ArrayList<HistoriaDTO>();
	private HistoriaDTO[] listSelectedHistoriaDTO;
	private HistoriaDataModel historiaDataModel; 

	/**
	 * 
	 */
	public HistoriaMB() {
		try {
			atualizaLista();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void atualizaLista() throws Exception{
		listHistoriaDTO = historiaDAO.list();

		historiaDataModel = new HistoriaDataModel(listHistoriaDTO);
	}
	
	public void check(SelectEvent event) {
		System.out.println("in check");
		System.out.println(listSelectedHistoriaDTO);
	}

	public void add(ActionEvent actionEvent) throws Exception {
		historiaDAO.save(historiaDTO);
		historiaDTO = new HistoriaDTO();
		atualizaLista();
		addMessage("salvo");
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		try{
			//System.out.println(listSelectedHistoriaDTO);
			for (HistoriaDTO n : listSelectedHistoriaDTO) {
				historiaDAO.delete(n);
			}
			if(listSelectedHistoriaDTO.length >0){
				addMessage("Apagado.");
			}else{
				addMessage("Nenhum Item Selecionado.");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				atualizaLista();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public HistoriaDTO getHistoriaDTO() {
		return historiaDTO;
	}

	public void setHistoriaDTO(HistoriaDTO historiaDTO) {
		this.historiaDTO = historiaDTO;
	}

	public List<HistoriaDTO> getListHistoriaDTO() {
		return listHistoriaDTO;
	}

	public void setListHistoriaDTO(List<HistoriaDTO> listHistoriaDTO) {
		this.listHistoriaDTO = listHistoriaDTO;
	}

	public HistoriaDTO[] getListSelectedHistoriaDTO() {
		return listSelectedHistoriaDTO;
	}

	public void setListSelectedHistoriaDTO(HistoriaDTO[] listSelectedHistoriaDTO) {
		this.listSelectedHistoriaDTO = listSelectedHistoriaDTO;
	}

	public HistoriaDataModel getHistoriaDataModel() {
		return historiaDataModel;
	}

	public void setHistoriaDataModel(HistoriaDataModel historiaDataModel) {
		this.historiaDataModel = historiaDataModel;
	}

}
