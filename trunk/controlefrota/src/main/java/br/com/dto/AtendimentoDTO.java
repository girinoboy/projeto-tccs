/**
 * 
 */
package br.com.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ind.indSituacao;

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
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="veiculo_id", referencedColumnName = "id",insertable=true,updatable=true,nullable=false)
	private VeiculoDTO veiculoDTO;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="usuario_id", referencedColumnName = "id",insertable=true,updatable=true,nullable=false)
	private UsuarioDTO usuarioDTO;
	private String destino;
	@Enumerated(EnumType.STRING)
    private indSituacao situacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_saida")
	private Date dataSaida;
	@Temporal(TemporalType.TIME)
	@Column(name="hora_saida")
	private Date horaSaida;
	@Temporal(TemporalType.DATE)
	@Column(name="data_chegada")
	private Date dataChegada;
	@Temporal(TemporalType.TIME)
	@Column(name="hora_chegada")
	private Date horaChegada;
	
	private String percurso;
	private String observacoes;
	
	@Column(name="km_inicial")
	private Long kmInicial;
	@Column(name="km_final")
	private Long kmFinal;

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

	public indSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(indSituacao situacao) {
		this.situacao = situacao;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Date getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(Date horaChegada) {
		this.horaChegada = horaChegada;
	}

	public String getPercurso() {
		return percurso;
	}

	public void setPercurso(String percurso) {
		this.percurso = percurso;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Long getKmInicial() {
		return kmInicial;
	}

	public void setKmInicial(Long kmInicial) {
		this.kmInicial = kmInicial;
	}

	public Long getKmFinal() {
		return kmFinal;
	}

	public void setKmFinal(Long kmFinal) {
		this.kmFinal = kmFinal;
	}

}
