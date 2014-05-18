/**
 * 
 */
package br.com.mb;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	private HttpSession session;
	private Usuario usuario = new Usuario();
	
	public String efetuaLogin() throws IOException{
		
		UsuarioDAO dao = new UsuarioDAO(Usuario.class);
		
		if(dao.bancoVazio()){
			Usuario u = new Usuario();
			u.setLogin("admin");
			u.setSenha("admin");
			dao.adicionar(u);
			usuario=u;
		}
		
		boolean loginValido = dao.existe(this.usuario);
		System.out.println("O login é valido? "+ loginValido);
		if (loginValido) {
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);//true cria sessão caso ñ exista - false retorna nulo caso ñ exista
			session.setAttribute("usuarioAutenticado", usuario);
//			FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");
			return "menu";
		}else{
			addMessage("Erro ao logar.");
			this.usuario = new Usuario();
			return "login";
		}
	}
	
	public void logout() {

		try {

			FacesContext ctx = FacesContext.getCurrentInstance();
			session = (HttpSession) ctx.getExternalContext().getSession(false);
			session.removeAttribute("usuarioAutenticado");
			ctx.getExternalContext().redirect("login.xhtml");
			session.invalidate();

		} catch (Exception e) {

		}

	}

	public Usuario getUsuario() {
		return this.usuario;
	}
}
