/**
 * 
 */
package br.com.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;

/**
 * @author Joelson
 * 
 */
@SessionScoped
@ManagedBean
public class LoginBean extends GenericBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4541568645378811608L;
	private Usuario usuario = new Usuario();
	
	public String efetuaLogin() throws IOException{
		
		UsuarioDAO dao = new UsuarioDAO(Usuario.class);
		
		if(dao.bancoVazio()){
			Usuario u = new Usuario();
			u.setLogin("admin");
			u.setSenha("admin");
			dao.adicionar(u);
		}
		
		boolean loginValido = dao.existe(this.usuario);
		System.out.println("O login é valido? "+ loginValido);
		if (loginValido) {
//			FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");
			return "menu";
		}else{
			addMessage("Erro ao logar.");
			this.usuario = new Usuario();
			return "login";
		}
		
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
}
