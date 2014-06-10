package br.ucb.modelo.acao;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.bean.ClubeBean;
import br.ucb.modelo.dao.ClubeDAO;
import br.ucb.modelo.enumerador.Uf;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ClubeAcao implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClubeDAO clubeDao = new ClubeDAO();
		ClubeBean clube = new ClubeBean();
		List <ClubeBean> clubes;
		
		// ACAOINTERNA = 1. null
		if (request.getParameter("acaoInterna") == null) {
			clubes = clubeDao.listar();
			request.setAttribute("clubes", clubes);
			return "LISTAGEM"; // RESULTADO = LISTAGEM
		}
		// Pega os parametros da requisição (campos de formulario) e monta o objeto clube na memória.
		// Este objeto é que será utilizado nas ações internas abaixo para persistir o clube com o banco.
		try {
			if (request.getParameter("nomeTime") != null)
				clube.setNomeTime(request.getParameter("nomeTime"));
			if (request.getParameter("dtCriacao") != null ){
			    int ano = 0;
			    System.out.println(request.getParameter("dtCriacao"));
			    ano = Integer.parseInt(request.getParameter("dtCriacao"));
				if (ano >= 1800 && ano <= 2012) {
			    	clube.setDtCriacao(ano);	
			    }
			}
			if (request.getParameter("uf") != null)
				clube.setUf(Uf.valueOf(request.getParameter("uf")));
			if (request.getParameter("descricao") != null){
				clube.setDescricao(request.getParameter("descricao"));
			    System.out.println(request.getParameter("descricao"));
			}
		}
		catch (Exception e) {
			// Cria uma variável (contexto/requisicao) para o ERRO
			request.setAttribute("erro", "Erro de conversao");
			return Acao.ENTRADA; // RESULTADO = ENTRADA
		}
		// ACAOINTERNA = 2. excluir
		if (request.getParameter("acaoInterna").equals("excluir")) {
			clube = clubeDao.consultar(Long.valueOf(request.getParameter("id")));
			try {
				if (clubeDao.excluir(clube) > 0)
					request.setAttribute("mensagem", "Excluído com sucesso");
				else
					request.setAttribute("erro", "Erro de exclusão");
			} catch (MySQLIntegrityConstraintViolationException e) {
				request.setAttribute("erro", "Exitem torcedores vinculados a este clube!<br/>Antes de excluir o curso, exclua estes torcedores.");
			}
		}
		// ACAOINTERNA = 3. incluir
		if (request.getParameter("acaoInterna").equals("incluir")){
			request.setAttribute("ufs", Uf.AC);
			return Acao.ENTRADA; // RESULTADO = ENTRADA
		} 
		// ACAOINTERNA = 4. alterar
		if (request.getParameter("acaoInterna").equals("alterar")) {
			clube = clubeDao.consultar(Long.valueOf(request.getParameter("id")));
			if (clube == null)
				request.setAttribute("erro", "Erro ao localizar para alteração");
			else {
				request.setAttribute("clube", clube);
				request.setAttribute("ufs", Uf.AC);
				return Acao.ENTRADA; // RESULTADO = ENTRADA
			}
		}
		// ACAOINTERNA = 5. salvar
		if (request.getParameter("acaoInterna").equals("salvar")) {
			if (request.getParameter("id").equals("")) {
				if (clubeDao.incluir(clube) > 0)
					request.setAttribute("mensagem", "Incluído com sucesso");
				else
					request.setAttribute("erro", "Erro de inclusão");
			}
			else {
				clube.setId(Long.valueOf(request.getParameter("id")));
				if (clubeDao.alterar(clube) > 0)
					request.setAttribute("mensagem", "Alterado com sucesso");
				else
					request.setAttribute("erro", "Erro de alteração");
			}
		}
		// ACAOINTERNA = 6. filtrar
		if (request.getParameter("acaoInterna").equals("filtrar"))
			clubes = clubeDao.listarFiltro(request.getParameter("nomeFiltro"));
		else
			clubes = clubeDao.listar();
		request.setAttribute("clubes", clubes);
		return "LISTAGEM"; // RESULTADO = LISTAGEM
	}

}