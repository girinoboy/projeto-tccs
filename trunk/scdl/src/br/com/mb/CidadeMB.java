package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.CidadeDAO;
import br.com.dto.CidadeDTO;

@ManagedBean
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
		addMessage("salvo");
		
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
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
