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
 * @author Victor Oka
 */
@Entity
@Table(name = "FILME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filme.findAll", query = "SELECT f FROM Filme f"),
    @NamedQuery(name = "Filme.findByIdFilme", query = "SELECT f FROM Filme f WHERE f.idFilme = :idFilme"),
    @NamedQuery(name = "Filme.findByNomeFilme", query = "SELECT f FROM Filme f WHERE f.nomeFilme = :nomeFilme"),
    @NamedQuery(name = "Filme.findByNota", query = "SELECT f FROM Filme f WHERE f.nota = :nota"),
    @NamedQuery(name = "Filme.findByFans", query = "SELECT f FROM Filme f WHERE f.fans = :fans")})
public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FILME")
    private Long idFilme;
    @Size(max = 100)
    @Column(name = "NOME_FILME")
    private String nomeFilme;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NOTA")
    private Double nota;
    @Column(name = "FANS")
    private Integer fans;
    @OneToMany(mappedBy = "idFilme")
    private List<RegistroFilme> registroFilmeList;
    @OneToMany(mappedBy = "anterior")
    private List<Filme> filmeList;
    @JoinColumn(name = "ANTERIOR", referencedColumnName = "ID_FILME")
    @ManyToOne
    private Filme anterior;
    @OneToMany(mappedBy = "sequencia")
    private List<Filme> filmeList1;
    @JoinColumn(name = "SEQUENCIA", referencedColumnName = "ID_FILME")
    @ManyToOne
    private Filme sequencia;

    public Filme() {
    }

    public Filme(Long idFilme) {
        this.idFilme = idFilme;
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
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
    public List<RegistroFilme> getRegistroFilmeList() {
        return registroFilmeList;
    }

    public void setRegistroFilmeList(List<RegistroFilme> registroFilmeList) {
        this.registroFilmeList = registroFilmeList;
    }

    @XmlTransient
    public List<Filme> getFilmeList() {
        return filmeList;
    }

    public void setFilmeList(List<Filme> filmeList) {
        this.filmeList = filmeList;
    }

    public Filme getAnterior() {
        return anterior;
    }

    public void setAnterior(Filme anterior) {
        this.anterior = anterior;
    }

    @XmlTransient
    public List<Filme> getFilmeList1() {
        return filmeList1;
    }

    public void setFilmeList1(List<Filme> filmeList1) {
        this.filmeList1 = filmeList1;
    }

    public Filme getSequencia() {
        return sequencia;
    }

    public void setSequencia(Filme sequencia) {
        this.sequencia = sequencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFilme != null ? idFilme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filme)) {
            return false;
        }
        Filme other = (Filme) object;
        if ((this.idFilme == null && other.idFilme != null) || (this.idFilme != null && !this.idFilme.equals(other.idFilme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.cinema.model.entities.Filme[ idFilme=" + idFilme + " ]";
    }
    
}
