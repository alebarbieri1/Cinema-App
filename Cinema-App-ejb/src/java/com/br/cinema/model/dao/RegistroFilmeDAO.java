/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.model.dao;

import com.br.cinema.model.entities.Filme;
import com.br.cinema.model.entities.RegistroFilme;
import com.br.cinema.model.entities.Usuario;
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
        em.remove(em.merge(e));

    }

    @Override
    public List<RegistroFilme> read() {
        Query query = em.createNamedQuery("RegistroFilme.findAll");
        return (List<RegistroFilme>) query.getResultList();
    }

    @Override
    public RegistroFilme readById(Long id) {
        return em.find(RegistroFilme.class, id);
    }

    public List<RegistroFilme> readByUsuario(Usuario u) {
        Query query = em.createQuery("SELECT r FROM RegistroFilme r WHERE (r.idUsuario = :idUsuario)", RegistroFilme.class);
        query.setParameter("idUsuario", u);
        return (List<RegistroFilme>) query.getResultList();
    }

    public RegistroFilme readByUsuarioAndFilme(Usuario u, Filme f) {
        RegistroFilme rf = null;
        Query query = em.createQuery("SELECT r FROM RegistroFilme r WHERE (r.idFilme = :idFilme AND r.idUsuario = :idUsuario)", RegistroFilme.class);
        query.setParameter("idFilme", f);
        query.setParameter("idUsuario", u);
        try {
            rf = (RegistroFilme) query.getSingleResult();
        } catch (Exception e) {

            return null;
        } //   return em.find(RegistroSerie.class, id);

        return rf;
    }

    
}
