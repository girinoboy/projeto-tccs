package br.com.mesa;

import java.util.List;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;

@PushEndpoint("/main")
@Singleton
public class MainResource {


	@OnMessage( encoders = {MainEncoder.class})
	public List<PersonagemDTO> onMessage(List<PersonagemDTO> message) {
		//String[] a = message.getDetail().split(" ");
		//System.out.println(a);
        return message;
    }

}
