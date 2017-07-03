package br.com.mesa;

import java.util.List;

import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;


public class MainEncoder  implements Encoder<List<PersonagemDTO>, String> {

    //@Override
    public String encode(List<PersonagemDTO> personagemDTO) {
        return new JSONObject().put("list",personagemDTO).toString();
    }

}

