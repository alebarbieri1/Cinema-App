/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victoroka
 */
@Entity
@Table(name = "SERIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s"),
    @NamedQuery(name = "Serie.findByIdSerie", query = "SELECT s FROM Serie s WHERE s.idSerie = :idSerie"),
    @NamedQuery(name = "Serie.findByNomeSerie", query = "SELECT s FROM Serie s WHERE s.nomeSerie = :nomeSerie"),
    @NamedQuery(name = "Serie.findByEpisodios", query = "SELECT s FROM Serie s WHERE s.episodios = :episodios"),
    @NamedQuery(name = "Serie.findByNota", query = "SELECT s FROM Serie s WHERE s.nota = :nota"),
    @NamedQuery(name = "Serie.findByFans", query = "SELECT s FROM Serie s WHERE s.fans = :fans")})
public class Serie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SERIE")
    private Long idSerie;
    @Size(max = 100)
    @Column(name = "NOME_SERIE")
    private String nomeSerie;
    @Column(name = "EPISODIOS")
    private Integer episodios;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NOTA")
    private Double nota;
    @Column(name = "FANS")
    private Integer fans;
    @OneToMany(mappedBy = "idSerie")
    private List<RegistroSerie> registroSerieList;
    @OneToMany(mappedBy = "temporadaSeguinte")
    private List<Serie> serieList;
    @JoinColumn(name = "TEMPORADA_SEGUINTE", referencedColumnName = "ID_SERIE")
    @ManyToOne
    private Serie temporadaSeguinte;
    @OneToMany(mappedBy = "temporadaAnterior")
    private List<Serie> serieList1;
    @JoinColumn(name = "TEMPORADA_ANTERIOR", referencedColumnName = "ID_SERIE")
    @ManyToOne
    private Serie temporadaAnterior;

    public Serie() {
    }

    public Serie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public String getNomeSerie() {
        return nomeSerie;
    }

    public void setNomeSerie(String nomeSerie) {
        this.nomeSerie = nomeSerie;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Integer episodios) {
        this.episodios = episodios;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    @XmlTransient
    public List<RegistroSerie> getRegistroSerieList() {
        return registroSerieList;
    }

    public void setRegistroSerieList(List<RegistroSerie> registroSerieList) {
        this.registroSerieList = registroSerieList;
    }

    @XmlTransient
    public List<Serie> getSerieList() {
        return serieList;
    }

    public void setSerieList(List<Serie> serieList) {
        this.serieList = serieList;
    }

    public Serie getTemporadaSeguinte() {
        return temporadaSeguinte;
    }

    public void setTemporadaSeguinte(Serie temporadaSeguinte) {
        this.temporadaSeguinte = temporadaSeguinte;
    }

    @XmlTransient
    public List<Serie> getSerieList1() {
        return serieList1;
    }

    public void setSerieList1(List<Serie> serieList1) {
        this.serieList1 = serieList1;
    }

    public Serie getTemporadaAnterior() {
        return temporadaAnterior;
    }

    public void setTemporadaAnterior(Serie temporadaAnterior) {
        this.temporadaAnterior = temporadaAnterior;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSerie != null ? idSerie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.idSerie == null && other.idSerie != null) || (this.idSerie != null && !this.idSerie.equals(other.idSerie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.cinema.model.entities.Serie[ idSerie=" + idSerie + " ]";
    }
    
}
