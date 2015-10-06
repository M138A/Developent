<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author M. Hartgring
 */
public class MainScreenController implements Initializable {
    @FXML
    private Label usernameLabel;
    private Users user = null;
    
    @FXML
    private void toServers(ActionEvent event)
    {
        fxmlController controller = new fxmlController();
        controller.setMainStage("Servers", "servers.fxml", 600,600);
    }
    
    public void setUser(Users user) {
        this.user = user;
        setGUI();
    }
    
    
    private void setGUI()
    {
        usernameLabel.setText("Welcome " + user.getUserName());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author M. Hartgring
 */
public class MainScreenController implements Initializable {
    @FXML
    private Label usernameLabel;
    private Users user = null;
    
    public void setUser(Users user) {
        this.user = user;
        System.out.println(this.user.getUserName());
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
>>>>>>> parent of 23d7c23... small fix
