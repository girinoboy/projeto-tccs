/**
 * 
 */
package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@RequestScoped
public class UsuarioMB extends GenericoMB implements ModeloMB{
	
	private List<UsuarioDTO> listUsuario;
	private List<UsuarioDTO> filteredUsuarios;
	private UsuarioDTO usuarioSelecionado = new UsuarioDTO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	
	public UsuarioMB(){
		try {
			listUsuario = usuarioDAO.list();
		} catch (Exception e) {
			addMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void reset(ActionEvent event) { 
        RequestContext.getCurrentInstance().reset("form:panel");  
    } 
	
	
	public void add(ActionEvent actionEvent) throws Exception {
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("salvo", false);
		usuarioDAO.save(usuarioDTO);
		context.addCallbackParam("salvo", true);
		addMessage("Salvo.");
		usuarioDTO = new UsuarioDTO();
	}
	
	public void edit(ActionEvent actionEvent) throws Exception {
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("salvo", false);
		usuarioDTO = (UsuarioDTO) usuarioDAO.listById(usuarioSelecionado.getId());
		context.addCallbackParam("salvo", true);
		
	}
	
	public String edit(SelectEvent event) throws Exception {  
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("salvo", false);
		usuarioDTO = usuarioDAO.getById(usuarioSelecionado.getId());
		context.addCallbackParam("salvo", true);
		
		return "editar";
	}
	
	public void del(ActionEvent actionEvent){
		try {
			if(usuarioSelecionado !=null && usuarioSelecionado.getId() !=null){
			usuarioDAO.delete(usuarioSelecionado);
			listUsuario = usuarioDAO.list();
			addMessage("Apagado.");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * @return the listUsuario
	 */
	public List<UsuarioDTO> getListUsuario() {
		return listUsuario;
	}
	/**
	 * @param listUsuario the listUsuario to set
	 */
	public void setListUsuario(List<UsuarioDTO> listUsuario) {
		this.listUsuario = listUsuario;
	}
	/**
	 * @return the filteredUsuarios
	 */
	public List<UsuarioDTO> getFilteredUsuarios() {
		return filteredUsuarios;
	}
	/**
	 * @param filteredUsuarios the filteredUsuarios to set
	 */
	public void setFilteredUsuarios(List<UsuarioDTO> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}
	/**
	 * @return the usuarioSelecionado
	 */
	public UsuarioDTO getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	/**
	 * @param usuarioSelecionado the usuarioSelecionado to set
	 */
	public void setUsuarioSelecionado(UsuarioDTO usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}


	/**
	 * @return the usuarioDTO
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}


	/**
	 * @param usuarioDTO the usuarioDTO to set
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
