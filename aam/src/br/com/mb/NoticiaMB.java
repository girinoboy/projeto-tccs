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

import br.com.dao.LinkDAO;
import br.com.dao.NoticiaDAO;
import br.com.dto.LinkDTO;
import br.com.dto.NoticiaDTO;
import br.com.utility.AbstractDataModel;
import br.com.utility.Constantes;
import br.com.utility.NoticiaDataModel;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@SessionScoped
public class NoticiaMB extends GenericoMB implements ModeloMB{

	private static final long serialVersionUID = -3399714918960475131L;
	private NoticiaDAO noticiaDAO = new NoticiaDAO();
	private NoticiaDTO noticiaDTO = new NoticiaDTO();
	private List<NoticiaDTO> listNoticiaDTO = new ArrayList<NoticiaDTO>();
	private NoticiaDTO[] listSelectedNoticiaDTO;
	private NoticiaDataModel noticiaDataModel;
	private LinkDAO linkDAO = new LinkDAO();
	private LinkDTO linkDTO = new LinkDTO();
	private AbstractDataModel<LinkDTO> linkDataModel;
	private List<LinkDTO> listLinkDTO = new ArrayList<LinkDTO>();
	private LinkDTO[] listSelectedLinkDTO;

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

	public void atualiza(ActionEvent event) throws Exception{
		listNoticiaDTO = noticiaDAO.list();

		noticiaDataModel = new NoticiaDataModel(listNoticiaDTO);
	}

	public void reset(ActionEvent event){
		noticiaDTO = new NoticiaDTO();
		listLinkDTO = new ArrayList<LinkDTO>();
		linkDataModel = new AbstractDataModel<LinkDTO>();
	}

	public void check(SelectEvent event) {
		System.out.println("in check");
		System.out.println(listSelectedNoticiaDTO);
		System.out.println(listSelectedLinkDTO);
	}

	public void handleDateSelect(SelectEvent event) {  
		//        FacesContext facesContext = FacesContext.getCurrentInstance();  
		//        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
		//        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		noticiaDTO.setSemData(false);
	} 

	public void add(ActionEvent actionEvent) throws Exception {

		noticiaDTO.setUsuarioDTO(getUserSession());
		noticiaDTO = noticiaDAO.save(noticiaDTO);
		//salva todos os links nas respctivas noticias
		for (LinkDTO l : listLinkDTO) {
			l.setNoticiaDTO(noticiaDTO);
			linkDAO.save(l);
		}

		reset(actionEvent);
		atualiza(actionEvent);

		addMessage("Opera��o realizada com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);

	}

	public void edit(ActionEvent actionEvent) throws Exception {
		//		reset(actionEvent);
		System.out.println(noticiaDTO);
		//		if(noticiaDTO!=null && noticiaDTO.getListLinkDTO()!=null){
		//			listLinkDTO = noticiaDTO.getListLinkDTO();
		//			linkDataModel = new AbstractDataModel<LinkDTO>(listLinkDTO);
		//		}
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

	public NoticiaDTO getNoticiaDTO() throws Exception {
		try{
			if(noticiaDTO!=null && noticiaDTO.getListLinkDTO()!=null){
				List<LinkDTO> listLinkDTO = linkDAO.listByIdNoticiaDTO(noticiaDTO.getId());
				if(listLinkDTO.size() > this.listLinkDTO.size()){
					this.listLinkDTO = listLinkDTO;
				}
				if(this.listLinkDTO.size() == noticiaDTO.getListLinkDTO().size())
					linkDataModel = new AbstractDataModel<LinkDTO>(this.listLinkDTO);
			}
		}catch(Exception e){
			linkDataModel = new AbstractDataModel<LinkDTO>(listLinkDTO);
		}
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

	public AbstractDataModel<LinkDTO> getLinkDataModel() {
		return linkDataModel;
	}

	public void setLinkDataModel(AbstractDataModel<LinkDTO> linkDataModel) {
		this.linkDataModel = linkDataModel;
	}

	public LinkDTO[] getListSelectedLinkDTO() {
		return listSelectedLinkDTO;
	}

	public void setListSelectedLinkDTO(LinkDTO[] listSelectedLinkDTO) {
		this.listSelectedLinkDTO = listSelectedLinkDTO;
	}

	public LinkDTO getLinkDTO() {
		return linkDTO;
	}

	public void setLinkDTO(LinkDTO linkDTO) {
		this.linkDTO = linkDTO;
	}

	public List<LinkDTO> getListLinkDTO() {
		return listLinkDTO;
	}

	public void setListLinkDTO(List<LinkDTO> listLinkDTO) {
		this.listLinkDTO = listLinkDTO;
	}

}
