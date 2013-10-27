package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.ResuldoAvaliacaoDAO;
import br.com.dto.ResuldoAvaliacaoDTO;

@ManagedBean
public class ResuldoAvaliacaoMB extends GenericoMB implements ModeloMB{
	
	private ResuldoAvaliacaoDTO resuldoAvaliacaoDTO = new ResuldoAvaliacaoDTO();
	private ResuldoAvaliacaoDAO resuldoAvaliacaoDAO = new ResuldoAvaliacaoDAO();
	private List<ResuldoAvaliacaoDTO> listResuldoAvaliacaoDTO = new ArrayList<ResuldoAvaliacaoDTO>();

	public ResuldoAvaliacaoMB() {
		try {
			listResuldoAvaliacaoDTO = resuldoAvaliacaoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public ResuldoAvaliacaoDTO getResuldoAvaliacaoDTO() {
		return resuldoAvaliacaoDTO;
	}

	public void setResuldoAvaliacaoDTO(ResuldoAvaliacaoDTO resuldoAvaliacaoDTO) {
		this.resuldoAvaliacaoDTO = resuldoAvaliacaoDTO;
	}

	public List<ResuldoAvaliacaoDTO> getListResuldoAvaliacaoDTO() {
		return listResuldoAvaliacaoDTO;
	}

	public void setListResuldoAvaliacaoDTO(
			List<ResuldoAvaliacaoDTO> listResuldoAvaliacaoDTO) {
		this.listResuldoAvaliacaoDTO = listResuldoAvaliacaoDTO;
	}

}
