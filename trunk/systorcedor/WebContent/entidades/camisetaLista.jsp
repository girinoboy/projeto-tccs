<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Camiseta</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/geral/grid.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<h1>Listagem de Camisetas</h1>
<font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font>
<p/>
<form method="post" action="${pageContext.request.contextPath}/clube.acao">
<fieldset>
<legend class="interno">
Filtro
</legend>
<input type="hidden" name="acaoInterna" value="filtrar" />
<label>Nome da Camiseta</label><br/>
<input type="text" name="nomeFiltro" value="${param.nomeFiltro}" size="50" maxlength="50"/>
<input type="submit" value="OK"/>
</fieldset>
</form>
<table id="gride" class="gride">
<thead class="grideCabeca">
<tr>
<th width="200" align="center">Nome da Camiseta</th>
<th width="70"  align="center">Data da Criação</th>
<th width="100"  align="center">Nome do Time</th>
<th width="60">&nbsp;</th>
</tr>
</thead>
<tbody>
<c:set var="linha" value="0" />
<c:forEach var="camiseta" items="${camisetas}">
	${linha%2==0?"<tr class='grade0'>":"<tr class='grade1'>"}
    <c:set var="linha" value="${linha+1}" />
	<td align="left">${camiseta.nomeCamiseta}</td>
	<td align="center">${camiseta.dtCriacao}</td>
	<td align="center">${camiseta.nomeTime}</td>
	<td align="center">
	<a href="${pageContext.request.contextPath}/camiseta.acao?acaoInterna=alterar&id=${camiseta.id}"><img src="${pageContext.request.contextPath}/img/alterar.png" border=0 width=15 alt="Alterar Camiseta"></a>&nbsp;
	<a href="${pageContext.request.contextPath}/camiseta.acao?acaoInterna=excluir&id=${camiseta.id}"><img src="${pageContext.request.contextPath}/img/excluir.png" border=0 width=15 alt="Excluir Camiseta"></a>
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
<p/>
<a href="${pageContext.request.contextPath}/camiseta.acao?acaoInterna=incluir">Incluir Camiseta</a>
<p/>
<a href="${pageContext.request.contextPath}/index.acao">Voltar</a>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>