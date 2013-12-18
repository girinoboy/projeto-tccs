package br.com.utility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.LocalDAO;
import br.com.dto.LocalDTO;
  
@FacesConverter("local")
public class LocalConverter implements Converter {  
  
	private static LocalDAO localDAO = new LocalDAO();
    
//    public static List<LocalDTO> localDB;  
  
//    static {  
//        localDB = new ArrayList<LocalDTO>();  
//        try {
//			localDB = localDAO.list();
			
//			localDB = new ArrayList<LocalDTO>();
//			localDB.add(new LocalDTO(1,"Águas Claras"));
//			localDB.add(new LocalDTO(2,"Brasilia"));
//			localDB.add(new LocalDTO(3,"Gama"));
//			localDB.add(new LocalDTO(7,"Guará"));
//			localDB.add(new LocalDTO(4,"Planaltina"));
//			localDB.add(new LocalDTO(5,"Sobradinho"));
//			localDB.add(new LocalDTO(6,"Taguatinga"));
	
			
			
//		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }  
    
  
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
    	
        if (submittedValue == null || submittedValue.equals("null") || submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                
//                for (LocalDTO c : localDB) {
//                    if (c.getId() == number) {
//                        return c;
//                    }
//                }
//                
//                return submittedValue;
                
                return localDAO.getById(number);
  
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
            return String.valueOf(((LocalDTO) value).getId());  
        }  
    }  
}  
