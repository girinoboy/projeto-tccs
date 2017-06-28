package br.com.rpg.mb;

import java.util.List;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;

import br.com.rpg.dto.PersonagemDTO;

@PushEndpoint("/main")
public class MainResource {


	@OnMessage(decoders = {MainDecoder.class}, encoders = {MainEncoder.class})
	public List<PersonagemDTO> onMessage(List<PersonagemDTO> message) {
		//String[] a = message.getDetail().split(" ");
		//System.out.println(a);
        return message;
    }

}
