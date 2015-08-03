package br.com.mb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.com.dao.GraduacaoDAO;
import br.com.dao.ResultadoAvaliacaoDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.ResultadoAvaliacaoDTO;
import br.com.dto.UsuarioDTO;
import br.com.enumeration.ClassificacaoDesempenhoE;
import br.com.enumeration.ConceitoE;
import br.com.utility.AbstractDataModel;
import br.com.utility.Constantes;

@ManagedBean
@SessionScoped
public class ResultadoAvaliacaoMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3823839345575509086L;
	private ResultadoAvaliacaoDTO resultadoAvaliacaoDTO = new ResultadoAvaliacaoDTO();
	private ResultadoAvaliacaoDAO resuldoAvaliacaoDAO = new ResultadoAvaliacaoDAO();
	private List<ResultadoAvaliacaoDTO> listResuldoAvaliacaoDTO = new ArrayList<ResultadoAvaliacaoDTO>();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private AbstractDataModel<ResultadoAvaliacaoDTO> resultadoAvaliacaoDataModel;
	private ResultadoAvaliacaoDTO[] listSelectedResultadoAvaliacaoDTO;
	private List<ClassificacaoDesempenhoE> listClassificacaoDesempenho;
	private List<ConceitoE> listConceito;

	public ResultadoAvaliacaoMB() {
		try {
			atualiza(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualiza(ActionEvent event) throws Exception{
		listResuldoAvaliacaoDTO = resuldoAvaliacaoDAO.listOrdenada();
		listGraduacaoDTO = graduacaoDAO.list();
		resultadoAvaliacaoDataModel = new AbstractDataModel<ResultadoAvaliacaoDTO>(listResuldoAvaliacaoDTO);
		listClassificacaoDesempenho = Arrays.asList(ClassificacaoDesempenhoE.values());
		listConceito = Arrays.asList(ConceitoE.values());
	}
	
	public void reset(ActionEvent event) {
		try {
			atualiza(null);
			listGraduacaoDTO = graduacaoDAO.list();
			resultadoAvaliacaoDTO = new ResultadoAvaliacaoDTO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleSelect(SelectEvent event) {  

		try {
			resultadoAvaliacaoDTO.setUsuarioDTO((UsuarioDTO)event.getObject()) ;

			addMessage("Gradua��o:" + resultadoAvaliacaoDTO.getUsuarioDTO().getGraduacaoDTO().getNome().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<UsuarioDTO> completarUsuario(String query) throws Exception {
		List<UsuarioDTO> suggestions = new ArrayList<UsuarioDTO>();
		
		Map<String, Object> filtrosConsulta = new HashMap<String, Object>();
		filtrosConsulta.put("nome", query);
		suggestions = usuarioDAO.listCriterio(null, filtrosConsulta, Constantes.TIPO_CONSULTA_ILIKE);

		return suggestions;
	}

	public void add(ActionEvent actionEvent) throws Exception {
		
		ResultadoAvaliacaoDTO a = resuldoAvaliacaoDAO.buscarNotaMesSelecionado(resultadoAvaliacaoDTO);
		if(a!=null)
			resultadoAvaliacaoDTO.setId(a.getId());
		resuldoAvaliacaoDAO.save(resultadoAvaliacaoDTO);
		atualiza(actionEvent);
		reset(actionEvent);
		addMessage("Opera��o realizada com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_RESULTADO_AVALIACAO);
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(resultadoAvaliacaoDTO);

	}

	public void del(ActionEvent actionEvent) throws Exception {
		try{
			for (ResultadoAvaliacaoDTO r : listSelectedResultadoAvaliacaoDTO) {
				resuldoAvaliacaoDAO.delete(r);//colocar uma flag para desativar usuario ao em vez de excluir definitivamente do bd
			}
			if(listSelectedResultadoAvaliacaoDTO.length >0){
				addMessage("Apagado.");
			}else{
				addMessage("Nenhum Item Selecionado.");
			}

		}catch(Exception e){
			addMessage("Registro n�o pode ser apagado.");
			e.printStackTrace();
		}finally{
			try {
				reset(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public ResultadoAvaliacaoDTO getResultadoAvaliacaoDTO() {
		return resultadoAvaliacaoDTO;
	}

	public void setResultadoAvaliacaoDTO(ResultadoAvaliacaoDTO resultadoAvaliacaoDTO) {
		this.resultadoAvaliacaoDTO = resultadoAvaliacaoDTO;
	}

	public List<ResultadoAvaliacaoDTO> getListResuldoAvaliacaoDTO() {
		return listResuldoAvaliacaoDTO;
	}

	public void setListResuldoAvaliacaoDTO(
			List<ResultadoAvaliacaoDTO> listResuldoAvaliacaoDTO) {
		this.listResuldoAvaliacaoDTO = listResuldoAvaliacaoDTO;
	}

	public List<GraduacaoDTO> getListGraduacaoDTO() {
		return listGraduacaoDTO;
	}

	public void setListGraduacaoDTO(List<GraduacaoDTO> listGraduacaoDTO) {
		this.listGraduacaoDTO = listGraduacaoDTO;
	}

	public AbstractDataModel<ResultadoAvaliacaoDTO> getResultadoAvaliacaoDataModel() {
		return resultadoAvaliacaoDataModel;
	}

	public void setResultadoAvaliacaoDataModel(
			AbstractDataModel<ResultadoAvaliacaoDTO> resultadoAvaliacaoDataModel) {
		this.resultadoAvaliacaoDataModel = resultadoAvaliacaoDataModel;
	}

	public ResultadoAvaliacaoDTO[] getListSelectedResultadoAvaliacaoDTO() {
		return listSelectedResultadoAvaliacaoDTO;
	}

	public void setListSelectedResultadoAvaliacaoDTO(
			ResultadoAvaliacaoDTO[] listSelectedResultadoAvaliacaoDTO) {
		this.listSelectedResultadoAvaliacaoDTO = listSelectedResultadoAvaliacaoDTO;
	}

	public List<ClassificacaoDesempenhoE> getListClassificacaoDesempenho() {
		return listClassificacaoDesempenho;
	}

	public void setListClassificacaoDesempenho(
			List<ClassificacaoDesempenhoE> listClassificacaoDesempenho) {
		this.listClassificacaoDesempenho = listClassificacaoDesempenho;
	}

	public List<ConceitoE> getListConceito() {
		return listConceito;
	}

	public void setListConceito(List<ConceitoE> listConceito) {
		this.listConceito = listConceito;
	}

	

}
