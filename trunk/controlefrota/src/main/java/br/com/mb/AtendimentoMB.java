/**
 * 
 */
package br.com.mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.dao.AtendimentoDAO;
import br.com.dto.AtendimentoDTO;
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
	}

	@Override
	public void reset(ActionEvent event) {
		atendimentoDTO = new AtendimentoDTO();
		atendimentoDTO.setDataSaida(new Date());
		atendimentoDTO.setHoraSaida(new Date());
		atendimentoDTO.setKmInicial("0");
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		if(atendimentoDTO.getSituacao() == null){
			atendimentoDTO.setSituacao(indSituacao.AGUARDANDO);
		}else if(atendimentoDTO.getSituacao().equals(indSituacao.AGUARDANDO) && atendimentoDTO.getDataSaida() != null){
			atendimentoDTO.setSituacao(indSituacao.EM_ANDAMENTO);
		}else if(atendimentoDTO.getSituacao().equals(indSituacao.EM_ANDAMENTO) && atendimentoDTO.getDataChegada() != null){
			atendimentoDTO.setSituacao(indSituacao.FINALIZADO);
		}
		atendimentoDAO.save(atendimentoDTO);
		atualiza(null);
		addMessage(rb.getString("successfullySaved"));
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

}