/**
 * 
 */
package br.com.mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import br.com.utility.Constantes;

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
	//	private List<UsuarioDTO> listProfissional;
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
//	@SuppressWarnings("serial")
	public AgendamentoMB() {
		//		FacesContext fc = FacesContext.getCurrentInstance();
		//		rb = ResourceBundle.getBundle("br.com.messages.messages",fc.getViewRoot().getLocale());

		//		listTipoConsulta = TipoConsultaConverter.listTipoConsulta;//populaTipoConsulta();
		//		HorarioAtendimentoSubUnidadeDTO hasu = new HorarioAtendimentoSubUnidadeDTO();
		//		hasu.getUnidadeSaudeDTO().setId(1);
		//		hasu.setDataTermino(new Date());
		carregaAgenda();

		//		listProfissional = new ArrayList<UsuarioDTO>();
	}


	private void carregaAgenda(){
		try {
			lazyEventModel = new LazyScheduleModel() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 6760142212935532934L;

				@Override
				public void loadEvents(Date start, Date end) {
					clear();
					//List<AgendaDTO> list =agendaDAO.consultarEntreDatas(start, end);
					for(AgendaDTO agenda:agendaDAO.consultarEntreDatas(start, end)){
						event = new DefaultScheduleEvent(agenda.getLocalDTO().getNome(),agenda.getStartDate(),agenda.getEndDate(),agenda.getAllDay().booleanValue());
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
			//event = new DefaultScheduleEvent(agendaDTO.getLocalDTO().getNome(),agendaDTO.getStartDate(),agendaDTO.getEndDate());
			//event.setId(agendaDTO.getId().toString());
			if(event.getId() == null){
				event = new DefaultScheduleEvent(agendaDTO.getLocalDTO().getNome(),agendaDTO.getStartDate(),agendaDTO.getEndDate(),agendaDTO.getAllDay().booleanValue());
				//eventModel.addEvent(event);
				lazyEventModel.addEvent(event);
			}else{
				//eventModel.updateEvent(event);
				lazyEventModel.updateEvent(event);
				agendaDTO.setId(Integer.valueOf(event.getId()));
			}
			if(agendaDTO.getStartDate().after(agendaDTO.getEndDate())){
				addMessage(rb.getString("msgDataLessEquals"));
			}else if(!agendaDAO.verificaPeriodoImpeditivo(agendaDTO)){
				agendaDTO = agendaDAO.save(agendaDTO);
				event = new DefaultScheduleEvent();
			}else{
				addMessage(rb.getString("msgPeriodDetracts"));
			}
		} catch (Exception e) {
			addMessage(rb.getString("dateNotFound"));
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
		agendaDTO.setStartDate(event.getStartDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(event.getEndDate());
		calendar.add(Calendar.MINUTE, 30);
		agendaDTO.setEndDate(calendar.getTime());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) throws Exception {
		this.event = event.getScheduleEvent();
		Integer id = Integer.valueOf(this.event.getId());
		agendaDTO.setId(id);
		agendaDTO = agendaDAO.getById(id);
		agendaDTO.setAllDay(event.getScheduleEvent().isAllDay());
		agendaDTO.setStartDate(this.event.getStartDate());
		agendaDTO.setEndDate(this.event.getEndDate());
		
		if(!agendaDAO.verificaPeriodoImpeditivo(agendaDTO)){
			agendaDTO = agendaDAO.save(agendaDTO);
			this.event = new DefaultScheduleEvent();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
			addMessage(message);
		}else{
			addMessage(rb.getString("msgPeriodDetracts"));
		}
		//carregaAgenda();
	}

	public void onEventResize(ScheduleEntryResizeEvent event) throws Exception {
		this.event = event.getScheduleEvent();
		Integer id = Integer.valueOf(this.event.getId());
		agendaDTO = agendaDAO.getById(id);
		agendaDTO.setId(id);
		agendaDTO.setStartDate(this.event.getStartDate());
		agendaDTO.setEndDate(this.event.getEndDate());
		
		if(!agendaDAO.verificaPeriodoImpeditivo(agendaDTO)){
			agendaDTO = agendaDAO.save(agendaDTO);
			this.event = new DefaultScheduleEvent();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
			addMessage(message);
		}else{
			addMessage(rb.getString("msgPeriodDetracts"));
		}
		//carregaAgenda();
	}

	public void filtrar(ActionEvent actionEvent){
		lazyEventModel = new LazyScheduleModel() { 
			private static final long serialVersionUID = 1L;
			@Override
			public void loadEvents(Date start, Date end) {
				clear();
				//List<AgendaDTO> list =agendaDAO.consultarEntreDatas(start, end);
				for(AgendaDTO agenda:agendaDAO.consultarEntreDatas(start, end,filtroAgendaDTO)){
					event = new DefaultScheduleEvent(agenda.getLocalDTO().getNome(),agenda.getStartDate(),agenda.getEndDate(),agenda.getAllDay().booleanValue());
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
		addMessage(rb.getString("filter"));
	}

	public void limpar(ActionEvent actionEvent){
		filtroAgendaDTO = new AgendaDTO();
		carregaAgenda();
	}
	public void mySchedule(ActionEvent actionEvent){
		lazyEventModel = new LazyScheduleModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void loadEvents(Date start, Date end) {
				clear();
				//List<AgendaDTO> list =agendaDAO.consultarEntreDatas(start, end);
				for(AgendaDTO agenda:agendaDAO.consultarEntreDatas(start, end,getUserSession())){
					event = new DefaultScheduleEvent(agenda.getLocalDTO().getNome(),agenda.getStartDate(),agenda.getEndDate(),agenda.getAllDay().booleanValue());
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
		if(getUserSession()!=null && getUserSession().getPerfilDTO().getId().equals(Constantes.ADMINISTRADOR)){
			return true;
		}else if(getUserSession().equals(agendaDTO.getUsuarioDTO())){
			return true;
		}else
			return false;
	}

	public Boolean getCanSave(){
		if(agendaDTO.getUsuarioDTO()==null || getUserSession().getPerfilDTO().getId().equals(Constantes.ADMINISTRADOR)){
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

	//	/**
	//	 * @return the listProfissional
	//	 */
	//	public List<UsuarioDTO> getListProfissional() {
	//		return listProfissional;
	//	}
	//
	//
	//	/**
	//	 * @param listProfissional the listProfissional to set
	//	 */
	//	public void setListProfissional(List<UsuarioDTO> listProfissional) {
	//		this.listProfissional = listProfissional;
	//	}


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
