/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.dao.LivroDAO;
import br.com.dto.LivroDTO;
import br.com.dto.UsuarioDTO;
import br.com.utility.Constantes;

/**
 * @author Marcle�nio
 *
 */
@ManagedBean
@SessionScoped
public class LivroMB extends GenericoMB implements ModeloMB{

	private LivroDTO livroDTO = new LivroDTO();
	private List<LivroDTO> listLivroDTO = new ArrayList<LivroDTO>();
	private LivroDAO livroDAO = new LivroDAO();
	private List<UsuarioDTO> filteredLivros;

	/**
	 * 
	 */
	public LivroMB() {
		try {
			listLivroDTO = livroDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void add(ActionEvent actionEvent) throws Exception {
		livroDAO.save(livroDTO);
		addMessage("salvo");
		livroDTO = new LivroDTO();
		listLivroDTO = livroDAO.list();
		FacesContext.getCurrentInstance().getExternalContext().redirect("livro.xhtml");
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(livroDTO);
	}

	public void del(ActionEvent actionEvent) throws Exception {
		try {
			livroDAO.delete(livroDTO);
			listLivroDTO = livroDAO.list();
			addMessage("Livro exclu�do com sucesso.");
		} catch (Exception e) {
			if(e.getCause().toString().toUpperCase().contains("ESCOLA_DIVULGADOR")){
				addMessage("N�o excluido. Existe um divuilgador vinculado");
			}else if(e.getCause().toString().toLowerCase().contains("escola_livro")){
				addMessage("N�o excluido. Existe um livro vinculado");
			}
			e.printStackTrace();
		}

	}

	public void populaLivro(){
		// popula combo de escola
		Map<String, Object> filtrosConsulta = new HashMap<>();

		try {
			filtrosConsulta.put("editoraDTO.id", livroDTO.getEditoraDTO().getId());
			listLivroDTO = livroDAO.listCriterio(null, filtrosConsulta, Constantes.TIPO_CONSULTA_EQUALS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LivroDTO getLivroDTO() {
		return livroDTO;
	}

	public void setLivroDTO(LivroDTO livroDTO) {
		this.livroDTO = livroDTO;
	}


	public List<LivroDTO> getListLivroDTO() {
		return listLivroDTO;
	}


	public void setListLivroDTO(List<LivroDTO> listLivroDTO) {
		this.listLivroDTO = listLivroDTO;
	}


	public List<UsuarioDTO> getFilteredLivros() {
		return filteredLivros;
	}


	public void setFilteredLivros(List<UsuarioDTO> filteredLivros) {
		this.filteredLivros = filteredLivros;
	}
}
