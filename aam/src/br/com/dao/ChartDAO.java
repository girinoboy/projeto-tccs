/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.dto.ResultadoAvaliacaoDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;

/**
 * @author Marcleônio
 *
 */
@SuppressWarnings("unchecked")
public class ChartDAO extends GenericoDAO<ResultadoAvaliacaoDTO, Serializable>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<ResultadoAvaliacaoDTO> listByIdUsuario(UsuarioDTO usuarioDTO) throws Exception {
		List<ResultadoAvaliacaoDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(ResultadoAvaliacaoDTO.class)
					.add(Restrictions.eq("usuarioDTO.id", usuarioDTO.getId()))
					.addOrder(Order.asc("data"))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}


	public List<ResultadoAvaliacaoDTO> cNotaMediaGraduacao(UsuarioDTO usuarioDTO) throws Exception {
		List<ResultadoAvaliacaoDTO> result;
		try{
			result = HibernateUtility.getSession().createCriteria(ResultadoAvaliacaoDTO.class)
					.createCriteria("graduacaoDTO").add(Restrictions.eq("id", usuarioDTO.getGraduacaoDTO().getId()))
					.list();

		}catch(Exception e){
			throw e;
		}

		return result;
	}


	public List<ResultadoAvaliacaoDTO> cMediaGeralAcademia(Date dataInicial, Date dataFinal) throws Exception {
		List<ResultadoAvaliacaoDTO> result;
		try{/*
			result = HibernateUtility.getSession().createCriteria(ResuldoAvaliacaoDTO.class)

					.add(Restrictions.between("data", dataInicial, dataFinal))
					.setProjection(Projections.projectionList()
							.add(Projections.groupProperty("data"))
							.add(Projections.avg("tecnica"))
							//.add(Projections.sqlProjection("", new String[] {"pvendi"}, new Type[] {Hibernate.DOUBLE}))
							)
					.addOrder(Order.asc("data"))
					.list();*/
			
			result = session.createSQLQuery(
					"select data,avg(tecnica+luta+conhecimentos)"
					+ " from resultado_avaliacao"
					+ " where data BETWEEN :dataInicial AND :dataFinal"
					+ " group by data"
					)
			.setParameter("dataInicial", dataInicial)
			.setParameter("dataFinal", dataFinal)
			.list();

		}catch(Exception e){
			throw e;
		}

		return result;
	}

}
