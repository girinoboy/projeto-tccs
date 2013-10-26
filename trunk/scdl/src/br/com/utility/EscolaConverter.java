package br.com.utility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.EscolaDAO;
import br.com.dto.EscolaDTO;
  
@FacesConverter(value="escola")
public class EscolaConverter implements Converter {  
  
	private static EscolaDAO escolaDAO = new EscolaDAO();
    
//    public static List<EscolaDTO> escolaDB;  
//  
//    static {  
//        EscolaDB = new ArrayList<EscolaDTO>();  
//        try {
//			escolaDB = EscolaDAO.list();
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
                int number = Integer.parseInt(submittedValue);
                /*
                for (EscolaDTO u : escolaDB) {
                    if (u.getId() == number) {
                        return u;
                    }
                }*/
                
                return escolaDAO.getById(number);
  
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
            return String.valueOf(((EscolaDTO) value).getId());  
        }  
    }  
}  
