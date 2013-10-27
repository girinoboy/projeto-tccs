/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dto.LivroDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
public class LivroMB extends GenericoMB implements ModeloMB{
	
	private LivroDTO livroDTO = new LivroDTO();

	/**
	 * 
	 */
	public LivroMB() {
		// TODO Auto-generated constructor stub
	}


	public void add(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public LivroDTO getLivroDTO() {
		return livroDTO;
	}
	
	public void setLivroDTO(LivroDTO livroDTO) {
		this.livroDTO = livroDTO;
	}
}
