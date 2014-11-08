/**
 * 
 */
package br.com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ind.IndTipo;
import br.com.ind.IndTipoCombustivel;

/**
 * @author marcleonio
 *
 */

@Entity
@Table(name = "veiculo")
public class VeiculoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6037278747810425918L;
	
	private String placa;
	private String modelo;
	private String chassi;
	private String renavam;
	@Column(name="capacidade_tanque")
	private Long capacidadeTanque;
	@Column(name="km_oleo")
	private Long kmOleo;
	@Column(name="km_peneu")
	private Long kmPeneu;
	@Column(name="km_litro")
	private Long kmLitro;
	@Column(name="km_revisao")
	private Long kmRevisao;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "marca_id", insertable = true, updatable = true, nullable = true)
	private MarcaDTO marcaDTO;
	@Column(name="ano_inicio")
	private String anoInicio;
	@Column(name="ano_fim")
	private String anoFim;
	@Enumerated(EnumType.STRING)
	private IndTipoCombustivel tipoCombustivel;
	@Enumerated(EnumType.STRING)
	private IndTipo tipo;
	@Column(name="codigo_veiculo")
	private String codigoVeiculo;
	@Column(name="km_atual")
	private Long kmAtual;

	public VeiculoDTO(){

	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public Long getCapacidadeTanque() {
		return capacidadeTanque;
	}

	public void setCapacidadeTanque(Long capacidadeTanque) {
		this.capacidadeTanque = capacidadeTanque;
	}

	public Long getKmOleo() {
		return kmOleo;
	}

	public void setKmOleo(Long kmOleo) {
		this.kmOleo = kmOleo;
	}

	public Long getKmPeneu() {
		return kmPeneu;
	}

	public void setKmPeneu(Long kmPeneu) {
		this.kmPeneu = kmPeneu;
	}

	public Long getKmLitro() {
		return kmLitro;
	}

	public void setKmLitro(Long kmLitro) {
		this.kmLitro = kmLitro;
	}

	public Long getKmRevisao() {
		return kmRevisao;
	}

	public void setKmRevisao(Long kmRevisao) {
		this.kmRevisao = kmRevisao;
	}

	public MarcaDTO getMarcaDTO() {
		return marcaDTO;
	}

	public void setMarcaDTO(MarcaDTO marcaDTO) {
		this.marcaDTO = marcaDTO;
	}

	public String getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(String anoInicio) {
		this.anoInicio = anoInicio;
	}

	public String getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(String anoFim) {
		this.anoFim = anoFim;
	}

	public IndTipoCombustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(IndTipoCombustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public IndTipo getTipo() {
		return tipo;
	}

	public void setTipo(IndTipo tipo) {
		this.tipo = tipo;
	}

	public String getCodigoVeiculo() {
		return codigoVeiculo;
	}

	public void setCodigoVeiculo(String codigoVeiculo) {
		this.codigoVeiculo = codigoVeiculo;
	}

	public Long getKmAtual() {
		return kmAtual;
	}

	public void setKmAtual(Long kmAtual) {
		this.kmAtual = kmAtual;
	}

}
