package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.CidadeDAO;
import br.com.dto.CidadeDTO;

@ManagedBean
@SessionScoped
public class CidadeMB extends GenericoMB implements ModeloMB{

	private List<CidadeDTO> listCidade;
	private CidadeDAO cidadeDAO = new CidadeDAO();
	private CidadeDTO cidadeDTO = new CidadeDTO();

	public CidadeMB() {
		try {
			listCidade = cidadeDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		cidadeDAO.save(cidadeDTO);
		cidadeDTO = new CidadeDTO();
		listCidade = cidadeDAO.list();
		addMessage("salvo");

	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void del(ActionEvent actionEvent) {
		try {
			cidadeDAO.delete(cidadeDTO);
			listCidade = cidadeDAO.list();
		} catch (Exception e) {
			addMessage("Erro ao apagar cidade: "+e.getCause());
			e.printStackTrace();
		}

	}

	public List<CidadeDTO> getListCidade() {
		return listCidade;
	}

	public void setListCidade(List<CidadeDTO> listCidade) {
		this.listCidade = listCidade;
	}


	public CidadeDTO getCidadeDTO() {
		return cidadeDTO;
	}


	public void setCidadeDTO(CidadeDTO cidadeDTO) {
		this.cidadeDTO = cidadeDTO;
	}

}
