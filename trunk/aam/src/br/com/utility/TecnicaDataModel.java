/**
 * 
 */
package br.com.utility;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.dto.TecnicaDTO;

/**
 * @author marcleonio.medeiros
 *
 */
public class TecnicaDataModel extends ListDataModel implements SelectableDataModel<TecnicaDTO> {

	/**
	 * 
	 */
	public TecnicaDataModel() {
		// TODO Auto-generated constructor stub
	}
	public TecnicaDataModel(List<TecnicaDTO> data) {  
		super(data);  
	} 

	public TecnicaDTO getRowData(String rowKey) {
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

		@SuppressWarnings("unchecked")
		List<TecnicaDTO> tecs = (List<TecnicaDTO>) getWrappedData();

		for(TecnicaDTO tec : tecs) {
			if(tec.getId().equals(rowKey))
				return tec;
		}

		return null;
	}

	public Object getRowKey(TecnicaDTO arg0) {
		return arg0.getId(); 
	}

}
