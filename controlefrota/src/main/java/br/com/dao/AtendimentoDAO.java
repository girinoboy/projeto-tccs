/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.AtendimentoDTO;
import br.com.dto.UsuarioDTO;
import br.com.factory.HibernateUtility;
import br.com.ind.indSituacao;

/**
 * @author marcleonio
 *
 */
@SuppressWarnings("unchecked")
public class AtendimentoDAO extends GenericoDAO<AtendimentoDTO, Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1298913077203993511L;

	/**
	 * 
	 */
	public AtendimentoDAO() {
		// TODO Auto-generated constructor stub
	}

	
	public List<AtendimentoDTO> listPorIdUsuario(UsuarioDTO userSession) throws Exception {
		try{
			return  HibernateUtility.getSession().createCriteria(AtendimentoDTO.class)
					//.setFetchMode("perfilDTO", FetchMode.JOIN)
					.add(Restrictions.eq("usuarioDTO.id", userSession.getId()))
					.list();
		}catch(Exception e){
			throw e;
		}finally{
			HibernateUtility.closeSession();
//			session.close();			
		}
	}


	public boolean verificaExisteAtendimento(AtendimentoDTO atendimentoDTO) throws HibernateException, Exception {
		return HibernateUtility.getSession().createCriteria(AtendimentoDTO.class)
		.add(Restrictions.in("situacao", Arrays.asList(indSituacao.AGUARDANDO,indSituacao.EM_ANDAMENTO)))
		.add(Restrictions.eq("veiculoDTO.id", atendimentoDTO.getVeiculoDTO().getId()))
		.add(Restrictions.ne("id", atendimentoDTO.getId()))
		.uniqueResult() == null;
	}

}
