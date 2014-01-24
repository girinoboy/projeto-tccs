package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.com.dao.GraduacaoDAO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.LinkDTO;
import br.com.dto.NoticiaDTO;
import br.com.dto.TecnicaDTO;
import br.com.utility.AbstractDataModel;
import br.com.utility.GraduacaoDataModel;


@ManagedBean
@SessionScoped
public class GraduacaoMB extends GenericoMB implements ModeloMB{
	
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private GraduacaoDTO graduacaoDTO = new GraduacaoDTO();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();
	private GraduacaoDataModel graduacaoDataModel;
	private GraduacaoDTO[] listSelectedGraduacaoDTO;
	private TecnicaDTO[] listSelectedTecnicaDTO;
	
	public GraduacaoMB() {
		try {
			listGraduacaoDTO = graduacaoDAO.list();
			//getDynamicImage();
			graduacaoDataModel = new GraduacaoDataModel(listGraduacaoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reset(ActionEvent event){
		graduacaoDTO = new GraduacaoDTO();
	}
	
	public void check(SelectEvent event) {
		System.out.println("in check");
		System.out.println(listSelectedGraduacaoDTO);
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

	public GraduacaoDataModel getGraduacaoDataModel() {
		return graduacaoDataModel;
	}

	public void setGraduacaoDataModel(GraduacaoDataModel graduacaoDataModel) {
		this.graduacaoDataModel = graduacaoDataModel;
	}

	public GraduacaoDTO[] getListSelectedGraduacaoDTO() {
		return listSelectedGraduacaoDTO;
	}

	public void setListSelectedGraduacaoDTO(GraduacaoDTO[] listSelectedGraduacaoDTO) {
		this.listSelectedGraduacaoDTO = listSelectedGraduacaoDTO;
	}

	public TecnicaDTO[] getListSelectedTecnicaDTO() {
		return listSelectedTecnicaDTO;
	}

	public void setListSelectedTecnicaDTO(TecnicaDTO[] listSelectedTecnicaDTO) {
		this.listSelectedTecnicaDTO = listSelectedTecnicaDTO;
	}


}
