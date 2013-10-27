package br.com.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="resultado_avaliacao")
public class ResuldoAvaliacaoDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	public ResuldoAvaliacaoDTO() {
		// TODO Auto-generated constructor stub
	}

}
