/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;

/**
 * @author marcleonio
 *
 */
@ManagedBean
@ViewScoped
public class FrequenciaMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9007826101364461566L;
	
	private UsuarioDTO usuarioDTO;
	
	private UsuarioDAO usuarioDAO;

	/**
	 * 
	 */
	public FrequenciaMB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
