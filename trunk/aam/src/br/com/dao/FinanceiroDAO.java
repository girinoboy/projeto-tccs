/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.dto.FinanceiroDTO;
import br.com.dto.RelatorioGestaoMensalDTO;
import br.com.factory.HibernateUtility;

/**
 * @author marcleonio.medeiros
 *
 */
public class FinanceiroDAO extends GenericoDAO<FinanceiroDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6593990035853478798L;

	/**
	 * 
	 */
	public FinanceiroDAO() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<FinanceiroDTO> consultaPorMesAno(Object object) throws Exception {
		List<FinanceiroDTO> result;
		Calendar data = new GregorianCalendar();
		data.setTime((Date) object);
		try{
			List valores = HibernateUtility.getSession().createCriteria(FinanceiroDTO.class)
					.add(Restrictions.eq("mes",  data.get(Calendar.MONTH)))
					.add(Restrictions.eq("ano",data.get(Calendar.YEAR)))
					.setProjection(Projections.projectionList()
				            .add(Projections.property("usuarioDTO.id"))
				    )
					.list();
			result =  HibernateUtility.getSession().createCriteria(FinanceiroDTO.class)
					
//					.add(Restrictions.eq("mes",  data.get(Calendar.MONTH)))
//					.add(Restrictions.eq("ano",data.get(Calendar.YEAR)))
					.add(Restrictions.or(
							Restrictions.and(Restrictions.eq("mes",  data.get(Calendar.MONTH)), Restrictions.eq("ano",data.get(Calendar.YEAR))),
					Restrictions.not(Restrictions.in("usuarioDTO.id", valores)))
					
					)
					.list();
			
			
		}catch(Exception e){
			throw e;
		}

		return result;
	}
	
	
	public List<?> listRelatorioGestaoMensal(RelatorioGestaoMensalDTO relatorioGestaoMensal) throws Exception {
		List<?> result;
		try{
			result =  HibernateUtility.getSession().createSQLQuery("select "
					
						+ " (select count(*) from usuario u inner join financeiro f on f.usuario_id = u.id where f.mes = financeiro.mes group by mes) as total_usuario,"
						+ " (select count(*) from usuario u inner join financeiro f on f.usuario_id = u.id where f.mes = financeiro.mes and ativoInativo = 1 group by mes) ativo,"
						+ " (select count(*) from usuario u inner join financeiro f on f.usuario_id = u.id where f.mes = financeiro.mes and ativoInativo = 0 group by mes) inativo,"
						+ " (select count(*) from financeiro f where f.mes = financeiro.mes and situacao = 1) sem_pendencia,"
						+ " (select count(*) from financeiro f where f.mes = financeiro.mes and situacao = 0) com_pendencia,"
						+ " (select sum(valor_com_desconto) from financeiro f where f.mes = financeiro.mes) total_arrecadado"
						+ " ,mes,ano"

						+ " from  financeiro")
//					.addEntity(RelatorioGestaoMensalDTO.class)
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}

}
