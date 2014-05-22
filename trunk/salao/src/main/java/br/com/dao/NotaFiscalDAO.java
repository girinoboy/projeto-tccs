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
		
		String hql = "from NotaFiscal n where lower(n.cnpj) LIKE lower(:busca)";  
		Query query = em.createQuery(hql);  
		query.setParameter("busca", "%" + busca + "%");
		
		List<NotaFiscal> lista = query.getResultList();
		
		em.close();
		
		return lista;
		
	}

}
