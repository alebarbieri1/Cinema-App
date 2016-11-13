/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.model.dao;

import com.br.cinema.model.entities.Filme;
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
public class FilmeDAO implements GenericDAO<Filme> {

    @PersistenceContext(unitName = "CinemaApp-ejbPU",
            type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void create(Filme e) {
        em.persist(e);
    }

    @Override
    public void update(Filme e) {
        em.merge(e);
    }

    @Override
    public void delete(Filme e) {
        em.remove(em.merge(e));

    }

    @Override
    public List<Filme> read() {
        Query query = em.createNamedQuery("Filme.findAll");
        return (List<Filme>) query.getResultList();
    }

    @Override
    public Filme readById(Long id) {
        return em.find(Filme.class, id);
    }

    public Filme readByIdApi(Long id) {
        Filme f;
        Query query = em.createNamedQuery("Filme.findByIdApi");
        query.setParameter("idApi", id);
        try {
            f = (Filme) query.getSingleResult();
        } catch (Exception e) {
            f = null;
        }
        return f;
    }

}
