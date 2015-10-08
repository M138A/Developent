/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author briang
 */
public class ServerController implements Initializable {

    private String hide = "Hide Info";
    private int joinUsers;
    private Servers s;
    private Users u;
    
    @FXML 
    private Label labelAantal;
    @FXML 
    private Label labelAdress;
    @FXML 
    private Label labelName;
    @FXML 
    private Button showInfo;
    
    private String serverUsers;
    private String serverAdress;
    private String serverName;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
   Runtime.getRuntime().addShutdownHook(new Thread()
  {
    @Override
    public void run()
    {
       
        joinUsers = s.getConnectedUsers();
        joinUsers = joinUsers - 1;
        
        
        s.setConnectedUsers(joinUsers);
       
        ShopFXMLController f = new ShopFXMLController();
        f.mergeEntityObject(s);
    }
 });
    }    

    public void setSU(Servers s, Users u) {
        this.s = s;
        this.u = u;
       
        
        if (s == null) {
            throw new NullPointerException("No server defined");
        }
        if (u == null) {
            throw new NullPointerException("No user defined");
        }
         
        showServerInfo();
    }
   
    
    public void backToServers(ActionEvent event) throws IOException
   {
        joinUsers = s.getConnectedUsers();
        joinUsers = joinUsers - 1;
        
        
        s.setConnectedUsers(joinUsers);
       
        ShopFXMLController f = new ShopFXMLController();
        f.mergeEntityObject(s);
        
        
       
       fxmlController x = new fxmlController(u);
       x.goToRegistrationForm(event, "servers.fxml", null, 5);
   
}
    public void showServerInfo()
    {
        serverUsers = s.getConnectedUsers().toString();
        serverAdress = s.getAdress();
        serverName = s.getName();
        
        System.out.print(serverUsers);
        
        labelAantal.setText("Users op server : " + serverUsers);
        labelAdress.setText("Server adress : " + serverAdress);
        labelName.setText("Server Name : " + serverName);
    }
    
   
}