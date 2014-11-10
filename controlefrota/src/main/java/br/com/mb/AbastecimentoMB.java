/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dto.AbastecimentoDTO;

/**
 * @author marcleonio
 *
 */
@ManagedBean
public class AbastecimentoMB extends GenericoMB<AbastecimentoDTO> implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3823196401563183947L;

	/**
	 * 
	 */
	public AbastecimentoMB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		super.inicio();
		
	}

	@Override
	public void reset(ActionEvent event) {
		abstractDTO = new AbastecimentoDTO();
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		abstractDAO.save(abstractDTO);
		addMessage(rb.getString("successfullySaved"));
		atualiza(null);
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		abstractDAO.delete(abstractDTO);
		addMessage(rb.getString("successfullyDeleted"));
		atualiza(null);
	}

}
