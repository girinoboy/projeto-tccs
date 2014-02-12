/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.dto.LinkDTO;
import br.com.dto.ResultadoDTO;
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

}
