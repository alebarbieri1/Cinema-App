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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "USUARIO_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioInfo.findAll", query = "SELECT u FROM UsuarioInfo u"),
    @NamedQuery(name = "UsuarioInfo.findByIdUsuarioinfo", query = "SELECT u FROM UsuarioInfo u WHERE u.idUsuarioinfo = :idUsuarioinfo"),
    @NamedQuery(name = "UsuarioInfo.findByNome", query = "SELECT u FROM UsuarioInfo u WHERE u.nome = :nome"),
    @NamedQuery(name = "UsuarioInfo.findByEmail", query = "SELECT u FROM UsuarioInfo u WHERE u.email = :email"),
    @NamedQuery(name = "UsuarioInfo.findByAniversario", query = "SELECT u FROM UsuarioInfo u WHERE u.aniversario = :aniversario"),
    @NamedQuery(name = "UsuarioInfo.findByCompletos", query = "SELECT u FROM UsuarioInfo u WHERE u.completos = :completos"),
    @NamedQuery(name = "UsuarioInfo.findByAssistindo", query = "SELECT u FROM UsuarioInfo u WHERE u.assistindo = :assistindo")})
public class UsuarioInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIOINFO")
    private Long idUsuarioinfo;
    @Size(max = 100)
    @Column(name = "NOME")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ANIVERSARIO")
    @Temporal(TemporalType.DATE)
    private Date aniversario;
    @Column(name = "COMPLETOS")
    private Integer completos;
    @Column(name = "ASSISTINDO")
    private Integer assistindo;
    @JoinColumn(name = "ID_USUARIOINFO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public UsuarioInfo() {
    }

    public UsuarioInfo(Long idUsuarioinfo) {
        this.idUsuarioinfo = idUsuarioinfo;
    }

    public Long getIdUsuarioinfo() {
        return idUsuarioinfo;
    }

    public void setIdUsuarioinfo(Long idUsuarioinfo) {
        this.idUsuarioinfo = idUsuarioinfo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAniversario() {
        return aniversario;
    }

    public void setAniversario(Date aniversario) {
        this.aniversario = aniversario;
    }

    public Integer getCompletos() {
        return completos;
    }

    public void setCompletos(Integer completos) {
        this.completos = completos;
    }

    public Integer getAssistindo() {
        return assistindo;
    }

    public void setAssistindo(Integer assistindo) {
        this.assistindo = assistindo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioinfo != null ? idUsuarioinfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioInfo)) {
            return false;
        }
        UsuarioInfo other = (UsuarioInfo) object;
        if ((this.idUsuarioinfo == null && other.idUsuarioinfo != null) || (this.idUsuarioinfo != null && !this.idUsuarioinfo.equals(other.idUsuarioinfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.cinema.model.entities.UsuarioInfo[ idUsuarioinfo=" + idUsuarioinfo + " ]";
    }
    
}
