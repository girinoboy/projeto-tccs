package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.model.NotaFiscal;
import br.com.util.JPAUtil;

public class NotaFiscalDAO extends DAO<NotaFiscal>{

	public NotaFiscalDAO(Class<NotaFiscal> classe) {
		super(classe);
		// TODO Auto-generated constructor stub
	}
	
	
	@SuppressWarnings("unchecked")
	public List<NotaFiscal> autoComplete(String busca){
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		String hql = "from NotaFiscal n e where lower(n.cnpj) = lower(:busca)";  
		Query query = em.createQuery(hql);  
		query.setParameter("busca", "%" + busca + "%");
		
		return query.getResultList();
		
	}

}
