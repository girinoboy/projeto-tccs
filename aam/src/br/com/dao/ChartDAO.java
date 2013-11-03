/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.dto.ResuldoAvaliacaoDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;

/**
 * @author Marcle�nio
 *
 */
@SuppressWarnings("unchecked")
public class ChartDAO extends GenericoDAO<ResuldoAvaliacaoDTO, Serializable>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<ResuldoAvaliacaoDTO> listByIdUsuario(UsuarioDTO usuarioDTO) throws Exception {
		List<ResuldoAvaliacaoDTO> result;
		try{
			result =  HibernateUtility.getSession().createCriteria(ResuldoAvaliacaoDTO.class)
					.add(Restrictions.eq("usuarioDTO.id", usuarioDTO.getId()))
					.addOrder(Order.asc("data"))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}


	public List<ResuldoAvaliacaoDTO> cNotaMediaGraduacao(UsuarioDTO usuarioDTO) throws Exception {
		List<ResuldoAvaliacaoDTO> result;
		try{
			result = HibernateUtility.getSession().createCriteria(ResuldoAvaliacaoDTO.class)
					.createCriteria("usuarioDTO.graduacaoDTO").add(Restrictions.eq("id", usuarioDTO.getGraduacaoDTO().getId()))
					.list();

		}catch(Exception e){
			throw e;
		}

		return result;
	}


	public List<ResuldoAvaliacaoDTO> cMediaGeralAcademia(Date dataInicial, Date dataFinal) throws Exception {
		List<ResuldoAvaliacaoDTO> result;
		try{
			result = HibernateUtility.getSession().createCriteria(ResuldoAvaliacaoDTO.class)

					.add(Restrictions.between("data", dataInicial, dataFinal))
					.setProjection(Projections.projectionList()
							.add(Projections.groupProperty("data"))
							.add(Projections.avg("tecnica"))
							)
					.addOrder(Order.asc("data"))
					.list();

		}catch(Exception e){
			throw e;
		}

		return result;
	}

}
