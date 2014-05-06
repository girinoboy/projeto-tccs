/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.DefaultScheduleEvent;

import br.com.dto.AgendaDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;
import br.com.utility.DataUtils;

/**
 * @author marcleonio.medeiros
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class AgendaDAO extends GenericoDAO<AgendaDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AgendaDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<DefaultScheduleEvent> findEventsBetween(Date start, Date end) throws HibernateException, Exception {
		List list =null;
		try{
			list = HibernateUtility.getSession().createCriteria(AgendaDTO.class).
					add(Restrictions.between("startDate",start,end)).list();
		}catch(Exception e){
			throw e;
		}
		return list;
	}
	
	public List<AgendaDTO> consultarEntreDatas(Date start, Date end) {
		List<AgendaDTO> list =null;
		try{
			list = HibernateUtility.getSession().createCriteria(AgendaDTO.class).
					add(Restrictions.between("startDate",start,end)).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}

	public List<AgendaDTO> consultarEntreDatas(Date start, Date end, AgendaDTO filtroAgendaDTO) {
		List<AgendaDTO> list =null;
		try{
			list = HibernateUtility.getSession().createCriteria(AgendaDTO.class)
					.add(Restrictions.or( 
							Restrictions.eq("usuarioDTO.id",filtroAgendaDTO.getUsuarioDTO() != null ? filtroAgendaDTO.getUsuarioDTO().getId():0),
							Restrictions.eq("localDTO.id",filtroAgendaDTO.getLocalDTO()!=null ? filtroAgendaDTO.getLocalDTO().getId():0)
							))
					.add(Restrictions.between("startDate",start,end)).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}

	public List<AgendaDTO> consultarEntreDatas(Date start, Date end, UsuarioDTO userSession) {
		List<AgendaDTO> list =null;
		try{
			list = HibernateUtility.getSession().createCriteria(AgendaDTO.class)
					.add(Restrictions.eq("usuarioDTO.id", userSession.getId()))
					.add(Restrictions.between("startDate",start,end)).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}

	public Boolean verificaPeriodoImpeditivo(AgendaDTO agendaDTO) {
		List<AgendaDTO> list =null;
		//Calendar dataMax = new GregorianCalendar();
		//dataMax.setTime(agendaDTO.getStartDate());
		//Calendar dataMin = new GregorianCalendar();
		//dataMin.setTime(agendaDTO.getStartDate());
		//int primeiro_dia_mes = dataMax.getActualMinimum(Calendar.DAY_OF_MONTH);  
		//dataMin.set(Calendar.DAY_OF_MONTH, primeiro_dia_mes);

		//int ultimo_dia_mes = dataMax.getActualMaximum(Calendar.DAY_OF_MONTH);  
		//dataMax.set(Calendar.DAY_OF_MONTH, ultimo_dia_mes);  
		
		//dataMin.set(dataMax.get(Calendar.YEAR), dataMax.get(Calendar.MONTH), dataMax.get(Calendar.DATE), 0, 0);
		//dataMax.set(dataMax.get(Calendar.YEAR), dataMax.get(Calendar.MONTH), dataMax.get(Calendar.DATE), 23, 59);
		try{
			list = HibernateUtility.getSession().createCriteria(AgendaDTO.class)
					.add(Restrictions.or(
							Restrictions.and(
									Restrictions.eq("localDTO.id",agendaDTO.getLocalDTO().getId()),
									Restrictions.lt("startDate", agendaDTO.getEndDate()),//<
									Restrictions.gt("endDate",agendaDTO.getStartDate()),//>
									Restrictions.ne("id",agendaDTO.getId() == null ? 0:agendaDTO.getId())
									),
							Restrictions.and(
									Restrictions.eq("localDTO.id",agendaDTO.getLocalDTO().getId()),
									Restrictions.eq("allDayDate", DataUtils.getSQLDate(agendaDTO.getAllDayDate())),//2014-01-04
									Restrictions.ne("id",agendaDTO.getId() == null ? 0:agendaDTO.getId()),
									Restrictions.eq("allDay",true)
									),
							Restrictions.and(
									Restrictions.eq("allDayDate", DataUtils.getSQLDate(agendaDTO.getAllDayDate())),
									Restrictions.eq("allDay", true)
									)
							)
							).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtility.closeSession();
		}
		
		if(list == null || list.isEmpty()){
			return false;
		}else{
			return true;
		}
		
		
	}

}
