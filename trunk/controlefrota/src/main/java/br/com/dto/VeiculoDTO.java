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
	private String capacidadeTanque;
	private String kmOleo;
	private String kmPeneu;
	private String kmLitro;
	private String kmRevisao;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "marca_id", insertable = true, updatable = true, nullable = true)
	private MarcaDTO marcaDTO;
	private String anoInicio;
	private String anoFim;
	@Enumerated(EnumType.STRING)
	private IndTipoCombustivel tipoCombustivel;
	@Enumerated(EnumType.STRING)
	private IndTipo tipo;
	private String codigoVeiculo;

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

	public String getCapacidadeTanque() {
		return capacidadeTanque;
	}

	public void setCapacidadeTanque(String capacidadeTanque) {
		this.capacidadeTanque = capacidadeTanque;
	}

	public String getKmOleo() {
		return kmOleo;
	}

	public void setKmOleo(String kmOleo) {
		this.kmOleo = kmOleo;
	}

	public String getKmPeneu() {
		return kmPeneu;
	}

	public void setKmPeneu(String kmPeneu) {
		this.kmPeneu = kmPeneu;
	}

	public String getKmLitro() {
		return kmLitro;
	}

	public void setKmLitro(String kmLitro) {
		this.kmLitro = kmLitro;
	}

	public String getKmRevisao() {
		return kmRevisao;
	}

	public void setKmRevisao(String kmRevisao) {
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

}
