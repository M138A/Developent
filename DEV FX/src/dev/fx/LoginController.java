/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author M. Hartgring
 */
public class LoginController implements Initializable {
    @FXML
    private Button regButton;
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    private Users loggingUser = null;
    private String username = "";
    private String password = "";
    /*
    Login method
    */
    
    @FXML
    private void startRegistrationForm(ActionEvent event) {
        fxmlController x = new fxmlController();
        x.setMainStage("Register", "registration.fxml", 450, 450);
       /* String name = "Mark";
        Characters x = new Characters();
        x.setName(name);
        x.persist(x);
        Users y = new Users();
        y.setUserName(name);
        y.setPassword("pass");
        y.persist(y);*/
        
        
    }
    private void disableAllInput()
    {
        usernameField.setDisable(true);
        passwordField.setDisable(true);
        regButton.setDisable(true);
        button.setDisable(true);    
        
        
    }
    @FXML
    private void checkInput(ActionEvent event) {
        if(!usernameField.getText().equals("") && !passwordField.getText().equals(""))
        {
            processLogin(usernameField.getText(), passwordField.getText());
        }
        else
        {
            label.setText("Fill in all fields");
        }
    }
    private void moveToMainScreen(Users loggingInUser)
    {
        fxmlController controller = new fxmlController(loggingInUser);
        controller.setMainStage("Main", "mainScreen.fxml", 600,600);
        
    }
    private void processLogin(String user, String pass)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        EntityManager em = emf.createEntityManager();        
        List results = em.createNamedQuery("Users.findByUserName")
                .setParameter("userName", user)
                .getResultList();
        if(results.size() != 0)
        {
            loggingUser = (Users) results.get(0);            
            if(String.valueOf(pass).equals(loggingUser.getPassword()))
            {
                disableAllInput();
                moveToMainScreen(loggingUser);
            }
        }
        else
        {
            label.setText("Wrong credentials");
        }       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
