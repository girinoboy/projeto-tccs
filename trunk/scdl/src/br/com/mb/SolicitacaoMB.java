package br.com.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.com.dao.LivroDAO;
import br.com.dao.SolicitacaoDAO;
import br.com.dto.LivroDTO;
import br.com.dto.SolicitacaoDTO;

@ManagedBean
public class SolicitacaoMB extends GenericoMB implements ModeloMB{
	
	private List<SolicitacaoDTO> listSolicitacao;
	private List<SolicitacaoDTO> filteredsolicitacoes;
	private SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();
	private SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	
	private LivroDAO livroDAO = new LivroDAO();

	public SolicitacaoMB() {
		try {
			listSolicitacao = solicitacaoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(ActionEvent actionEvent) throws Exception {
		solicitacaoDAO.save(solicitacaoDTO);
		solicitacaoDTO = new SolicitacaoDTO();
		addMessage("salvo");
	}

	@Override
	public void edit(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(ActionEvent actionEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void populaLivro(ActionEvent actionEvent){
		
		Serializable id = null;
		LivroDTO livroDTO = null;
		try {
			livroDTO = livroDAO.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		solicitacaoDTO.setLivroDTO(livroDTO);
	}

	public List<SolicitacaoDTO> getListSolicitacao() {
		return listSolicitacao;
	}

	public void setListSolicitacao(List<SolicitacaoDTO> listSolicitacao) {
		this.listSolicitacao = listSolicitacao;
	}

	public List<SolicitacaoDTO> getFilteredsolicitacoes() {
		return filteredsolicitacoes;
	}

	public void setFilteredsolicitacoes(List<SolicitacaoDTO> filteredsolicitacoes) {
		this.filteredsolicitacoes = filteredsolicitacoes;
	}

	public SolicitacaoDTO getSolicitacaoDTO() {
		return solicitacaoDTO;
	}

	public void setSolicitacaoDTO(SolicitacaoDTO solicitacaoDTO) {
		this.solicitacaoDTO = solicitacaoDTO;
	}

}
