/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.model.dao;

import com.br.cinema.model.entities.RegistroFilme;
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
 */@LocalBean
@Stateful
public class RegistroFilmeDAO implements GenericDAO<RegistroFilme> {
    
    @PersistenceContext(unitName = "CinemaApp-ejbPU",
            type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void create(RegistroFilme e) {
        em.persist(e);
    }

    @Override
    public void update(RegistroFilme e) {
        em.merge(e);
    }

    @Override
    public void delete(RegistroFilme e) {
        em.merge(e);
        em.remove(e);
    }

    @Override
    public List<RegistroFilme> read() {
        Query query = em.createNamedQuery("RegistroFilme.findAll");
        return query.getResultList();
    }

    @Override
    public RegistroFilme readById(Long id) {
        return em.find(RegistroFilme.class, id);
    }

}


