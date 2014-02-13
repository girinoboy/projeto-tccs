package br.com.mb;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.dao.AnexoDAO;
import br.com.dao.GenericoDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.AnexoDTO;
import br.com.dto.TecnicaDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.Constantes;

@ManagedBean
public class GenericoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected AnexoDAO anexoDAO = new AnexoDAO();
	private HttpSession session;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public void setUserSession(UsuarioDTO usuarioDTO){
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);//true cria sess�o caso � exista - false retorna nulo caso � exista
		session.setAttribute("usuarioAutenticado", usuarioDTO);
	}
	
	public UsuarioDTO getUserSession(){
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return ((UsuarioDTO) session.getAttribute("usuarioAutenticado"));
	}
	
	public Boolean getAdm(){
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return ((Boolean) session.getAttribute("adm"));
	}
	
	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}
	
	public List<UsuarioDTO> completarUsuario(String query) throws Exception {
		List<UsuarioDTO> suggestions = new ArrayList<UsuarioDTO>();
		
		Map<String, Object> filtrosConsulta = new HashMap<String, Object>();
		filtrosConsulta.put("nome", query);
		suggestions = usuarioDAO .listCriterio(null, filtrosConsulta, Constantes.TIPO_CONSULTA_ILIKE);

		return suggestions;
	}
	
	public StreamedContent getDynamicImage() {
		byte[] emptyImage = new byte[0];
		try{
			String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("image_id");
			if(id == null){
				id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:idAnexo");
			}if(id == null){
				if(FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("tecnica")!=null)
					id = ((TecnicaDTO)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("tecnica")).getAnexoDTO().getId().toString();
			}
			//String filterValue = (String) FacesContext.getCurrentInstance().getAttributes().get("image_id");
			if (id != null && !id.equals("")){
				Integer imagemId = Integer.valueOf(id);
				AnexoDTO anexoDTO = anexoDAO.getById(imagemId);
				if(anexoDTO != null && anexoDTO.getAnexo() != null) {
					return new DefaultStreamedContent(new ByteArrayInputStream(anexoDTO.getAnexo()),anexoDTO.getContentType(),anexoDTO.getNome());
				}
			}else
				return new DefaultStreamedContent(new ByteArrayInputStream(emptyImage), "image/png");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}

		return new DefaultStreamedContent(new ByteArrayInputStream(emptyImage), "image/png");
	}

}
