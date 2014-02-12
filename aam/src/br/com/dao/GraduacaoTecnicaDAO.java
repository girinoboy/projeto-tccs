/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.dto.GraduacaoTecnicaDTO;
import br.com.dto.TecnicaDTO;
import br.com.factory.HibernateUtility;

/**
 * @author Marcleônio
 *
 */
@SuppressWarnings("unchecked")
public class GraduacaoTecnicaDAO extends GenericoDAO<GraduacaoTecnicaDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8864469333368280816L;

	/**
	 * 
	 */
	public GraduacaoTecnicaDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<GraduacaoTecnicaDTO> listByIdGraduacaoDTO(Integer id) throws Exception {
		List<GraduacaoTecnicaDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(GraduacaoTecnicaDTO.class)
					.add(Restrictions.eq("graduacaoDTO.id", id))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}
	
	
	public List<TecnicaDTO> listByIdGraduacaoDTO2(Integer id) throws Exception {
		List<TecnicaDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(GraduacaoTecnicaDTO.class)
					
					.add(Restrictions.eq("graduacaoDTO.id", id))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}

}
