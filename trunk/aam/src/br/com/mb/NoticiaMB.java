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

import br.com.dao.NoticiaDAO;
import br.com.dto.NoticiaDTO;
import br.com.utility.NoticiaDataModel;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@SessionScoped
public class NoticiaMB extends GenericoMB implements ModeloMB{
	
	private NoticiaDAO noticiaDAO = new NoticiaDAO();
	private NoticiaDTO noticiaDTO = new NoticiaDTO();
	private List<NoticiaDTO> listNoticiaDTO = new ArrayList<NoticiaDTO>();
	private NoticiaDTO[] listSelectedNoticiaDTO;
	private NoticiaDataModel noticiaDataModel; 

	/**
	 * 
	 */
	public NoticiaMB() {
		try {
			listNoticiaDTO = noticiaDAO.list();

			noticiaDataModel = new NoticiaDataModel(listNoticiaDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void check(SelectEvent event) {
		System.out.println("in check");
		System.out.println(listSelectedNoticiaDTO);
	}

	public void add(ActionEvent actionEvent) throws Exception {
		noticiaDTO.setUsuarioDTO(getUserSession());
		noticiaDAO.save(noticiaDTO);
		noticiaDTO = new NoticiaDTO();
		addMessage("salvo");
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(noticiaDTO);
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		try{
			//System.out.println(listSelectedNoticiaDTO);
			for (NoticiaDTO n : listSelectedNoticiaDTO) {
				noticiaDAO.delete(n);
			}
			if(listSelectedNoticiaDTO.length >0){
				addMessage("Apagado.");
			}else{
				addMessage("Nenhum Item Selecionado.");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				listNoticiaDTO = noticiaDAO.list();

				noticiaDataModel = new NoticiaDataModel(listNoticiaDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public NoticiaDTO getNoticiaDTO() {
		return noticiaDTO;
	}

	public void setNoticiaDTO(NoticiaDTO noticiaDTO) {
		this.noticiaDTO = noticiaDTO;
	}

	public List<NoticiaDTO> getListNoticiaDTO() {
		return listNoticiaDTO;
	}

	public void setListNoticiaDTO(List<NoticiaDTO> listNoticiaDTO) {
		this.listNoticiaDTO = listNoticiaDTO;
	}

	public NoticiaDTO[] getListSelectedNoticiaDTO() {
		return listSelectedNoticiaDTO;
	}

	public void setListSelectedNoticiaDTO(NoticiaDTO[] listSelectedNoticiaDTO) {
		this.listSelectedNoticiaDTO = listSelectedNoticiaDTO;
	}

	public NoticiaDataModel getNoticiaDataModel() {
		return noticiaDataModel;
	}

	public void setNoticiaDataModel(NoticiaDataModel noticiaDataModel) {
		this.noticiaDataModel = noticiaDataModel;
	}

}
