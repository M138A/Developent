/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
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
    private Label currentSlotsLabel, currentMonthsLabel, balanceLabel, addMoneyMistakeLabel, addSlotsMistakeLabel, renSubMistakeLabel;
    @FXML
    private TextField characterAmountField, moneyAmountField;
    @FXML
    private RadioButton radioOneMonth, radioTwoMonth, radioThreeMonth, radioTwelveMonth;
    @FXML
    private ToggleGroup subGroup;
    
    private int extraSlotsSinglePrice = 10;
    private int extraSlotsTotalPrice, newBalance;
    
    private int subscriptionFee;

    public void setUser(Users x) {
        user = x;
        if (user == null) {
            throw new NullPointerException("No user defined");
        }
        setCurrentSlots();
        setBalanceLabel();
        setMonthsLabel();
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

    public void mergeEntityObject(Object ob) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(ob);
        em.getTransaction().commit();
    }

    @FXML
    public void buySlots(ActionEvent event) {
        if(characterAmountField.getText().equals("")) {
            addSlotsMistakeLabel.setText("Please use at least 1 number");
            
        } else if(isInteger(characterAmountField, addSlotsMistakeLabel) && isPositive(characterAmountField, addSlotsMistakeLabel)) {
            int numberOfSlots = Integer.valueOf(characterAmountField.getText());
        
            extraSlotsTotalPrice = extraSlotsSinglePrice * numberOfSlots;
            newBalance = user.getBalance() - extraSlotsTotalPrice;
        
            if(newBalance < 0) {
                addSlotsMistakeLabel.setText("You don't have enough money");
            } else {
                numberOfSlots += user.getCharacterSlots();
                user.setBalance(newBalance);
                user.setCharacterSlots(numberOfSlots);
                mergeEntityObject(user);
                setCurrentSlots();
                setBalanceLabel();
                addSlotsMistakeLabel.setText("");
            } 
        }

    }
    
    @FXML
    public void addMoney(ActionEvent event) {
        if(moneyAmountField.getText().equals("")) {
            addMoneyMistakeLabel.setText("Please use at least 1 number");
            
        } else if(isInteger(moneyAmountField, addMoneyMistakeLabel) && isPositive(moneyAmountField, addMoneyMistakeLabel)){
            int moneyAmount = Integer.valueOf(moneyAmountField.getText());
            moneyAmount += user.getBalance();
            user.setBalance(moneyAmount);
            mergeEntityObject(user);
            setBalanceLabel();
            addMoneyMistakeLabel.setText("");          
        }
    }
    
    private boolean isPositive(TextField shopAmount, Label showMistake) {
        if(Integer.parseInt(shopAmount.getText()) > 0) {
            return true;
        } else {
            showMistake.setText("Please use only positive numbers");
            return false;
        }
    }
    
    private boolean isInteger(TextField shopAmount, Label showMistake) {
        try{
            Integer.parseInt(shopAmount.getText());
            return true;
        } catch(NumberFormatException ex){
            showMistake.setText("Please use only numbers");
            return false;
        }
    }

    private void setBalanceLabel() {
        balanceLabel.setText("Balance : " + String.valueOf(user.getBalance()));
    }

    private void setCurrentSlots() {
        currentSlotsLabel.setText(String.valueOf(user.getCharacterSlots()));
    }
    
    private void setMonthsLabel() {
        currentMonthsLabel.setText(String.valueOf(user.getMonthsPayed()));
    }
    
    @FXML
    public void renSub(ActionEvent event) {
        int numberOfMonths = getSelectedRadioButton();
        Date today = getCurrentDate(); 
        
        newBalance = user.getBalance() - subscriptionFee;
        
        if(newBalance > 0) {
            if(numberOfMonths == -1) {
                renSubMistakeLabel.setText("Please Select 1 of the options");
            } else if(user.getMonthsPayed() > 0){
                renSubMistakeLabel.setText("Please wait till your subscription\nis over");
            } else {
                user.setLastPayment(today);
                user.setMonthsPayed(numberOfMonths);
            
                mergeEntityObject(user);
                setMonthsLabel();
                renSubMistakeLabel.setText("");
            }
        } else {
            renSubMistakeLabel.setText("You don't have enough money");
        }
    }
    
    private Date getCurrentDate()
    {
        /*Calendar cal = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); */
        
        Date today = new Date();   
        return today;
        
        /*formatter.format(today);
        
        cal.setTime(today);
        
        int[] date = new int[3];
        date[0] = formatter.getCalendar().get(Calendar.DAY_OF_MONTH);
        date[1] = formatter.getCalendar().get(Calendar.MONTH) + 1;
        date[2] = formatter.getCalendar().get(Calendar.YEAR);
        return date;*/
    }
    
    private int getSelectedRadioButton() {
        if (radioOneMonth.isSelected()) {
            subscriptionFee = 15;
            return 1;
        }
        if (radioTwoMonth.isSelected()) {
            subscriptionFee = 28;
            return 2;
        }
        if (radioThreeMonth.isSelected()) {
            subscriptionFee = 40;
            return 3;
        }
        if (radioTwelveMonth.isSelected()) {
            subscriptionFee = 100;
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
