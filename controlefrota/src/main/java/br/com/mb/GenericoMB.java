package br.com.mb;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dao.GenericoDAO;
import br.com.dto.MenuDTO;
import br.com.dto.PerfilDTO;
import br.com.dto.UsuarioDTO;
@SuppressWarnings("unchecked")
public class GenericoMB<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ResourceBundle rb;
	
	private Class<T> oclass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	protected GenericoDAO<T, Serializable> abstractDAO;
	
	protected T abstractDTO;

	protected List<T> abstractList;
	
	@PostConstruct
	public void inicio() throws Exception{
		abstractList =  abstractDAO.list();
	}
	
	public GenericoMB() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if(fc.getViewRoot() != null)
			rb = ResourceBundle.getBundle("br.com.messages.messages",fc.getViewRoot().getLocale());
		
//		Class<T>  oclass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		if(abstractDAO == null){
			abstractDAO = new GenericoDAO<T, Serializable>(oclass);
		}
		try {
			abstractList = abstractDAO.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editar(){
		
	}
	
	public UsuarioDTO getUserSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return ((UsuarioDTO) session.getAttribute("usuarioAutenticado"));
	}
	
	public List<MenuDTO> getMenuSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return  (List<MenuDTO>) session.getAttribute("listMenuAutenticado");
	}
	public List<PerfilDTO> getPerfilSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return (List<PerfilDTO>) session.getAttribute("listPerfilAutenticado");
	}
	//metodo generico que envia mesagens para a tela
	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void addMessage(String summary,String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  detail);  
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<T> getAbstractList() {
		return abstractList;
	}

	public void setAbstractList(List<T> abstractList) {
		this.abstractList = abstractList;
	}

	public T getAbstractDTO() {
		return abstractDTO;
	}

	public void setAbstractDTO(T abstractDTO) {
		this.abstractDTO = abstractDTO;
	}


}
