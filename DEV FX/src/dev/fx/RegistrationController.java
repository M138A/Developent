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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author M. Hartgring
 */
public class RegistrationController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label messageLabel;
     @FXML
    private void checkInput(ActionEvent event)
    {
        if(name.getText().isEmpty() || surname.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty())
        {
            messageLabel.setText("Fill in all fields!");
        } 
        else if (checkNonExistingUser())
        {
            register();
        }
        else
        {
            messageLabel.setText("User already exists.");
        }
    }
    private void register()
    {
        
        /*Characters newCharacter = new Characters();
        newCharacter.setName(username.getText());
        newCharacter.persist(newCharacter);*/
        Users newUser = new Users();
        newUser.setFirstName(name.getText());
        newUser.setLastName(surname.getText());
        newUser.setUserName(username.getText());
        newUser.setPassword(password.getText());
        newUser.persist(newUser);
        goToMainScreen(newUser);
        
    }
    private void goToMainScreen(Users u)
    {
        fxmlController x = new fxmlController(u);
        x.setMainStage("Register", "mainScreen.fxml", 700, 700);
    }
    private boolean checkNonExistingUser()
    {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        EntityManager em = emf.createEntityManager();        
        List results = em.createNamedQuery("Users.findByUserName")
                .setParameter("userName", username.getText())
                .getResultList();
        if(results.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
