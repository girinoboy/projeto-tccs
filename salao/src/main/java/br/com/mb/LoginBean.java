/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bo.UsuarioBO;
import br.com.model.UsuarioDTO;

/**
 * @author Joelson
 *
 */
@ViewScoped
@ManagedBean
public class LoginBean {

	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private UsuarioBO usuarioBO = new UsuarioBO();
	
	public void efetuaLogin(){
		usuarioBO.efetuaLogin(usuarioDTO);
	}
	
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}


	public boolean isLogado() {
		return usuarioBO.isLogado(usuarioDTO);
	}
}
