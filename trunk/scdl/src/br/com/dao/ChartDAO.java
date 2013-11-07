/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import br.com.dto.CidadeDTO;
import br.com.dto.EscolaDTO;
import br.com.dto.EscolaVisitadaDTO;
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


	@SuppressWarnings({ "unused" })
	public List escolasVisitadasAdotaramLivro(Integer ano) throws HibernateException, Exception {
		Calendar data = new GregorianCalendar();

		Calendar dataMin = new GregorianCalendar(data.get(Calendar.YEAR),Calendar.JANUARY,01);
		Calendar dataMax = new GregorianCalendar(data.get(Calendar.YEAR), Calendar.DECEMBER,31);
		//List a = HibernateUtility.getSession().createCriteria(PagamentoDTO.class).list();
		List result = session.createCriteria(EscolaVisitadaDTO.class)
				.createCriteria("escolaDTO")
				.createCriteria("escolaLivroDTO")
//				.add(Restrictions.between("mes", dataMin.get(Calendar.MONTH), dataMax.get(Calendar.MONTH)))
//				.add(Restrictions.eq("ano", data.get(Calendar.YEAR)))
//				.setProjection(Projections.projectionList()
//						.add(Projections.groupProperty("mes"))
//						.add(Projections.sum("valor"))
//					  	)
//				.addOrder(Order.asc("mes"))
				.list();
		
		
//		select count(*) total,e.nome from escola_visitada ev
//		inner join escola e on e.id = ev.escola_id
//		inner join escola_livro el on e.id = el.escola_id
//		inner join cidade c on c.id = e.cidade_id
//		where ev.data_visita = now()
//		group by e.nome
		
		return result;
	}


	
	public List escolasVisitadasNaoAdotaramLivro(Integer ano) {
		// TODO Auto-generated method stub
		return null;
	}


	public List escolasNaoVisitadas(Integer ano) {
		// TODO Auto-generated method stub
		return null;
	}

}
