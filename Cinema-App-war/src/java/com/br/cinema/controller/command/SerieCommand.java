/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.controller.command;

import com.br.cinema.json.JSONParser;
import com.br.cinema.model.dao.RegistroSerieDAO;
import com.br.cinema.model.dao.SerieDAO;
import com.br.cinema.model.dao.UsuarioDAO;
import com.br.cinema.model.entities.RegistroSerie;
import com.br.cinema.model.entities.Serie;
import com.br.cinema.model.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexandre Barbieri
 */
public class SerieCommand implements Command {

    SerieDAO serieDAO = lookupSerieDAOBean();

    UsuarioDAO usuarioDAO = lookupUsuarioDAOBean();

    RegistroSerieDAO registroSerieDAO = lookupRegistroSerieDAOBean();

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("command").split("\\.")[1];
        switch (action) {
            case "listar":
                listar();
                break;
            case "listarEsta":
                listarEsta();
                break;
        }
    }

    @Override
    public String getResponsePage() {
        return responsePage;
    }

    private void listar() {
        int qtd = Integer.parseInt(request.getParameter("qtd"));
        List<Serie> series = new ArrayList();

        for (int i = 1; i <= qtd; i++) {
            String content = JSONParser.openURL("https://api.themoviedb.org/3/tv/" + i + "?api_key=0793bedcbb5893728b91c114222266ff&language=en-US");
            if (content != null) {
                Serie serie = JSONParser.parseSerieDetails(content);
                series.add(serie);
            }
        }
        request.getSession().setAttribute("series", series);
        responsePage = "home.jsp";
    }

    private void listarEsta() {
        int id = Integer.parseInt(request.getParameter("id"));
        Serie serie = new Serie();
        long idU = Long.parseLong(request.getParameter("idU"));
        Usuario u = usuarioDAO.readById(idU);
        System.out.println(u.getNomeUsuario());
        String content = JSONParser.openURL("https://api.themoviedb.org/3/tv/" + id + "?api_key=0793bedcbb5893728b91c114222266ff&language=en-US");
        if (content != null) {
            serie = JSONParser.parseSerieDetails(content);
        }

        Serie teste = serieDAO.readById(serie.getIdSerie());
        RegistroSerie rs = registroSerieDAO.readByUsuarioAndSerie(u, teste);
        request.getSession().setAttribute("registro", rs);
        request.getSession().setAttribute("serie", serie);
        //   System.out.println(rs.getProgresso());
        responsePage = "serie.jsp";
    }

    private RegistroSerieDAO lookupRegistroSerieDAOBean() {
        try {
            Context c = new InitialContext();
            return (RegistroSerieDAO) c.lookup("java:global/Cinema-App/Cinema-App-ejb/RegistroSerieDAO!com.br.cinema.model.dao.RegistroSerieDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private UsuarioDAO lookupUsuarioDAOBean() {
        try {
            Context c = new InitialContext();
            return (UsuarioDAO) c.lookup("java:global/Cinema-App/Cinema-App-ejb/UsuarioDAO!com.br.cinema.model.dao.UsuarioDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private SerieDAO lookupSerieDAOBean() {
        try {
            Context c = new InitialContext();
            return (SerieDAO) c.lookup("java:global/Cinema-App/Cinema-App-ejb/SerieDAO!com.br.cinema.model.dao.SerieDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
