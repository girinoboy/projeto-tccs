package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.EditoraDAO;
import br.com.dto.EditoraDTO;

@ManagedBean
public class EditoraMB extends GenericoMB implements ModeloMB{
	
	private List<EditoraDTO> listEditora;
	private EditoraDAO editoraDAO = new EditoraDAO();
	private EditoraDTO editoraDTO = new EditoraDTO();

	public EditoraMB() {
		try {
			listEditora = editoraDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		editoraDAO.save(editoraDTO);
		editoraDTO = new EditoraDTO();
		addMessage("salvo");
		
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public List<EditoraDTO> getListEditora() {
		return listEditora;
	}

	public void setListEditora(List<EditoraDTO> listEditora) {
		this.listEditora = listEditora;
	}


	public EditoraDTO getEditoraDTO() {
		return editoraDTO;
	}


	public void setEditoraDTO(EditoraDTO editoraDTO) {
		this.editoraDTO = editoraDTO;
	}

}
