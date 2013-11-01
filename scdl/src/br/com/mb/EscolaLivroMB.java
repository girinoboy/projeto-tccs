package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.EscolaLivroDAO;
import br.com.dto.EscolaLivroDTO;

@ManagedBean
public class EscolaLivroMB extends GenericoMB implements ModeloMB{

	private EscolaLivroDTO escolaLivroDTO = new EscolaLivroDTO();
	private List<EscolaLivroDTO> listEscolaLivroDTO;
	EscolaLivroDAO escolaLivroDAO = new EscolaLivroDAO();
	
	public EscolaLivroMB() {
		try {
			listEscolaLivroDTO = escolaLivroDAO.list();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
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

}
