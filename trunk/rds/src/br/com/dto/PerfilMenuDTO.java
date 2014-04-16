package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "perfil_menu")
public class PerfilMenuDTO extends AbstractDTO{

//	@Id 
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="perfil_id", referencedColumnName = "id",insertable=true,updatable=true,nullable=false)
	private PerfilDTO perfilDTO;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="menu_id", referencedColumnName = "id", insertable=true,updatable=true,nullable=false)
    private MenuDTO menuDTO;
    @Column(name="ativo_inativo")
    private Boolean ativoInativo;
    
    public PerfilMenuDTO(){}

    public PerfilMenuDTO(PerfilDTO perfilDTO, MenuDTO menuDTO,
			Boolean ativoInativo) {
		super();
		this.perfilDTO = perfilDTO;
		this.menuDTO = menuDTO;
		this.ativoInativo = ativoInativo;
	}

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	public PerfilDTO getPerfilDTO() {
		return perfilDTO;
	}

	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public MenuDTO getMenuDTO() {
		return menuDTO;
	}

	public void setMenuDTO(MenuDTO menuDTO) {
		this.menuDTO = menuDTO;
	}
	
}

