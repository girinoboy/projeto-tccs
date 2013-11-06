package br.com.utility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.EditoraDAO;
import br.com.dto.EditoraDTO;
  
@FacesConverter(value="editora")
public class EditoraConverter implements Converter {  
  
	private static EditoraDAO editoraDAO = new EditoraDAO();
    
//    public static List<EditoraDTO> editoraDB;  
//  
//    static {  
//        editoraDB = new ArrayList<EditoraDTO>();  
//        try {
//			editoraDB = editoraDAO.list();
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
                for (EditoraDTO e : editoraDB) {
                    if (e.getId() == number) {
                        return e;
                    }
                }*/
                
                return editoraDAO.getById(number);
  
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
            return String.valueOf(((EditoraDTO) value).getId());  
        }  
    }  
}  
