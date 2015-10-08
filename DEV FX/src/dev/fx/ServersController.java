/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author briang
 */
public class ServersController implements Initializable {
    
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
    @FXML
    private TextField serverJoin;
    @FXML
    private Label serverError;
    
    private ActionEvent event;
    private ObservableList<serverTableEntry> serverData;
    private String serverAdress;
    private int connectedUsers;
    private String serverLocation;
    private int serverMaxUsers;
    private String serverName;
    private Collection<Users> UsersCollection;
    
    private Servers s;
    private String serverIp;
    private String joinAdress;
    private int joinUsers;
    private String joinLocation;
    private int joinMaxUsers;
    private String joinName;
    private Users u;
            
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
                
           }
        else
        {
            
        }
           
        
       
    }
    
    public void pickServer(ActionEvent Event)
    {
        serverIp = serverJoin.getText();
        
        if(serverIp != null)
        {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        EntityManager em = emf.createEntityManager();        
        List results = em.createNamedQuery("Servers.findByAdress")
                .setParameter("adress", serverIp)
                .getResultList();
        
        if(!results.isEmpty())
        {
            for (int i=0 ;i < results.size(); i++)
           {
              s = (Servers) results.get(i);
              joinAdress = s.getAdress();
              joinUsers = s.getConnectedUsers();
              joinLocation = s.getLocation();
              joinMaxUsers = s.getMaxUsers();
              joinName = s.getName();
              
              System.out.println(joinAdress + " " +joinUsers + " " +joinLocation + " " +joinMaxUsers + " " +joinName);
              event = Event;
              joinServer();
              
        }
        
        }
        else
        {
            serverError.setText("Server adress niet beschikbaar");
        }
        
    }
    }

    public void setSU(Servers s, Users user) {
        this.s = s;
        this.u = user;
    }
    
    public void setU(Users user) {
        this.u = user;
    }
    
    public void joinServer() 
    {
        
        joinUsers = joinUsers + 1;
        
        if(u.getMonthsPayed() >= 1)
        {
            if(joinUsers <= joinMaxUsers) {
            s.setConnectedUsers(joinUsers);
            ShopFXMLController f = new ShopFXMLController();
            f.mergeEntityObject(s);
        
        
            try{
            fxmlController x = new fxmlController(s, u);
            x.goToRegistrationForm(event, "server.fxml", joinName, 4);
            }
            catch(IOException e)
            {
                System.out.print(e);
            }
            }
            else{
            
            serverError.setText("Server is full");
            }
        }
        else
        {
           serverError.setText("Your subscription has expired"); 
        }
    }
    
    public void toMainMenu (ActionEvent event)
    {
        fxmlController c = new fxmlController(u);
        try {
            c.goToRegistrationForm(event, "mainScreen.fxml", "Main", 1);
        } catch (IOException ex) {
            Logger.getLogger(ShopFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
}
