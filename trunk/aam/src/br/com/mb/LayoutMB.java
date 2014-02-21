/**
 * 
 */
package br.com.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.layout.LayoutUnit;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ResizeEvent;
import org.primefaces.event.ToggleEvent;


/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
public class LayoutMB {

	private String viewedPage = "topGrafico.xhtml"; 
	private Integer myActiveIndex =0;

	/**
	 * 
	 */
	public LayoutMB() {
		String pagina = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
		
		if(pagina.equals("/avaliacaoMembro.xhtml")){
			myActiveIndex =0;
		}else if(pagina.equals("/notaMediaGraduacao.xhtml")){
			myActiveIndex =1;
		}else if(pagina.equals("/mediaGeralAcademia.xhtml")){
			myActiveIndex =2;
		}else{
			myActiveIndex =0;
		}
		
		viewedPage = "topGrafico.xhtml"; 
	}


	public void handleClose(CloseEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unit Closed", "Position:'" + ((LayoutUnit)event.getComponent()).getPosition() + "'");

		addMessage(message);
	}

	public void handleToggle(ToggleEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, ((LayoutUnit)event.getComponent()).getPosition() + " toggled", "Status:" + event.getVisibility().name());

		addMessage(message);
	}

	public void handleResize(ResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, ((LayoutUnit)event.getComponent()).getPosition() + " resized", "Width:" + event.getWidth() + ", Height:" + event.getHeight());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private void addMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	public String getViewedPage() {
		return viewedPage;
	}

	public void setViewedPage(String viewedPage) {
		this.viewedPage = viewedPage;
	}

	public void requestPage(ActionEvent event) {
		String pag = (String) event.getComponent().getAttributes().get("pag");
		viewedPage = pag + ".xhtml";
		addMessage("Data saved"); 
	}


	public Integer getMyActiveIndex() {
		return myActiveIndex;
	}


	public void setMyActiveIndex(Integer myActiveIndex) {
		this.myActiveIndex = myActiveIndex;
	} 


}
