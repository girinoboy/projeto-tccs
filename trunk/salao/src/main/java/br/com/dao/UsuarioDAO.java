/**
 * 
 */
package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.model.UsuarioDTO;
import br.com.util.JPAUtil;

/**
 * @author Joelson
 *
 */
public class UsuarioDAO extends DAO<UsuarioDTO>{

	private static final long serialVersionUID = 2817329528072312197L;

	public boolean existe(UsuarioDTO usuario){
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from Usuario u where u.login = :pLogin and u.senha = :pSenha");
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		
		boolean encontrado = !query.getResultList().isEmpty();
		
		em.getTransaction().commit();
		em.close();
		
		return encontrado;
	}
	
}
