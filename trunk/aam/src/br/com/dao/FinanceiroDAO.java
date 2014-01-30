/**
 * 
 */
package br.com.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.dto.FinanceiroDTO;
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
			result =  HibernateUtility.getSession().createCriteria(FinanceiroDTO.class)
					.add(Restrictions.eq("mes",  data.get(Calendar.MONTH)))
					.add(Restrictions.eq("ano",data.get(Calendar.YEAR)))
					.list();
		}catch(Exception e){
			throw e;
		}

		return result;
	}

}
