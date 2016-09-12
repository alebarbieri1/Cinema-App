/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.model.dao;

import com.br.cinema.model.entities.Usuario;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Prog Felipe
 */
@Stateful
@LocalBean
public class UsuarioDAO implements GenericDAO<Usuario> {

    @PersistenceContext(unitName = "CinemaApp-ejbPU",
            type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void create(Usuario e) {
        em.persist(e);
    }

    @Override
    public void update(Usuario e) {
        em.merge(e);
    }

    @Override
    public void delete(Usuario e) {
        em.merge(e);
        em.remove(e);
    }

    @Override
    public List<Usuario> read() {
        Query query = em.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }

    @Override
    public Usuario readById(Long id) {
        return em.find(Usuario.class, id);
    }

}
