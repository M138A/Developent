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
import java.util.GregorianCalendar;
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
    private Label currentSlotsLabel, balanceLabel, monthsRemainingLabel;
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
        activeSub();
    }

    private void setMonthsPayedLabel() {
        monthsRemainingLabel.setText(String.valueOf(user.getMonthsPayed()));
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
        if (pay(numberOfSlots)) {
            numberOfSlots += user.getCharacterSlots();
            user.setCharacterSlots(numberOfSlots);
            mergeEntityObject(user);
            setCurrentSlots();
        }
        else
        {
            System.out.println("Upgrade failed, due to lack of money");
        }

    }

    private void mergeEntityObject(Object ob) {
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
        //calculates remaining months        
        int numberOfMonths = getSelectedRadioButton();
        if (pay(amountForSub(numberOfMonths))) {
            int monthsRemaining = user.getMonthsPayed();
            monthsRemaining += numberOfMonths;
            user.setMonthsPayed(monthsRemaining);
            mergeEntityObject(user);
            activeSub();
        } else {
            System.out.println("Upgrade failed, due to lack of money");
        }

    }

    private int amountForSub(int months) {
        switch (months) {
            case 1:
                return 5;
            case 2:
                return 8;
            case 3:
                return 10;
            case 12:
                return 35;
            default:
                return 0;
        }
    }

    private boolean pay(int amount) {
        int balance = user.getBalance();
        balance -= amount;
        if (balance >= 0) {
            user.setBalance(balance);
            mergeEntityObject(user);
            setBalanceLabel();
            return true;
        } else {
            return false;
        }
    }
    /*
     Calculates months that are still remaining
     */

    private int refreshsub(Calendar lastPayment, Calendar now) {
        int diffYear = lastPayment.get(Calendar.YEAR) - now.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + lastPayment.get(Calendar.MONTH) - now.get(Calendar.MONTH);
        monthsRemainingLabel.setText(String.valueOf(diffMonth));
        user.setMonthsPayed(diffMonth);
        mergeEntityObject(user);
        return diffMonth;
    }

    private boolean activeSub() {
        Calendar lastPayment = Calendar.getInstance();
        lastPayment.setTime(user.getLastPayment());
        lastPayment.add(Calendar.MONTH, user.getMonthsPayed());
        Calendar now = getCurrentDate();

        if (lastPayment.after(now)) {
            refreshsub(lastPayment, now);

        }
        return lastPayment.after(now);
    }

    private Calendar getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
        formatter.format(today);
        cal.setTime(today);
        return cal;
        /*int[] date = new int[3];
         date[0] = formatter.getCalendar().get(Calendar.DAY_OF_MONTH);
         date[1] = formatter.getCalendar().get(Calendar.MONTH) + 1;
         date[2] = formatter.getCalendar().get(Calendar.YEAR);
         return date;*/
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
