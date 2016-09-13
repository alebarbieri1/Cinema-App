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
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script src="javascript/javaScript.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="col s12 z-depth-4 card-panel">
                <h3 class="center-align">Create an account</h3>
                <div class="row">
                    <form class="col s12">
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="first_name" type="text">
                                <label class="active" for="first_name">First Name</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="last_name" type="text" class="validate">
                                <label class="active" for="last_name">Last Name</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="email" type="email" class="validate">
                                <label class="active" for="email">Email</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="password1" type="password" class="validate">
                                <label class="active" for="password1">Password</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="password2" type="password" class="validate">
                                <label class="active" for="password2">Confirm password</label>
                            </div>
                        </div>
                    </form>
                    <div class="center-align">
                        <button class="waves-effect waves-light btn" type="submit" name="action">
                            <i class="material-icons right">done</i>
                            Register
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
