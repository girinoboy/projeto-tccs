/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.LivroDAO;
import br.com.dto.LivroDTO;
import br.com.dto.UsuarioDTO;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
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
 		
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
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
