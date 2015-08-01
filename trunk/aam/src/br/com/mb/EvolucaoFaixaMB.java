package br.com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.sun.org.apache.bcel.internal.generic.IUSHR;

import br.com.dto.ResultadoAvaliacaoDTO;
import br.com.dto.UsuarioDTO;

@ManagedBean
@ViewScoped
public class EvolucaoFaixaMB extends GenericoMB implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206506428796107752L;
	
	private UsuarioDTO usuarioDTO;
	private ResultadoAvaliacaoDTO resultadoAvaliacaoDTO;
	private String classificacaoDesempenho;
	private String conceito;

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

}
