/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import br.com.dto.CampeonatoDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class CampeonatoDAO extends GenericoDAO<CampeonatoDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5287534050019538343L;

	/**
	 * 
	 */
	public CampeonatoDAO() {
		// TODO Auto-generated constructor stub
	}

	public Integer numCampeonatosParticipado(UsuarioDTO usuarioDTO) throws HibernateException, Exception {
		LocalDate seisMesesDepois = new LocalDate();
		seisMesesDepois.plusMonths(6);
		return (Integer) HibernateUtility.getSession().createCriteria(CampeonatoDTO.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.le("campeonatoDTO.data", seisMesesDepois.toDate()))  
				.add(Restrictions.eq("usuarioDTO.id", usuarioDTO.getId()))
				.uniqueResult();
		
	}

}
