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
 * @author Alexandre Barbieri
 */
public class UserCommand implements Command {

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
            case "register":
                register();
                break;
            case "login":
                login();
                break;
            case "logout":
                logout();
                break;
        }
    }

    private void register() {

    }

    private void login() {
        responsePage = "home.jsp";
    }

    private void logout() {

    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
    }
}
