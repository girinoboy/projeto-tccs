package br.com.mb;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dto.MenuDTO;
import br.com.dto.PerfilDTO;
import br.com.dto.UsuarioDTO;
@SuppressWarnings("unchecked")
public class GenericoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ResourceBundle rb;
	
	public GenericoMB(){
		FacesContext fc = FacesContext.getCurrentInstance();
		if(fc.getViewRoot() != null)
			rb = ResourceBundle.getBundle("br.com.messages.messages",fc.getViewRoot().getLocale());
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

}
