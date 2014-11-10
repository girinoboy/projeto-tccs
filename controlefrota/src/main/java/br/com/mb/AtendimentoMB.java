/**
 * 
 */
package br.com.mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.AtendimentoDAO;
import br.com.dto.AtendimentoDTO;
import br.com.dto.VeiculoDTO;
import br.com.ind.indSituacao;

/**
 * @author marcleonio
 *
 */
@ManagedBean
@SessionScoped
public class AtendimentoMB extends GenericoMB<AtendimentoDTO> implements ModeloMB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7164991406034265304L;
	private AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
	private AtendimentoDTO atendimentoDTO;
	private List<AtendimentoDTO> listAtendimentoDTO;
	
	@ManagedProperty("#{veiculoMB}")
	private VeiculoMB veiculoMB;

	@PostConstruct
	public void inicio() throws Exception{
		super.inicio();
		atendimentoDTO = new AtendimentoDTO();
		System.out.println(1);
		//		atendimentoDTO = new AtendimentoDTO();
		//		listAtendimentoDTO = new ArrayList<AtendimentoDTO>();
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public AtendimentoMB() throws Exception {
		atualiza(null);
	}

	@Override
	public void atualiza(ActionEvent event) throws Exception {
		if(getUserSession().getPerfilDTO().getId().equals(3)){
			listAtendimentoDTO = atendimentoDAO.listPorIdUsuario(getUserSession());
		}else{
			listAtendimentoDTO = atendimentoDAO.list();
		}
		atendimentoDTO = new AtendimentoDTO();
	}

	@Override
	public void reset(ActionEvent event) {
		atendimentoDTO = new AtendimentoDTO();
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		if(atendimentoDAO.verificaExisteAtendimento(atendimentoDTO)){		
			if(atendimentoDTO.getSituacao() == null){
				atendimentoDTO.setSituacao(indSituacao.AGUARDANDO);
			}else if(atendimentoDTO.getSituacao().equals(indSituacao.AGUARDANDO) && atendimentoDTO.getDataSaida() != null){
				atendimentoDTO.setSituacao(indSituacao.EM_ANDAMENTO);
			}else if(atendimentoDTO.getSituacao().equals(indSituacao.EM_ANDAMENTO) && atendimentoDTO.getDataChegada() != null){
				atendimentoDTO.setSituacao(indSituacao.FINALIZADO);
			}
			calculaQuilometragem();
			atendimentoDAO.save(atendimentoDTO);
			atualiza(null);
			veiculoMB.atualiza(null);
			addMessage(rb.getString("successfullySaved"));
		}else{
			addMessage("Já existe um atendimento para esse carro");
		}
	}

	private void calculaQuilometragem() {
		Long kmAtual = atendimentoDTO.getVeiculoDTO().getKmAtual() == null ? 0:atendimentoDTO.getVeiculoDTO().getKmAtual();
		Long kmFinal = atendimentoDTO.getKmFinal() == null ? 0:atendimentoDTO.getKmFinal();
		atendimentoDTO.setKmInicial(kmAtual);
		atendimentoDTO.getVeiculoDTO().setKmAtual(kmFinal);
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		atualiza(null);

	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		abstractDAO.delete(abstractDTO);
		addMessage(rb.getString("successfullyDeleted"));
		atualiza(null);
	}

	public void populaAtendimento(AtendimentoDTO atendimento ){
		atendimentoDTO = atendimento;
		if(atendimentoDTO.getDataSaida() == null){
			atendimentoDTO.setDataSaida(new Date());
		}
		if(atendimentoDTO.getHoraSaida() == null){
			atendimentoDTO.setHoraSaida(new Date());
		}
		if(atendimentoDTO.getKmInicial() == null){
			atendimentoDTO.setKmInicial(0L);
		}
		if(atendimentoDTO.getVeiculoDTO() == null){
			atendimentoDTO.setVeiculoDTO(new VeiculoDTO());
			atendimentoDTO.getVeiculoDTO().setKmAtual(0L);
		}else if(atendimentoDTO.getVeiculoDTO().getKmAtual() == null){
			atendimentoDTO.getVeiculoDTO().setKmAtual(0L);
		}
		
		if(atendimentoDTO.getSituacao().equals(indSituacao.EM_ANDAMENTO)){
			atendimentoDTO.setDataChegada(new Date());
			atendimentoDTO.setHoraChegada(new Date());
		}
	}

	public AtendimentoDTO getAtendimentoDTO() {
		return atendimentoDTO;
	}

	public void setAtendimentoDTO(AtendimentoDTO atendimentoDTO) {
		this.atendimentoDTO = atendimentoDTO;
	}

	public List<AtendimentoDTO> getListAtendimentoDTO() {
		return listAtendimentoDTO;
	}

	public void setListAtendimentoDTO(List<AtendimentoDTO> listAtendimentoDTO) {
		this.listAtendimentoDTO = listAtendimentoDTO;
	}

	public void setVeiculoMB(VeiculoMB veiculoMB) {
		this.veiculoMB = veiculoMB;
	}

}
