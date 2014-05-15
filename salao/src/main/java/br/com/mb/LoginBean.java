/**
 * 
 */
package br.com.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;

/**
 * @author Joelson
 * 
 */
@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4541568645378811608L;
	private Usuario usuario = new Usuario();
	
	public String efetuaLogin(){
		UsuarioDAO dao = new UsuarioDAO();
		boolean loginValido = dao.existe(this.usuario);
		System.out.println("O login é valido? "+ loginValido);
		if (loginValido) {
			return "cadastro";
		}else{
			this.usuario = new Usuario();
			return "login";
		}
		
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
}
