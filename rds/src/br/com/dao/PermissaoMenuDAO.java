/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.PermissaoMenuDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class PermissaoMenuDAO extends GenericoDAO<PermissaoMenuDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<PermissaoMenuDTO> listByIdPerfil(Integer id) throws Exception {
		try {
			 
			@SuppressWarnings("unchecked")
			List<PermissaoMenuDTO> list = HibernateUtility.getSession().createCriteria(PermissaoMenuDTO.class)
			.add(Restrictions.not(Restrictions.eq("id", id )))
			.list();
			//HibernateUtility.closeSession();
			return (List<PermissaoMenuDTO>) list;
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			HibernateUtility.closeSession();
		}
		
	}

}
