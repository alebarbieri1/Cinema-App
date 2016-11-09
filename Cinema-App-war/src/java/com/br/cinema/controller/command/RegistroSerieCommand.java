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
public class RegistroSerieCommand implements Command {

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
        String status = request.getParameter("status");
        int progresso;
        //idU é o id do usuario
        //idS é o id da serie
        long idU, idS;
        progresso = Integer.parseInt(request.getParameter("episodios"));
        progresso = progresso < 0 ? 0 : progresso;
        idS = Long.parseLong(request.getParameter("id_serie"));
        idU = Long.parseLong(request.getParameter("id_usuario"));
        String statusPadrao = "";
        Usuario u = usuarioDAO.readById(idU);
        Serie s = serieDAO.readById(idS);

        //Tratamento do Status para evitar inconsistencia
        switch (status) {
            case "completo":
                statusPadrao = "completo";
                progresso = s.getEpisodios();
                break;
            case "assistirei":
                statusPadrao = "assistirei";
                progresso = 0;
                break;
            case "em_espera":
                statusPadrao = "em_espera";
                break;
            case "assistindo":
                statusPadrao = "assistindo";
                progresso = progresso == 0 ? 1 : progresso;
                break;
            default:
                statusPadrao = "assistindo";
                progresso = progresso == 0 ? 1 : progresso;
                break;
        }

        RegistroSerie rs;
        // Caso não haja registro dessa série para esse usuário
        if ((rs = registroSerieDAO.readByUsuarioAndSerie(u, s)) == null) {
            rs = new RegistroSerie();
            rs.setIdUsuario(usuarioDAO.readById(idU));
            rs.setProgresso(progresso);
            rs.setStatus(status);
            rs.setIdSerie(serieDAO.readById(idS));
            registroSerieDAO.create(rs);
            // Caso já exista um registro da série para o usuário
        } else {
            rs.setProgresso(progresso);
            rs.setStatus(status);
            registroSerieDAO.update(rs);
        }
        responsePage = "home.jsp";
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
