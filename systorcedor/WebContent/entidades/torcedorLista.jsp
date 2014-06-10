<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Torcedor</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/geral/grid.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<h1>Listagem de Torcedores</h1>
<font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font>
<p/>
<form method="post" action="${pageContext.request.contextPath}/torcedor.acao">
<fieldset>
<legend class="interno">
Filtro
</legend>
<input type="hidden" name="acaoInterna" value="filtrar" />
<label>Nome do Torcedor</label><br/>
<input type="text" name="nomeFiltro" value="${param.nomeFiltro}" size="50" maxlength="50"/>
<input type="submit" value="OK"/>
</fieldset>
</form>
<table id="gride" class="gride">
<thead class="grideCabeca">
<tr>
<th width="160" align="center">Nome</th>
<th width="80" align="center">Estado Civil</th>
<th width="110" align="center">E-mail</th>
<th width="70" align="center">Nasc</th>
<th width="40" align="center">UF</th>
<th width="160" align="center">Clube</th>
<th width="40">&nbsp;</th>
</tr>
</thead>
<tbody>
<c:set var="linha" value="0" />
<c:forEach var="torcedor" items="${torcedores}">
	${linha%2==0?"<tr class='grade0'>":"<tr class='grade1'>"}
    <c:set var="linha" value="${linha+1}" />
	<td>${torcedor.nome}</td>
	<td align="center">${torcedor.estadoCivil}</td>
	<td>${torcedor.email}</td>
	<td align="center"><fmt:formatDate value="${torcedor.dtaNasc}"/></td>
	<td align="center">${torcedor.uf}</td>
	<td>${torcedor.clube.nomeTime}</td>
	<td align="center">
	<a href="${pageContext.request.contextPath}/torcedor.acao?acaoInterna=alterar&id=${torcedor.id}"><img src="${pageContext.request.contextPath}/img/alterar.png" border=0 width=15 alt="Alterar torcedor"></a>
	<a href="${pageContext.request.contextPath}/torcedor.acao?acaoInterna=excluir&id=${torcedor.id}"><img src="${pageContext.request.contextPath}/img/excluir.png" border=0 width=15 alt="Excluir torcedor"></a>
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
<p/>
<a href="${pageContext.request.contextPath}/torcedor.acao?acaoInterna=incluir">Incluir torcedor</a>
<p/>
<a href="${pageContext.request.contextPath}/index.acao">Voltar</a>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>