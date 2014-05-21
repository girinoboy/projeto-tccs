package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.model.Cliente;
import br.com.util.JPAUtil;


public class ClienteDAO extends DAO<Cliente> {

	public ClienteDAO(Class<Cliente> classe) {
		super(classe);
		// TODO Auto-generated constructor stub
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Cliente> autoComplete(String busca){
		
		EntityManager em = new JPAUtil().getEntityManager();
		
//		CriteriaQuery<Cliente> query1 = em.getCriteriaBuilder().createQuery(classe);
		
		String hql = "from Cliente c e where lower(c.nome) = lower(:busca)";  
		Query query = em.createQuery(hql);  
		query.setParameter("busca", "%" + busca + "%");
		
		return query.getResultList();
		
	}

}
