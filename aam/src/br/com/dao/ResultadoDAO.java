/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import br.com.dto.CampeonatoDTO;
import br.com.dto.LinkDTO;
import br.com.dto.ResultadoDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;
/**
 * @author marcleonio.medeiros
 *
 */
@SuppressWarnings("unchecked")
public class ResultadoDAO extends GenericoDAO<ResultadoDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5509692578134816443L;

	/**
	 * 
	 */
	public ResultadoDAO() {
		// TODO Auto-generated constructor stub
	}

	
	public List<ResultadoDTO> listByIdCampeonatoDTO(Integer id) throws Exception {
		List<ResultadoDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(ResultadoDTO.class)
					.add(Restrictions.eq("campeonatoDTO.id", id))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
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
