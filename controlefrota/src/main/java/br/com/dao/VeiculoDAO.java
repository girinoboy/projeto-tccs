/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import br.com.dto.VeiculoDTO;

/**
 * @author marcleonio
 *
 */
@SuppressWarnings("unchecked")
public class VeiculoDAO extends GenericoDAO<VeiculoDTO, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8663969675148891916L;

	/**
	 * 
	 */
	public VeiculoDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<VeiculoDTO> verificarAlertaQuilometragem() {
		List<VeiculoDTO> list = criteria 
				.add(Restrictions.disjunction()
				.add(Restrictions.eqProperty("kmAtual", "kmOleo"))
				.add(Restrictions.eqProperty("kmAtual", "kmPneu"))
				.add(Restrictions.eqProperty("kmAtual", "kmLitro"))
				.add(Restrictions.eqProperty("kmAtual", "kmRevisao")))
				.list();
		return list;
	}

	public List<VeiculoDTO> verificarAlertaGasto() {
		List<VeiculoDTO> list ;//= criteria 
//				.setProjection(Projections.projectionList().add( Projections.groupProperty("placa")
//						).add(CustomProjections.groupByHaving(columns, aliases, types, "count(distinct id) >= 3 and name='1"
//								+ "'")))
//				.list();
		
		String sql = "select placa,km_atual as kmAtual ,km_litro as kmLitro from veiculo v"
		+ " inner join abastecimento a on v.id = a.veiculo_id"
		+ " group by placa"
		+ " having km_atual < (km_litro * sum(quantidade_litros_abastecidos))";
		list = session.createSQLQuery(sql).addScalar("placa",StringType.INSTANCE).addScalar("kmAtual",LongType.INSTANCE).addScalar("kmLitro",LongType.INSTANCE).setResultTransformer(Transformers  
                .aliasToBean(VeiculoDTO.class)). list();
		return list;
	}

}
