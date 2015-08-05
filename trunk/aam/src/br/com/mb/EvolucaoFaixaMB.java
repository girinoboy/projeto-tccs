package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;

import com.sun.org.apache.bcel.internal.generic.IUSHR;

import br.com.dao.CampeonatoDAO;
import br.com.dao.FrequenciaDAO;
import br.com.dao.ResultadoDAO;
import br.com.dao.UsuarioDAO;
import br.com.dto.GraduacaoDTO;
import br.com.dto.ResultadoAvaliacaoDTO;
import br.com.dto.UsuarioDTO;
import br.com.enumeration.ClassificacaoDesempenhoE;
import br.com.enumeration.ConceitoE;

@ManagedBean
@ViewScoped
public class EvolucaoFaixaMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206506428796107752L;
	
	private UsuarioDTO usuarioDTO;
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	private ResultadoAvaliacaoDTO resultadoAvaliacaoDTO;
	private String classificacaoDesempenho;
	private String conceito;
	
	@ManagedProperty(value = "#{usuarioMB}")
	private UsuarioMB usuarioMB;

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(ActionEvent event) {
		usuarioDTO = new UsuarioDTO();
		resultadoAvaliacaoDTO = new ResultadoAvaliacaoDTO();
		
	}
	
	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public Integer getNumCampeonatosParticipado(){

		try {
			if(usuarioDTO != null)
				return resultadoDAO.numCampeonatosParticipado(usuarioDTO);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public boolean getVerificaCriterios(){
		Double total = 0d;
		if(usuarioDTO != null){
			if(usuarioDTO.getContadorMes() >=12){
				total = total + 0.5;
			}
			if(classificacaoDesempenho != null){
				total = total + Double.valueOf(classificacaoDesempenho);
			}
			if(resultadoAvaliacaoDTO.getQtdExercicios() >= 2){
				total = total + 1d;
			}
			if(getNumCampeonatosParticipado() >= 2){
				total = total + 0.5;
			}
			if(resultadoAvaliacaoDTO.getConceito() == ConceitoE.C){
				total = total + 1d;
			}
			if(resultadoAvaliacaoDTO.getTecnica() >= 6.5){
				total = total + 1.5;
			}
			if(resultadoAvaliacaoDTO.getLuta() >= 6.5){
				total = total + 1.5;
			}
			if(resultadoAvaliacaoDTO.getConhecimentos() >= 6.5){
				total = total + 1.5;
			}
		}
		if(total >= 8)
			return false;
		else{
			return true;
		}
	}
	
	public void mudaFaixa(){
		usuarioDTO.setGraduacaoDTO(new GraduacaoDTO(usuarioDTO.getGraduacaoDTO().getId()+1));
		try {
//			usuarioDTO.setGraduacaoDTO((new GraduacaoDAO()).getById(usuarioDTO.getGraduacaoDTO().getId()+2));
			(new UsuarioDAO()).save(usuarioDTO);
//			reset(null);
//			atualiza(null);
			usuarioMB.atualiza(null);
			addMessage("Opera��o realizada com sucesso!");
		} catch (Exception e) {
			addMessage("Graduação inexistente");
			e.printStackTrace();
		}
	}
	
	public UsuarioDTO getUsuarioDTO() {
		if(usuarioDTO != null){
			for (ResultadoAvaliacaoDTO r : usuarioDTO.getListResultadoAvaliacaoDTO()) {
				if(r.getGraduacaoDTO().getId().equals(usuarioDTO.getGraduacaoDTO().getId())){
					this.resultadoAvaliacaoDTO = r;
				}
			}
		}
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public ResultadoAvaliacaoDTO getResultadoAvaliacaoDTO() {
		return resultadoAvaliacaoDTO;
	}

	public void setResultadoAvaliacaoDTO(ResultadoAvaliacaoDTO resultadoAvaliacaoDTO) {
		this.resultadoAvaliacaoDTO = resultadoAvaliacaoDTO;
	}

	public String getClassificacaoDesempenho() {
		if(resultadoAvaliacaoDTO != null && resultadoAvaliacaoDTO.getClassificacaoDesempenho() != null){
			if(resultadoAvaliacaoDTO.getClassificacaoDesempenho().equals(ClassificacaoDesempenhoE.BOM)){
				classificacaoDesempenho = "0.5";
			}else
				if(resultadoAvaliacaoDTO.getClassificacaoDesempenho().equals(ClassificacaoDesempenhoE.REGULAR)){
					classificacaoDesempenho = "1.0";
				} else
					if(resultadoAvaliacaoDTO.getClassificacaoDesempenho().equals(ClassificacaoDesempenhoE.OTIMO)){
						classificacaoDesempenho = "1.5";
					}
		}
		return classificacaoDesempenho;
	}

	public void setClassificacaoDesempenho(String classificacaoDesempenho) {
		this.classificacaoDesempenho = classificacaoDesempenho;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	public UsuarioMB getUsuarioMB() {
		return usuarioMB;
	}

	public void setUsuarioMB(UsuarioMB usuarioMB) {
		this.usuarioMB = usuarioMB;
	}

}
