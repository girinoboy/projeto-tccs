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

import br.com.dto.FinanceiroDTO;
import br.com.dto.RelatorioGestaoMensalDTO;
import br.com.dto.UsuarioDTO;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FinanceiroDTO> consultaPorMesAno(Object object) throws Exception {
		List<FinanceiroDTO> result;
		List<UsuarioDTO> resultU;
		Calendar data = new GregorianCalendar();
		data.setTime((Date) object);
		try{
			
			List array = HibernateUtility.getSession().createCriteria(FinanceiroDTO.class)
					.add(Restrictions.eq("mes",  data.get(Calendar.MONTH)))
					.add(Restrictions.eq("ano",data.get(Calendar.YEAR)))
					.setProjection(Projections.projectionList().add(Projections.property("usuarioDTO.id"))).list();
			if(array.isEmpty())
				array.add(0);
			resultU = HibernateUtility.getSession().createCriteria(UsuarioDTO.class)
					.add(Restrictions.not(Restrictions.in("id",array)))
					.add(Restrictions.isNotNull("financeiroDTO.id"))
					.list();
			if(resultU.size()>0){
				for (UsuarioDTO usuarioDTO : resultU) {
					usuarioDTO.getFinanceiroDTO().setId(null);
					usuarioDTO.getFinanceiroDTO().setSituacao(false);
					usuarioDTO.getFinanceiroDTO().setDataPagamento(data.getTime());
					usuarioDTO.getFinanceiroDTO().setDia(null);
					usuarioDTO.getFinanceiroDTO().setMes(null);
					usuarioDTO.getFinanceiroDTO().setAno(null);
					usuarioDTO.getFinanceiroDTO().getDataPagamento();
					try{
						save(usuarioDTO.getFinanceiroDTO());
					}catch(Exception e){
						save(usuarioDTO.getFinanceiroDTO());
					}
				}
			}
			result =  HibernateUtility.getSession().createCriteria(FinanceiroDTO.class)
					.add(Restrictions.eq("mes",  data.get(Calendar.MONTH)))
					.add(Restrictions.eq("ano",data.get(Calendar.YEAR)))
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
