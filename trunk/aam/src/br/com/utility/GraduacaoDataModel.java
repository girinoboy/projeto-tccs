/**
 * 
 */
package br.com.utility;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.dto.GraduacaoDTO;

/**
 * @author marcleonio.medeiros
 *
 */
public class GraduacaoDataModel extends ListDataModel implements SelectableDataModel<GraduacaoDTO> {

	/**
	 * 
	 */
	public GraduacaoDataModel() {
		// TODO Auto-generated constructor stub
	}
	public GraduacaoDataModel(List<GraduacaoDTO> data) {  
		super(data);  
	} 

	public GraduacaoDTO getRowData(String rowKey) {
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

		@SuppressWarnings("unchecked")
		List<GraduacaoDTO> tecs = (List<GraduacaoDTO>) getWrappedData();

		for(GraduacaoDTO tec : tecs) {
			if(tec.getId().equals(rowKey))
				return tec;
		}

		return null;
	}

	public Object getRowKey(GraduacaoDTO arg0) {
		return arg0.getId(); 
	}

}
