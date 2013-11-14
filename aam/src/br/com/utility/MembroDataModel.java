/**
 * 
 */
package br.com.utility;

import java.util.List;  
import javax.faces.model.ListDataModel;  
import br.com.dto.UsuarioDTO;
import org.primefaces.model.SelectableDataModel; 


/**
 * @author marcleonio.medeiros
 *
 */
public class MembroDataModel  extends ListDataModel implements SelectableDataModel<UsuarioDTO> {    
  
	/**
	 * 
	 */
	public MembroDataModel() {
	}
  
    public MembroDataModel(List<UsuarioDTO> data) {  
        super(data);  
    }  
    @SuppressWarnings("unchecked")
    public UsuarioDTO getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
		List<UsuarioDTO> users = (List<UsuarioDTO>) getWrappedData();
          
        for(UsuarioDTO use : users) {  
            if(use.getId().equals(rowKey))  
                return use;  
        }  
          
        return null;  
    }  
  
	public Object getRowKey(UsuarioDTO user) {  
        return user.getId();  
    }  
}  

