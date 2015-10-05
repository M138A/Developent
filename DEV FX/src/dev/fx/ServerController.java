/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author briang
 */
public class ServerController implements Initializable {
    
    @FXML
    private ListView serverList;
    @FXML
    private Label serverCountLabel;
    
    private int serverCount = 0;
    private String serverAdress;
    private int connectedUsers;
    private String serverlocation;
    private int serverMaxUsers;
    private String serverName;
    private Collection<Users> UsersCollection;

    public void getServers()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        EntityManager em = emf.createEntityManager();        
        List results = em.createNamedQuery("Servers.findAll")
                .getResultList();
        if(results.size() != 0)
        {
           for (int i=0;i < results.size();i++)
           {
                
                Servers s = new Servers();
                serverAdress = s.getAdress() ;
                connectedUsers = s.getConnectedUsers();   
                serverlocation = s.getLocation();
                serverMaxUsers = s.getMaxUsers();
                serverName = s.getName();
                UsersCollection = s.getUsersCollection();
                
                setServers();
                serverCount += 1;
           }
            
        }
        else
        {
            setServers();
        }
           
        
       
    }
    
    public void joinServer()
    {
        
    }

    public void exitServer()
    {
        
    }
    
    public void setServers()
    {
        serverCountLabel.setText("Aantal Servers beschikbaar: " + serverCount);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
