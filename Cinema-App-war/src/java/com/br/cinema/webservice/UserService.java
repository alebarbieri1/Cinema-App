/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.webservice;

import com.br.cinema.model.dao.RegistroSerieDAO;
import com.br.cinema.model.dao.UsuarioDAO;
import com.br.cinema.model.entities.RegistroSerie;
import com.br.cinema.model.entities.Usuario;
import com.br.cinema.model.entities.UsuarioInfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Alexandre Lopes
 */
@Path("/users")
public class UserService {

    RegistroSerieDAO registroSerieDAO = lookupRegistroSerieDAOBean();

    UsuarioDAO usuarioDAO = lookupUsuarioDAOBean();

    @GET
    @Produces("application/xml")
    @Path("/getInfoByUsername/{username}")
    public UsuarioInfo getInfoByUsername(@PathParam("username") String username) {
        Usuario u = usuarioDAO.readByName(username);
        return u.getUsuarioInfo();
    }
    
    @GET
    @Produces("text/html")
    @Path("/getInfoByUsername/2/{username}")
    public String getInfoByUsername2(@PathParam("username") String username) {
        Usuario u = usuarioDAO.readByName(username);
        if (u == null) return "Usuário não encontrado!";
        
        UsuarioInfo ui = u.getUsuarioInfo();
        StringBuilder sb = new StringBuilder();
        sb.append("<b>E-mail: </b>");
        sb.append(ui.getEmail());
        sb.append("<br>");
        sb.append("<b>Nome do usuário: </b>");
        sb.append(ui.getNome());
        sb.append("<br>");
        sb.append("<b>Completos: </b>");
        sb.append(ui.getCompletos());
        sb.append("<br>");
        sb.append("<b>Assistindo: </b>");
        sb.append(ui.getAssistindo());
        return sb.toString();
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

    private RegistroSerieDAO lookupRegistroSerieDAOBean() {
        try {
            Context c = new InitialContext();
            return (RegistroSerieDAO) c.lookup("java:global/Cinema-App/Cinema-App-ejb/RegistroSerieDAO!com.br.cinema.model.dao.RegistroSerieDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
    