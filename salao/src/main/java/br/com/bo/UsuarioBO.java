/**
 * 
 */
package br.com.bo;

import br.com.dao.UsuarioDAO;
import br.com.model.UsuarioDTO;

/**
 * @author Joelson
 *
 */
public class UsuarioBO {
	
	private UsuarioDAO dao = new UsuarioDAO();
	
	public UsuarioBO() {
	}
	
	public String efetuaLogin(UsuarioDTO usuario){
		boolean loginValido = dao.existe(usuario);		
		System.out.println("O login era valido? "+loginValido);
		if (loginValido) {
			return "produto?faces-redirect=true";
		}else{
			usuario = new UsuarioDTO();
			return "login";
		}
	
	}
	public boolean isLogado(UsuarioDTO usuario) {
		return usuario.getLogin() != null;
	}
	

}
