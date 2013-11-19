package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.EscolaDAO;
import br.com.dao.EscolaLivroDAO;
import br.com.dao.LivroDAO;
import br.com.dto.EscolaDTO;
import br.com.dto.EscolaLivroDTO;
import br.com.dto.LivroDTO;

@ManagedBean
@RequestScoped
public class EscolaLivroMB extends GenericoMB implements ModeloMB{

	private EscolaLivroDTO escolaLivroDTO = new EscolaLivroDTO();
	private List<EscolaLivroDTO> listEscolaLivroDTO;
	private EscolaLivroDAO escolaLivroDAO = new EscolaLivroDAO();
	private List<LivroDTO> listLivroDTO;
	private LivroDAO livroDAO = new LivroDAO();
	private EscolaDAO escolaDAO = new EscolaDAO();
	private List<EscolaDTO> listEscola;
	
	public EscolaLivroMB() {
		try {
			listEscolaLivroDTO = escolaLivroDAO.list();
			listLivroDTO = livroDAO.list();
			listEscola = escolaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		escolaLivroDAO.save(escolaLivroDTO);
		escolaLivroDTO = new EscolaLivroDTO();
		listEscolaLivroDTO = escolaLivroDAO.list();
		addMessage("salvo");
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(escolaLivroDTO);
	}

	@Override
	public void del(ActionEvent actionEvent){
		try {
			escolaLivroDAO.delete(escolaLivroDTO);
			listEscolaLivroDTO = escolaLivroDAO.list();
			addMessage("registro apagado");
		} catch (Exception e) {
			addMessage("erro ao apagar registro");
			e.printStackTrace();
		}
	}

	public EscolaLivroDTO getEscolaLivroDTO() {
		return escolaLivroDTO;
	}

	public void setEscolaLivroDTO(EscolaLivroDTO escolaLivroDTO) {
		this.escolaLivroDTO = escolaLivroDTO;
	}

	public List<EscolaLivroDTO> getListEscolaLivroDTO() {
		return listEscolaLivroDTO;
	}

	public void setListEscolaLivroDTO(List<EscolaLivroDTO> listEscolaLivroDTO) {
		this.listEscolaLivroDTO = listEscolaLivroDTO;
	}

	public List<LivroDTO> getListLivroDTO() {
		return listLivroDTO;
	}

	public void setListLivroDTO(List<LivroDTO> listLivroDTO) {
		this.listLivroDTO = listLivroDTO;
	}

	public List<EscolaDTO> getListEscola() {
		return listEscola;
	}

	public void setListEscola(List<EscolaDTO> listEscola) {
		this.listEscola = listEscola;
	}

}
