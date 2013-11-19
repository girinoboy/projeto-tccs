package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.dto.JustificativaDTO;
import br.com.factory.HibernateUtility;

public class JustificativaDAO extends GenericoDAO<JustificativaDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JustificativaDAO() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	public List quantidadeJustificativaAgrupada(Integer ano) throws HibernateException, Exception {

		Calendar dataMin = new GregorianCalendar(ano,Calendar.JANUARY,01);
		Calendar dataMax = new GregorianCalendar(ano,Calendar.DECEMBER,31);

		List result; 
		Criteria criteria = HibernateUtility.getSession().createCriteria(JustificativaDTO.class)
				.add(Restrictions.ge("dataJustificativa", dataMin.getTime()))
				.add(Restrictions.lt("dataJustificativa", dataMax.getTime()))
				.setProjection(Projections.projectionList()
						.add(Projections.groupProperty("nome"))
						.add(Projections.count("nome"),"total")
						)
						.addOrder(Order.asc("nome"));
		result = criteria.list();
		return result;
	}

}
