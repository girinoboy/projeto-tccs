/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import br.com.dto.CidadeDTO;
import br.com.dto.EscolaDTO;
import br.com.dto.MetaDTO;
import br.com.dto.UsuarioDTO;

/**
 * @author Marcleônio
 *
 */
@SuppressWarnings("rawtypes")
public class ChartDAO extends GenericoDAO<EscolaDTO, Serializable>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChartDAO(){
		
	}

	@SuppressWarnings("unchecked")
	public List<MetaDTO> metaByIdDivulgador(UsuarioDTO usuarioDTO){
		/*
		Query query = session.createQuery("update Stock set stockName = :stockName" +
				" where stockCode = :stockCode");
		query.setParameter("stockName", "DIALOG1");
		query.setParameter("stockCode", "7277");
		int result = query.executeUpdate();
		 */
		Query query = session.createSQLQuery(
				"select c.id,c.nome,sum(l.preco*el.quantidade_aluno) total from escola_divulgador ed"+
						" inner join escola e on e.id = ed.escola_id"+
						" inner join usuario d on d.id = ed.usuario_id"+
						" inner join cidade c on c.id = e.cidade_id"+
						" inner join meta m on m.cidade_id =c.id"+
						" inner join escola_livro el on el.escola_id = e.id"+
						" inner join livro l on l.id = el.livro_id"+
						" where d.id = :idUsuario"+
				" group by c.id,c.nome")

		.setParameter("idUsuario", usuarioDTO.getId());

		return query.list();
	}

	public MetaDTO metaByIdCidade(CidadeDTO cidadeDTO){

		MetaDTO metaDTO = (MetaDTO) session.createCriteria(MetaDTO.class)
				.add(Restrictions.eq("cidadeDTO.id", cidadeDTO.getId()))
				.uniqueResult();

		return metaDTO;
	}


	public List escolasVisitadasAdotaramLivro(Integer ano) throws HibernateException, Exception {

		Query query = session.createSQLQuery(
				" select c.nome,count(e.nome) from escola e"+
				" inner join cidade c on e.cidade_id=c.id"+
				" where e.id in"+
				" ("+
				" select ev.escola_id from escola_visitada ev"+
				" where ev.escola_id in(select el.escola_id from escola_livro el)"+
				" and year(data_visita) = :ano"+
				" )"+
				" group by c.nome"
				)
				.setParameter("ano",ano);

		return query.list();
	}


	
	public List escolasVisitadasNaoAdotaramLivro(Integer ano) throws HibernateException, Exception {
		Query query = session.createSQLQuery(
				" select c.nome, count(e.nome) from escola e"+
				" inner join cidade c on e.cidade_id=c.id"+
				" where e.id in"+
				" ("+
				" select ev.escola_id from escola_visitada ev "+
				" where ev.escola_id not in(select el.escola_id from escola_livro el)"+
				" and year(ev.data_visita) = :ano"+
				" )"+
				" group by c.nome"
				)
				.setParameter("ano",ano);
		return query.list();
	}


	public List escolasNaoVisitadas(Integer ano) throws HibernateException, Exception {
		
		Query query = session.createSQLQuery(
				" select c.nome, count(e.nome) total from escola e"+
				" inner join cidade c on c.id = e.cidade_id"+
		" where e.id not in(select escola_id from escola_visitada where year(data_visita) = :ano)"+
		" group by c.nome")
		.setParameter("ano",ano);
		return query.list();
	}

}
