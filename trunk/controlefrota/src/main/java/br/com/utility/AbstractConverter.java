package br.com.utility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.AbstractDAO;
import br.com.dao.GenericoDAO;
import br.com.dto.AbstractDTO;

@FacesConverter(value="abstractConverter")
public class AbstractConverter implements Converter{

	AbstractDAO genericoDAO = new AbstractDAO();
	public AbstractConverter() {
		// TODO Auto-generated constructor stub
	}

	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.equals("null") || submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                Integer number = Integer.parseInt(submittedValue);
                /*
                for (TecnicaDTO t : tecnicaDB) {
                    if (u.getId() == number) {
                        return t;
                    }
                }*/
                
                return genericoDAO.getById(number);
  
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user")); 
            } catch (Exception e) {
            	throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user"));
			}
        }
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = "";
		if (arg2 instanceof AbstractDTO) 
			str = ((AbstractDTO) arg2).getId().toString(); 
		return str; 
	}

}
