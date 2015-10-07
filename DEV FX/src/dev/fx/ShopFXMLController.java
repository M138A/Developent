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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author weseykone
 */
public class ShopFXMLController implements Initializable {
    private Users user;
    @FXML
    private TextField characterAmountField, moneyAmountField;
    @FXML
    private RadioButton radioOneMonth, radioTwoMonth, radioThreeMonth, radioTwelveMonth;
    @FXML
    private ToggleGroup subGroup;
    public void setUser(Users x)
    {
        user = x;
//        System.out.println(user.getUserName() + " " + user.getPassword());
    }
    @FXML
    public void backToMainScreen(ActionEvent event) {        
        //TODO add user as a parameter
            fxmlController c = new fxmlController();
        try {
            c.goToRegistrationForm(event, "mainScreen.fxml", "Main",0);
        } catch (IOException ex) {
            Logger.getLogger(ShopFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void buySlots(ActionEvent event) {
            System.out.println(user);
    }

    @FXML
    public void addMoney(ActionEvent event) {

    }

    @FXML
    public void renSub(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
