package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.LocalDAO;
import br.com.dto.LocalDTO;

@ManagedBean
@SessionScoped
public class LocalMB extends GenericoMB implements ModeloMB{

	private List<LocalDTO> listLocal;
	private LocalDAO localDAO = new LocalDAO();
	private LocalDTO localDTO = new LocalDTO();

	public LocalMB() {
		try {
			listLocal = localDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		localDAO.save(localDTO);
		localDTO = new LocalDTO();
		listLocal = localDAO.list();
		addMessage("salvo");

	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void del(ActionEvent actionEvent) {
		try {
			localDAO.delete(localDTO);
			listLocal = localDAO.list();
		} catch (Exception e) {
			addMessage("Erro ao apagar local: "+e.getCause());
			e.printStackTrace();
		}

	}
	
	@Override
	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reset(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	public List<LocalDTO> getListLocal() {
		return listLocal;
	}

	public void setListLocal(List<LocalDTO> listLocal) {
		this.listLocal = listLocal;
	}


	public LocalDTO getLocalDTO() {
		return localDTO;
	}


	public void setLocalDTO(LocalDTO localDTO) {
		this.localDTO = localDTO;
	}

}
