package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import br.com.dto.EscolaLivroDTO;
import br.com.factory.HibernateUtility;

public class EscolaLivroDAO extends GenericoDAO<EscolaLivroDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EscolaLivroDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<EscolaLivroDTO> consultaSQLQuery(String consulta) throws HibernateException, Exception {
		Query query = HibernateUtility.getSession().createSQLQuery(consulta);


				return query.list();
	}

}
