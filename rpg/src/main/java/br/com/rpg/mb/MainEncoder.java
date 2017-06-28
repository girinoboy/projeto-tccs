package br.com.rpg.mb;

import java.util.List;

import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;

import br.com.rpg.dto.PersonagemDTO;


public class MainEncoder  implements Encoder<List<PersonagemDTO>, String> {

    //@Override
    public String encode(List<PersonagemDTO> personagemDTO) {
        return new JSONObject().put("list",personagemDTO).toString();
    }

}

