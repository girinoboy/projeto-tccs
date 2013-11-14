/**
 * 
 */
package br.com.mb;

import java.util.List;

import javax.faces.event.ActionEvent;

import br.com.dao.FinanceiroDAO;
import br.com.dto.FinanceiroDTO;

/**
 * @author marcleonio.medeiros
 *
 */
public class FinanceiroMB extends GenericoMB implements ModeloMB{
	
	private FinanceiroDTO financeiroDTO = new FinanceiroDTO();
	private FinanceiroDAO financeiroDAO = new FinanceiroDAO();
	private List<FinanceiroDTO> listFinanceiroDTO;
	/**
	 * 
	 */
	public FinanceiroMB() {
		try {
			listFinanceiroDTO = financeiroDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(ActionEvent actionEvent) throws Exception {
		financeiroDAO.save(financeiroDTO);
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public FinanceiroDTO getFinanceiroDTO() {
		return financeiroDTO;
	}

	public void setFinanceiroDTO(FinanceiroDTO financeiroDTO) {
		this.financeiroDTO = financeiroDTO;
	}

	public List<FinanceiroDTO> getListFinanceiroDTO() {
		return listFinanceiroDTO;
	}

	public void setListFinanceiroDTO(List<FinanceiroDTO> listFinanceiroDTO) {
		this.listFinanceiroDTO = listFinanceiroDTO;
	}

}
