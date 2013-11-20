package br.com.mb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import br.com.dao.EscolaDAO;
import br.com.dao.EscolaVisitadaDAO;
import br.com.dto.CidadeDTO;
import br.com.dto.EscolaDTO;
import br.com.dto.EscolaVisitadaDTO;
import br.com.utility.Constantes;
import br.com.utility.DataUtils;

@ManagedBean
@SessionScoped
public class EscolaVisitadaMB extends GenericoMB implements ModeloMB{
	
	private List<EscolaVisitadaDTO> listEscolaVisitada;
	private EscolaVisitadaDAO escolaVisitadaDAO = new EscolaVisitadaDAO();
	private EscolaVisitadaDTO escolaVisitadaDTO = new EscolaVisitadaDTO();
	private List<EscolaDTO> listEscolaFiltrada;
	private EscolaDAO escolaDAO = new EscolaDAO();
	private CidadeDTO cidadeDTO = new CidadeDTO();

	public EscolaVisitadaMB() {
		try {
			listEscolaVisitada = escolaVisitadaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void valueChangeMethod(ValueChangeEvent e){
		//...
		System.out.println(1);
	}
	
	public void populaEscola(){
		// popula combo de escola
		Map<String, Object> filtrosConsulta = new HashMap<>();
		
		try {
			filtrosConsulta.put("cidadeDTO.id", cidadeDTO.getId());
			listEscolaFiltrada = escolaDAO.listCriterio(null, filtrosConsulta, Constantes.TIPO_CONSULTA_EQUALS);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		escolaVisitadaDAO.delete(escolaVisitadaDTO);
		listEscolaVisitada = escolaVisitadaDAO.list();
		addMessage("Apagado.");
		
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

	public List<EscolaDTO> getListEscolaFiltrada() {
		return listEscolaFiltrada;
	}

	public void setListEscolaFiltrada(List<EscolaDTO> listEscolaFiltrada) {
		this.listEscolaFiltrada = listEscolaFiltrada;
	}

	public CidadeDTO getCidadeDTO() {
		return cidadeDTO;
	}

	public void setCidadeDTO(CidadeDTO cidadeDTO) {
		this.cidadeDTO = cidadeDTO;
	}
	

}
