/**
 * 
 */
package br.com.utility;

import java.util.List;  
import javax.faces.model.ListDataModel;  
import br.com.dto.HistoriaDTO;
import org.primefaces.model.SelectableDataModel; 


/**
 * @author marcleonio.medeiros
 *
 */
public class HistoriaDataModel  extends ListDataModel implements SelectableDataModel<HistoriaDTO> {    
  
	/**
	 * 
	 */
	public HistoriaDataModel() {
	}
  
    public HistoriaDataModel(List<HistoriaDTO> data) {  
        super(data);  
    }  
    @SuppressWarnings("unchecked")
    public HistoriaDTO getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
		List<HistoriaDTO> historiaDTO = (List<HistoriaDTO>) getWrappedData();
          
        for(HistoriaDTO h : historiaDTO) {  
            if(h.getId().equals(rowKey))  
                return h;  
        }  
          
        return null;  
    }  
  
	public Object getRowKey(HistoriaDTO h) {  
        return h.getId();  
    }  
}  

