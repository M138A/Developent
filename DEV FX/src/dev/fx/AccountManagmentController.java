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
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author M. Hartgring
 */
public class AccountManagmentController implements Initializable {
    private Users user;
    public void setUser(Users u)
    {
        user = u;
        setLabels();
    }
    @FXML
    Label usernameLabel, firstNameLabel, lastNameLabel, subscriptionLabel, bannedLabel, errorLabel;
    @FXML
    PasswordField oldPasswordField, newPasswordField;
    
    @FXML
    public void changePassword(ActionEvent event)
    {
        if(oldPasswordField.getText().equals(user.getPassword()))
        {
            user.setPassword(newPasswordField.getText());
            user.mergeEntityObject(user);
            errorLabel.setText("Password changed");
        }
        else
        {
            errorLabel.setText("Wrong old password");
        }
    }
    private void setLabels()
    {
        usernameLabel.setText(user.getUserName());
        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
        subscriptionLabel.setText("Your account is " + user.getMonthsPayed() + " months valid");
        bannedLabel.setText(user.getBanned().toString());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
}
