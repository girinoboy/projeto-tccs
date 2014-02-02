package br.com.utility;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.TecnicaDAO;
import br.com.dto.TecnicaDTO;
  
@FacesConverter(value="tecnica")
public class TecnicaConverter implements Converter {  
  
	private static TecnicaDAO tecnicaDAO = new TecnicaDAO();
    
//    public static List<TecnicaDTO> tecnicaDB;  
//  
//    static {  
//        tecnicaDB = new ArrayList<TecnicaDTO>();  
//        try {
//			tecnicaDB = tecnicaDAO.list();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }  
  
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
                
                return tecnicaDAO.getById(number);
  
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
            return String.valueOf(((TecnicaDTO) value).getId());  
        }  
    }  
}  
