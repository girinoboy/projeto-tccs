package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.dto.ResultadoAvaliacaoDTO;
import br.com.factory.HibernateUtility;

public class ResultadoAvaliacaoDAO extends GenericoDAO<ResultadoAvaliacaoDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResultadoAvaliacaoDAO() {
		// TODO Auto-generated constructor stub
	}

	public ResultadoAvaliacaoDTO buscarNotaMesSelecionado(ResultadoAvaliacaoDTO resuldoAvaliacaoDTO) throws HibernateException, Exception {
		Calendar data = new GregorianCalendar();
		data.setTime(resuldoAvaliacaoDTO.getData());
		
		ResultadoAvaliacaoDTO result = (ResultadoAvaliacaoDTO) HibernateUtility.getSession().createCriteria(ResultadoAvaliacaoDTO.class)
				.add(Restrictions.sqlRestriction(" month(data)=" + (data.get(Calendar.MONTH)+1)))
				.add(Restrictions.eq("usuarioDTO.id", resuldoAvaliacaoDTO.getUsuarioDTO().getId()))
				//.add(Restrictions.ilike("data", data.getTime()))
				.addOrder(Order.asc("data"))
				.uniqueResult();
		return result;
	}



}
