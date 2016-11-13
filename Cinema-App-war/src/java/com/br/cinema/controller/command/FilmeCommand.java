/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.controller.command;

import com.br.cinema.json.JSONParser;
import com.br.cinema.model.dao.FilmeDAO;
import com.br.cinema.model.dao.RegistroFilmeDAO;
import com.br.cinema.model.dao.UsuarioDAO;
import com.br.cinema.model.entities.Filme;
import com.br.cinema.model.entities.RegistroFilme;
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
public class FilmeCommand implements Command {

    RegistroFilmeDAO registroFilmeDAO = lookupRegistroFilmeDAOBean();

    FilmeDAO filmeDAO = lookupFilmeDAOBean();

    UsuarioDAO usuarioDAO = lookupUsuarioDAOBean();

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
            case "listarEste":
                listarEste();
                break;
        }
    }

    @Override
    public String getResponsePage() {
        return responsePage;
    }

    private void listar() {
//        int qtd = Integer.parseInt(request.getParameter("qtd"));
        List<Filme> filmes = new ArrayList();

        String content = JSONParser.openURL("https://api.themoviedb.org/3/movie/popular?api_key=0793bedcbb5893728b91c114222266ff&language=en-US");
        filmes = JSONParser.parseFilmeList(content);

        request.getSession().setAttribute("filmes", filmes);
        responsePage = "home.jsp";
    }

    private void listarEste() {
        int id = Integer.parseInt(request.getParameter("id"));
        Filme filme = new Filme();
        long idU = ((Usuario) request.getSession().getAttribute("usuario")).getIdUsuario();
        Usuario u = usuarioDAO.readById(idU);
        String content = JSONParser.openURL("https://api.themoviedb.org/3/movie/" + id + "?api_key=0793bedcbb5893728b91c114222266ff&language=en-US");
        if (content != null) {
            filme = JSONParser.parseFilmeDetails(content);
        }

        Filme teste = filmeDAO.readByIdApi(filme.getIdApi());

        RegistroFilme rf = registroFilmeDAO.readByUsuarioAndFilme(u, teste);
        request.getSession().setAttribute("registro", rf);
        request.getSession().setAttribute("filme", filme);
        //   System.out.println(rs.getProgresso());
        responsePage = "filme.jsp";
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

    private FilmeDAO lookupFilmeDAOBean() {
        try {
            Context c = new InitialContext();
            return (FilmeDAO) c.lookup("java:global/Cinema-App/Cinema-App-ejb/FilmeDAO!com.br.cinema.model.dao.FilmeDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RegistroFilmeDAO lookupRegistroFilmeDAOBean() {
        try {
            Context c = new InitialContext();
            return (RegistroFilmeDAO) c.lookup("java:global/Cinema-App/Cinema-App-ejb/RegistroFilmeDAO!com.br.cinema.model.dao.RegistroFilmeDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
