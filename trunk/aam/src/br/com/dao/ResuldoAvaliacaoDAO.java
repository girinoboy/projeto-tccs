package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.dto.ResuldoAvaliacaoDTO;
import br.com.factory.HibernateUtility;

public class ResuldoAvaliacaoDAO extends GenericoDAO<ResuldoAvaliacaoDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResuldoAvaliacaoDAO() {
		// TODO Auto-generated constructor stub
	}

	public ResuldoAvaliacaoDTO buscarNotaMesSelecionado(ResuldoAvaliacaoDTO resuldoAvaliacaoDTO) throws HibernateException, Exception {
		Calendar data = new GregorianCalendar();
		data.setTime(resuldoAvaliacaoDTO.getData());
		
		ResuldoAvaliacaoDTO result = (ResuldoAvaliacaoDTO) HibernateUtility.getSession().createCriteria(ResuldoAvaliacaoDTO.class)
				.add(Restrictions.sqlRestriction(" month(data)=" + (data.get(Calendar.MONTH)+1))) 
				//.add(Restrictions.ilike("data", data.getTime()))
				.addOrder(Order.asc("data"))
				.uniqueResult();
		return result;
	}



}
