/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.dto.EscolaDTO;
import br.com.dto.TurnoDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class EscolaDAO extends GenericoDAO<EscolaDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EscolaDAO() {
		// TODO Auto-generated constructor stub
	}

	public Boolean existeEnderecoOuNome(EscolaDTO escolaDTO) throws HibernateException, Exception {
		EscolaDTO escola = (EscolaDTO) HibernateUtility.getSession().createCriteria(EscolaDTO.class)
				.add(Restrictions.or( 
						Restrictions.ilike("endereco", escolaDTO.getEndereco()),
						Restrictions.ilike("nome", escolaDTO.getNome())
						))
				.uniqueResult();

		if(escola != null){
			return true;
		}else{
			return false;
		}
	}

	public List<TurnoDTO> populaTurno(Integer id) {
		List<TurnoDTO> list =null;/* session.createCriteria(TurnoDTO.class)
				.add(Restrictions.eq(propertyName, id))
				.list();
		*/
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<EscolaDTO> filtrar(Integer idEscola, String nomeEscola) throws HibernateException, Exception {
		
		List<EscolaDTO> list =  HibernateUtility.getSession().createCriteria(EscolaDTO.class)
				.add(Restrictions.or( 
						Restrictions.eq("id", idEscola),
						Restrictions.ilike("nome",nomeEscola)
						))
			.list();
		return list;
	}

}
