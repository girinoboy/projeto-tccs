package br.com.mb;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.dao.AnexoDAO;
import br.com.dao.FinanceiroDAO;
import br.com.dao.GraduacaoDAO;
import br.com.dao.ParametroDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.AnexoDTO;
import br.com.dto.FinanceiroDTO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.ParametroDTO;
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
	private FinanceiroDAO financeiroDAO = new FinanceiroDAO();

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

	public void handleSelect(SelectEvent event) {  

		try {
			usuarioDTO = (UsuarioDTO)event.getObject();

			addMessage("Selected:" + usuarioDTO.getId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(ActionEvent actionEvent) {
		try{calculaDesconto();
			System.out.println(usuarioDTO.getFinanceiroDTO().getValorMensalidade());
			usuarioDTO.setAnexoDTO(anexoDAO.getById(usuarioDTO.getAnexoDTO().getId()));
			if(usuarioDTO.getId() !=null){
				//verifica se existe um novo anexo, pois o anexo é salvo ao capturar
				usuarioDTO.setAnexoDTO(usuarioDAO.getById(usuarioDTO.getId()).getAnexoDTO());

				Calendar c = new GregorianCalendar();
				c.setTime(usuarioDTO.getFinanceiroDTO().getDataPagamento());
				Map<String, Object> filtrosConsulta = new HashMap<String, Object>();
				filtrosConsulta.put("mes", c.get(Calendar.MONTH));
				filtrosConsulta.put("ano", c.get(Calendar.YEAR));
				filtrosConsulta.put("usuarioDTO.id", usuarioDTO.getId());
				//teste para verificar se o usuario ja pagou no mes
				List<FinanceiroDTO> f = financeiroDAO.listCriterio(null, filtrosConsulta , 1);

				if(!f.isEmpty()){
					usuarioDTO.getFinanceiroDTO().setId(f.get(0).getId());
				}

			}
			
			
			usuarioDTO = usuarioDAO.save(usuarioDTO);
			usuarioDTO.getFinanceiroDTO().setUsuarioDTO(usuarioDTO);
			usuarioDTO.getFinanceiroDTO().getDia();
			usuarioDTO.getFinanceiroDTO().getMes();
			usuarioDTO.getFinanceiroDTO().getAno();
			usuarioDTO = usuarioDAO.save(usuarioDTO);
			addMessage("Salvo");
			usuarioDTO = new UsuarioDTO();
			listUsuarioDTO = usuarioDAO.list();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void edit(ActionEvent actionEvent) throws Exception {
		addMessage("Salvo");

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
	
	public void calculaDesconto() throws HibernateException, Exception{
		ParametroDAO parametroDAO = new ParametroDAO();
		ParametroDTO p = parametroDAO.recuperaParametro("mensalidade");
		if(p != null && p.getValor() != null)
			usuarioDTO.getFinanceiroDTO().setValorComDesconto(Double.valueOf(p.getValor()) - Double.valueOf(p.getValor()) * usuarioDTO.getDesconto());
		else
			usuarioDTO.getFinanceiroDTO().setValorComDesconto(0d);
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
