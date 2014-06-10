<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista de Torcedores do Time</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/geral/grid.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<font color="red"><h2 align="center"><b><u>${clube.nomeTime}</u></b></h2></font>
<p/>
<p/>
<label><b>Ano de Fundação</b></label><br/>
<font>${clube.dtCriacao}</font>
<p/>
<p/>
<label><b>Descrição</b></label><br/>
<font>${clube.descricao}</font>
<p/>
<p/>
</body>
<body>
<h1>Torcedores que torcem para ${clube.nomeTime}</h1>
<font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font>
<p/>
<table id="gride" class="gride" width="780">
<thead class="grideCabeca">
<tr>
<th width="160" align="center">Nome</th>
<th width="80" align="center">Estado Civil</th>
<th width="110" align="center">E-mail</th>
<th width="70" align="center">Nasc</th>
<th width="40" align="center">UF</th>
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
		</tr>
</c:forEach>
</tbody>
</table>
<p/>
<a href="${pageContext.request.contextPath}/ClubesCadast.acao">Voltar</a>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>