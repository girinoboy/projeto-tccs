package br.com.mb;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.dao.AnexoDAO;
import br.com.dao.GraduacaoDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.AnexoDTO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.UsuarioDTO;

@ManagedBean
public class UsuarioMB extends GenericoMB implements ModeloMB{
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private List<UsuarioDTO> listUsuariDTO = new ArrayList<UsuarioDTO>();
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();
	private AnexoDAO anexoDAO = new AnexoDAO();
	
	public UsuarioMB(){
		try {
			listUsuariDTO = usuarioDAO.list();
			listGraduacaoDTO = graduacaoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public StreamedContent getDynamicImage() {
		byte[] emptyImage = new byte[0];
		try{
			String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("image_id");
			//String filterValue = (String) FacesContext.getCurrentInstance().getAttributes().get("image_id");
			if (id != null && !id.equals("")){
				Integer imagemId = Integer.valueOf(id);
				AnexoDTO anexoDTO = anexoDAO.getById(imagemId);
				if(anexoDTO != null && anexoDTO.getAnexo() != null) {
					return new DefaultStreamedContent(new ByteArrayInputStream(anexoDTO.getAnexo()),"image/png");
				}
			}else
				return new DefaultStreamedContent(new ByteArrayInputStream(emptyImage), "image/png");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(e.getMessage());
		}

		return new DefaultStreamedContent(new ByteArrayInputStream(emptyImage), "image/png");
	}
	
	public void handleSelect(SelectEvent event) {  
		
		try {
			usuarioDTO = (UsuarioDTO)event.getObject();
			
			addMessage("Selected:" + usuarioDTO.getId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(ActionEvent actionEvent) throws Exception {
		usuarioDAO.save(usuarioDTO);
		addMessage("Salvo");
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public List<UsuarioDTO> getListUsuariDTO() {
		return listUsuariDTO;
	}

	public void setListUsuariDTO(List<UsuarioDTO> listUsuariDTO) {
		this.listUsuariDTO = listUsuariDTO;
	}

	public List<GraduacaoDTO> getListGraduacaoDTO() {
		return listGraduacaoDTO;
	}

	public void setListGraduacaoDTO(List<GraduacaoDTO> listGraduacaoDTO) {
		this.listGraduacaoDTO = listGraduacaoDTO;
	}

}
