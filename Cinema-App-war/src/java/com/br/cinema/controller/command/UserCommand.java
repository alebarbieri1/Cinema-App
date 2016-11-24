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
            case "listarP":
                listarP();
                break;
            case "login":
                login();
                break;
            case "logout":
                logout();
                break;
            case "update":
                update();
                break;
            case "deletar":
                remover();
                break;
            case "update_senha":
                updateSenha();
                break;
        }
    }

    private void remover() {
        long id = Long.parseLong(request.getParameter("id"));
        usuarioDAO.delete(usuarioDAO.readById(id));
        listarP();
    }

    private void update() {
        Usuario u = usuarioDAO.readById(Long.parseLong(request.getParameter("id")));
        //Verificar se nome de usuario ja existe
        if (!u.getNomeUsuario().equals(request.getParameter("nome_usuario")) && (usuarioDAO.readByName(request.getParameter("nome_usuario")) != null)) {
            responsePage = "error.jsp";
            request.getSession().setAttribute("erro", "Nome de usuário já existente");
            request.getSession().setAttribute("page", "perfil");
            request.getSession().setAttribute("previousPage", "home.jsp");
            return;
        }

        //  Caso não exista nenhum e-mail cadastrado igual ao solicitado OU caso já exista esse e-mail no banco de dados mas seja do usuário atual - OK
        if (usuarioDAO.readByEmail(request.getParameter("email")) == null || usuarioDAO.readByEmail(request.getParameter("email")).getIdUsuario().longValue() == u.getIdUsuario().longValue()) {
            UsuarioInfo ui = u.getUsuarioInfo();
            ui.setEmail(request.getParameter("email"));
            ui.setNome(request.getParameter("name"));
            u.setNomeUsuario(request.getParameter("nome_usuario"));

            usuarioDAO.update(u);
        } else {
            responsePage = "error.jsp";
            request.getSession().setAttribute("erro", "E-mail já existente");
            request.getSession().setAttribute("page", "perfil");
            request.getSession().setAttribute("previousPage", "home.jsp");
            return;
        }

        request.getSession().setAttribute("page", "perfil");
        request.getSession().setAttribute("usuario", u);
        responsePage = "home.jsp";
    }

    private void listarP() {
        request.getSession().setAttribute("usuarios", usuarioDAO.read());
        request.getSession().setAttribute("page", "painel");
        responsePage = "home.jsp";
    }

    private void updateSenha() {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (!request.getParameter("senha").equals(request.getParameter("senha2"))) {
            responsePage = "error.jsp";
            request.getSession().setAttribute("erro", "Senhas não conferem");
            request.getSession().setAttribute("page", "perfil");
            request.getSession().setAttribute("previousPage", "home.jsp");
            return;
        }
        u.setSenha(request.getParameter("senha"));
        usuarioDAO.update(u);
        request.getSession().setAttribute("page", "perfil");
        request.getSession().setAttribute("usuario", u);
        responsePage = "home.jsp";
    }

    private void register() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        UsuarioInfo ui = new UsuarioInfo();
        try {
            ui.setAniversario((sdf.parse(request.getParameter("aniversario"))));
        } catch (ParseException ex) {
            ui.setAniversario(new Date());
        }
        ui.setAssistindo(0);
        ui.setCompletos(0);
        ui.setEmail(request.getParameter("email"));
        ui.setNome(request.getParameter("first_name") + " " + request.getParameter("last_name"));
        Usuario u = new Usuario();
        u.setNomeUsuario(request.getParameter("nome_usuario"));
        u.setAcesso(0);

        if (usuarioDAO.readByEmail(request.getParameter("email")) != null) {
            responsePage = "error.jsp";
            request.getSession().setAttribute("erro", "E-mail já cadastrado");
            request.getSession().setAttribute("previousPage", "register.jsp");
            return;
        }

        //Verificar se nome de usuario ja existe
        if (usuarioDAO.readByName(u.getNomeUsuario()) != null) {
            // Caso não exista nenhum usuário com o nome escolhido, o método acima lançará uma excessão, impedindo a execução das linhas abaixo
            responsePage = "error.jsp";
            request.getSession().setAttribute("erro", "Nome de usuário já existente");
            request.getSession().setAttribute("previousPage", "register.jsp");
            return;
        }

        if (!request.getParameter("senha").equals(request.getParameter("senha2"))) {
            responsePage = "error.jsp";
            request.getSession().setAttribute("erro", "Senhas não conferem");
            request.getSession().setAttribute("previousPage", "register.jsp");
            return;
        }
        u.setSenha(request.getParameter("senha"));
        ui.setUsuario(u);
        u.setUsuarioInfo(ui);

        usuarioDAO.create(u);
        responsePage = "index.jsp";
    }

    private void login() {
        Usuario temp;
        String username = request.getParameter("nome_usuario");
        String password = request.getParameter("senha");

        temp = usuarioDAO.readByName(username);
        if (temp == null) {

            // Não encontrou nenhum usuário
            responsePage = "error.jsp";
            request.getSession().setAttribute("erro", "Usuário não encontrado");
            request.getSession().setAttribute("previousPage", "index.jsp");
            return;
        }
        if (!password.equals(temp.getSenha())) {
            responsePage = "error.jsp";
            request.getSession().setAttribute("erro", "Senha incorreta");
            request.getSession().setAttribute("previousPage", "index.jsp");
        } else {
            request.getSession().setAttribute("usuario", temp);
            request.getSession().setAttribute("page", "bemvindo");
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
