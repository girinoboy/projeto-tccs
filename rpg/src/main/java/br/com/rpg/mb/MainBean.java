package br.com.rpg.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.component.dnd.Draggable;
import org.primefaces.component.panel.Panel;
import org.primefaces.config.PrimeConfiguration;
import org.primefaces.config.StartupPrimeConfiguration;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import br.com.rpg.dto.PersonagemDTO;

/**
 *
 * @author hatemalimam
 */
@ManagedBean
@ViewScoped
public class MainBean implements Serializable {

	/**
	 * Creates a new instance of MainBean
	 */
	private String currentNav;

	private ScheduleModel model;

	private List<String> batImages;

	private String pfVersion;

	private Double doubleValue;

	private int left = 0,top = 0;

	private List<PersonagemDTO> panelList ;


	public MainBean() {
		currentNav = "/checkBoxesJQuery/main.xhtml";
//		fillList();
//		createPieModel1();
//		createDiagramModel();

		doubleValue = 2d;

		model = new DefaultScheduleModel();
		model.addEvent(new DefaultScheduleEvent("Event1", new Date(), new Date()));

		batImages = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			batImages.add("bat" + i + ".jpg");
		}

		PrimeConfiguration config = new StartupPrimeConfiguration(FacesContext.getCurrentInstance());
		pfVersion = RequestContext.getCurrentInstance().getApplicationContext().getConfig().getBuildVersion();

		panelList = new ArrayList<PersonagemDTO>();

//		panelList.add(new PersonagemDTO("1","414px","552px","P1"));
//		panelList.add(new PersonagemDTO("2","0px","0px","P2"));
//		panelList.add(new PersonagemDTO("3","0px","0px","P3"));
//		panelList.add(new PersonagemDTO("4","0px","0px","P4"));
//		panelList.add(new PersonagemDTO("5","0px","0px","P5"));
//		panelList.add(new PersonagemDTO("6","0px","0px","P6"));

	}

	public FacesMessage addMessage(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(message);
		facesContext.addMessage(null, facesMessage);

		return facesMessage;
	}

	public void onCreate(){

		if(panelList != null){
			panelList.add(new PersonagemDTO(""+panelList.size(),"0px","0px","P"+panelList.size()));
		}

		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish("/main", panelList);
	}

	public void onDrop(DragDropEvent dragDropEvent) {
		String dargId = dragDropEvent.getDragId();
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String left = params.get(dargId + "_left");
		String top = params.get(dargId + "_top");
		String wgv = params.get(dargId + "_dragWgv");
		//addMessage("Left: " + left + " Top: " + top);

		for (PersonagemDTO p : panelList) {
			if(p.getId().equals(wgv.split("_")[1]) || p.getId().equals(dargId)){
				p.setId(dargId);
				p.setLeft(left);
				p.setTop(top);
			}
		}
		this.left = new Integer(left);
		this.top = new Integer(top);
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
//		eventBus.publish("/main", new FacesMessage(StringEscapeUtils.escapeHtml3("id: "+ dargId +" Left: " + left + " Top: " + top)));
		eventBus.publish("/main", panelList);
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public List<PersonagemDTO> getPanelList() {
		return panelList;
	}

	public void setPanelList(List<PersonagemDTO> panelList) {
		this.panelList = panelList;
	}

}
