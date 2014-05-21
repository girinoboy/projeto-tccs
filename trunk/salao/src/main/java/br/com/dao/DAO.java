/**
 * 
 */
package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.util.JPAUtil;

@SuppressWarnings("unchecked")
public class DAO<T> {
	
	protected final Class<T> classe;
	
	EntityManager em = null;

	public DAO(Class<T> classe) {
		this.classe = classe;
		em = new JPAUtil().getEntityManager();
	}

	public void adicionar(T t) {
		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		// abre transacao
		em.getTransaction().begin();
		// persiste o objeto
		em.persist(t);
		// comita a transacao
		em.getTransaction().commit();
		// fecha a entity manager
		em.close();
	}
	
	public T save(T t) {
		Object obj = null;
		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		// abre transacao
		em.getTransaction().begin();
		// persiste o objeto
		obj = em.merge(t);
		// comita a transacao
		em.getTransaction().commit();
		// fecha a entity manager
		em.close();
		return (T) obj;
	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
	}

	public T buscaPorId(Long id) {
		EntityManager em = new JPAUtil().getEntityManager();
		return (T) em.find(classe, id);
	}

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();
		
		em.close();
		return lista;
	}
	
}
