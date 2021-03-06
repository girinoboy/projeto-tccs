package br.com.mb;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String theme = "cupertino"; //default

	public String getTheme() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
		if(pagina.equals("/login.xhtml")){
			theme = "cupertino"; //default para pagina inicial
		}else if(theme==null) {
			theme = "cupertino";
		}

		if(params.containsKey("theme")) {
			theme = params.get("theme");
		}

		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
