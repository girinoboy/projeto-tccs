package br.com.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Servico {
	
	@Id
	@GeneratedValue
	private Long id;
	
	//est�tica capilar, higiene, est�tica e embelezamento das m�os e p�s, depila��o, est�tica das sobrancelhas e c�lios, maquiagem facial, podendo agregar outros servi�os da est�tica facial.
}
