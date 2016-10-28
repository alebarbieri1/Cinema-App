/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.controller.command;

import com.br.cinema.json.JSONParser;
import com.br.cinema.model.entities.Serie;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexandre Barbieri
 */
public class SerieCommand implements Command {

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

        String content = JSONParser.openURL("https://api.themoviedb.org/3/tv/" + id + "?api_key=0793bedcbb5893728b91c114222266ff&language=en-US");
        if (content != null) {
            serie = JSONParser.parseSerieDetails(content);
        }

        request.getSession().setAttribute("serie", serie);
        responsePage = "serie.jsp";
    }

}
