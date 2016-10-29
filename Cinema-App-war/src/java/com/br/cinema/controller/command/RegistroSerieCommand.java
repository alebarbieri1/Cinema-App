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
        RegistroSerie rs = new RegistroSerie();
        String status = request.getParameter("status");
        int progresso;
        //idU é o id do usuario
        //idS é o id da serie
        long idU, idS;
        progresso = Integer.parseInt(request.getParameter("episodios"));
        idS = Long.parseLong(request.getParameter("id_serie"));
        idU = Long.parseLong(request.getParameter("id_usuario"));
        rs.setIdSerie(serieDAO.readById(idS));

        //Tratamento do Status para evitar inconsistencia
        switch (status) {
            case "completo":
                rs.setStatus("Completo");
                progresso = rs.getIdSerie().getEpisodios();
                break;
            case "vou_assistir":
                rs.setStatus("Vou assistir");
                progresso = 0;
                break;
            case "pausado":
                rs.setStatus("Pausado");
                break;
            case "assistindo":
                rs.setStatus("Assistindo");
                progresso = progresso == 0 ? 1 : progresso;

                break;
            default:
                rs.setStatus("Assistindo");
                progresso = progresso == 0 ? 1 : progresso;
                break;

        }

        rs.setIdUsuario(usuarioDAO.readById(idU));
        rs.setProgresso(progresso);
        rs.setStatus(status);
        registroSerieDAO.create(rs);
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
