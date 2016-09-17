<%-- 
    Document   : index
    Created on : 31/08/2016, 23:04:44
    Author     : Victor Oka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <!-- Materialize CSS -->
        <link href="materialize/css/materialize.min.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Materialize JS -->
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        <!-- Google Icons -->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
        <!-- CSS -->
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <!-- JavaScript -->
        <script src="javascript/javaScript.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="col s12 z-depth-4 card-panel" style="background-color: #D3CEAA;">
                <h3 class="center-align">Login</h3>
                <div class="row valign-wrapper">
                    <div class="col s4"></div>
                    <form action="Controller" method="POST" class="col s4">
                        <input type="hidden" name="command" value="User.login">
                        <div class="row">
                            <div class="input-field col s12 valign">
                                <i class="material-icons prefix">account_circle</i>
                                <input id="usuario" type="text" class="validate " required="required" name="nome_usuario">
                                <label class="active" for="usuario"><b>Username</b></label>
                            </div>
                        </div>  
                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">lock</i>
                                <input id="senha" type="password" class="validate" required="required" name="senha">
                                <label class="active" for="senha"><b>Password</b></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="center-align">
                                    <button class="btn waves-effect waves-light" type="submit" name="action" style="background-color: #8E001C; color: #D3CEAA">Entrar
                                        <i class="material-icons right">send</i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 center-align">
                                <a href="register.jsp">
                                    Don't have an account? Click here!
                                </a>
                            </div>
                        </div>
                    </form>
                    <div class="col s4"></div>
                </div>
            </div>
        </div>
    </body>
</html>
