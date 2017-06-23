package br.com.rpg.mb;

import javax.faces.application.FacesMessage;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/main")
public class MainResource {


	@OnMessage(encoders = {JSONEncoder.class})
	public FacesMessage onMessage(FacesMessage message) {
		String[] a = message.getDetail().split(" ");
		System.out.println(a);
        return message;
    }

}
