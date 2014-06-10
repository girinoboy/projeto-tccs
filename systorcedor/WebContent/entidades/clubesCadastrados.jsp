<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<title>Clube</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/geral/grid.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<h1>Clubes Cadastrados</h1>
<font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font>
<p/>
<form method="post" action="${pageContext.request.contextPath}/ClubesCadast.acao">
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
<th width="200" align="center">Clube</th>
<th width="60">&nbsp;</th>
</tr>
</thead>
<tbody>
<c:set var="linha" value="0" />
<c:forEach var="clube" items="${clubes}">
	${linha%2==0?"<tr class='grade0'>":"<tr class='grade1'>"}
    <c:set var="linha" value="${linha+1}" />
	<td align="left">${clube.nomeTime}</td>
	<td align="center">
	<a href="${pageContext.request.contextPath}/ClubesCadast.acao?acaoInterna=irPagina&id=${clube.id}"><img src="${pageContext.request.contextPath}/img/time_page.jpg" border=0 width=30 alt="Pagina Clube"></a>&nbsp;
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
<p/>
<a href="${pageContext.request.contextPath}/index.acao">Voltar</a>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>