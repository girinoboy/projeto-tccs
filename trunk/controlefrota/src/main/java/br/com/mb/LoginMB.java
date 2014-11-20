/**
 * 
 */
package br.com.mb;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;



import br.com.dao.MarcaDAO;
//import br.com.dao.LocalDAO;
import br.com.dao.MenuDAO;
import br.com.dao.PerfilDAO;
import br.com.dao.PerfilMenuDAO;
import br.com.dao.UsuarioDAO;
import br.com.dao.UsuarioPerfilDAO;
import br.com.dto.MarcaDTO;
//import br.com.dto.LocalDTO;
import br.com.dto.MenuDTO;
import br.com.dto.PerfilDTO;
import br.com.dto.PerfilMenuDTO;
import br.com.dto.UsuarioDTO;
import br.com.dto.UsuarioPerfilDTO;
import br.com.utility.Constantes;

/**
 * @author Marcle�nio
 *
 */
@ManagedBean
@ViewScoped
public class LoginMB extends GenericoMB<UsuarioDTO>{
	
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private HttpSession session;
//	@ManagedProperty(value = "#{guestPreferences}")
//	private GuestPreferences gp;

	/**
	 * 
	 */
	public LoginMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;
		boolean adm = false;
		
		try{
			usuarioDTO = usuarioDAO.verificaLoginSenha(usuarioDTO);
			adm = usuarioDTO!=null&& usuarioDTO.getUsuario().equals("admin") && usuarioDTO.getPerfilDTO().getId().equals(1);
			session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);//true cria sess�o caso � exista - false retorna nulo caso � exista
			
			if(usuarioDTO != null && usuarioDTO.getTema() != null){
				loggedIn = true;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("welcome"), usuarioDTO.getUsuario());
				session.setAttribute("usuarioAutenticado", usuarioDTO);
				session.setAttribute("listMenuAutenticado",usuarioDTO.getPerfilDTO().getListMenu());
				session.setAttribute("adm", adm);
				
//				gp.setTheme(usuarioDTO.getTema());
				context.execute("PF('dlg').hide();");
				FacesContext.getCurrentInstance().getExternalContext().redirect("layoutElement.xhtml");
			} else if(usuarioDTO==null && usuarioDAO.list().size()==0){
				//***********cria os meus iniciais********//
				criaMenus();
				//***********cria os meus iniciais********//

				loggedIn = true;
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, rb.getString("welcome"), usuarioDTO.getUsuario());
				
				session.setAttribute("adm", adm);
				session.setAttribute("usuarioAutenticado", usuarioDTO);
				session.setAttribute("listMenuAutenticado",usuarioDTO.getPerfilDTO().getListMenu());
//				gp.setTheme(usuarioDTO.getTema());
				context.execute("PF('dlg').hide();");
				FacesContext.getCurrentInstance().getExternalContext().redirect("layoutElement.xhtml");
			}else{
				loggedIn = false;  
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials"); 
				usuarioDTO = new UsuarioDTO();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Erro no banco");  
		}finally{
			context.addCallbackParam("loggedIn", loggedIn);
			context.addCallbackParam("perfil", usuarioDTO.getPerfilDTO().getId());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}

	private void criaMenus() throws Exception {
		//cria perfil
		PerfilDTO perfilDTO = new PerfilDAO().save(new PerfilDTO(1));
		new PerfilDAO().save(new PerfilDTO(2,"Chefe"));//Chefe
		new PerfilDAO().save(new PerfilDTO(3,"Motorista"));//Motorista
		
		//cria salas
//		new LocalDAO().save(new LocalDTO("Sala 1"));
//		new LocalDAO().save(new LocalDTO("Sala 2"));
		
		//cria usuario
		usuarioDTO = new UsuarioDTO();
		usuarioDTO.setMatricula("admin");
		usuarioDTO.setUsuario("admin");
		usuarioDTO.setSenha("admin");
		usuarioDTO.setTema("flick");
		usuarioDTO.setNome("Administrador do Sistema");
		usuarioDTO.setDataNascimento(new Date());
		usuarioDTO.setPerfilDTO(new PerfilDTO(1));
		usuarioDTO = usuarioDAO.save(usuarioDTO);
		
		//INSERT INTO USUARIO_PERFIL VALUES(1,1,1);
		new UsuarioPerfilDAO().save(new UsuarioPerfilDTO(usuarioDTO, perfilDTO));
		
		//cria menus
		PerfilMenuDAO perfilMenuDAO = new PerfilMenuDAO();
		MenuDAO menuDAO = new MenuDAO();
		MenuDTO menuDTO;
		int contPai = 0;

		menuDTO = new MenuDTO();
		menuDTO.setNome("accessControl");
		menuDTO.setUrl("controleAcesso.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("profileRegister");
//				menuDTO.setUrl("controleAcesso.xhtml");
		menuDTO.setOutcome("cadastrarPerfil.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("configureMenu");
		menuDTO.setUrl("configuraMenu.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));

		menuDTO = new MenuDTO();
		menuDTO.setNome("user");
		menuDTO.setUrl("listarUsuarios.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("Veículo");
		menuDTO.setUrl("listarVeiculos.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("Atendimento");
		menuDTO.setUrl("listarAtendimentos.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("Abastecimento");
		menuDTO.setUrl("listarAbastecimentos.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("Oficina");
//		menuDTO.setUrl("listarAbastecimentos.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("Posto");
//		menuDTO.setUrl("listarAbastecimentos.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("Manutenção");
//		menuDTO.setUrl("listarAbastecimentos.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("Multas");
//		menuDTO.setUrl("listarAbastecimentos.xhtml");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("Relatório");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		MenuDTO menuPai = menuDTO;
		menuDTO = new MenuDTO();
		menuDTO.setNome("Quilômetros rodados da frota");
		menuDTO.setUrl("relatorioKmRodadosFrota.xhtml");
		menuDTO.setComando("#{veiculoMB.atualiza}");
		menuDTO.setMenuDTO(menuPai);
		menuDTO.setDropIndex(0);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		
		menuDTO = new MenuDTO();
		menuDTO.setNome("exit");
		menuDTO.setComando("#{loginMB.logout}");
		menuDTO.setDropIndex(contPai++);
		menuDTO.setAtivoInativo(true);
		menuDTO = menuDAO.save(menuDTO);				
		perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
		
		MarcaDAO marcadao = new MarcaDAO();
		MarcaDTO marcaDTO = new MarcaDTO();
		marcaDTO.setNome("Fiat");
		marcadao.save(marcaDTO );
		
//		PerfilMenuDTO perfilMenuDTO = perfilMenuDAO.save(new PerfilMenuDTO(perfilDTO, menuDTO, true));
//		System.out.println(perfilMenuDTO);
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

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
