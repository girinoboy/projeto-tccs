/**
 * 
 */
package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.model.Usuario;
import br.com.util.JPAUtil;

public class UsuarioDAO extends DAO<Usuario>{

	public UsuarioDAO(Class<Usuario> classe) {
		super(classe);
	}


	public boolean existe(Usuario usuario){
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
	
	
	public boolean bancoVazio(){
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from Usuario");
		
		boolean encontrado = query.getResultList().isEmpty();
		
		em.getTransaction().commit();
		em.close();
		
		return encontrado;
		
	}


	public void cadastrarAdm() {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from Usuario");
		
		query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
