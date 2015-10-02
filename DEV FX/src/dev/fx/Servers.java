/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author M. Hartgring
 */
@Entity
@Table(name = "servers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servers.findAll", query = "SELECT s FROM Servers s"),
    @NamedQuery(name = "Servers.findByAdress", query = "SELECT s FROM Servers s WHERE s.adress = :adress"),
    @NamedQuery(name = "Servers.findByName", query = "SELECT s FROM Servers s WHERE s.name = :name"),
    @NamedQuery(name = "Servers.findByLocation", query = "SELECT s FROM Servers s WHERE s.location = :location"),
    @NamedQuery(name = "Servers.findByMaxUsers", query = "SELECT s FROM Servers s WHERE s.maxUsers = :maxUsers"),
    @NamedQuery(name = "Servers.findByConnectedUsers", query = "SELECT s FROM Servers s WHERE s.connectedUsers = :connectedUsers")})
public class Servers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "adress")
    private String adress;
    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "max_users")
    private Integer maxUsers;
    @Column(name = "connected_users")
    private Integer connectedUsers;
    @JoinTable(name = "stores", joinColumns = {
        @JoinColumn(name = "adress", referencedColumnName = "adress")}, inverseJoinColumns = {
        @JoinColumn(name = "name", referencedColumnName = "user_name")})
    @ManyToMany
    private Collection<Users> usersCollection;

    public Servers() {
    }

    public Servers(String adress) {
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Integer getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(Integer connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adress != null ? adress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servers)) {
            return false;
        }
        Servers other = (Servers) object;
        if ((this.adress == null && other.adress != null) || (this.adress != null && !this.adress.equals(other.adress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.fx.Servers[ adress=" + adress + " ]";
    }
    
}
