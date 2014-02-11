package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.dto.LinkDTO;
import br.com.factory.HibernateUtility;

@SuppressWarnings("unchecked")
public class LinkDAO extends GenericoDAO<LinkDTO, Serializable>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public List<LinkDTO> listByIdNoticiaDTO(Integer id) throws Exception {
		List<LinkDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(LinkDTO.class)
					.add(Restrictions.eq("noticiaDTO.id", id))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}
	
	public List<LinkDTO> listByIdHistoriaDTO(Integer id) throws Exception {
		List<LinkDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(LinkDTO.class)
					.add(Restrictions.eq("historiaDTO.id", id))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}
	
	public List<LinkDTO> listByIdCampeonatoDTO(Integer id) throws Exception {
		List<LinkDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(LinkDTO.class)
					.add(Restrictions.eq("campeonatoDTO.id", id))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}
}
