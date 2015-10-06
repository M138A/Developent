/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author briang
 */
public class serverTableEntry {
    private final SimpleStringProperty adress;
    private final SimpleIntegerProperty users;
    private final SimpleStringProperty location;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty maxUsers;
    
    public serverTableEntry(String adress, int users, String location, int maxUsers, String name)
    {
        this.adress = new SimpleStringProperty(adress);
        this.users = new SimpleIntegerProperty(users);
        this.location = new SimpleStringProperty(location);
        this.maxUsers = new SimpleIntegerProperty(maxUsers);
        this.name = new SimpleStringProperty(name);
    }


    public String getAdress() {
        return adress.get();
    }

    public Integer getUsers() {
        return users.get();
    }

    public String getLocation() {
        return location.get();
    }

    public String getName() {
        return name.get();
    }

    public Integer getMaxUsers() {
        return maxUsers.get();
    }
    
    
}
