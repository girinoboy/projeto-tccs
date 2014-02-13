package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.dto.RelatorioGestaoMensal;
import br.com.dto.ResultadoAvaliacaoDTO;
import br.com.factory.HibernateUtility;

//@SuppressWarnings("unchecked")
public class ResultadoAvaliacaoDAO extends GenericoDAO<ResultadoAvaliacaoDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResultadoAvaliacaoDAO() {
		// TODO Auto-generated constructor stub
	}

	public ResultadoAvaliacaoDTO buscarNotaMesSelecionado(ResultadoAvaliacaoDTO resuldoAvaliacaoDTO) throws HibernateException, Exception {
		Calendar data = new GregorianCalendar();
		data.setTime(resuldoAvaliacaoDTO.getData());
		
		ResultadoAvaliacaoDTO result = (ResultadoAvaliacaoDTO) HibernateUtility.getSession().createCriteria(ResultadoAvaliacaoDTO.class)
				.add(Restrictions.sqlRestriction(" month(data)=" + (data.get(Calendar.MONTH)+1)))
				.add(Restrictions.eq("usuarioDTO.id", resuldoAvaliacaoDTO.getUsuarioDTO().getId()))
				//.add(Restrictions.ilike("data", data.getTime()))
				.addOrder(Order.asc("data"))
				.uniqueResult();
		return result;
	}
	
	
	
	public List<?> listRelatorioGestaoMensal(RelatorioGestaoMensal relatorioGestaoMensal) throws Exception {
		List<?> result;
		try{
			result =  HibernateUtility.getSession().createSQLQuery("select * from"
						+ " ("
						+ "  select mes,count(*) from usuario u"
						+ "  inner join financeiro f on f.usuario_id = u.id"
						+ "  group by mes"
						+ "  union"
						+ "  select mes,count(*) from usuario u"
						+ "  inner join financeiro f on f.usuario_id = u.id"
						+ "  where ativoInativo = 1"
						+ "  group by mes"
						+ "  union"
						+ "  select mes,count(*) from usuario u"
						+ "  inner join financeiro f on f.usuario_id = u.id"
						+ "  where ativoInativo = 0"
						+ "  group by mes"
						+ "  union"
						+ "  select mes,count(*) from financeiro where situacao = 1"
						+ "  union"
						+ "  select mes,count(*) from financeiro where situacao = 0"
						+ "  union"
						+ "  select mes,sum(valor_com_desconto) from financeiro"
						+ " ) "
					+ "relatorioGestaoMensal order by 1")
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}



}
