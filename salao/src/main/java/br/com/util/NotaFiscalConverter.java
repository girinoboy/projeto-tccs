package br.com.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.NotaFiscalDAO;
import br.com.dao.ProdutoDAO;
import br.com.model.NotaFiscal;
import br.com.model.Produto;


@FacesConverter("notaFiscalConverter")
public class NotaFiscalConverter implements Converter {  
  
	private static NotaFiscalDAO dao = new NotaFiscalDAO(NotaFiscal.class);
    
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
    	
        if (submittedValue == null || submittedValue.equals("null") || submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                long number = Long.parseLong(submittedValue);
                
                return dao.buscaPorId(number);
  
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user")); 
            } catch (Exception e) {
            	throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user"));
			}
        }
  
    }
  
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((NotaFiscal) value).getId());  
        }  
    }  
}