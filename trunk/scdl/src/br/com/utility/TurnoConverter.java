package br.com.utility;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.dao.TurnoDAO;
import br.com.dto.TurnoDTO;
  
@FacesConverter("turno")
public class TurnoConverter implements Converter {  
  
	private static TurnoDAO turnoDAO = new TurnoDAO();
    
    public static List<TurnoDTO> turnoDB;  
  
    static {  
        turnoDB = new ArrayList<TurnoDTO>();  
        try {
//			turnoDB = turnoDAO.list();
			
			turnoDB = new ArrayList<TurnoDTO>();
			turnoDB.add(new TurnoDTO(1,"Matutino"));
			turnoDB.add(new TurnoDTO(2,"Vespertino"));
			turnoDB.add(new TurnoDTO(3,"Noturno"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    
  
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
    	
        if (submittedValue == null || submittedValue.equals("null") || submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                
                for (TurnoDTO t : turnoDB) {
                    if (t.getId() == number) {
                        return t;
                    }
                }
                
                return turnoDAO.getById(number);
  
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
            return String.valueOf(((TurnoDTO) value).getId());  
        }  
    }  
}  
