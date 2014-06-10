<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erro</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
</head>
<body>
<h1>ERRO DO CONTROLE</h1>
<font color="#FF0000">
<c:if test="${resultado == null}">
	A&ccedil;&atilde;o (<b>${acao}</b>) n&atilde;o localizada!
</c:if>
<c:if test="${resultado != null}">
	Resultado (<b>${resultado}</b>) n&atilde;o localizado para a a&ccedil;&atilde;o <b> ${acao}</b>!
</c:if>
</font>
</body>
</html>