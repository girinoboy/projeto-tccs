package br.ucb.modelo.acao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.bean.CamisetaBean;
import br.ucb.modelo.dao.CamisetaDAO;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class CamisetaAcao implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CamisetaDAO camisetaDao = new CamisetaDAO();
		CamisetaBean camiseta = new CamisetaBean();
		List <CamisetaBean> camisetas;
		
		// ACAOINTERNA = 1. null
		if (request.getParameter("acaoInterna") == null) {
			camisetas = camisetaDao.listar();
			request.setAttribute("camisetas", camisetas);
			return "LISTAGEM"; // RESULTADO = LISTAGEM
		}
		// Pega os parametros da requisição (campos de formulario) e monta o objeto camiseta na memória.
		// Este objeto é que será utilizado nas ações internas abaixo para persistir o camiseta com o banco.
		try {
			if (request.getParameter("nomeTime") != null)
				camiseta.setNomeTime(request.getParameter("nomeTime"));
			if (request.getParameter("dtCriacao") != null ){
			    int ano = 0;
			    System.out.println(request.getParameter("dtCriacao"));
			    ano = Integer.parseInt(request.getParameter("dtCriacao"));
				if (ano >= 1800 && ano <= 2012) {
			    	camiseta.setDtCriacao(ano);	
			    }
			}
			if (request.getParameter("nomeTime") != null)
				camiseta.setNomeTime(request.getParameter("nomeTime"));
			if (request.getParameter("descricao") != null){
				camiseta.setDescricao(request.getParameter("descricao"));
			    System.out.println(request.getParameter("descricao"));
			}
			if (request.getParameter("nomeCamiseta") != null)
				camiseta.setNomeCamiseta(request.getParameter("nomeCamiseta"));
		}
		catch (Exception e) {
			// Cria uma variável (contexto/requisicao) para o ERRO
			request.setAttribute("erro", "Erro de conversao");
			return Acao.ENTRADA; // RESULTADO = ENTRADA
		}
		// ACAOINTERNA = 2. excluir
		if (request.getParameter("acaoInterna").equals("excluir")) {
			camiseta = camisetaDao.consultar(Long.valueOf(request.getParameter("id")));
			try {
				if (camisetaDao.excluir(camiseta) > 0)
					request.setAttribute("mensagem", "Excluído com sucesso");
				else
					request.setAttribute("erro", "Erro de exclusão");
			} catch (MySQLIntegrityConstraintViolationException e) {
				request.setAttribute("erro", "Exitem torcedores vinculados a esta camiseta!<br/>Antes de excluir o curso, exclua estes torcedores.");
			}
		}
		// ACAOINTERNA = 3. incluir
		if (request.getParameter("acaoInterna").equals("incluir")){
			return Acao.ENTRADA; // RESULTADO = ENTRADA
		} 
		// ACAOINTERNA = 4. alterar
		if (request.getParameter("acaoInterna").equals("alterar")) {
			camiseta = camisetaDao.consultar(Long.valueOf(request.getParameter("id")));
			if (camiseta == null)
				request.setAttribute("erro", "Erro ao localizar para alteração");
			else {
				request.setAttribute("camiseta", camiseta);
				return Acao.ENTRADA; // RESULTADO = ENTRADA
			}
		}
		// ACAOINTERNA = 5. salvar
		if (request.getParameter("acaoInterna").equals("salvar")) {
			if (request.getParameter("id").equals("")) {
				if (camisetaDao.incluir(camiseta) > 0)
					request.setAttribute("mensagem", "Incluído com sucesso");
				else
					request.setAttribute("erro", "Erro de inclusão");
			}
			else {
				camiseta.setId(Long.valueOf(request.getParameter("id")));
				if (camisetaDao.alterar(camiseta) > 0)
					request.setAttribute("mensagem", "Alterado com sucesso");
				else
					request.setAttribute("erro", "Erro de alteração");
			}
		}
		// ACAOINTERNA = 6. filtrar
		if (request.getParameter("acaoInterna").equals("filtrar"))
			camisetas = camisetaDao.listarFiltro(request.getParameter("nomeFiltro"));
		else
			camisetas = camisetaDao.listar();
		request.setAttribute("camisetas", camisetas);
		return "LISTAGEM"; // RESULTADO = LISTAGEM
		
	}

	public CamisetaAcao() {
		// TODO Auto-generated constructor stub
	}

}
