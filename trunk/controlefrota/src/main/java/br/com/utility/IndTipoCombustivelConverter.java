/**
 * 
 */
package br.com.utility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.ind.IndTipoCombustivel;

/**
 * @author marcleonio
 *
 */
@FacesConverter(value="indTipoCombustivelConverter")
public class IndTipoCombustivelConverter implements Converter {  

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.equals("null") || submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                
                return IndTipoCombustivel.valueOf(submittedValue);
  
            } catch (Exception e) {
            	throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid id"));
			}
        }
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((IndTipoCombustivel) value).name());
        }
    }


}
