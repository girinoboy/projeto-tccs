<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Clube</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<font color="#FF0000">${erro}</font>
<form method="post" action="${pageContext.request.contextPath}/clube.acao">
<fieldset>
<legend>
<c:if test="${clube.id == null}">
	Inclus&atilde;o de Clube
</c:if>
<c:if test="${clube.id != null}">
	Altera&ccedil;&atilde;o de Clube
</c:if>
</legend>
<input type="hidden" name="acaoInterna" value="salvar" />
<input type="hidden" name="id" value="${clube.id}" />
<label>Nome do Clube</label><br/>
<input type="text" name="nomeTime" value="${clube.nomeTime}" size="50" maxlength="50"/><p/>
<label>Ano da Criação do Clube (AAAA)</label><br/>
<input type="text" name="dtCriacao" value="${clube.dtCriacao}" size="4" maxlength="4"/><p/>
<label>Estado do Clube</label><br/>
<select name="uf">
<c:forEach var="uf" items="${ufs.valores}">
	<c:if test="${clube.uf == uf}">
		<option selected>${uf}</option>
	</c:if>
	<c:if test="${clube.uf != uf}">
		<option>${uf}</option>
	</c:if>
</c:forEach>
</select><p/>
<label>Descrição do Clube</label><br/>
<TEXTAREA name="descricao"  rows="4" cols="70">${clube.descricao}</TEXTAREA>
</fieldset>
<p/><input type="submit" value="OK"/>
</form>
<p/>
<a href="${pageContext.request.contextPath}/clube.acao">Voltar</a>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>