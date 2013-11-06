package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.JustificativaDAO;
import br.com.dto.JustificativaDTO;

@ManagedBean
public class JustificativaMB extends GenericoMB implements ModeloMB{
	
	private List<JustificativaDTO> listJustificativa;
	private JustificativaDAO justificativaDAO = new JustificativaDAO();
	private JustificativaDTO justificativaDTO = new JustificativaDTO();

	public JustificativaMB() {
		try {
			listJustificativa = justificativaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		justificativaDAO.save(justificativaDTO);
		justificativaDTO = new JustificativaDTO();
		listJustificativa = justificativaDAO.list();
		addMessage("salvo");
		
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		justificativaDAO.delete(justificativaDTO);
		listJustificativa = justificativaDAO.list();
		
		addMessage("Justificativa excluída com sucesso.");
		
	}
	
	public List<JustificativaDTO> getListJustificativa() {
		return listJustificativa;
	}

	public void setListJustificativa(List<JustificativaDTO> listJustificativa) {
		this.listJustificativa = listJustificativa;
	}


	public JustificativaDTO getJustificativaDTO() {
		return justificativaDTO;
	}


	public void setJustificativaDTO(JustificativaDTO justificativaDTO) {
		this.justificativaDTO = justificativaDTO;
	}

}
