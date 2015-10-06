/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author M. Hartgring
 */
public class MainScreenController implements Initializable {
    
    @FXML
    private Label paymentLabel, balanceLabel;
    private Users user = null;
    @FXML
    private Button ibanSubmitButton;
    @FXML
    TextField ibanField, amountField;
    public static String username;
    EntityManagerFactory emf;
    EntityManager em;

    public void setUser(Users user) {

        this.user = user;
        setupApplication();
    }
    
    private void setupApplication() {
        emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        em = emf.createEntityManager();
        username = user.getUserName();
        setBalanceLabel();
       
    }

    @FXML
    private void goToServer(ActionEvent event) throws IOException {
        fxmlController c = new fxmlController();
        c.goToRegistrationForm(event, "servers.fxml", "Servers");
    }

    @FXML
    private void goToCharacters(ActionEvent event) throws IOException {
        fxmlController c = new fxmlController();
        c.goToRegistrationForm(event, "characters.fxml", "Characters");
    }
    private void setBalanceLabel()
    {
        balanceLabel.setText("Your balance: € " + String.valueOf(user.getBalance()));
    }
    @FXML
    public void insertIBAN(ActionEvent action) {
        em.getTransaction().begin();
        try {
            em.createNativeQuery("UPDATE users SET iban=? WHERE user_name=?")
                    .setParameter(1, ibanField.getText())
                    .setParameter(2, user.getUserName())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }    
    }
    @FXML
    public void addMoney(ActionEvent event)
    {
        int amount = Integer.valueOf(amountField.getText());
        amount += user.getBalance();
        em.getTransaction().begin();
        try {
            em.createNativeQuery("UPDATE users SET balance=? WHERE user_name=?")
                    .setParameter(1, amount)
                    .setParameter(2, user.getUserName())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
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
