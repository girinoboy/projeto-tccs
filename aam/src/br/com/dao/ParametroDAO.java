/**
 * 
 */
package br.com.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.ParametroDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class ParametroDAO extends GenericoDAO<ParametroDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ParametroDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public ParametroDTO recuperaParametro(String nome) throws HibernateException, Exception{
		try{
			return (ParametroDTO) HibernateUtility.getSession().createCriteria(ParametroDTO.class)
					.add(Restrictions.eq("nome", nome))
					.uniqueResult();
		}catch(Exception e){
			throw e;
		}

	}
	
}
