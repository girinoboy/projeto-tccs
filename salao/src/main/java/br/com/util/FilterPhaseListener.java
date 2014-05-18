/**
 * 
 */
package br.com.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.model.Usuario;

/**
 * @author 
 *
 */
public class FilterPhaseListener implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -666895419021337416L;

	private String pagina = null;
	/**
	 * 
	 */
	public FilterPhaseListener() {
	}

	public void afterPhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		String viewId = fc.getViewRoot().getViewId();
		try {
			if (viewId != null) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Usuario usuario = ((Usuario) session.getAttribute("usuarioAutenticado"));
				String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();

				if(usuario != null){
					if(pagina.equals("/login.xhtml")){//caso esteja autenticado redireciona para index
						FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");
					}
				}else if(!pagina.equals("/login.xhtml")){
					session.removeAttribute("usuarioAutenticado");
					session.removeAttribute("indexController");
					FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void beforePhase(PhaseEvent event) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
