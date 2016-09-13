/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.controller.command;

import com.br.cinema.model.dao.UsuarioDAO;
import com.br.cinema.model.entities.Usuario;
import com.br.cinema.model.entities.UsuarioInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class UserCommand implements Command {

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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        UsuarioInfo ui = new UsuarioInfo();
        try {
            ui.setAniversario(sdf.parse(request.getParameter("aniversario")));
        } catch (ParseException ex) {
            ui.setAniversario(new Date());
        }
        ui.setAssistindo(0);
        ui.setCompletos(0);
        ui.setEmail(request.getParameter("email"));
        //ui.setNome(request.getParameter("first_name") + " " + request.getParameter("last_name"));
        ui.setNome(request.getParameter("first_name"));
        Usuario u = new Usuario();
        u.setNomeUsuario(request.getParameter("username"));
        u.setSenha(request.getParameter("password"));
        ui.setUsuario(u);
        u.setUsuarioInfo(ui);
        
        usuarioDAO.create(u);
        responsePage = "index.jsp";
    }

    private void login() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Usuario temp = usuarioDAO.readByName(username);
        if (temp == null) {
            responsePage = "error.jsp";
            request.getSession().setAttribute("error", "Usuario não encontrado");
        } else if (!password.equals(temp.getSenha())) {
            responsePage = "error.jsp";
            request.getSession().setAttribute("error", "Senha incorreta");
        } else {
            request.getSession().setAttribute("user", temp);
            request.getSession().setAttribute("page", "home");
            responsePage = "home.jsp";
        }

    }

    private void logout() {
        request.getSession().invalidate();
        responsePage = "index.jsp";
    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
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
}
