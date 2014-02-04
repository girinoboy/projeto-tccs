package br.com.utility;

import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.dto.UsuarioDTO;

public class LoginPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		
		FacesContext fc = event.getFacesContext();
		String viewId = fc.getViewRoot().getViewId();
		//NavigationHandler nh = fc.getApplication().getNavigationHandler();
		try {
			if (viewId != null) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				UsuarioDTO usuario = ((UsuarioDTO) session.getAttribute("usuarioAutenticado"));
				String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
				
				if(usuario != null){
					if(pagina.equals("/"+Constantes.PAGINA_LOGIN)){//caso esteja autenticado redireciona para index
//						session.removeAttribute("usuarioAutenticado");
//						session.removeAttribute("indexController");
//						nh.handleNavigation(fc, null, "pretty:login");
						FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
					}
				}else if(!pagina.equals("/"+Constantes.PAGINA_LOGIN)){
					session.removeAttribute("usuarioAutenticado");
					session.removeAttribute("indexController");
					//nh.handleNavigation(fc, null, "pretty:acessoNegado");
					FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_LOGIN);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
			Calendar c = Calendar.getInstance();
			c.set(2016, Calendar.NOVEMBER, 8);
			System.out.println(pagina);
			if(c.getTime().before(new Date()) && !pagina.equals("/404.xhtml")){
				//FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
			    FacesContext.getCurrentInstance().getExternalContext().redirect("404.xhtml");  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
