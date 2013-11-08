package br.com.utility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.CidadeDAO;
import br.com.dto.CidadeDTO;
  
@FacesConverter("cidade")
public class CidadeConverter implements Converter {  
  
	private static CidadeDAO cidadeDAO = new CidadeDAO();
    
//    public static List<CidadeDTO> cidadeDB;  
  
//    static {  
//        cidadeDB = new ArrayList<CidadeDTO>();  
//        try {
//			cidadeDB = cidadeDAO.list();
			
//			cidadeDB = new ArrayList<CidadeDTO>();
//			cidadeDB.add(new CidadeDTO(1,"Águas Claras"));
//			cidadeDB.add(new CidadeDTO(2,"Brasilia"));
//			cidadeDB.add(new CidadeDTO(3,"Gama"));
//			cidadeDB.add(new CidadeDTO(7,"Guará"));
//			cidadeDB.add(new CidadeDTO(4,"Planaltina"));
//			cidadeDB.add(new CidadeDTO(5,"Sobradinho"));
//			cidadeDB.add(new CidadeDTO(6,"Taguatinga"));
	
			
			
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
                
//                for (CidadeDTO c : cidadeDB) {
//                    if (c.getId() == number) {
//                        return c;
//                    }
//                }
//                
//                return submittedValue;
                
                return cidadeDAO.getById(number);
  
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
            return String.valueOf(((CidadeDTO) value).getId());  
        }  
    }  
}  
