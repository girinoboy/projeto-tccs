package br.com.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.MetaDAO;
import br.com.dto.MetaDTO;

@ManagedBean
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
