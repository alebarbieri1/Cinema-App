<%-- 
    Document   : register
    Created on : Sep 12, 2016, 4:16:11 PM
    Author     : victoroka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <!-- Materialize CSS -->
        <link href="materialize/css/materialize.min.css" rel="stylesheet" type="text/css"/>
        <!-- Materialize JS -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script src="javascript/javaScript.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="col s12 z-depth-4 card-panel">
                <h3 class="center-align">Create an account</h3>
                <div class="row">
                    <form action="Controller" method="POST" class="col s12">
                        <input type="hidden" name="command" value="User.register">
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="first_name" name="first_name" type="text" required="required">
                                <label class="active" for="first_name">First Name</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="last_name" type="text" name="last_name" class="validate" required="required">
                                <label class="active" for="last_name">Last Name</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="email" type="email" name="email" class="validate" required="required">
                                <label class="active" for="email">Email</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="usuario" type="text" name="usuario" class="validate" required="required">
                                <label class="active" for="usuario">Usuário</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="aniversario" type="date" name="aniversario" required="required">
                                <label class="active" for="dataNascimento">Data Nascimento</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="password1" type="password" class="validate" name="password" required="required">
                                <label class="active" for="password1">Password</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="password2" type="password" class="validate" name="password2" required="required">
                                <label class="active" for="password2">Confirm password</label>
                            </div>
                        </div>
                        <div class="center-align">
                            <button class="waves-effect waves-light btn" type="submit" name="action">
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
