/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.com.dao.CampeonatoDAO;
import br.com.dao.LinkDAO;
import br.com.dao.ResultadoDAO;
import br.com.dto.CampeonatoDTO;
import br.com.dto.LinkDTO;
import br.com.dto.ResultadoDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.AbstractDataModel;
import br.com.utility.Constantes;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@SessionScoped
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

//		listCampeonatoDTO = new ArrayList<CampeonatoDTO>();
//		campeonatoDataModel = new AbstractDataModel<CampeonatoDTO>(listCampeonatoDTO);

		listLinkDTO = new ArrayList<LinkDTO>();
		linkDataModel = new AbstractDataModel<LinkDTO>();

		listResultadoDTO = new ArrayList<ResultadoDTO>();
		resultadoDataModel = new AbstractDataModel<ResultadoDTO>();

	}
	
	public void handleSelect(SelectEvent event) {  

		try {
			resultadoDTO.setUsuarioDTO((UsuarioDTO)event.getObject()) ;

			addMessage("Graduação:" + resultadoDTO.getUsuarioDTO().getGraduacaoDTO().getNome().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void check(SelectEvent event) {
		System.out.println("in check");
	}

	public void add(ActionEvent actionEvent) throws Exception {
		//		campeonatoDTO.setUsuarioDTO(getUserSession());
		campeonatoDTO = campeonatoDAO.save(campeonatoDTO);
		//salva todos os links nas respctivas noticias
		for (LinkDTO l : listLinkDTO) {
			l.setCampeonatoDTO(campeonatoDTO);
			linkDAO.save(l);
		}
		//salva todos os resultados nas respctivas noticias
		for (ResultadoDTO r : listResultadoDTO) {
			r.setCampeonatoDTO(campeonatoDTO);
			resultadoDAO.save(r);
		}

		reset(actionEvent);
		atualiza(actionEvent);

		addMessage("Operação realizada com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_CAMPEONATO);

	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	public void del(ActionEvent actionEvent) throws Exception {
		try{
			//System.out.println(listSelectedNoticiaDTO);
			for (CampeonatoDTO n : listSelectedCampeonatoDTO) {
				campeonatoDAO.delete(n);
			}
			if(listSelectedCampeonatoDTO.length >0){
				addMessage("Apagado.");
			}else{
				addMessage("Nenhum Item Selecionado.");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				atualiza(actionEvent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

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

	public void addResultado(ActionEvent actionEvent) throws Exception{
		if(listResultadoDTO == null){
			listResultadoDTO = new ArrayList<ResultadoDTO>();
		}
		listResultadoDTO.add(resultadoDAO.save(resultadoDTO));
		resultadoDataModel = new AbstractDataModel<ResultadoDTO>(listResultadoDTO);
	}

	public void delResultado(ActionEvent actionEvent) throws Exception{
		for (ResultadoDTO l : listSelectedResultadoDTO) {
			resultadoDAO.delete(l);
			listResultadoDTO.remove(l);
		}
		resultadoDataModel = new AbstractDataModel<ResultadoDTO>(listResultadoDTO);
	}


	public CampeonatoDTO getCampeonatoDTO() {
		try {
			if(campeonatoDTO!=null && campeonatoDTO.getListResultadoDTO()!=null){
				List<ResultadoDTO> listResultadoDTO = resultadoDAO.listByIdCampeonatoDTO(campeonatoDTO.getId());
				if(listResultadoDTO.size() > this.listResultadoDTO.size()){
					this.listResultadoDTO = listResultadoDTO;
				}
			}
			if(campeonatoDTO!=null && campeonatoDTO.getListLinkDTO()!=null){
				List<LinkDTO> listLinkDTO = linkDAO.listByIdCampeonatoDTO(campeonatoDTO.getId());
				if(listLinkDTO.size() > this.listLinkDTO.size()){
					this.listLinkDTO = listLinkDTO;
				}
			}
			if(listLinkDTO.size() == campeonatoDTO.getListLinkDTO().size())
				linkDataModel = new AbstractDataModel<LinkDTO>(listLinkDTO);
			if(listResultadoDTO.size() == campeonatoDTO.getListResultadoDTO().size())
				campeonatoDTO.setListResultadoDTO(listResultadoDTO);
		}catch(Exception e){
			linkDataModel = new AbstractDataModel<LinkDTO>(listLinkDTO);
			resultadoDataModel = new AbstractDataModel<ResultadoDTO>(listResultadoDTO);
		}
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
