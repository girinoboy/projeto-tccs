/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
public class MenuBean {
	
	String pagina;

	/**
	 * 
	 */
	public MenuBean() {
		pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
//		System.out.println(pagina);
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
