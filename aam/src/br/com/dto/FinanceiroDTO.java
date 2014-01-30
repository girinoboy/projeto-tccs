package br.com.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

/**
 * @author Marcleônio
 *
 */
@Entity
@Table(name = "financeiro")
public class FinanceiroDTO {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Boolean situacao;
	@Column(name="valor_mensalidade")
	private Double valorMensalidade;
	@Column(name="valor_com_desconto")
	private Double valorComDesconto;
	@Column(name ="data_pagamento")
	private Date dataPagamento;
	private Integer dia;
	private Integer mes;
	private Integer ano;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", insertable = true, updatable = true, nullable = true)
	private UsuarioDTO usuarioDTO;

	public FinanceiroDTO() {
		getDataPagamento();
		if(dataPagamento!=null){
			Calendar data = new GregorianCalendar();
			data.setTime(dataPagamento);

			dia = data.get(Calendar.DAY_OF_MONTH);

			mes = data.get(Calendar.MONTH);

			ano = data.get(Calendar.YEAR);

		}else{
			dataPagamento = new Date();
		}
		getDia();
		getMes();
		getAno();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorMensalidade() {
		return valorMensalidade;
	}

	public void setValorMensalidade(Double valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}

	public Double getValorComDesconto() {
		return valorComDesconto;
	}

	public void setValorComDesconto(Double valorComDesconto) {
		this.valorComDesconto = valorComDesconto;
	}

	public Date getDataPagamento() {
		if(dataPagamento == null)
			dataPagamento = new Date();
		getDia();
		getMes();
		getAno();
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getDia() {
		if(dia==null && dataPagamento!=null){
			Calendar data = new GregorianCalendar();
			data.setTime(dataPagamento);
			dia = data.get(Calendar.DAY_OF_MONTH);

		}
		return dia;
	}


	public void setDia(Integer dia) {
		this.dia = dia;
	}


	public Integer getMes() {
		if(mes==null && dataPagamento!=null){
			Calendar data = new GregorianCalendar();
			data.setTime(dataPagamento);
			mes = data.get(Calendar.MONTH);
		}
		return mes;
	}


	public void setMes(Integer mes) {
		this.mes = mes;
	}


	public Integer getAno() {
		if(ano==null && dataPagamento!=null){
			Calendar data = new GregorianCalendar();
			data.setTime(dataPagamento);
			ano = data.get(Calendar.YEAR);
		}
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}


}
