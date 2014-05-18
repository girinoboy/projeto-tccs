package br.com.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.model.Usuario;

public class GenericBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericBean() {
		
	}
	
	public Usuario getSessionUser(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return ((Usuario) session.getAttribute("usuarioAutenticado"));
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
