/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.model.dao;

import com.br.cinema.model.entities.Serie;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Prog Felipe
 */
@LocalBean
@Stateful
public class SerieDAO implements GenericDAO<Serie> {

    @PersistenceContext(unitName = "CinemaApp-ejbPU",
            type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void create(Serie e) {
        em.persist(e);
    }

    @Override
    public void update(Serie e) {
        em.merge(e);
    }

    @Override
    public void delete(Serie e) {
        em.remove(em.merge(e));

    }

    @Override
    public List<Serie> read() {
        Query query = em.createNamedQuery("Serie.findAll");
        return (List<Serie>) query.getResultList();
    }

    @Override
    public Serie readById(Long id) {
        return em.find(Serie.class, id);
    }

}
