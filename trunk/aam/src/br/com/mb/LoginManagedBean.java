package br.com.mb;


import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.dao.ParametroDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.ParametroDTO;
import br.com.dto.PerfilDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.Constantes;


@ManagedBean
@RequestScoped
public class LoginManagedBean {

	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private HttpSession session;


	public LoginManagedBean(){}

	public String login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;
		String retorno = "ok";
		boolean adm = false;
		try{
			usuarioDTO = usuarioDAO.verificaLoginSenha(usuarioDTO);
			adm = usuarioDTO!=null&& usuarioDTO.getUsuario().equals("admin") && usuarioDTO.getPerfilDTO().getId().equals(1);
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);//true cria sess�o caso � exista - false retorna nulo caso � exista
			
			if(usuarioDTO != null && usuarioDTO.getTema() != null && usuarioDTO.getPerfilDTO().getId() !=null){
				loggedIn = true;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", usuarioDTO.getUsuario());
				session.setAttribute("usuarioAutenticado", usuarioDTO);
				session.setAttribute("adm", adm);
				FacesContext.getCurrentInstance().getExternalContext().redirect("layout.xhtml");
				//gp.setTheme(usuario.getTema());
			} if(usuarioDTO==null && usuarioDAO.list().size()==0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo", "Admin");
				usuarioDTO = new UsuarioDTO();
				//configura parametros do sistema
				usuarioDTO.setUsuario("admin");
				usuarioDTO.setSenha("admin");
				usuarioDTO.setTema("flick");
				usuarioDTO.setNome("Administrador do Sistema");
				usuarioDTO.setDataNascimento(new Date());
				usuarioDTO.setPerfilDTO(new PerfilDTO(1));
				usuarioDAO.save(usuarioDTO);
				ParametroDAO parametroDAO =new ParametroDAO();
				ParametroDTO p = new ParametroDTO();
				p.setNome("mensalidade");
				p.setValor("0");
				parametroDAO.save(p);
				session.setAttribute("adm", adm);
				FacesContext.getCurrentInstance().getExternalContext().redirect("layout.xhtml");
			}else {
				loggedIn = false;  
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");  
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
			session.removeAttribute("adm");
			//session.setAttribute("usuarioAutenticado", null);
			//session.setAttribute("usuarioSession", null);
			//session.setAttribute("indexController", null);

			session.invalidate();
			ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/" + Constantes.PAGINA_LOGIN);


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



}
