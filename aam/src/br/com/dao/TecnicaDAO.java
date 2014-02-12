package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.dto.TecnicaDTO;
import br.com.factory.HibernateUtility;

public class TecnicaDAO extends GenericoDAO<TecnicaDTO, Serializable>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<TecnicaDTO> listByIdGraduacaoDTO(Integer id) throws Exception {
		List<TecnicaDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(TecnicaDTO.class)
					.createCriteria("graduacaoTecnicaDTO")
					.add(Restrictions.eq("graduacaoDTO.id", id))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}
}
