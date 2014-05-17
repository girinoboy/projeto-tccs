/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
public class UsuarioMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	/**
	 * 
	 */
	public UsuarioMB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(ActionEvent event) {
		usuarioDTO = new UsuarioDTO();
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		usuarioDAO.save(usuarioDTO);
		System.out.println(111);
		addMessage("Salvo.");
//		atualizaUserList(usuarioDTO);
		usuarioDTO = new UsuarioDTO();
		
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
