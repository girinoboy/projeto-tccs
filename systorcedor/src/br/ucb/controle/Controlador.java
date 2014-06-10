package br.ucb.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.modelo.acao.Acao;
import br.ucb.modelo.acao.CamisetaAcao;
import br.ucb.modelo.acao.ListClubesAcao;
import br.ucb.modelo.acao.TorcedorAcao;
import br.ucb.modelo.acao.ClubeAcao;

public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String acao=request.getServletPath();
		String resultado=null, pagina="/mvc/erroControleResultado.jsp";
		try {
			// *** index.acao *********************************************************
			if (acao.equals("/index.acao"))
				pagina = "/menu.jsp";
			// *** clube.acao *********************************************************
			if (acao.equals("/clube.acao")) {
				ClubeAcao obj = new ClubeAcao(); 
				resultado = obj.executar(request, response);
				if (resultado.equals("LISTAGEM"))   pagina = "/entidades/clubeLista.jsp";
				if (resultado.equals(Acao.ENTRADA)) pagina = "/entidades/clubeEntrada.jsp";
			}
			// *** torcedor.acao *********************************************************
			if (acao.equals("/torcedor.acao")) {
				TorcedorAcao obj = new TorcedorAcao(); 
				resultado = obj.executar(request, response);
				if (resultado.equals("LISTAGEM"))   pagina = "/entidades/torcedorLista.jsp";
				if (resultado.equals(Acao.ENTRADA)) pagina = "/entidades/torcedorEntrada.jsp";
			}
			// *** torcedor.acao *********************************************************
			if (acao.equals("/camiseta.acao")) {
				CamisetaAcao obj = new CamisetaAcao(); 
				resultado = obj.executar(request, response);
				if (resultado.equals("LISTAGEM"))   pagina = "/entidades/camisetaLista.jsp";
				if (resultado.equals(Acao.ENTRADA)) pagina = "/entidades/camisetaEntrada.jsp";
			}
			// *** ClubesCadast.acao *********************************************************
			if (acao.equals("/ClubesCadast.acao")) {
				ListClubesAcao obj = new ListClubesAcao(); 
				resultado = obj.executar(request, response);
				if (resultado.equals("LISTAGEM"))   pagina = "/entidades/clubesCadastrados.jsp";
				if (resultado.equals(Acao.ENTRADA)) pagina = "/entidades/pageTime.jsp";
		    }
		} catch (Exception e) {
			request.setAttribute("excessao", e);
			
			System.out.println("==> [["+ e.getClass().getSimpleName() +"]]");
			
			pagina="/mvc/erroControleExecucao.jsp";
		}
		if (pagina.equals("/mvc/erroControleResultado.jsp")) {
			request.setAttribute("acao", acao);
			request.setAttribute("resultado", resultado);
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}