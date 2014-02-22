package br.com.mb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import br.com.dao.AnexoDAO;
import br.com.dao.FinanceiroDAO;
import br.com.dao.GraduacaoDAO;
import br.com.dao.ParametroDAO;
import br.com.dao.ResultadoDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.AnexoDTO;
import br.com.dto.FinanceiroDTO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.ParametroDTO;
import br.com.dto.PerfilDTO;
import br.com.dto.ResultadoDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.AbstractDataModel;
import br.com.utility.Constantes;

@ManagedBean
@SessionScoped
public class UsuarioMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8789376951090610154L;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private List<UsuarioDTO> listUsuarioDTO = new ArrayList<UsuarioDTO>();
	private UsuarioDTO[] listSelectedUsuarioDTO;// = new ArrayList<UsuarioDTO>();
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();
	private AnexoDAO anexoDAO = new AnexoDAO();
	private FinanceiroDAO financeiroDAO = new FinanceiroDAO();

	private AbstractDataModel<UsuarioDTO> membroDataModel; 

	public UsuarioMB(){
		try {
			reset(null);
			atualiza(null);

			//			listUsuarioDTO = new ArrayList<UsuarioDTO>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualiza(ActionEvent event) throws Exception {
//		if(getAdm()){
			listUsuarioDTO = usuarioDAO.listExclusaoLogica(false);
//		}else{
//			listUsuarioDTO = usuarioDAO.listById(getUserSession().getId());
//		}
		listGraduacaoDTO = graduacaoDAO.list();
		membroDataModel = new AbstractDataModel<UsuarioDTO>(listUsuarioDTO);

	}

	public void reset(ActionEvent event) {
		try {
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setFinanceiroDTO(new FinanceiroDTO());
			usuarioDTO.setAnexoDTO(new AnexoDTO());
			listGraduacaoDTO = graduacaoDAO.list();
		//		membroDataModel = new AbstractDataModel<UsuarioDTO>();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) throws IOException {  
		usuarioDTO = (UsuarioDTO) event.getObject();  
		if(!getAdm() && getUserSession().getId().equals(usuarioDTO.getId())){
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroMembros.xhtml"); 
		}else if(getAdm()){
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroMembros.xhtml");
		}
	}
	
	public void visualizarCadastro(ActionEvent event) throws IOException{
		usuarioDTO = getUserSession();  
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastroMembros.xhtml");
	}

	public void handleFileUpload(FileUploadEvent event) throws Exception {
		AnexoDTO anexoDTO = new AnexoDTO();
		usuarioDTO.setAnexoDTO(anexoDTO);
		usuarioDTO.getAnexoDTO().setNome(event.getFile().getFileName());
		usuarioDTO.getAnexoDTO().setAnexo(event.getFile().getContents());
		usuarioDTO.getAnexoDTO().setTamanho(event.getFile().getSize());
		usuarioDTO.getAnexoDTO().setContentType(event.getFile().getContentType());

		//usuarioDTO = usuarioDAO.save(usuarioDTO);
		usuarioDTO.setAnexoDTO(anexoDAO.save(usuarioDTO.getAnexoDTO()));
		//		setUserSession(usuarioDTO);
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
		try{
			validaCPF();
			validaLogin();
			calculaDesconto();
			System.out.println(usuarioDTO.getFinanceiroDTO().getValorMensalidade());
//			if(usuarioDTO.getAnexoDTO().getId() !=null)
//				usuarioDTO.setAnexoDTO(anexoDAO.getById(usuarioDTO.getAnexoDTO().getId()));
			if(usuarioDTO.getId() !=null){
				//verifica se existe um novo anexo, pois o anexo é salvo ao capturar
//				usuarioDTO.setAnexoDTO(usuarioDAO.getById(usuarioDTO.getId()).getAnexoDTO());

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
			if(usuarioDTO.getPerfilDTO()==null)
				usuarioDTO.setPerfilDTO(new PerfilDTO(2));
			usuarioDTO = usuarioDAO.save(usuarioDTO);
			usuarioDTO.getFinanceiroDTO().setUsuarioDTO(usuarioDTO);
			usuarioDTO.getFinanceiroDTO().setSituacao(false);
			usuarioDTO.getFinanceiroDTO().getDia();
			usuarioDTO.getFinanceiroDTO().getMes();
			usuarioDTO.getFinanceiroDTO().getAno();
			usuarioDTO = usuarioDAO.save(usuarioDTO);
			addMessage("Operação realizada com sucesso!");
			usuarioDTO = new UsuarioDTO();
			listUsuarioDTO = usuarioDAO.list();
			reset(null);
			atualiza(null);
			//redireciona para a home apos incluir
			FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_USUARIO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void validaLogin() throws Exception {
		try {

			if(usuarioDAO.validaLogin(usuarioDTO)){
				addMessage("login existente");
				throw new Exception("login invalido");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private void validaCPF() throws Exception {
		try {

			if(usuarioDAO.validaCPF(usuarioDTO)){
				addMessage("cpf existente");
				throw new Exception("cpf invalido");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}


	}

	public void edit(ActionEvent actionEvent) throws Exception {
		atualiza(null);
		System.out.println(usuarioDTO);
		addMessage("Operação realizada com sucesso!");

	}

	public void del(ActionEvent actionEvent) {
		try{
			//System.out.println(listSelectedUsuarioDTO);
			for (UsuarioDTO u : listSelectedUsuarioDTO) {
				u.setExcluido(true);
				usuarioDAO.exclusaoLogica(u);
//				if(u.getListAnexoDTO().isEmpty()){
//					u.setListAnexoDTO(null);
//				}
//				if(u.getListFinanceiroDTO().isEmpty()){
//					u.setListFinanceiroDTO(null);
//				}
//				if(u.getListResultadoDTO().isEmpty()){
//					u.setListResultadoDTO(null);
//				}else{
//					for (ResultadoDTO r : u.getListResultadoDTO()) {
//						 ResultadoDAO rdao = new ResultadoDAO();
//						 rdao.delete(r);
//					}
//					u.setListResultadoDTO(null);
//				}
//				if(u.getFinanceiroDTO().getId() ==null){
//					u.setFinanceiroDTO(null);
//				}
//				if(u.getAnexoDTO().getId() ==null){
//					u.setAnexoDTO(null);
//				}
//				usuarioDAO.delete(u);//colocar uma flag para desativar usuario ao em vez de excluir definitivamente do bd
			}
			if(listSelectedUsuarioDTO.length >0){
				addMessage("Apagado.");
			}else{
				addMessage("Nenhum Item Selecionado.");
			}

		}catch(Exception e){
			addMessage("Registro não pode ser apagado.");
			e.printStackTrace();
		}finally{
			try {
				atualiza(null);
			} catch (Exception e) {
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


	public AbstractDataModel<UsuarioDTO> getMembroDataModel() throws Exception {
		if(membroDataModel == null || membroDataModel.getRowCount()==-1)
			atualiza(null);
		return membroDataModel;
	}

	public void setMembroDataModel(AbstractDataModel<UsuarioDTO> membroDataModel) {
		this.membroDataModel = membroDataModel;
	}

	public UsuarioDTO[] getListSelectedUsuarioDTO() {
		return listSelectedUsuarioDTO;
	}

	public void setListSelectedUsuarioDTO(UsuarioDTO[] listSelectedUsuarioDTO) {
		this.listSelectedUsuarioDTO = listSelectedUsuarioDTO;
	}

}
