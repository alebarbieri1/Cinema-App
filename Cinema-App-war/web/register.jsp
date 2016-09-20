<%-- 
    Document   : register
    Created on : Sep 12, 2016, 4:16:11 PM
    Author     : Victor Oka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
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
    <body class="cartao">
        <div class="container">
            <div class="col s12 z-depth-4 card-panel" style="background-color: #D3CEAA;">
                <h3 class="center-align">Create an account</h3>
                <div class="row">
                    <form action="Controller" method="POST" class="col s12">
                        <input type="hidden" name="command" value="User.register">
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="first_name" name="first_name" type="text" required="required">
                                <label class="active" for="first_name"><b>First Name</b></label>
                            </div>
                            <div class="input-field col s6">
                                <input id="last_name" type="text" name="last_name" class="validate" required="required">
                                <label class="active" for="last_name"><b>Last Name</b></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="email" type="email" name="email" class="validate" required="required">
                                <label class="active" for="email"><b>Email</b></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="usuario" type="text" name="nome_usuario" class="validate" required="required">
                                <label class="active" for="usuario"><b>Usuário</b></label>
                            </div>
                            <div class="input-field col s6">
                                <input id="aniversario" type="date" name="aniversario" required="required">
                                <label class="active" for="dataNascimento"><b>Data&nbsp;Nascimento</b></label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="senha" type="password" class="validate" name="senha" required="required">
                                <label class="active" for="senha"><b>Password</b></label>
                            </div>
                            <div class="input-field col s6">
                                <input id="senha2" type="password" class="validate" name="senha2" required="required">
                                <label class="active" for="senha2"><b>Confirm&nbsp;password</b></label>
                            </div>
                        </div>
                        <div class="center-align">
                            <button class="waves-effect waves-light btn" type="submit" name="action" style="background-color: #8E001C; color: #D3CEAA">
                                <i class="material-icons right">done</i>
                                Register
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
