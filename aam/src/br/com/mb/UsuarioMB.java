package br.com.mb;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.dao.AnexoDAO;
import br.com.dao.GraduacaoDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.AnexoDTO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.MembroDataModel;

@ManagedBean
public class UsuarioMB extends GenericoMB implements ModeloMB{

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private List<UsuarioDTO> listUsuarioDTO = new ArrayList<UsuarioDTO>();
	private UsuarioDTO[] listSelectedUsuarioDTO;// = new ArrayList<UsuarioDTO>();
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();
	private AnexoDAO anexoDAO = new AnexoDAO();

	private MembroDataModel membroDataModel; 

	public UsuarioMB(){
		try {
			listUsuarioDTO = usuarioDAO.list();
			listGraduacaoDTO = graduacaoDAO.list();

			membroDataModel = new MembroDataModel(listUsuarioDTO);

			//			listUsuarioDTO = new ArrayList<UsuarioDTO>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleFileUpload(FileUploadEvent event) throws Exception {

		usuarioDTO.getAnexoDTO().setNome(event.getFile().getFileName());
		usuarioDTO.getAnexoDTO().setAnexo(event.getFile().getContents());
		usuarioDTO.getAnexoDTO().setTamanho(event.getFile().getSize());
		usuarioDTO.getAnexoDTO().setContentType(event.getFile().getContentType());

		//usuarioDTO = usuarioDAO.save(usuarioDTO);
		usuarioDTO.setAnexoDTO(anexoDAO.save(usuarioDTO.getAnexoDTO()));
		addMessage("Imagem add");
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

	public void add(ActionEvent actionEvent) {
		try{
			usuarioDTO.setAnexoDTO(anexoDAO.getById(usuarioDTO.getAnexoDTO().getId()));
			usuarioDAO.save(usuarioDTO);
			addMessage("Salvo");
			usuarioDTO = new UsuarioDTO();
			listUsuarioDTO = usuarioDAO.list();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	public void del(ActionEvent actionEvent) {
		try{
			//System.out.println(listSelectedUsuarioDTO);
			for (UsuarioDTO u : listSelectedUsuarioDTO) {
				usuarioDAO.delete(u);
			}
			if(listSelectedUsuarioDTO.length >0){
				addMessage("Apagado.");
			}else{
				addMessage("Nenhum Item Selecionado.");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				listUsuarioDTO = usuarioDAO.list();

				listGraduacaoDTO = graduacaoDAO.list();

				membroDataModel = new MembroDataModel(listUsuarioDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void onEdit(RowEditEvent event) {  
		FacesMessage msg = new FacesMessage("Car Edited", ((UsuarioDTO) event.getObject()).getId().toString());  

		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}  

	public void onCancel(RowEditEvent event) {  
		FacesMessage msg = new FacesMessage("Car Cancelled", ((UsuarioDTO) event.getObject()).getId().toString());  

		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}  

	public void onCellEdit(CellEditEvent event) throws Exception {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		usuarioDAO.save((UsuarioDTO) membroDataModel.getRowData());
		if(newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void check(SelectEvent event) {
		System.out.println("in check");
		System.out.println(listSelectedUsuarioDTO);
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public List<UsuarioDTO> getListUsuarioDTO() {
		return listUsuarioDTO;
	}

	public void setListUsuarioDTO(List<UsuarioDTO> listUsuarioDTO) {
		this.listUsuarioDTO = listUsuarioDTO;
	}

	public List<GraduacaoDTO> getListGraduacaoDTO() {
		return listGraduacaoDTO;
	}

	public void setListGraduacaoDTO(List<GraduacaoDTO> listGraduacaoDTO) {
		this.listGraduacaoDTO = listGraduacaoDTO;
	}


	public MembroDataModel getMembroDataModel() {
		return membroDataModel;
	}

	public void setMembroDataModel(MembroDataModel membroDataModel) {
		this.membroDataModel = membroDataModel;
	}

	public UsuarioDTO[] getListSelectedUsuarioDTO() {
		return listSelectedUsuarioDTO;
	}

	public void setListSelectedUsuarioDTO(UsuarioDTO[] listSelectedUsuarioDTO) {
		this.listSelectedUsuarioDTO = listSelectedUsuarioDTO;
	}

}
