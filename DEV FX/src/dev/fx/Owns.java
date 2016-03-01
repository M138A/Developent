/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author M. Hartgring
 */
@Entity
@Table(name = "owns")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Owns.findAll", query = "SELECT o FROM Owns o"),
    @NamedQuery(name = "Owns.findByName", query = "SELECT o FROM Owns o WHERE o.ownsPK.name = :name"),
    @NamedQuery(name = "Owns.findByUserName", query = "SELECT o FROM Owns o WHERE o.ownsPK.userName = :userName")})
public class Owns implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OwnsPK ownsPK;
    @JoinColumn(name = "name", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Characters characters;

    public Owns() {
    }

    public Owns(OwnsPK ownsPK) {
        this.ownsPK = ownsPK;
    }

    public Owns(String name, String userName) {
        this.ownsPK = new OwnsPK(name, userName);
    }

    public OwnsPK getOwnsPK() {
        return ownsPK;
    }

    public void setOwnsPK(OwnsPK ownsPK) {
        this.ownsPK = ownsPK;
    }

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ownsPK != null ? ownsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owns)) {
            return false;
        }
        Owns other = (Owns) object;
        if ((this.ownsPK == null && other.ownsPK != null) || (this.ownsPK != null && !this.ownsPK.equals(other.ownsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.fx.Owns[ ownsPK=" + ownsPK + " ]";
    }
    
}
