<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar novo usuário</title>
    </head>
    <body>
        <form method="POST" action='UserController' name="frmAddUser">
            <% String action = request.getParameter("action");
                System.out.println(action);
            %>
            <% if (action.equalsIgnoreCase("edit")) {%>
            Nome : <input type="text" name="uname"
                               value="<c:out value="${user.uname}" />" readonly="readonly"/> (Você não pode alterar esse)<br />
            <%} else {%>
            Nome : <input type="text" name="uname"
                               value="<c:out value="${user.uname}" />" /> <br />
            <%}%>
            Senha : <input
                type="password" name="pass"
                value="<c:out value="${user.password}" />" /> <br />
            Email : <input
                type="text" name="email"
                value="<c:out value="${user.email}" />" /> <br />
 
            <% if (action.equalsIgnoreCase("edit")) {%>
            Inscrição : <input
                type="text" name="dob"
                value="<fmt:formatDate pattern="yyyy/MM/dd" value="${user.registeredon}" />" readonly="readonly"/>(Você não pode alterar esse)  <br />
            <%} else {%>
            Inscrição : <input
                type="text" name="dob"
                value="<fmt:formatDate pattern="yyyy/MM/dd" value="${user.registeredon}" />" />(yyyy/MM/dd)  <br />
            <%}%>
            <input  type="submit" value="Submit" />
        </form>
    </body>
</html>