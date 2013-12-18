package br.com.mb;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;
import br.com.utility.Constantes;


@ManagedBean
@ViewScoped
public class LoginManagedBean extends GenericoMB{

	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private HttpSession session;
	@ManagedProperty(value = "#{guestPreferences}")
	private GuestPreferences gp;

	public LoginManagedBean(){}

	public String login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;
		String retorno = "ok";
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);//true cria sessão caso ñ exista - false retorna nulo caso ñ exista

		try{  
			if(usuarioDTO.getUsuario().equals("admin2") && usuarioDTO.getSenha().equals("admin2")){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo", "Admin");
				FacesContext.getCurrentInstance().getExternalContext().redirect("layoutElement.xhtml");
				usuarioDTO.setId(1);
				usuarioDTO.setNome("Mark");
				usuarioDTO.setSenha("123456");
				session.setAttribute("usuarioAutenticado", usuarioDTO);
			}else{
				usuarioDTO = usuarioDAO.verificaLoginSenha(usuarioDTO);

				if(usuarioDTO != null && usuarioDTO.getTema() != null){
					loggedIn = true;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("welcome"), usuarioDTO.getUsuario());
					session.setAttribute("usuarioAutenticado", usuarioDTO);
					session.setAttribute("listMenuAutenticado",usuarioDTO.getPerfilDTO().getListMenu());

					gp.setTheme(usuarioDTO.getTema());
				} else {
					loggedIn = false;  
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");  
				}
			}

			FacesContext.getCurrentInstance().addMessage(null, msg);  
			context.addCallbackParam("loggedIn", loggedIn);

		}catch(Exception e){
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Erro no banco");  
			FacesContext.getCurrentInstance().addMessage(null, msg); 
		}

		context.addCallbackParam("perfil", retorno);
		return retorno;  
	}

	public void logout() {

		try {

			FacesContext ctx = FacesContext.getCurrentInstance();

			session = (HttpSession) ctx.getExternalContext().getSession(false);

			session.removeAttribute("usuarioAutenticado");
			//session.setAttribute("usuarioAutenticado", null);
			//session.setAttribute("usuarioSession", null);
			//session.setAttribute("indexController", null);

			//ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/" + Constantes.PAGINA_LOGIN);
			ctx.getExternalContext().redirect(Constantes.PAGINA_LOGIN);
			session.invalidate();

		} catch (Exception e) {

		}

	}

	/**
	 * @return the usuario
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setGp(GuestPreferences gp) {  
		this.gp = gp;  
	} 

}
