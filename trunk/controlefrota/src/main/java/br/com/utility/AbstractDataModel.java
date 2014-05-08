/**
 * 
 */
package br.com.utility;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.dto.AbstractDTO;

/**
 * @author marcleonio.medeiros
 * @param <T>
 *
 */
@SuppressWarnings("unchecked")
public class AbstractDataModel<T> extends ListDataModel implements SelectableDataModel<T> {

	/**
	 * 
	 */
	public AbstractDataModel() {
		// TODO Auto-generated constructor stub
	}
	public AbstractDataModel(List<T> data) {  
		super(data);  
	} 

	public T getRowData(String rowKey) {
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

		List<T> os = (List<T>) getWrappedData();

		for(T o : os) {
			if(((AbstractDTO) o).getId().equals(rowKey))
				return o;
		}

		return null;
	}

	public Object getRowKey(T arg0) {
		return ((AbstractDTO) arg0).getId(); 
	}

}
