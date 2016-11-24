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
 * @author SPSILVA
 */
@Entity
@Table(name = "REGISTRO_FILME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroFilme.findAll", query = "SELECT r FROM RegistroFilme r"),
    @NamedQuery(name = "RegistroFilme.findByIdRegistroFilme", query = "SELECT r FROM RegistroFilme r WHERE r.idRegistroFilme = :idRegistroFilme"),
    @NamedQuery(name = "RegistroFilme.findByStatus", query = "SELECT r FROM RegistroFilme r WHERE r.status = :status"),
    @NamedQuery(name = "RegistroFilme.findByDataAssistido", query = "SELECT r FROM RegistroFilme r WHERE r.dataAssistido = :dataAssistido"),
    @NamedQuery(name = "RegistroFilme.findByNota", query = "SELECT r FROM RegistroFilme r WHERE r.nota = :nota")})
public class RegistroFilme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO_FILME")
    private Long idRegistroFilme;
    @Size(max = 32)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DATA_ASSISTIDO")
    @Temporal(TemporalType.DATE)
    private Date dataAssistido;
    @Column(name = "NOTA")
    private Integer nota;
    @JoinColumn(name = "ID_FILME", referencedColumnName = "ID_FILME")
    @ManyToOne
    private Filme idFilme;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public RegistroFilme() {
    }

    public RegistroFilme(Long idRegistroFilme) {
        this.idRegistroFilme = idRegistroFilme;
    }

    public Long getIdRegistroFilme() {
        return idRegistroFilme;
    }

    public void setIdRegistroFilme(Long idRegistroFilme) {
        this.idRegistroFilme = idRegistroFilme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido(Date dataAssistido) {
        this.dataAssistido = dataAssistido;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Filme getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Filme idFilme) {
        this.idFilme = idFilme;
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
        hash += (idRegistroFilme != null ? idRegistroFilme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroFilme)) {
            return false;
        }
        RegistroFilme other = (RegistroFilme) object;
        if ((this.idRegistroFilme == null && other.idRegistroFilme != null) || (this.idRegistroFilme != null && !this.idRegistroFilme.equals(other.idRegistroFilme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.cinema.model.entities.RegistroFilme[ idRegistroFilme=" + idRegistroFilme + " ]";
    }
    
}
