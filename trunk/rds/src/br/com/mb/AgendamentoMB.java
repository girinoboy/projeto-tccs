/**
 * 
 */
package br.com.mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;

import org.hibernate.HibernateException;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.dao.AgendaDAO;
import br.com.dto.AgendaDTO;
import br.com.dto.UsuarioDTO;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean(name="marcarConsultaMB")
@ViewScoped
public class AgendamentoMB extends GenericoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date1;
	//HorarioAtendimentoSubUnidadeDAO horarioAtendimentoSubUnidadeDAO;
//	private EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
//	private ConsultaDAO consultaDAO = new ConsultaDAO();
	private List<UsuarioDTO> listProfissional;
//	private HorarioAtendimentoSubUnidadeDAO horarioAtendimentoSubUnidadeDAO = new HorarioAtendimentoSubUnidadeDAO();

	private ScheduleModel eventModel;
	private ScheduleModel lazyEventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	
	private AgendaDAO agendaDAO = new AgendaDAO();
	private AgendaDTO agendaDTO = new AgendaDTO();
	private AgendaDTO filtroAgendaDTO = new AgendaDTO();
	
	/**
	 * 
	 */
	@SuppressWarnings("serial")
	public AgendamentoMB() {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		rb = ResourceBundle.getBundle("br.com.messages.messages",fc.getViewRoot().getLocale());
		
//		listTipoConsulta = TipoConsultaConverter.listTipoConsulta;//populaTipoConsulta();
//		HorarioAtendimentoSubUnidadeDTO hasu = new HorarioAtendimentoSubUnidadeDTO();
//		hasu.getUnidadeSaudeDTO().setId(1);
//		hasu.setDataTermino(new Date());
		try {
//			listEspecialidade = especialidadeDAO.consultarEspecialidadesUnidadeSaude(hasu);
//			if(consultaDTO !=null){
//				listHorarioAtendimento = horarioAtendimentoSubUnidadeDAO.consultarHorariosDisponiveis(consultaDTO);
//			}else{
//				consultaDTO = new ConsultaDTO();
//			}
			lazyEventModel = new LazyScheduleModel() {

				@Override
				public void loadEvents(Date start, Date end) {
					clear();
					//List<AgendaDTO> list =agendaDAO.consultarEntreDatas(start, end);
					for(AgendaDTO agenda:agendaDAO.consultarEntreDatas(start, end)){
						event = new DefaultScheduleEvent(agenda.getLocalDTO().getNome(),agenda.getDataHoraI(),agenda.getDataHoraF());
						//event.setAllDay(agenda.getAllDay());
						event.setId(agenda.getId().toString());
						addEvent(event);
						event.setId(agenda.getId().toString());
					}
				}
			};

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		listProfissional = new ArrayList<UsuarioDTO>();
	}




	public void handleDateSelect(SelectEvent event) {  
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));  
	}

	public void addEvent(ActionEvent actionEvent)  {
		try {
//			agendaDTO.setDataHoraI(event.getStartDate());
//			agendaDTO.setDataHoraF(event.getEndDate());
//			consultaDTO.setObs(event.getTitle());
			//event = new DefaultScheduleEvent(agendaDTO.getLocalDTO().getNome(),agendaDTO.getDataHoraI(),agendaDTO.getDataHoraF());
			//event.setId(agendaDTO.getId().toString());
			if(event.getId() == null){
				//eventModel.addEvent(event);
				lazyEventModel.addEvent(event);
			}else{
				//eventModel.updateEvent(event);
				lazyEventModel.updateEvent(event);
				agendaDTO.setId(Integer.valueOf(event.getId()));
			}

			agendaDTO = agendaDAO.save(agendaDTO);
//			agendaDTO.setId(agendaDTO.getId());
			event = new DefaultScheduleEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLazyEvent(ActionEvent actionEvent) {
		if(event.getId() == null)
			lazyEventModel.addEvent(event);
		else
			lazyEventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) throws Exception {
		event = (ScheduleEvent) selectEvent.getObject(); 
		Integer id = Integer.valueOf(event.getId());
		agendaDTO = agendaDAO.getById(id);
	}

	public void onDateSelect(SelectEvent  selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		agendaDTO = new AgendaDTO();
		agendaDTO.setDataHoraI(event.getStartDate());
		agendaDTO.setDataHoraF(event.getEndDate());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}
	
	public void filtrar(ActionEvent actionEvent){
		lazyEventModel = new LazyScheduleModel() { 
			private static final long serialVersionUID = 1L;
			@Override
			public void loadEvents(Date start, Date end) {
				clear();
				//List<AgendaDTO> list =agendaDAO.consultarEntreDatas(start, end);
				for(AgendaDTO agenda:agendaDAO.consultarEntreDatas(start, end,filtroAgendaDTO)){
					event = new DefaultScheduleEvent(agenda.getLocalDTO().getNome(),agenda.getDataHoraI(),agenda.getDataHoraF());
					//event.setAllDay(agenda.getAllDay());
					event.setId(agenda.getId().toString());
					addEvent(event);
					event.setId(agenda.getId().toString());
				}
				
			}
		};
		if(actionEvent.getPhaseId().equals(PhaseId.INVOKE_APPLICATION)){
			System.out.println(lazyEventModel);
		//	filtroAgendaDTO = new AgendaDTO();
		}else{
			System.out.println(2);
		}
		addMessage("filtrar");
	}
	
	public void limpar(ActionEvent actionEvent){
		filtroAgendaDTO = new AgendaDTO();
	}
	public void mySchedule(ActionEvent actionEvent){
		lazyEventModel = new LazyScheduleModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void loadEvents(Date start, Date end) {
				clear();
				//List<AgendaDTO> list =agendaDAO.consultarEntreDatas(start, end);
				for(AgendaDTO agenda:agendaDAO.consultarEntreDatas(start, end,getUserSession())){
					event = new DefaultScheduleEvent(agenda.getLocalDTO().getNome(),agenda.getDataHoraI(),agenda.getDataHoraF());
					//event.setAllDay(agenda.getAllDay());
					event.setId(agenda.getId().toString());
					addEvent(event);
					event.setId(agenda.getId().toString());
				}
			}
		};
		filtroAgendaDTO = new AgendaDTO();
		addMessage("minha agenda");
	}
	
	public void delEvent(ActionEvent actionEvent) throws Exception{
		agendaDAO.delete(agendaDTO);
		addMessage("excluirdo");
	}
	
	public Boolean getCanDel(){
		if(agendaDTO.getUsuarioDTO()!=null && agendaDTO.getUsuarioDTO().getPerfilDTO().getNome().equals("administrador")){
			return true;
		}else if(getUserSession().equals(agendaDTO.getUsuarioDTO())){
			return true;
		}else
			return false;
	}
	
	public Boolean getCanSave(){
		if(agendaDTO.getUsuarioDTO()==null || agendaDTO.getUsuarioDTO().getPerfilDTO().getNome().equals("administrador")){
			return true;
		}else if(getUserSession().equals(agendaDTO.getUsuarioDTO())){
			return true;
		}else
			return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getDate1() {  
		return date1;  
	}  
	/**
	 * 
	 * @param date1
	 */
	public void setDate1(Date date1) {  
		this.date1 = date1;  
	}




	/**
	 * @return the listProfissional
	 */
	public List<UsuarioDTO> getListProfissional() {
		return listProfissional;
	}


	/**
	 * @param listProfissional the listProfissional to set
	 */
	public void setListProfissional(List<UsuarioDTO> listProfissional) {
		this.listProfissional = listProfissional;
	}


	/**
	 * @return the eventModel
	 */
	public ScheduleModel getEventModel() {
		return eventModel;
	}


	/**
	 * @param eventModel the eventModel to set
	 */
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}


	/**
	 * @return the lazyEventModel
	 */
	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}


	/**
	 * @param lazyEventModel the lazyEventModel to set
	 */
	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}


	/**
	 * @return the event
	 */
	public ScheduleEvent getEvent() {
		return event;
	}


	/**
	 * @param event the event to set
	 */
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public AgendaDTO getAgendaDTO() {
		return agendaDTO;
	}


	public void setAgendaDTO(AgendaDTO agendaDTO) {
		this.agendaDTO = agendaDTO;
	}


	public AgendaDTO getFiltroAgendaDTO() {
		return filtroAgendaDTO;
	}


	public void setFiltroAgendaDTO(AgendaDTO filtroAgendaDTO) {
		this.filtroAgendaDTO = filtroAgendaDTO;
	} 

}
