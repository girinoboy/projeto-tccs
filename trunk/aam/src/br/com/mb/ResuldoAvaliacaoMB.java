package br.com.mb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.com.dao.GraduacaoDAO;
import br.com.dao.ResuldoAvaliacaoDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.ResuldoAvaliacaoDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.Constantes;

@ManagedBean
@RequestScoped
public class ResuldoAvaliacaoMB extends GenericoMB implements ModeloMB{

	private ResuldoAvaliacaoDTO resuldoAvaliacaoDTO = new ResuldoAvaliacaoDTO();
	private ResuldoAvaliacaoDAO resuldoAvaliacaoDAO = new ResuldoAvaliacaoDAO();
	private List<ResuldoAvaliacaoDTO> listResuldoAvaliacaoDTO = new ArrayList<ResuldoAvaliacaoDTO>();
	private List<GraduacaoDTO> listGraduacaoDTO = new ArrayList<GraduacaoDTO>();
	private GraduacaoDAO graduacaoDAO = new GraduacaoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public ResuldoAvaliacaoMB() {
		try {
			listGraduacaoDTO = graduacaoDAO.list();
			listResuldoAvaliacaoDTO = resuldoAvaliacaoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleSelect(SelectEvent event) {  

		try {
			resuldoAvaliacaoDTO.setUsuarioDTO((UsuarioDTO)event.getObject()) ;

			addMessage("Graduação:" + resuldoAvaliacaoDTO.getUsuarioDTO().getGraduacaoDTO().getNome().toString());
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
		
		ResuldoAvaliacaoDTO a = resuldoAvaliacaoDAO.buscarNotaMesSelecionado(resuldoAvaliacaoDTO);
		if(a!=null)
			resuldoAvaliacaoDTO.setId(a.getId());
		resuldoAvaliacaoDAO.save(resuldoAvaliacaoDTO);
		addMessage("Salvo");
		resuldoAvaliacaoDTO = new ResuldoAvaliacaoDTO();

	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	public ResuldoAvaliacaoDTO getResuldoAvaliacaoDTO() {
		return resuldoAvaliacaoDTO;
	}

	public void setResuldoAvaliacaoDTO(ResuldoAvaliacaoDTO resuldoAvaliacaoDTO) {
		this.resuldoAvaliacaoDTO = resuldoAvaliacaoDTO;
	}

	public List<ResuldoAvaliacaoDTO> getListResuldoAvaliacaoDTO() {
		return listResuldoAvaliacaoDTO;
	}

	public void setListResuldoAvaliacaoDTO(
			List<ResuldoAvaliacaoDTO> listResuldoAvaliacaoDTO) {
		this.listResuldoAvaliacaoDTO = listResuldoAvaliacaoDTO;
	}

	public List<GraduacaoDTO> getListGraduacaoDTO() {
		return listGraduacaoDTO;
	}

	public void setListGraduacaoDTO(List<GraduacaoDTO> listGraduacaoDTO) {
		this.listGraduacaoDTO = listGraduacaoDTO;
	}

}
