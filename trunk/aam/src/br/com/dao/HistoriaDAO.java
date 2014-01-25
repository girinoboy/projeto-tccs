package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.dto.HistoriaDTO;
import br.com.factory.HibernateUtility;

@SuppressWarnings("unchecked")
public class HistoriaDAO extends GenericoDAO<HistoriaDTO, Serializable>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<HistoriaDTO> list(boolean categoria) throws Exception {
		List<HistoriaDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(HistoriaDTO.class)
					.add(Restrictions.eq("categoria", categoria))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}

}
