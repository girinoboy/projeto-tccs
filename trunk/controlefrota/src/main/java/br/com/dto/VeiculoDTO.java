/**
 * 
 */
package br.com.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

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
	private String marca;
	private String ano;
	private String tipoCombustivel;
	private String tipo;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodigoVeiculo() {
		return codigoVeiculo;
	}

	public void setCodigoVeiculo(String codigoVeiculo) {
		this.codigoVeiculo = codigoVeiculo;
	}

}
