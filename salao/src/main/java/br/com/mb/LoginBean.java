/**
 * 
 */
package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.bo.UsuarioBO;
import br.com.model.Usuario;

/**
 * @author Joelson
 *
 */
@ViewScoped
@ManagedBean
public class LoginBean {

	private Usuario usuario = new Usuario();
	private UsuarioBO usuarioBO = new UsuarioBO();
	
	public void efetuaLogin(){
		usuarioBO.efetuaLogin(usuario);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}


	public boolean isLogado() {
		return usuarioBO.isLogado(usuario);
	}
}
