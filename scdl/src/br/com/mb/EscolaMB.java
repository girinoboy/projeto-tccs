/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.EscolaDAO;
import br.com.dto.CidadeDTO;
import br.com.dto.EscolaDTO;
import br.com.dto.TurnoDTO;
import br.com.utility.CidadeConverter;
import br.com.utility.TurnoConverter;

/**
 * @author Marcle�nio
 *
 */
@ManagedBean
@RequestScoped
public class EscolaMB extends GenericoMB implements ModeloMB{
	
	EscolaDTO escolaDTO = new EscolaDTO();
	EscolaDAO escolaDAO = new EscolaDAO();
	List<EscolaDTO> listEscola = new ArrayList<EscolaDTO>();
	private List<CidadeDTO> listCidadeDTO;
	private List<TurnoDTO> listTurnoDTO;
	private List<EscolaDTO> filteredEscolas;//para filtro na tabela

	/**
	 * 
	 */
	public EscolaMB() {
		try {
			listEscola = escolaDAO.list();
			listCidadeDTO = CidadeConverter.cidadeDB;
			listTurnoDTO = TurnoConverter.turnoDB;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void add() throws Exception {
		escolaDTO = escolaDAO.save(escolaDTO);
		
		addMessage("Salvo.");
		//listEscola = escolaDAO.list();
		
	}
	
	public void add(ActionEvent actionEvent) throws Exception {
		
		if(escolaDAO.existeEnderecoOuNome(escolaDTO)){
			addMessage("Endere�o ou nome duplicado.");
		}else{
			escolaDTO = escolaDAO.save(escolaDTO);
			addMessage("Escola inserida com sucesso.");
		}
		//listEscola = escolaDAO.list();
	}

	public void edit(ActionEvent actionEvent) throws Exception {
		//actionEvent.
		//System.out.println(escolaDTO);
	}

	public void del(ActionEvent actionEvent) throws Exception {
		
		escolaDAO.delete(escolaDTO);
		listEscola = escolaDAO.list();
		
		addMessage("Escola exclu�da com sucesso.");
		
	}
	
	public EscolaDTO getEscolaDTO() {
		return escolaDTO;
	}

	public void setEscolaDTO(EscolaDTO escolaDTO) {
		this.escolaDTO = escolaDTO;
	}

	public List<EscolaDTO> getListEscola() {
		return listEscola;
	}

	public void setListEscola(List<EscolaDTO> listEscola) {
		this.listEscola = listEscola;
	}

	public List<CidadeDTO> getListCidadeDTO() {
		return listCidadeDTO;
	}

	public void setListCidadeDTO(List<CidadeDTO> listCidadeDTO) {
		this.listCidadeDTO = listCidadeDTO;
	}

	public List<TurnoDTO> getListTurnoDTO() {
		return listTurnoDTO;
	}

	public void setListTurnoDTO(List<TurnoDTO> listTurnoDTO) {
		this.listTurnoDTO = listTurnoDTO;
	}

	public List<EscolaDTO> getFilteredEscolas() {
		return filteredEscolas;
	}

	public void setFilteredEscolas(List<EscolaDTO> filteredEscolas) {
		this.filteredEscolas = filteredEscolas;
	}

}
