<%-- 
    Document   : home
    Created on : 07/09/2016, 12:32:17
    Author     : Alexandre Barbieri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <!-- Materialize CSS -->
        <link href="materialize/css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Materialize JS -->
        <script src="materialize/js/materialize.js" type="text/javascript"></script>
        <!-- jQuery 3.x -->
        <script src="https://code.jquery.com/jquery-3.1.0.min.js"   integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="   crossorigin="anonymous"></script>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    </head>
    <body>
        <c:if test="${usuario == null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <h1>Bem vindo ${usuario.usuarioInfo.nome}!</h1>
        <a href="Controller?command=User.logout">Clique aqui para sair</a>
    </body>
</html>
