/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.PerfilDAO;
import br.com.dto.PerfilDTO;

/**
 * @author Marcle�nio
 *
 */
@ManagedBean
@RequestScoped
public class PerfilMB extends GenericoMB implements ModeloMB{
	private PerfilDTO perfilDTO = new PerfilDTO();
	private PerfilDAO perfilDAO = new PerfilDAO();
	private List<PerfilDTO> listPerfil = new ArrayList<PerfilDTO>();
	/**
	 * 
	 */
	public PerfilMB() {
		try {
			listPerfil = //PerfilConverter.perfilDB;
			perfilDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		perfilDAO.save(perfilDTO);
		addMessage("Salvo com sucesso.");
		perfilDTO = new PerfilDTO();
		
	}
	
	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		perfilDAO.delete(perfilDTO);
		addMessage("Apagado com sucesso.");
		perfilDTO = new PerfilDTO();
		
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public void addPerfil(ActionEvent actionEvent) throws Exception {
		add(null);
	}
	
	public PerfilDTO getPerfilDTO() {
		return perfilDTO;
	}
	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public List<PerfilDTO> getListPerfil() {
		return listPerfil;
	}

	public void setListPerfil(List<PerfilDTO> listPerfil) {
		this.listPerfil = listPerfil;
	}

}
