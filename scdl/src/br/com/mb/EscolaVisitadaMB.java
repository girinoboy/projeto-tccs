package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import br.com.dao.EscolaVisitadaDAO;
import br.com.dto.EscolaVisitadaDTO;

@ManagedBean
public class EscolaVisitadaMB extends GenericoMB implements ModeloMB{
	
	private List<EscolaVisitadaDTO> listEscolaVisitada;
	private EscolaVisitadaDAO escolaVisitadaDAO = new EscolaVisitadaDAO();
	private EscolaVisitadaDTO escolaVisitadaDTO = new EscolaVisitadaDTO();

	public EscolaVisitadaMB() {
		try {
			listEscolaVisitada = escolaVisitadaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void valueChangeMethod(ValueChangeEvent e){
		//...
	}
	
	public void populaEscola(){
		// popula combo de escola
	}
	

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		escolaVisitadaDAO.save(escolaVisitadaDTO);
		escolaVisitadaDTO = new EscolaVisitadaDTO();
		listEscolaVisitada = escolaVisitadaDAO.list();
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


	public List<EscolaVisitadaDTO> getListEscolaVisitada() {
		return listEscolaVisitada;
	}


	public void setListEscolaVisitada(List<EscolaVisitadaDTO> listEscolaVisitada) {
		this.listEscolaVisitada = listEscolaVisitada;
	}


	public EscolaVisitadaDTO getEscolaVisitadaDTO() {
		return escolaVisitadaDTO;
	}


	public void setEscolaVisitadaDTO(EscolaVisitadaDTO escolaVisitadaDTO) {
		this.escolaVisitadaDTO = escolaVisitadaDTO;
	}
	

}
