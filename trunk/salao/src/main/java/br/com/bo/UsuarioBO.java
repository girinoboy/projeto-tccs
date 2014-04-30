/**
 * 
 */
package br.com.bo;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;

/**
 * @author Joelson
 *
 */
public class UsuarioBO {
	
	private UsuarioDAO dao = new UsuarioDAO();
	
	public UsuarioBO() {
	}
	
	public String efetuaLogin(Usuario usuario){
		boolean loginValido = dao.existe(usuario);		
		System.out.println("O login era valido? "+loginValido);
		if (loginValido) {
			return "produto?faces-redirect=true";
		}else{
			usuario = new Usuario();
			return "login";
		}
	
	}
	public boolean isLogado(Usuario usuario) {
		return usuario.getLogin() != null;
	}
	

}
