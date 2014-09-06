/**
 * 
 */
package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.utility.Situacao;

/**
 * @author marcleonio
 *
 */
@Entity
@Table(name = "atendimento")
public class AtendimentoDTO extends AbstractDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4082530746039132605L;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="veiculo_id", referencedColumnName = "id",insertable=true,updatable=true,nullable=false)
	private VeiculoDTO veiculoDTO;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuario_id", referencedColumnName = "id",insertable=true,updatable=true,nullable=false)
	private UsuarioDTO usuarioDTO;
	private String destino;
	@Enumerated(EnumType.STRING)
    private Situacao situacao;

	/**
	 * 
	 */
	public AtendimentoDTO() {
		// TODO Auto-generated constructor stub
	}

	public VeiculoDTO getVeiculoDTO() {
		return veiculoDTO;
	}

	public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
		this.veiculoDTO = veiculoDTO;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}
