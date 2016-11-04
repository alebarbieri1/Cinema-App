<%-- 
    Document   : home
    Created on : 07/09/2016, 12:32:17
    Author     : Alexandre Barbieri
--%>

<%@page import="com.br.cinema.model.entities.Serie"%>
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
            <div>
                <c:if test="${usuario == null}">
                    <c:redirect url="index.jsp"></c:redirect>
                </c:if>
                <c:if test="${serie == null}">
                    <c:redirect url="home.jsp"></c:redirect>
                </c:if>

                <%@include file="WEB-INF/sideNav.jspf"%>
                <c:if test="${serie.poster_path == ''}">
                    <img src="http://image.flaticon.com/icons/svg/36/36601.svg" width="60" height="60">
                </c:if>
                <c:if test="${serie.poster_path != ''}">
                    <img src="https://image.tmdb.org/t/p/w500${serie.poster_path}" width="60" height="60">
                </c:if>
                </a>
                <b>Id: </b>${serie.idSerie} -
                <b>Nome: </b>${serie.nomeSerie} -
                <b>Qtd de Episódios: </b>${serie.episodios}<br><br>
                <div class="col s12 z-depth-4 card-panel" style="background-color: #E7E8D1;">
                    <div class="row">

                        <form action="Controller" method="POST">
                            <div class="col s6">
                                <label>Status</label>
                                <select name="status" id="status">
                                    <option value="completo">Completo</option>
                                    <option value="assistindo">Assistindo</option>
                                    <option value="pausado">Pausado</option>
                                    <option value="vou_assistir">Vou assistir</option>
                                </select> 
                            </div>
                            <div class="col s6">
                                <label >Episodios</label>
                                <input type="number" name="episodios" min="0" max="${serie.episodios}">
                                <input type="hidden" name="id_usuario" value="${usuario.idUsuario}">
                                <input type="hidden" name="id_serie" value="${serie.idSerie}">
                                <input type="hidden" name="command" value="RegistroSerie.adicionar">
                            </div>
                            <div class="center-align">
                                <button class="waves-effect waves-light btn" type="submit" name="action" style="background-color: #8E001C; color: #D3CEAA">
                                    <i class="material-icons right">done</i>
                                    Adicionar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
