package br.ucb.modelo.acao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.bean.ClubeBean;
import br.ucb.modelo.bean.TorcedorBean;
import br.ucb.modelo.dao.ListClubesDAO;

public class ListClubesAcao implements Acao {

	public String executar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ListClubesDAO listClubesDAO = new ListClubesDAO();
		ClubeBean clube = new ClubeBean();
		List <ClubeBean> clubes;
		List <TorcedorBean> torcedores = null;
		
		
		if (request.getParameter("acaoInterna") == null) {
			clubes = listClubesDAO.listar();
			request.setAttribute("clubes", clubes);
			return "LISTAGEM"; // RESULTADO = LISTAGEM
		}
		
		if (request.getParameter("acaoInterna").equals("irPagina")) {
			clube = listClubesDAO.clube(Long.parseLong(request.getParameter("id")));
			torcedores = listClubesDAO.listarTorcedorDoClube(Long.parseLong(request.getParameter("id")));
			request.setAttribute("clube", clube);
			request.setAttribute("torcedores", torcedores);
			return Acao.ENTRADA;
		}
		
		if (request.getParameter("acaoInterna").equals("filtrar"))
			clubes = listClubesDAO.listarFiltro(request.getParameter("nomeFiltro"));
		else
			clubes = listClubesDAO.listar();
		request.setAttribute("clubes", clubes);
		return "LISTAGEM"; // RESULTADO = LISTAGEM
	}

}
