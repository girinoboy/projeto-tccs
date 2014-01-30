/**
 * 
 */
package br.com.mb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import br.com.dao.TecnicaDAO;
import br.com.dto.AnexoDTO;
import br.com.dto.TecnicaDTO;
import br.com.utility.TecnicaDataModel;

/**
 * @author marcleonio.medeiros
 *
 */
@ManagedBean
@SessionScoped
public class TecnicaMB extends GenericoMB implements ModeloMB{
	
	private TecnicaDAO tecnicaDAO = new TecnicaDAO();
	private TecnicaDTO tecnicaDTO = new TecnicaDTO();
	private List<TecnicaDTO> listTecnicaDTO = new ArrayList<TecnicaDTO>();
	private TecnicaDTO[] listSelectedTecnicaDTO;
	private TecnicaDataModel tecnicaDataModel;
	private UploadedFile file;

	/**
	 * 
	 */
	public TecnicaMB() {
		try {
			 atualiza(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualiza(ActionEvent event) throws Exception {
		listTecnicaDTO = tecnicaDAO.list();

		tecnicaDataModel = new TecnicaDataModel(listTecnicaDTO);
		
	}

	public void reset(ActionEvent event) {
		tecnicaDTO = new TecnicaDTO();
		
	}
	
    public void upload() {  
        if(file != null) {  
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        }  
    }
    
    public void onRowSelect(SelectEvent event) throws IOException {  
    	tecnicaDTO = (TecnicaDTO) event.getObject();  

		//FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroMembros.xhtml"); 
	} 
	
	public void check(SelectEvent event) {
		System.out.println("in check");
		System.out.println(listSelectedTecnicaDTO);
	}

	public void add(ActionEvent actionEvent){
		try{
			if(file != null) {
				AnexoDTO anexoDTO = new AnexoDTO();
				anexoDTO.setNome(file.getFileName());
				anexoDTO.setAnexo(file.getContents());
				anexoDTO.setTamanho(file.getSize());
				anexoDTO.setContentType(file.getContentType());
	
				tecnicaDTO.setAnexoDTO(anexoDTO);
			}
			tecnicaDAO.save(tecnicaDTO);
			tecnicaDTO = new TecnicaDTO();
			addMessage("Operação realizada com sucesso!");
			atualiza(null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(tecnicaDTO);
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		try{
			//System.out.println(listSelectedTecnicaDTO);
			for (TecnicaDTO n : listSelectedTecnicaDTO) {
				tecnicaDAO.delete(n);
			}
			if(listSelectedTecnicaDTO.length >0){
				addMessage("Apagado.");
			}else{
				addMessage("Nenhum Item Selecionado.");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				listTecnicaDTO = tecnicaDAO.list();

				tecnicaDataModel = new TecnicaDataModel(listTecnicaDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public TecnicaDTO getTecnicaDTO() {
		return tecnicaDTO;
	}

	public void setTecnicaDTO(TecnicaDTO tecnicaDTO) {
		this.tecnicaDTO = tecnicaDTO;
	}

	public List<TecnicaDTO> getListTecnicaDTO() {
		return listTecnicaDTO;
	}

	public void setListTecnicaDTO(List<TecnicaDTO> listTecnicaDTO) {
		this.listTecnicaDTO = listTecnicaDTO;
	}

	public TecnicaDTO[] getListSelectedTecnicaDTO() {
		return listSelectedTecnicaDTO;
	}

	public void setListSelectedTecnicaDTO(TecnicaDTO[] listSelectedTecnicaDTO) {
		this.listSelectedTecnicaDTO = listSelectedTecnicaDTO;
	}

	public TecnicaDataModel getTecnicaDataModel() {
		return tecnicaDataModel;
	}

	public void setTecnicaDataModel(TecnicaDataModel tecnicaDataModel) {
		this.tecnicaDataModel = tecnicaDataModel;
	}
	
	public UploadedFile getFile() {  
        return file;  
    }  
  
    public void setFile(UploadedFile file) {  
        this.file = file;  
    }

}
