package br.com.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.dto.PerfilMenuDTO;
import br.com.factory.HibernateUtility;

@SuppressWarnings("unchecked")
public class PerfilMenuDAO extends GenericoDAO<PerfilMenuDTO, Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PerfilMenuDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<PerfilMenuDTO> listCabecalho() throws Exception {
		List<PerfilMenuDTO> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(PerfilMenuDTO.class)
					.add(Restrictions.eq("ativoInativo",true))
					.createCriteria("menuDTO").add(Restrictions.isNull("menuDTO.id"))
					.addOrder(Order.asc("dropIndex"))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}
	
	public List<PerfilMenuDTO> listInativos() throws Exception {
		List<PerfilMenuDTO> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(PerfilMenuDTO.class)
					.add(Restrictions.eq("ativoInativo",false))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}
	
	public List<PerfilMenuDTO> getMenuByIdPerfil(Integer id) throws Exception {
		List<PerfilMenuDTO> list;
		try{
			list = HibernateUtility.getSession().createCriteria(PerfilMenuDTO.class)
					.add(Restrictions.eq("perfilDTO.id", id))
					.createCriteria("menuDTO").add(Restrictions.eq("ativoInativo",true))
					.addOrder(Order.asc("dropIndex"))
					.list();
		}catch(Exception e){
			throw e;
		}finally{
			HibernateUtility.closeSession();
			//session.close();
		}
		return list;
	}
	
	
	public List<PerfilMenuDTO> getPerfilByIdMenu(Integer id) throws Exception {
		List<PerfilMenuDTO> list;
		try{
			list = HibernateUtility.getSession().createCriteria(PerfilMenuDTO.class)
					.add(Restrictions.eq("menuDTO.id", id))
					.list();
		}catch(Exception e){
			throw e;
		}finally{
			HibernateUtility.closeSession();
			//session.close();
		}
		return list;
	}
	
}
