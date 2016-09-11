/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.cinema.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "REGISTRO_SERIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroSerie.findAll", query = "SELECT r FROM RegistroSerie r"),
    @NamedQuery(name = "RegistroSerie.findByIdRegistroSerie", query = "SELECT r FROM RegistroSerie r WHERE r.idRegistroSerie = :idRegistroSerie"),
    @NamedQuery(name = "RegistroSerie.findByStatus", query = "SELECT r FROM RegistroSerie r WHERE r.status = :status"),
    @NamedQuery(name = "RegistroSerie.findByDataInicio", query = "SELECT r FROM RegistroSerie r WHERE r.dataInicio = :dataInicio"),
    @NamedQuery(name = "RegistroSerie.findByDataFim", query = "SELECT r FROM RegistroSerie r WHERE r.dataFim = :dataFim"),
    @NamedQuery(name = "RegistroSerie.findByProgresso", query = "SELECT r FROM RegistroSerie r WHERE r.progresso = :progresso"),
    @NamedQuery(name = "RegistroSerie.findByNota", query = "SELECT r FROM RegistroSerie r WHERE r.nota = :nota")})
public class RegistroSerie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO_SERIE")
    private Long idRegistroSerie;
    @Size(max = 32)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DATA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "DATA_FIM")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Column(name = "PROGRESSO")
    private Integer progresso;
    @Column(name = "NOTA")
    private Integer nota;
    @JoinColumn(name = "ID_SERIE", referencedColumnName = "ID_SERIE")
    @ManyToOne
    private Serie idSerie;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public RegistroSerie() {
    }

    public RegistroSerie(Long idRegistroSerie) {
        this.idRegistroSerie = idRegistroSerie;
    }

    public Long getIdRegistroSerie() {
        return idRegistroSerie;
    }

    public void setIdRegistroSerie(Long idRegistroSerie) {
        this.idRegistroSerie = idRegistroSerie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getProgresso() {
        return progresso;
    }

    public void setProgresso(Integer progresso) {
        this.progresso = progresso;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Serie getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistroSerie != null ? idRegistroSerie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroSerie)) {
            return false;
        }
        RegistroSerie other = (RegistroSerie) object;
        if ((this.idRegistroSerie == null && other.idRegistroSerie != null) || (this.idRegistroSerie != null && !this.idRegistroSerie.equals(other.idRegistroSerie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.cinema.model.entities.RegistroSerie[ idRegistroSerie=" + idRegistroSerie + " ]";
    }
    
}
