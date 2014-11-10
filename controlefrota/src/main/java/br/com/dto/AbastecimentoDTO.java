/**
 * 
 */
package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author marcleonio
 *
 */
@Entity
@Table(name = "abastecimento")
public class AbastecimentoDTO extends AbstractDTO{
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="veiculo_id", referencedColumnName = "id",insertable=true,updatable=true,nullable=false)
	private VeiculoDTO veiculoDTO;
	@Column(name="quantidade_litros_abastecidos")
	private Long quantidadeLitrosAbastecidos;

	/**
	 * 
	 */
	public AbastecimentoDTO() {
		// TODO Auto-generated constructor stub
	}

	public VeiculoDTO getVeiculoDTO() {
		return veiculoDTO;
	}

	public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
		this.veiculoDTO = veiculoDTO;
	}

	public Long getQuantidadeLitrosAbastecidos() {
		return quantidadeLitrosAbastecidos;
	}

	public void setQuantidadeLitrosAbastecidos(Long quantidadeLitrosAbastecidos) {
		this.quantidadeLitrosAbastecidos = quantidadeLitrosAbastecidos;
	}

}
