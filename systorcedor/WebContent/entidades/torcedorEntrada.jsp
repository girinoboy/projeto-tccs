<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Torcedor</title></head>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/calendario/jquery.click-calendario-1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/validacao/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/validacao/jquery_funcao_validacao.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/maskedinput/jquery.maskedinput-1.3.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.click-calendario-1.0.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css" />
<script type="text/javascript">
	$(document).ready( function() {
    	$("#frmTorcedor").validate({
      		rules:{
        		nome:{
          			required: true,
          			minlength: 2
        		},
        		email:{
            		required: true,
            		email: true
        		},
        		dtaNasc:{
            		required: true,
            		dateBR: true
        		}
      		},
      		messages:{
        		nome:{
          			required: "Nome obrigatorio",
          			minlength: "Minimo 2 caracteres"
        		},
        		email:{
          			required: "E-mail obrigatorio",
          			email: "E-mail invalido"
              	},
        		dtaNasc:{
          			required: "Data de nascimento obrigatoria",
          			dateBR: "Data de nascimento invalida"
        		}
      		}
    	});
  	});
	// http://www.tidbits.com.br/plugin-de-mascara-para-jquery-masked-input
	// a - letra (A-Z,a-z); 9 - numero (0-9); * - caractere (A-Z,a-z,0-9)
	$(document).ready(function(){
		$("#dtaNasc").mask("99/99/9999");
	});
	$(document).ready(function(){
		$('#dtaNasc').focus(function(){
			$(this).calendario({
				target:'#dtaNasc',
				${!empty(torcedor.dtaNasc)?",dateDefault:$(this).val()":""}
    		});
  	});
</script>
<body>
<jsp:include page="/template/cabecalho.jsp"/>
<font color="#FF0000">${erro}</font>
<form id="frmTorcedor" method="post" action="${pageContext.request.contextPath}/torcedor.acao">
<fieldset>
<input type="hidden" name="acaoInterna" value="salvar"/>
<input type="hidden" name="id" value="${torcedor.id}"/>
<legend>
<c:if test="${clube.id == null}">
	Inclus&atilde;o de Torcedor
</c:if>
<c:if test="${clube.id != null}">
	Altera&ccedil;&atilde;o de Torcedor
</c:if>
</legend>
Nome<br>
<input type="text" name="nome" value="${torcedor.nome}" size="50"/> <p/>
Estado Civil<br>
<select name="estCivil">
<c:forEach var="estadoCivil" items="${estadosCivis.valores}">
	<c:if test="${torcedor.estadoCivil == estadoCivil}">
		<option selected>${estadoCivil}</option>
	</c:if>
	<c:if test="${torcedor.estadoCivil != estadoCivil}">
		<option>${estadoCivil}</option>
	</c:if>
</c:forEach>
</select><p/>
UF<br>
<select name="uf">
<c:forEach var="uf" items="${ufs}">
	<c:if test="${torcedor.uf == uf.sigla}">
		<option selected value="${uf.sigla}">${uf.sigla}</option>
	</c:if>
	<c:if test="${torcedor.uf != uf.sigla}">
		<option value="${uf.sigla}">${uf.sigla}</option>
	</c:if>
</c:forEach>
</select><p/>
E-mail<br>
<input type="text" name="email" value="${torcedor.email}" size="40"/> <p/>
Data de Nascimento (DD/MM/AAAA)<br>
<input type="text" name="dtaNasc" value="<fmt:formatDate value="${torcedor.dtaNasc}"/>" id="dtaNasc" size="10" maxlength="10" /><p/>
Clube<br>
<select name="clubeId">
<c:forEach var="clube" items="${clubes}">
	<c:if test="${torcedor.clube.id == clube.id}">
		<option selected value="${clube.id}">${clube.nomeTime}</option>
	</c:if>
	<c:if test="${torcedor.clube.id != clube.id}">
		<option value="${clube.id}">${clube.nomeTime}</option>
	</c:if>
</c:forEach>
</select><br/>
Camiseta<br>
<select name="camisetaId">
<c:forEach var="camiseta" items="${camisetas}">
	<c:if test="${torcedor.camiseta.id == camiseta.id}">
		<option selected value="${camiseta.id}">${camiseta.nomeCamiseta}</option>
	</c:if>
	<c:if test="${torcedor.camiseta.id != camiseta.id}">
		<option value="${camiseta.id}">${camiseta.nomeCamiseta}</option>
	</c:if>
</c:forEach>
</select>
</fieldset>
<p/>
<input type="submit" value="OK"/>
</form>
<a href="${pageContext.request.contextPath}/torcedor.acao">Voltar</a>
<jsp:include page="/template/rodape.jsp"/>
</body>
</html>