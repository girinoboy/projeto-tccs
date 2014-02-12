package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import br.com.dao.GraduacaoDAO;
import br.com.dao.GraduacaoTecnicaDAO;
import br.com.dto.AnexoDTO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.GraduacaoTecnicaDTO;
import br.com.dto.TecnicaDTO;
import br.com.utility.Constantes;
import br.com.utility.GraduacaoDataModel;


@ManagedBean
@SessionScoped
public class GraduacaoMB extends GenericoMB implements ModeloMB{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1718094179027910495L;
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private GraduacaoDTO graduacaoDTO = new GraduacaoDTO();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();
	private GraduacaoDataModel graduacaoDataModel;
	private GraduacaoDTO[] listSelectedGraduacaoDTO;
	private TecnicaDTO[] listSelectedTecnicaDTO;
	private List<TecnicaDTO> listTecnicaDTO;
	private GraduacaoTecnicaDAO graduacaoTecnicaDAO = new GraduacaoTecnicaDAO();
	private UploadedFile file;
	
	public GraduacaoMB() {
		try {
			atualiza(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualiza(ActionEvent event) throws Exception {
		listGraduacaoDTO = graduacaoDAO.list();
		//getDynamicImage();
		graduacaoDataModel = new GraduacaoDataModel(listGraduacaoDTO);
	}
	
	public void reset(ActionEvent event){
		graduacaoDTO = new GraduacaoDTO();
	}
	
	public void check(SelectEvent event) {
		System.out.println("in check");
		System.out.println(listSelectedGraduacaoDTO);
	}
	
	public void add(ActionEvent actionEvent) throws Exception {
//		for (TecnicaDTO e : listSelectedTecnicaDTO) {
//			graduacaoDTO.setListTecnica(new ArrayList<TecnicaDTO>());
//			graduacaoDTO.getListTecnica().add(e);
//		}
		
		graduacaoDAO.save(graduacaoDTO);
		reset(null);
		atualiza(actionEvent);
		addMessage("Operação realizada com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_GRADUACAO);
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(graduacaoDTO);
//		List<TecnicaDTO> a = new ArrayList<TecnicaDTO>();
//		if(graduacaoDTO.getListTecnica() !=null)
//		for (TecnicaDTO e : graduacaoDTO.getListTecnica() ) {
//			a.add(e);
//		}
//		graduacaoDTO.setListTecnica(a);
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		try{
			//System.out.println(listSelectedTecnicaDTO);
			for (GraduacaoDTO n : listSelectedGraduacaoDTO) {
				graduacaoDAO.delete(n);
			}
			if(listSelectedGraduacaoDTO.length >0){
				addMessage("Apagado.");
			}else{
				addMessage("Nenhum Item Selecionado.");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reset(null);
				atualiza(actionEvent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		AnexoDTO anexoDTO = new AnexoDTO();
		graduacaoDTO.setAnexoDTO(anexoDTO);
		graduacaoDTO.getAnexoDTO().setNome(event.getFile().getFileName());
		graduacaoDTO.getAnexoDTO().setAnexo(event.getFile().getContents());
		graduacaoDTO.getAnexoDTO().setTamanho(event.getFile().getSize());
		graduacaoDTO.getAnexoDTO().setContentType(event.getFile().getContentType());

		//usuarioDTO = usuarioDAO.save(usuarioDTO);
		graduacaoDTO.setAnexoDTO(anexoDAO.save(graduacaoDTO.getAnexoDTO()));
//		setUserSession(usuarioDTO);
		addMessage("Imagem add");
	}

	public GraduacaoDTO getGraduacaoDTO() {
		try{
		if(graduacaoDTO!=null && graduacaoDTO.getListTecnica()!=null){
			listTecnicaDTO = new ArrayList<TecnicaDTO>();
			List<GraduacaoTecnicaDTO> listGraduacaoTecnicaDTO = graduacaoTecnicaDAO.listByIdGraduacaoDTO(graduacaoDTO.getId());
			for (GraduacaoTecnicaDTO graduacaoTecnicaDTO : listGraduacaoTecnicaDTO) {
				listTecnicaDTO.add(graduacaoTecnicaDTO.getTecnicaDTO());
			}
			if(listTecnicaDTO.size() == graduacaoDTO.getListTecnica().size())
				graduacaoDTO.setListTecnica(listTecnicaDTO);
//			tecnicaDataModel = new AbstractDataModel<TecnicaDTO>(listTecnicaDTO);
		}
		}catch(Exception e){
			graduacaoDTO.setListTecnica(listTecnicaDTO);
		}
		return graduacaoDTO;
	}

	public void setGraduacaoDTO(GraduacaoDTO graduacaoDTO) {
		this.graduacaoDTO = graduacaoDTO;
	}

	public List<GraduacaoDTO> getListGraduacaoDTO() {
		return listGraduacaoDTO;
	}

	public void setListGraduacaoDTO(List<GraduacaoDTO> listGraduacaoDTO) {
		this.listGraduacaoDTO = listGraduacaoDTO;
	}

	public GraduacaoDataModel getGraduacaoDataModel() {
		return graduacaoDataModel;
	}

	public void setGraduacaoDataModel(GraduacaoDataModel graduacaoDataModel) {
		this.graduacaoDataModel = graduacaoDataModel;
	}

	public GraduacaoDTO[] getListSelectedGraduacaoDTO() {
		return listSelectedGraduacaoDTO;
	}

	public void setListSelectedGraduacaoDTO(GraduacaoDTO[] listSelectedGraduacaoDTO) {
		this.listSelectedGraduacaoDTO = listSelectedGraduacaoDTO;
	}

	public TecnicaDTO[] getListSelectedTecnicaDTO() {
		return listSelectedTecnicaDTO;
	}

	public void setListSelectedTecnicaDTO(TecnicaDTO[] listSelectedTecnicaDTO) {
		this.listSelectedTecnicaDTO = listSelectedTecnicaDTO;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
