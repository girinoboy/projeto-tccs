<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Clube</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/geral/grid.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<h1>Listagem de Clubes</h1>
<font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font>
<p/>
<form method="post" action="${pageContext.request.contextPath}/clube.acao">
<fieldset>
<legend class="interno">
Filtro
</legend>
<input type="hidden" name="acaoInterna" value="filtrar" />
<label>Nome do Clube</label><br/>
<input type="text" name="nomeFiltro" value="${param.nomeFiltro}" size="50" maxlength="50"/>
<input type="submit" value="OK"/>
</fieldset>
</form>
<table id="gride" class="gride">
<thead class="grideCabeca">
<tr>
<th width="200" align="center">Nome do Clube</th>
<th width="70"  align="center">Data da Criação</th>
<th width="40"  align="center">Estado</th>
<th width="60">&nbsp;</th>
</tr>
</thead>
<tbody>
<c:set var="linha" value="0" />
<c:forEach var="clube" items="${clubes}">
	${linha%2==0?"<tr class='grade0'>":"<tr class='grade1'>"}
    <c:set var="linha" value="${linha+1}" />
	<td align="left">${clube.nomeTime}</td>
	<td align="center">${clube.dtCriacao}</td>
	<td align="center">${clube.uf}</td>
	<td align="center">
	<a href="${pageContext.request.contextPath}/clube.acao?acaoInterna=alterar&id=${clube.id}"><img src="${pageContext.request.contextPath}/img/alterar.png" border=0 width=15 alt="Alterar Clube"></a>&nbsp;
	<a href="${pageContext.request.contextPath}/clube.acao?acaoInterna=excluir&id=${clube.id}"><img src="${pageContext.request.contextPath}/img/excluir.png" border=0 width=15 alt="Excluir Clube"></a>
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
<p/>
<a href="${pageContext.request.contextPath}/clube.acao?acaoInterna=incluir">Incluir Clube</a>
<p/>
<a href="${pageContext.request.contextPath}/index.acao">Voltar</a>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>