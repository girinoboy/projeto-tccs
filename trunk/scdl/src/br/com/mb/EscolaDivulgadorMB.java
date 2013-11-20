package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.EscolaDAO;
import br.com.dao.EscolaDivulgadorDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.EscolaDTO;
import br.com.dto.EscolaDivulgadorDTO;
import br.com.dto.UsuarioDTO;

@ManagedBean
@SessionScoped
public class EscolaDivulgadorMB extends GenericoMB implements ModeloMB{
	
	private EscolaDivulgadorDTO escolaDivulgadorDTO = new EscolaDivulgadorDTO();
	private List<EscolaDivulgadorDTO> listEscolaDivulgador;
	private EscolaDivulgadorDAO escolaDivulgadorDAO = new EscolaDivulgadorDAO();
	
	private EscolaDAO escolaDAO = new EscolaDAO();
	private List<EscolaDTO> listEscola;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<UsuarioDTO> listDivulgador;

	public EscolaDivulgadorMB() {
		try {
			listEscolaDivulgador = escolaDivulgadorDAO.list();
			listDivulgador = usuarioDAO.list();
			listEscola = escolaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		escolaDivulgadorDAO.save(escolaDivulgadorDTO);
		addMessage("salvo");
		escolaDivulgadorDTO = new EscolaDivulgadorDTO();
		listEscolaDivulgador = escolaDivulgadorDAO.list();
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent){
		try {
			escolaDivulgadorDAO.delete(escolaDivulgadorDTO);
			listEscolaDivulgador = escolaDivulgadorDAO.list();
			addMessage("registro apagado");
		} catch (Exception e) {
			addMessage("erro ao apagar registro");
			e.printStackTrace();
		}
	}

	public EscolaDivulgadorDTO getEscolaDivulgadorDTO() {
		return escolaDivulgadorDTO;
	}

	public void setEscolaDivulgadorDTO(EscolaDivulgadorDTO escolaDivulgadorDTO) {
		this.escolaDivulgadorDTO = escolaDivulgadorDTO;
	}

	public List<EscolaDivulgadorDTO> getListEscolaDivulgador() {
		return listEscolaDivulgador;
	}

	public void setListEscolaDivulgador(
			List<EscolaDivulgadorDTO> listEscolaDivulgador) {
		this.listEscolaDivulgador = listEscolaDivulgador;
	}

	public List<EscolaDTO> getListEscola() {
		return listEscola;
	}

	public void setListEscola(List<EscolaDTO> listEscola) {
		this.listEscola = listEscola;
	}

	public List<UsuarioDTO> getListDivulgador() {
		return listDivulgador;
	}

	public void setListDivulgador(List<UsuarioDTO> listDivulgador) {
		this.listDivulgador = listDivulgador;
	}

}
