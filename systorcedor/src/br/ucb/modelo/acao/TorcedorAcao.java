package br.ucb.modelo.acao;

import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.bean.CamisetaBean;
import br.ucb.modelo.bean.TorcedorBean;
import br.ucb.modelo.bean.ClubeBean;
import br.ucb.modelo.bean.UfBean;
import br.ucb.modelo.dao.CamisetaDAO;
import br.ucb.modelo.dao.TorcedorDAO;
import br.ucb.modelo.dao.ClubeDAO;
import br.ucb.modelo.dao.UfDAO;
import br.ucb.modelo.enumerador.EstadoCivil;
import br.ucb.modelo.enumerador.Uf;

public class TorcedorAcao implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TorcedorBean torcedor = new TorcedorBean();
		TorcedorDAO torcedorDao = new TorcedorDAO();
		UfDAO ufDao = new UfDAO();
		ClubeDAO cursoDao = new ClubeDAO();
		CamisetaDAO camisetaDao = new CamisetaDAO();
		List <TorcedorBean> torcedores;
		List <ClubeBean> clubes;
		List <CamisetaBean> camisetas;
		System.out.println("1" + request.getParameter("acaoInterna"));
		System.out.println("2" + request.getParameter("nomeFiltro"));
		if (request.getParameter("acaoInterna") == null) {
			torcedores = torcedorDao.listar();
			request.setAttribute("torcedores", torcedores);
			return "LISTAGEM";
		}
		try {
			if (request.getParameter("nome") != null)
				torcedor.setNome(request.getParameter("nome"));
			if (request.getParameter("email") != null)
				torcedor.setEmail(request.getParameter("email"));
			if (request.getParameter("estCivil") != null)
				torcedor.setEstadoCivil(EstadoCivil.valueOf(request.getParameter("estCivil")));
			if (request.getParameter("dtaNasc") != null) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				df.setLenient(false);
				Date data = df.parse(request.getParameter("dtaNasc"));
				torcedor.setDtaNasc(data);
			}
			if (request.getParameter("uf") != null)
				torcedor.setUf(Uf.valueOf(request.getParameter("uf")));
			if (request.getParameter("clubeId") != null)
				(torcedor.getClube()).setId(Long.valueOf(request.getParameter("clubeId")));
			if (request.getParameter("camisetaId") != null)
				(torcedor.getCamiseta()).setId(Long.valueOf(request.getParameter("camisetaId")));
			
		}
		catch (Exception e) {
			// Cria uma variável (contexto/requisicao) para o ERRO
			request.setAttribute("erro", "Erro de conversao");
			entrada(request, torcedor, cursoDao, camisetaDao,ufDao);
			return Acao.ENTRADA;
		}
		if (request.getParameter("acaoInterna").equals("excluir")) {
			torcedor = torcedorDao.consultar(Long.valueOf(request.getParameter("id")));
			if (torcedorDao.excluir(torcedor) > 0)
				request.setAttribute("mensagem", "Excluído com sucesso");
			else
				request.setAttribute("erro", "Erro de exclusão");
		}
		if (request.getParameter("acaoInterna").equals("incluir")) {
			clubes = cursoDao.listar();
			camisetas = camisetaDao.listar();
			if (clubes.size() == 0 || camisetas.size() == 0)
				request.setAttribute("erro", "Necessário incluir clube/camiseta antes de incluir torcedor");
			else {
				entrada(request, torcedor, cursoDao, camisetaDao,ufDao);
				return Acao.ENTRADA;
			}
		}
		if (request.getParameter("acaoInterna").equals("alterar")) {
			torcedor = torcedorDao.consultar(Long.valueOf(request.getParameter("id")));
			if (torcedor == null)
				request.setAttribute("erro", "Erro ao localizar para alteração");
			else {
				entrada(request, torcedor, cursoDao, camisetaDao,ufDao);
				return Acao.ENTRADA;
			}
		}
		if (request.getParameter("acaoInterna").equals("salvar")) {
			if (request.getParameter("id").equals("")) {
				if (torcedorDao.incluir(torcedor) > 0)
					request.setAttribute("mensagem", "Incluído com sucesso");
				else
					request.setAttribute("erro", "Erro de inclusão");
			}
			else {
				torcedor.setId(Long.valueOf(request.getParameter("id")));
				if (torcedorDao.alterar(torcedor) > 0)
					request.setAttribute("mensagem", "Alterado com sucesso");
				else
					request.setAttribute("erro", "Erro de alteração");
			}
		}
		if (request.getParameter("acaoInterna").equals("filtrar"))
			torcedores = torcedorDao.listarFiltro(request.getParameter("nomeFiltro"));
		else
			torcedores = torcedorDao.listar();
		request.setAttribute("torcedores", torcedores);
		return "LISTAGEM";
	}

	private void entrada(HttpServletRequest request, TorcedorBean torcedor,
			ClubeDAO cursoDao, CamisetaDAO camisetaDao,UfDAO ufDao) throws SQLException {
		List<ClubeBean> clubes;
		List<CamisetaBean> camisetas;
		List<UfBean> ufs;
		ufs = ufDao.listar();
		clubes = cursoDao.listar();
		camisetas = camisetaDao.listar();
		request.setAttribute("torcedor", torcedor);
		request.setAttribute("clubes", clubes);
		request.setAttribute("camisetas", camisetas);
		request.setAttribute("estadosCivis", EstadoCivil.CASADO);
		request.setAttribute("ufs", ufs);
	}

}