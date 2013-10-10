/**
 * 
 */
package br.com.utility;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dto.TipoConsultaDTO;
  
/**
 * @author marcleonio.medeiros
 *
 */

@FacesConverter(value="tipoConsulta")
public class TipoConsultaConverter implements Converter {  
  
    public static List<TipoConsultaDTO> listTipoConsulta;  
    
    static {  
    	listTipoConsulta = new ArrayList<TipoConsultaDTO>();  
        
        listTipoConsulta.add(new TipoConsultaDTO("Primeira Consulta","I"));
		listTipoConsulta.add(new TipoConsultaDTO("Retorno","R"));
		listTipoConsulta.add(new TipoConsultaDTO("Sess�o","S"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Peri�dico(M�dico/Odontol�gico)","E"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Peri�dico M�dico","M"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Peri�dico Odontol�gico","O"));
		listTipoConsulta.add(new TipoConsultaDTO("Exame Admissional","A"));
		listTipoConsulta.add(new TipoConsultaDTO("Per�cia","P"));
    }  
  
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {  
        if (submittedValue.trim().equals("")) {  
            return null;  
        } else {  
            try {  
                int number = Integer.parseInt(submittedValue);  
  
                for (TipoConsultaDTO p : listTipoConsulta) {  
                    if (p.getId() == number) {  
                        return p;  
                    }  
                }  
  
            } catch(NumberFormatException exception) {  
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));  
            }  
        }  
  
        return null;  
    }  
  
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((TipoConsultaDTO) value).getId());  
        }  
    }  
}  

