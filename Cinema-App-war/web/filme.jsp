<%-- 
    Document   : home
    Created on : 07/09/2016, 12:32:17
    Author     : Alexandre Barbieri
--%>

<%@page import="com.br.cinema.model.entities.Filme"%>
<%@page import="java.util.List"%>
<%@page import="com.br.cinema.json.JSONParser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
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
        <!— Favicon —>
        <link rel="icon" type="image/png" href="image/cinema-favicon.png">
    </head>
    <body style="background-color: #D3CEAA; height: auto; min-height: 100%">
        <header>
            <div class="navbar-fixed">
                <nav style="background-color: #8E001C">
                    <div class="nav-wrapper container">
                        <div>
                            <a href="#" data-activates="slide-out" class="btn waves-effect light button-collapse" 
                               onclick="Materialize.showStaggeredList('#slide-out')" style="background-color: #8E001C">
                                <i class="material-icons" style="font-size: 1.6em;">
                                    menu
                                </i>
                            </a>
                        </div>
                        <a href="Controller?command=Page.bemvindo" class="brand-logo center">CinemaApp</a>
                    </div>
                </nav>
            </div>
        </header>
        <div class="container">
            <c:if test="${usuario == null}">
                <c:redirect url="index.jsp"></c:redirect>
            </c:if>
            <c:if test="${filme == null}">
                <c:redirect url="home.jsp"></c:redirect>
            </c:if>

            <%@include file="WEB-INF/sideNav.jspf"%>
            <div class="col s12 z-depth-4 card-panel detalheSerieCard">
                <div class="center-align">
                    <figure>
                        <c:if test="${filme.poster_path == ''}">
                            <img src="" width="300" height="300">
                        </c:if>
                        <c:if test="${filme.poster_path != ''}">
                            <img src="https://image.tmdb.org/t/p/w500${filme.poster_path}" width="300" height="300" style="align-items: center;">
                        </c:if>
                        <figcaption>${filme.nomeFilme}</figcaption>
                    </figure>
                </div>
                <form action="Controller" method="POST">
                    <div class="row">
                        <div class="col s6">
                            <label for="status">Status</label>
                            <select name="status" id="status" required>
                                <c:choose>
                                    <c:when test="${registro==null}">
                                        <option value="" disabled selected>Escolha uma opção</option>
                                        <option value="completo">Completo</option>
                                        <option value="assistindo">Assistindo</option>
                                        <option value="em_espera">Em Espera</option>
                                        <option value="assistirei">Assistirei</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="completo"${registro.status.equals("completo")?"selected":""}>Completo</option>
                                        <option value="assistindo"${registro.status.equals("assistindo")?"selected":""}>Assistindo</option>
                                        <option value="em_espera"${registro.status.equals("em_espera")?"selected":""}>Em Espera</option>
                                        <option value="assistirei"${registro.status.equals("assistirei")?"selected":""}>Assistirei</option>
                                    </c:otherwise>
                                </c:choose>
                            </select> 
                        </div>

                    </div>
                    <input type="hidden" name="id_usuario" value="${usuario.idUsuario}">
                    <input type="hidden" name="id_filme" value="${filme.idApi}">
                    <input type="hidden" name="command" value="RegistroFilme.adicionar">
                    <button class="btn waves-effect waves-light white-text" style="background-color: #8E001C; margin-top: 10px;">
                        <a href="home.jsp" class="white-text">Voltar</a>
                    </button>
                    <button class="btn waves-effect waves-light right-align" style="background-color: #8E001C; margin-top: 10px;" type="submit">
                        ${registro==null?"Adicionar":"Atualizar"}
                    </button>
                </form>
            </div>
            <br>
            <br>
        </div>
    </body>
</html>
