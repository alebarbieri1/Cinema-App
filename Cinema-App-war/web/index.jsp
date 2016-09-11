<%-- 
    Document   : index
    Created on : 31/08/2016, 23:04:44
    Author     : SPSILVA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <!-- Materialize CSS -->
        <link href="materialize/css/materialize.css" rel="stylesheet" type="text/css"/>
        <!-- Materialize JS -->
        <script src="materialize/js/materialize.js" type="text/javascript"></script>
        <!-- jQuery 3.x -->
        <script src="https://code.jquery.com/jquery-3.1.0.min.js"   integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="   crossorigin="anonymous"></script>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="row valign-wrapper">
                <div class="col s4"></div>
                <form action="Controller" method="POST" class="col s4">
                    <input type="hidden" name="command" value="User.login">
                    <div class="row">
                        <div class="input-field col s12 valign">
                            <i class="material-icons prefix white-text">account_circle</i>
                            <input id="usuario" type="text" class="validate " required="required">
                            <label class="active white-text" for="usuario">Usuário</label>
                        </div>
                    </div>  
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix white-text">lock</i>
                            <input id="senha" type="password" class="validate" required="required">
                            <label class="active white-text" for="senha">Senha</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Entrar
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </form>
                <div class="col s4"></div>
            </div>
        </div>
    </body>
</html>
