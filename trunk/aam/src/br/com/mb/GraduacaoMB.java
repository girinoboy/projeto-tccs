package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.GraduacaoDAO;
import br.com.dto.GraduacaoDTO;


@ManagedBean
public class GraduacaoMB extends GenericoMB implements ModeloMB{
	
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private GraduacaoDTO graduacaoDTO = new GraduacaoDTO();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();

	public GraduacaoMB() {
		try {
			listGraduacaoDTO = graduacaoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(ActionEvent actionEvent) throws Exception {
		graduacaoDAO.save(graduacaoDTO);
		addMessage("salvo.");
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public GraduacaoDTO getGraduacaoDTO() {
		return graduacaoDTO;
	}

	public void setGraduacaoDTO(GraduacaoDTO graduacaoDTO) {
		this.graduacaoDTO = graduacaoDTO;
	}

	public List<GraduacaoDTO> getListGraduacaoDTO() {
		return listGraduacaoDTO;
	}

	public void setListGraduacaoDTO(List<GraduacaoDTO> listGraduacaoDTO) {
		this.listGraduacaoDTO = listGraduacaoDTO;
	}


}
