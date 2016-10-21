/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Victor Oka
 */
public class PageCommand implements Command {

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
        responsePage = "home.jsp";
        String action = request.getParameter("command").split("\\.")[1];
        request.getSession().setAttribute("page", action);
    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
    }
}
