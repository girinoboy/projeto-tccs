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

import br.com.dao.HistoriaDAO;
import br.com.dao.LinkDAO;
import br.com.dto.HistoriaDTO;
import br.com.dto.LinkDTO;
import br.com.utility.AbstractDataModel;
import br.com.utility.Constantes;
import br.com.utility.HistoriaDataModel;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@SessionScoped
public class HistoriaMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5789131838614438535L;
	private HistoriaDAO historiaDAO = new HistoriaDAO();
	private HistoriaDTO historiaDTO = new HistoriaDTO();
	private List<HistoriaDTO> listHistoriaDTO = new ArrayList<HistoriaDTO>();
	private HistoriaDTO[] listSelectedHistoriaDTO;
	private HistoriaDataModel historiaDataModel; 
	private AbstractDataModel<HistoriaDTO> dojoDataModel;
	private AbstractDataModel<HistoriaDTO> yagyuDataModel;

	private LinkDAO linkDAO = new LinkDAO();
	private LinkDTO linkDTO = new LinkDTO();
	private AbstractDataModel<LinkDTO> linkDataModel;
	private List<LinkDTO> listLinkDTO = new ArrayList<LinkDTO>();
	private LinkDTO[] listSelectedLinkDTO;

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

	public void atualiza(ActionEvent event) throws Exception {
		atualizaLista();

	}

	private void atualizaLista() throws Exception{
		listHistoriaDTO = historiaDAO.list();

		historiaDataModel = new HistoriaDataModel(listHistoriaDTO);

		yagyuDataModel = new AbstractDataModel<HistoriaDTO>(historiaDAO.list(true));
		dojoDataModel = new AbstractDataModel<HistoriaDTO>(historiaDAO.list(false));
	}

	public void reset(ActionEvent event){
		historiaDTO = new HistoriaDTO();
		listLinkDTO = new ArrayList<LinkDTO>();
		linkDataModel = new AbstractDataModel<LinkDTO>();
		//		dojoDataModel = new AbstractDataModel<HistoriaDTO>();
		//		yagyuDataModel = new AbstractDataModel<HistoriaDTO>();
	}

	public void check(SelectEvent event) {
		System.out.println("in check");
		System.out.println(listSelectedHistoriaDTO);
	}

	public void add(ActionEvent actionEvent) throws Exception {
		historiaDTO.setUsuarioDTO(getUserSession());
		historiaDAO.save(historiaDTO);

		//salva todos os links nas respctivas historia
		for (LinkDTO l : listLinkDTO) {
			l.setHistoriaDTO(historiaDTO);
			linkDAO.save(l);
		}

		reset(null);
		atualizaLista();
		addMessage("Operação realizada com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_HISTORIA);

	}

	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(historiaDTO);

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

	public HistoriaDTO getHistoriaDTO() throws Exception {
		try{
			if(historiaDTO!=null && historiaDTO.getListLinkDTO()!=null){
				listLinkDTO = linkDAO.listByIdHistoriaDTO(historiaDTO.getId());
				if(listLinkDTO.size() == historiaDTO.getListLinkDTO().size())
					linkDataModel = new AbstractDataModel<LinkDTO>(listLinkDTO);
			}
		}catch(Exception e){
			linkDataModel = new AbstractDataModel<LinkDTO>(listLinkDTO);
		}
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

	public AbstractDataModel<HistoriaDTO> getDojoDataModel() {
		return dojoDataModel;
	}

	public void setDojoDataModel(AbstractDataModel<HistoriaDTO> dojoDataModel) {
		this.dojoDataModel = dojoDataModel;
	}

	public AbstractDataModel<HistoriaDTO> getYagyuDataModel() {
		return yagyuDataModel;
	}

	public void setYagyuDataModel(AbstractDataModel<HistoriaDTO> yagyuDataModel) {
		this.yagyuDataModel = yagyuDataModel;
	}

}
