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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author weseykone
 */
public class ShopFXMLController implements Initializable {
    public Users user;
    @FXML
    private TextField characterAmountField, moneyAmountField;
    @FXML
    private RadioButton radioOneMonth, radioTwoMonth, radioThreeMonth, radioTwelveMonth;
    @FXML
    private ToggleGroup subGroup;

    @FXML
    public void backToMainScreen(ActionEvent event) {
            
    }

    @FXML
    public void buySlots(ActionEvent event) {

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
