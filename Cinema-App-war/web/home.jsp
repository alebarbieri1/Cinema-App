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
        <!-- Favicon -->
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

                <%@include file="WEB-INF/sideNav.jspf"%>
                <c:choose>
                    <c:when test="${page.equals('bemvindo')}">
                        <button class="btn waves-effect waves-light" style="background-color: #8E001C; margin-top: 10px;">
                            <a href="Controller?command=Serie.listar" class="white-text">Series</a>
                        </button>
                        <button class="btn waves-effect waves-light" style="background-color: #8E001C; margin-top: 10px;">
                            <a href="Controller?command=Filme.listar" class="white-text">Filmes</a>
                        </button>
                        <c:if test="${series != null}">
                            <%@include file="WEB-INF/listarSerie.jspf"%>

                        </c:if>
                        <c:if test="${filmes != null}">
                            <%@include file="WEB-INF/listarFilme.jspf"%>

                        </c:if>
                    </c:when>
                    <c:when test="${page.equals('perfil')}">
                        <%@include file="WEB-INF/profile.jspf"%> 
                    </c:when>
                    <c:when test="${page.equals('painel')}">
                        <%@include file="WEB-INF/painel.jspf"%> 
                    </c:when>
                    <c:when test="${page.equals('alterar')}">
                        <%@include file="WEB-INF/alterar.jspf"%> 
                    </c:when>
                    <c:when test="${page.equals('alterar_senha')}">
                        <%@include file="WEB-INF/senha.jspf"%> 
                    </c:when>
                </c:choose>
            </div>
        </div>
    </body>
</html>
