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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prog Felipe
 */
public class RegistroFilmeCommand implements Command {

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
            case "adicionar":
                adicionar();
                break;
        }
    }

    @Override
    public String getResponsePage() {
        return responsePage;
    }

    private void adicionar() {
        RegistroFilme rf = new RegistroFilme();
        String status = request.getParameter("status");
        //idU é o id do usuario
        //idS é o id do filme na API
        long idU, idF;
        idF = Long.parseLong(request.getParameter("id_filme"));
        idU = Long.parseLong(request.getParameter("id_usuario"));

        //Caso o filme não exista, acessar a API e cadastrar o filme no banco.
        Filme f = filmeDAO.readByIdApi(idF);
        if (f == null) {
            String content = JSONParser.openURL("https://api.themoviedb.org/3/movie/" + idF + "?api_key=0793bedcbb5893728b91c114222266ff&language=en-US");
            if (content != null) {
                f = JSONParser.parseFilmeDetails(content);
                filmeDAO.create(f);
            }
        }

        rf.setIdFilme(filmeDAO.readByIdApi(idF));
        //Tratamento do Status para evitar inconsistencia
        switch (status) {
            case "completo":
                rf.setStatus("completo");
                break;
            case "assistirei":
                rf.setStatus("assistirei");
                break;
            case "em_espera":
                rf.setStatus("em_espera");
                break;
            default:
                rf.setStatus("assistindo");
                break;
        }

        rf.setIdUsuario(usuarioDAO.readById(idU));
        registroFilmeDAO.create(rf);

        RegistroSerieCommand rsc = new RegistroSerieCommand();
        rsc.init(request, response);
        rsc.contarRegistros();
        
        responsePage = "home.jsp";
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
