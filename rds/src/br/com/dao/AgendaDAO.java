/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.DefaultScheduleEvent;

import br.com.dto.AgendaDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;

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
					add(Restrictions.between("dataHoraI",start,end)).list();
		}catch(Exception e){
			throw e;
		}
		return list;
	}
	
	public List<AgendaDTO> consultarEntreDatas(Date start, Date end) {
		List<AgendaDTO> list =null;
		try{
			list = HibernateUtility.getSession().createCriteria(AgendaDTO.class).
					add(Restrictions.between("dataHoraI",start,end)).list();
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
					.add(Restrictions.between("dataHoraI",start,end)).list();
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
					.add(Restrictions.between("dataHoraI",start,end)).list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}

	public Boolean verificaPeriodoImpeditivo(AgendaDTO agendaDTO) {
		List<AgendaDTO> list =null;
		try{
			list = HibernateUtility.getSession().createCriteria(AgendaDTO.class)
					.add(Restrictions.eq("localDTO.id",agendaDTO.getLocalDTO().getId()))
					.add(Restrictions.lt("dataHoraI", agendaDTO.getDataHoraF()))//<
					.add(Restrictions.gt("dataHoraF",agendaDTO.getDataHoraI()))//>
					.add(Restrictions.ne("id",agendaDTO.getId()))
					.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtility.closeSession();
		}
		
		if(list.isEmpty()){
			return false;
		}else{
			return true;
		}
		
		
	}

}
