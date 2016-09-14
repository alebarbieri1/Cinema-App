<%-- 
    Document   : error
    Created on : 14/09/2016, 11:43:59
    Author     : Alexandre Barbieri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
        <!-- Materialize CSS -->
        <link href="materialize/css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Materialize JS -->
        <script src="materialize/js/materialize.js" type="text/javascript"></script>
        <!-- jQuery 3.x -->
        <script src="https://code.jquery.com/jquery-3.1.0.min.js"   integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="   crossorigin="anonymous"></script>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    </head>
    <body>
        <h5 class="card-panel red white-text">Erro - ${erro}!</h5>
        <a href="${previousPage}">Clique aqui para voltar</a>
    </body>
</html>