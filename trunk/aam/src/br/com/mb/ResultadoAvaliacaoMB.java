package br.com.mb;

import java.util.ArrayList;
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
import br.com.utility.AbstractDataModel;
import br.com.utility.Constantes;
import br.com.utility.NoticiaDataModel;

@ManagedBean
@SessionScoped
public class ResultadoAvaliacaoMB extends GenericoMB implements ModeloMB{

	private ResultadoAvaliacaoDTO resultadoAvaliacaoDTO = new ResultadoAvaliacaoDTO();
	private ResultadoAvaliacaoDAO resuldoAvaliacaoDAO = new ResultadoAvaliacaoDAO();
	private List<ResultadoAvaliacaoDTO> listResuldoAvaliacaoDTO = new ArrayList<ResultadoAvaliacaoDTO>();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private AbstractDataModel<ResultadoAvaliacaoDTO> resultadoAvaliacaoDataModel;
	private ResultadoAvaliacaoDTO[] listSelectedResultadoAvaliacaoDTO;

	public ResultadoAvaliacaoMB() {
		try {
			listGraduacaoDTO = graduacaoDAO.list();
			listResuldoAvaliacaoDTO = resuldoAvaliacaoDAO.list();
			resultadoAvaliacaoDataModel = new AbstractDataModel<ResultadoAvaliacaoDTO>(listResuldoAvaliacaoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualiza(ActionEvent event) throws Exception{
		listResuldoAvaliacaoDTO = resuldoAvaliacaoDAO.list();

		resultadoAvaliacaoDataModel = new AbstractDataModel<ResultadoAvaliacaoDTO>(listResuldoAvaliacaoDTO);
	}
	
	public void reset(ActionEvent event) {
		resultadoAvaliacaoDTO = new ResultadoAvaliacaoDTO();
	}

	public void handleSelect(SelectEvent event) {  

		try {
			resultadoAvaliacaoDTO.setUsuarioDTO((UsuarioDTO)event.getObject()) ;

			addMessage("Graduação:" + resultadoAvaliacaoDTO.getUsuarioDTO().getGraduacaoDTO().getNome().toString());
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
		addMessage("Operação realizada com sucesso!");
		FacesContext.getCurrentInstance().getExternalContext().redirect(Constantes.PAGINA_INDEX);
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(resultadoAvaliacaoDTO);

	}

	public void del(ActionEvent actionEvent) throws Exception {
		System.out.println(resultadoAvaliacaoDTO);

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

	

}
