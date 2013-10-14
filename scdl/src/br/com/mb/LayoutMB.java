/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.layout.LayoutUnit;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.ResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;


/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean(name = "layoutBean")
public class LayoutMB {

	private String viewedPage; 

	/**
	 * 
	 */
	public LayoutMB() {
		viewedPage = "escola.xhtml"; 
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

	public void requestPage() {
		viewedPage = "" + ".xhtml";
		 addMessage("Data saved"); 
	} 
	
}
