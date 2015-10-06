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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private TableView<serverTableEntry> serversTable;
    @FXML
    private TableColumn<serverTableEntry, String> adress; 
    @FXML
    private TableColumn<serverTableEntry, Integer> users; 
    @FXML
    private TableColumn<serverTableEntry, String> location; 
    @FXML
    private TableColumn<serverTableEntry, Integer> maxUsers; 
    @FXML
    private TableColumn<serverTableEntry, String> name;
    
    private ObservableList<serverTableEntry> serverData;
    private int serverCount = 0;
    private String serverAdress;
    private int connectedUsers;
    private String serverLocation;
    private int serverMaxUsers;
    private String serverName;
    private Collection<Users> UsersCollection;

       @Override
    public void initialize(URL location, ResourceBundle resources) {
            
        getServers();
            
            /*TableColumn address = new TableColumn("Address");
            address.setCellFactory(new PropertyValueFactory<serverTableEntry, String>(""));*/
    }
    
    public void getServers()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        EntityManager em = emf.createEntityManager();        
        List results = em.createNamedQuery("Servers.findAll")
                .getResultList();
        if(!results.isEmpty())
        {
            serverData = FXCollections.observableArrayList();
           for (int i=0 ;i < results.size(); i++)
           {
              Servers s = (Servers) results.get(i);
              serverAdress = s.getAdress();
              connectedUsers = s.getConnectedUsers();
              serverLocation = s.getLocation();
              serverMaxUsers = s.getMaxUsers();
              serverName = s.getName();
              
              serverTableEntry se = new serverTableEntry(serverAdress, connectedUsers, serverLocation, serverMaxUsers, serverName);
              serverData.add(se);
               
            }
               
                adress.setCellValueFactory(new PropertyValueFactory<dev.fx.serverTableEntry, String>("adress"));
                users.setCellValueFactory(new PropertyValueFactory<dev.fx.serverTableEntry, Integer>("users"));
                location.setCellValueFactory(new PropertyValueFactory<dev.fx.serverTableEntry, String>("location"));        
                maxUsers.setCellValueFactory(new PropertyValueFactory<dev.fx.serverTableEntry, Integer>("maxUsers"));        
                name.setCellValueFactory(new PropertyValueFactory<dev.fx.serverTableEntry, String>("name"));
            
                
                serversTable.setItems(serverData);
                
                setServers();
                serverCount += 1;
           }
        else
        {
            setServers();
            serverCountLabel.setText("Aantal Servers beschikbaar: " + serverCount);
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
        
    }
      
}
