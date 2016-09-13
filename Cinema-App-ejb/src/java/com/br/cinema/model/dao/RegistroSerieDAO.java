/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.model.dao;

import com.br.cinema.model.entities.RegistroSerie;
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
public class RegistroSerieDAO implements GenericDAO<RegistroSerie> {
    
    @PersistenceContext(unitName = "CinemaApp-ejbPU",
            type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void create(RegistroSerie e) {
        em.persist(e);
    }

    @Override
    public void update(RegistroSerie e) {
        em.merge(e);
    }

    @Override
    public void delete(RegistroSerie e) {
        em.merge(e);
        em.remove(e);
    }

    @Override
    public List<RegistroSerie> read() {
        Query query = em.createNamedQuery("RegistroSerie.findAll");
        return (List<RegistroSerie>)query.getResultList();
    }

    @Override
    public RegistroSerie readById(Long id) {
        return em.find(RegistroSerie.class, id);
    }

}


