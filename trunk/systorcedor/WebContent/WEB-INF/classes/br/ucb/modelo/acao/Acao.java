package br.ucb.modelo.acao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Acao {
	public static String SUCESSO="SUCESSO";
	public static String ERRO="ERRO";
	public static String ENTRADA="ENTRADA";
	
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception;
}