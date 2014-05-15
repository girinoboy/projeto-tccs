/**
 * 
 */
package br.com.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bo.UsuarioBO;
import br.com.model.Usuario;

/**
 * @author Joelson
 *
 */
@ViewScoped
@ManagedBean
public class LoginBean implements Serializable{

	private static final long serialVersionUID = -2666993159978308992L;
	private Usuario usuarioDTO = new Usuario();
	private UsuarioBO usuarioBO = new UsuarioBO();
	
	public void efetuaLogin(){
		usuarioBO.efetuaLogin(usuarioDTO);
	}
	
	public Usuario getUsuarioDTO() {
		return usuarioDTO;
	}


	public boolean isLogado() {
		return usuarioBO.isLogado(usuarioDTO);
	}
}
