/**
 * 
 */
package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.dao.EscolaDAO;
import br.com.dto.EscolaDTO;
import br.com.dto.EscolaTurnoDTO;
import br.com.dto.TurnoDTO;
import br.com.utility.TurnoConverter;

/**
 * @author Marcleônio
 *
 */
@ManagedBean
@SessionScoped
public class EscolaMB extends GenericoMB implements ModeloMB{

	private EscolaDTO escolaDTO = new EscolaDTO();
	private EscolaDAO escolaDAO = new EscolaDAO();
	private List<EscolaDTO> listEscola = new ArrayList<EscolaDTO>();
	//	private List<CidadeDTO> listCidadeDTO;
	private List<TurnoDTO> listTurnoDTO;
	private List<TurnoDTO> listAltTurnoDTO = new ArrayList<TurnoDTO>();
	private List<EscolaDTO> filteredEscolas;//para filtro na tabela
	private Integer idEscola;
	private String nomeEscola;

	/**
	 * 
	 */
	public EscolaMB() {
		try {
			listEscola = escolaDAO.list();
			//			listCidadeDTO = CidadeConverter.cidadeDB;
			listTurnoDTO = TurnoConverter.turnoDB;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(ActionEvent actionEvent) {
		try{
			System.out.println(listAltTurnoDTO);
			if(listAltTurnoDTO !=null)
				escolaDTO.setListTurnoDTO(listAltTurnoDTO);
			if(escolaDTO.getId() ==null && escolaDAO.existeEnderecoOuNome(escolaDTO)){
				addMessage("Endereço ou nome duplicado.");
			}else{
				escolaDTO = escolaDAO.save(escolaDTO);
				addMessage("Escola inserida com sucesso.");
			}
			//escolaDTO = new EscolaDTO();
			listEscola = escolaDAO.list();
			listTurnoDTO = TurnoConverter.turnoDB;
			FacesContext.getCurrentInstance().getExternalContext().redirect("escola.xhtml");  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void edit(ActionEvent actionEvent)  {
		try{
			//escolaDTO.setListTurnoDTO(escolaDAO.populaTurno(escolaDTO.getId()));
			System.out.println(escolaDTO.getListTurnoDTO());
			//listAltTurnoDTO =escolaDTO.getListTurnoDTO();
			listAltTurnoDTO = new ArrayList<TurnoDTO>();
			if(escolaDTO.getListTurnoDTO() !=null)
				for (TurnoDTO t : escolaDTO.getListTurnoDTO()) {
					listAltTurnoDTO.add(new TurnoDTO(t.getId(),t.getNome()));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void del(ActionEvent actionEvent) throws Exception {

		escolaDAO.delete(escolaDTO);
		listEscola = escolaDAO.list();

		addMessage("Escola excluída com sucesso.");

	}

	public void filtrar() {
		try{
			
			
			if(idEscola==null &&nomeEscola==null)
				listEscola = escolaDAO.list();
			else if(idEscola == null && nomeEscola !=null){
				idEscola =0;
				listEscola = escolaDAO.filtrar(idEscola,nomeEscola);
			}else{
				nomeEscola="";
				listEscola = escolaDAO.filtrar(idEscola,nomeEscola);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar(){
		listAltTurnoDTO = new ArrayList<TurnoDTO>();
		if(escolaDTO.getListTurnoDTO() !=null)
			for (TurnoDTO t : escolaDTO.getListTurnoDTO()) {
				listAltTurnoDTO.add(new TurnoDTO(t.getId(),t.getNome()));
			}
		
		escolaDTO = new EscolaDTO();
		escolaDTO.setListTurnoDTO(listAltTurnoDTO);
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

	//	public List<CidadeDTO> getListCidadeDTO() {
	//		return listCidadeDTO;
	//	}
	//
	//	public void setListCidadeDTO(List<CidadeDTO> listCidadeDTO) {
	//		this.listCidadeDTO = listCidadeDTO;
	//	}

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

	public List<TurnoDTO> getListAltTurnoDTO() {
		return listAltTurnoDTO;
	}

	public void setListAltTurnoDTO(List<TurnoDTO> listAltTurnoDTO) {
		this.listAltTurnoDTO = listAltTurnoDTO;
	}

	public Integer getIdEscola() {
		return idEscola;
	}

	public void setIdEscola(Integer idEscola) {
		this.idEscola = idEscola;
	}

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

}
