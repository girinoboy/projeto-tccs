/**
 * 
 */
package br.com.utility;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.dto.NoticiaDTO;

/**
 * @author marcleonio.medeiros
 *
 */
public class NoticiaDataModel extends ListDataModel implements SelectableDataModel<NoticiaDTO> {

	/**
	 * 
	 */
	public NoticiaDataModel() {
		// TODO Auto-generated constructor stub
	}
	public NoticiaDataModel(List<NoticiaDTO> data) {  
		super(data);  
	} 

	public NoticiaDTO getRowData(String rowKey) {
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

		@SuppressWarnings("unchecked")
		List<NoticiaDTO> nots = (List<NoticiaDTO>) getWrappedData();

		for(NoticiaDTO not : nots) {
			if(not.getId().equals(rowKey))
				return not;
		}

		return null;
	}

	public Object getRowKey(NoticiaDTO arg0) {
		return arg0.getId(); 
	}

}
