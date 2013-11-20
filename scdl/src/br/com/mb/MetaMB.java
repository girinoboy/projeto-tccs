package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.MetaDAO;
import br.com.dto.MetaDTO;

@ManagedBean
@SessionScoped
public class MetaMB extends GenericoMB implements ModeloMB{

	private MetaDTO metaDTO = new MetaDTO();
	private MetaDAO metaDAO = new MetaDAO();
	private List<MetaDTO> listMeta; 
	private List<MetaDTO> filteredMetas;

	public MetaMB() {
		try {
			listMeta = metaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		metaDAO.save(metaDTO);
		metaDTO = new MetaDTO();
		listMeta = metaDAO.list();
		addMessage("salvo");

	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		System.out.println(metaDTO.getId());
		//metaDTO = metaDAO.getById(metaDTO.getId());

	}

	@Override
	public void del(ActionEvent actionEvent)  {
		try{
			metaDAO.delete(metaDTO);
			System.out.println(metaDTO);
			listMeta = metaDAO.list();
			addMessage("Registro apagado.");
		} catch (Exception e) {
			addMessage("Erro ao apagar meta: "+e.getCause());
			e.printStackTrace();
		}

	}

	public MetaDTO getMetaDTO() {
		return metaDTO;
	}

	public void setMetaDTO(MetaDTO metaDTO) {
		this.metaDTO = metaDTO;
	}

	public List<MetaDTO> getListMeta() {
		return listMeta;
	}

	public void setListMeta(List<MetaDTO> listMeta) {
		this.listMeta = listMeta;
	}

	public List<MetaDTO> getFilteredMetas() {
		return filteredMetas;
	}

	public void setFilteredMetas(List<MetaDTO> filteredMetas) {
		this.filteredMetas = filteredMetas;
	}

}
