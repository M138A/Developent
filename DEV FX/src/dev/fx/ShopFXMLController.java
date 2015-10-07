/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author weseykone
 */
public class ShopFXMLController implements Initializable {

    private Users user;
    @FXML
    private Label currentSlotsLabel, balanceLabel;
    @FXML
    private TextField characterAmountField, moneyAmountField;
    @FXML
    private RadioButton radioOneMonth, radioTwoMonth, radioThreeMonth, radioTwelveMonth;
    @FXML
    private ToggleGroup subGroup;

    private void setCurrentSlots() {
        currentSlotsLabel.setText(String.valueOf(user.getCharacterSlots()));
    }

    public void setUser(Users x) {
        user = x;
        if (user == null) {
            throw new NullPointerException("No user defined");
        }
        setCurrentSlots();
        setBalanceLabel();
    }

    @FXML
    public void backToMainScreen(ActionEvent event) {
        //TODO add user as a parameter
        fxmlController c = new fxmlController(user);
        try {
            c.goToRegistrationForm(event, "mainScreen.fxml", "Main", 1);
        } catch (IOException ex) {
            Logger.getLogger(ShopFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void buySlots(ActionEvent event) {
        int numberOfSlots = Integer.valueOf(characterAmountField.getText());
        numberOfSlots += user.getCharacterSlots();
        user.setCharacterSlots(numberOfSlots);
        mergeEntityObject(user);
        setCurrentSlots();

    }

    public void mergeEntityObject(Object ob) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(ob);
        em.getTransaction().commit();
    }

    @FXML
    public void addMoney(ActionEvent event) {
        int moneyAmount = Integer.valueOf(moneyAmountField.getText());
        moneyAmount += user.getBalance();
        user.setBalance(moneyAmount);
        mergeEntityObject(user);
        setBalanceLabel();

    }

    private void setBalanceLabel() {
        balanceLabel.setText("Balance : " + String.valueOf(user.getBalance()));
    }

    @FXML
    public void renSub(ActionEvent event) {
        int numberOfMonths = getSelectedRadioButton();
        int[] today = getCurrentDate();
        
        
    }
    private int[] getCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");        
        Date today = new Date();        
        formatter.format(today);
        cal.setTime(today);
        int[] date = new int[3];
        date[0] = formatter.getCalendar().get(Calendar.DAY_OF_MONTH);
        date[1] = formatter.getCalendar().get(Calendar.MONTH) + 1;
        date[2] = formatter.getCalendar().get(Calendar.YEAR);
        return date;
    }
    private int getSelectedRadioButton() {
        if (radioOneMonth.isSelected()) {
            return 1;
        }
        if (radioTwoMonth.isSelected()) {
            return 2;
        }
        if (radioThreeMonth.isSelected()) {
            return 3;
        }
        if (radioTwelveMonth.isSelected()) {
            return 12;
        }
        return -1;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
