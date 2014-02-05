/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.com.dao.CampeonatoDAO;
import br.com.dao.LinkDAO;
import br.com.dao.ResultadoDAO;
import br.com.dto.CampeonatoDTO;
import br.com.dto.LinkDTO;
import br.com.dto.ResultadoDTO;
import br.com.utility.AbstractDataModel;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@RequestScoped
public class CampeonatoMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4722198864111611255L;
	
	private CampeonatoDTO campeonatoDTO = new CampeonatoDTO();
	private CampeonatoDAO campeonatoDAO = new CampeonatoDAO();
	private List<CampeonatoDTO> listCampeonatoDTO = new ArrayList<CampeonatoDTO>();
	private AbstractDataModel<CampeonatoDTO> campeonatoDataModel;
	private CampeonatoDTO[] listSelectedCampeonatoDTO;
	
	private LinkDAO linkDAO = new LinkDAO();
	private LinkDTO linkDTO = new LinkDTO();
	private AbstractDataModel<LinkDTO> linkDataModel;
	private List<LinkDTO> listLinkDTO = new ArrayList<LinkDTO>();
	private LinkDTO[] listSelectedLinkDTO;
	
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	private ResultadoDTO resultadoDTO = new ResultadoDTO();
	private AbstractDataModel<ResultadoDTO> resultadoDataModel;
	private List<ResultadoDTO> listResultadoDTO = new ArrayList<ResultadoDTO>();
	private ResultadoDTO[] listSelectedResultadoDTO;

	/**
	 * 
	 */
	public CampeonatoMB() {
		try {
			atualiza(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualiza(ActionEvent event) throws Exception {
		listCampeonatoDTO = campeonatoDAO.list();

		campeonatoDataModel = new AbstractDataModel<CampeonatoDTO>(listCampeonatoDTO);
		
	}

	public void reset(ActionEvent event) {
		campeonatoDTO = new CampeonatoDTO();
		listCampeonatoDTO = new ArrayList<CampeonatoDTO>();
		campeonatoDataModel = new AbstractDataModel<CampeonatoDTO>(listCampeonatoDTO);
		
	}
	
	public void check(SelectEvent event) {
		System.out.println("in check");
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
	
	public void addLink(ActionEvent actionEvent) throws Exception{
		if(listLinkDTO == null){
			listLinkDTO = new ArrayList<LinkDTO>();
		}
		listLinkDTO.add(linkDAO.save(linkDTO));
		linkDataModel = new AbstractDataModel<LinkDTO>(listLinkDTO);
	}

	public void delLink(ActionEvent actionEvent) throws Exception{
		for (LinkDTO l : listSelectedLinkDTO) {
			linkDAO.delete(l);
			listLinkDTO.remove(l);
		}
		linkDataModel = new AbstractDataModel<LinkDTO>(listLinkDTO);
	}

	public CampeonatoDTO getCampeonatoDTO() {
		return campeonatoDTO;
	}

	public void setCampeonatoDTO(CampeonatoDTO campeonatoDTO) {
		this.campeonatoDTO = campeonatoDTO;
	}

	public List<CampeonatoDTO> getListCampeonatoDTO() {
		return listCampeonatoDTO;
	}

	public void setListCampeonatoDTO(List<CampeonatoDTO> listCampeonatoDTO) {
		this.listCampeonatoDTO = listCampeonatoDTO;
	}

	public AbstractDataModel<CampeonatoDTO> getCampeonatoDataModel() {
		return campeonatoDataModel;
	}

	public void setCampeonatoDataModel(
			AbstractDataModel<CampeonatoDTO> campeonatoDataModel) {
		this.campeonatoDataModel = campeonatoDataModel;
	}

	public CampeonatoDTO[] getListSelectedCampeonatoDTO() {
		return listSelectedCampeonatoDTO;
	}

	public void setListSelectedCampeonatoDTO(
			CampeonatoDTO[] listSelectedCampeonatoDTO) {
		this.listSelectedCampeonatoDTO = listSelectedCampeonatoDTO;
	}

	public LinkDTO getLinkDTO() {
		return linkDTO;
	}

	public void setLinkDTO(LinkDTO linkDTO) {
		this.linkDTO = linkDTO;
	}

	public AbstractDataModel<LinkDTO> getLinkDataModel() {
		return linkDataModel;
	}

	public void setLinkDataModel(AbstractDataModel<LinkDTO> linkDataModel) {
		this.linkDataModel = linkDataModel;
	}

	public List<LinkDTO> getListLinkDTO() {
		return listLinkDTO;
	}

	public void setListLinkDTO(List<LinkDTO> listLinkDTO) {
		this.listLinkDTO = listLinkDTO;
	}

	public LinkDTO[] getListSelectedLinkDTO() {
		return listSelectedLinkDTO;
	}

	public void setListSelectedLinkDTO(LinkDTO[] listSelectedLinkDTO) {
		this.listSelectedLinkDTO = listSelectedLinkDTO;
	}

	public ResultadoDTO getResultadoDTO() {
		return resultadoDTO;
	}

	public void setResultadoDTO(ResultadoDTO resultadoDTO) {
		this.resultadoDTO = resultadoDTO;
	}

	public AbstractDataModel<ResultadoDTO> getResultadoDataModel() {
		return resultadoDataModel;
	}

	public void setResultadoDataModel(
			AbstractDataModel<ResultadoDTO> resultadoDataModel) {
		this.resultadoDataModel = resultadoDataModel;
	}

	public ResultadoDTO[] getListSelectedResultadoDTO() {
		return listSelectedResultadoDTO;
	}

	public void setListSelectedResultadoDTO(ResultadoDTO[] listSelectedResultadoDTO) {
		this.listSelectedResultadoDTO = listSelectedResultadoDTO;
	}

	public List<ResultadoDTO> getListResultadoDTO() {
		return listResultadoDTO;
	}

	public void setListResultadoDTO(List<ResultadoDTO> listResultadoDTO) {
		this.listResultadoDTO = listResultadoDTO;
	}

}
