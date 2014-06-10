<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SysTorcedor</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<h1>Sistema de Cadastro</h1>
<ul>
  <li><a href="${pageContext.request.contextPath}/clube.acao">Manter Clube</a></li>
  <li><a href="${pageContext.request.contextPath}/torcedor.acao">Manter Tocedor</a></li>
  <li><a href="${pageContext.request.contextPath}/camiseta.acao">Manter Camiseta</a></li>
  <li><a href="${pageContext.request.contextPath}/ClubesCadast.acao">Clubes Cadastrados</a></li>
</ul>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>