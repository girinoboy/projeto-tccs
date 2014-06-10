<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erro</title>
</head>
<body>
<h1>ERRO DO CONTROLE</h1>
<font color="#FF0000">Erro de execu&ccedil;&atilde;o</font><p/>
<b>EXCESS&Atilde;O:</b> ${excessao.getClass().getSimpleName()}<p/>
<b>MENSAGEM:</b> ${excessao.message}<p/>
<b>DETALHAMENTO:</b><p/>
<table border="1">
<thead>
<tr>
<th>Classe (Arquivo)</th>
<th>M&eacute;todo</th>
<th>Linha</th>
</tr>
</thead>
<tbody>
<c:forEach var="erro" items="${excessao.getStackTrace()}">
	<tr class="grade0">
	<td>${erro.className} (${erro.fileName})</td>
	<td>${erro.methodName}</td>
	<td>${erro.lineNumber}</td>
	</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>