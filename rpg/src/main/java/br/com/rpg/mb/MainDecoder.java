package br.com.rpg.mb;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.push.Decoder;

import br.com.rpg.dto.PersonagemDTO;

public class MainDecoder implements Decoder<String,List<PersonagemDTO>> {

    //@Override
    public List<PersonagemDTO> decode(String s) {
        String[] userAndMessage = s.split(":");
        if (userAndMessage.length >= 2) {
            return new ArrayList<PersonagemDTO>();
        }
        else {
            return new ArrayList<PersonagemDTO>();
        }
    }
}