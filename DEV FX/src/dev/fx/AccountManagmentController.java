/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    Label usernameLabel, firstNameLabel, lastNameLabel, subscriptionLabel, bannedLabel, changePasswordInfoLabel, ibanInfoLabel;
    @FXML
    PasswordField oldPasswordField, newPasswordField;
    @FXML
    TextField ibanTextField;
    
    @FXML
    public void changePassword(ActionEvent event)
    {
        if(oldPasswordField.getText().equals(user.getPassword()))
        {
            user.setPassword(newPasswordField.getText());
            user.mergeEntityObject(user);
            changePasswordInfoLabel.setText("Password changed");
        }
        else
        {
            changePasswordInfoLabel.setText("Wrong old password");
        }
    }
    
    public void changeIBAN() {
        if(ibanTextField.getText().trim().isEmpty()) {
            ibanInfoLabel.setText("Use at least 1 letter");
        } else {
            user.setIban(ibanTextField.getText().trim());
            user.mergeEntityObject(user);
            ibanInfoLabel.setText("Iban changed");
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
    
    public void toMainMenu(ActionEvent event) {
        fxmlController c = new fxmlController(user);   
        try {
            c.goToRegistrationForm(event, "mainScreen.fxml", "Main", 1);
        } catch (IOException ex) {
            Logger.getLogger(ShopFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
