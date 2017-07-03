package br.com.mesa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class PersonagemMB implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<PersonagemDTO> listPersonagem;

	@PostConstruct
	public void init() {
		listPersonagem = new ArrayList<PersonagemDTO>();
	}

	public List<PersonagemDTO> getListPersonagem() {
		return listPersonagem;
	}

	public void remove(PersonagemDTO personagem) {
		this.listPersonagem.remove(personagem);
	}

	public void add(PersonagemDTO personagem) {
		this.listPersonagem.add(personagem);
	}

	public boolean contains(PersonagemDTO personagem) {
		return this.listPersonagem.contains(personagem);
	}
}
