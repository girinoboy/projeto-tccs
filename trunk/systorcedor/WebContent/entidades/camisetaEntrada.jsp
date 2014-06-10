<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Camiseta</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<font color="#FF0000">${erro}</font>
<form method="post" action="${pageContext.request.contextPath}/camiseta.acao">
<fieldset>
<legend>
<c:if test="${camiseta.id == null}">
	Inclus&atilde;o de Camiseta
</c:if>
<c:if test="${camiseta.id != null}">
	Altera&ccedil;&atilde;o de Camiseta
</c:if>
</legend>
<input type="hidden" name="acaoInterna" value="salvar" />
<input type="hidden" name="id" value="${camiseta.id}" />
<label>Nome do Camiseta</label><br/>
<input type="text" name="nomeCamiseta" value="${camiseta.nomeCamiseta}" size="50" maxlength="50"/><p/>
<label>Ano da Criação do Camiseta (AAAA)</label><br/>
<input type="text" name="dtCriacao" value="${camiseta.dtCriacao}" size="4" maxlength="4"/><p/>
<label>Nome do Time</label><br/>
<input type="text" name="nomeTime" value="${camiseta.nomeTime}" size="50" maxlength="50"/><p/>
<label>Descrição do Camiseta</label><br/>
<TEXTAREA name="descricao"  rows="4" cols="70">${camiseta.descricao}</TEXTAREA>
</fieldset>
<p/><input type="submit" value="OK"/>
</form>
<p/>
<a href="${pageContext.request.contextPath}/camiseta.acao">Voltar</a>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>