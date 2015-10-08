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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author M. Hartgring
 */
public class MainScreenController implements Initializable {
    
    //@FXML
    //private Label paymentLabel, balanceLabel;
    private Users user = null;
    //@FXML
    //private Button ibanSubmitButton;
    //@FXML
    //TextField ibanField, amountField; */
    //public static String username;
    @FXML
    Label characterNameLabel, characterRaceLabel, characterClassLabel, characterLevelLabel;
    
    private String characterName;
    private String selectedClass;
    private String race;
    private int level;
    private String selectedCharacter;
    EntityManagerFactory emf;
    EntityManager em;
    
    
    
    @FXML
    ChoiceBox characterChoiceBox = new ChoiceBox();

    public void setUser(Users user) {
        this.user = user;
        
        setupApplication();
        addCharactersToMenu();
    }
    
    private void setupApplication() {
        emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        em = emf.createEntityManager();
        //setBalanceLabel(); 
    }
    @FXML
    private void goToAccount(ActionEvent event)
    {
        try {
            fxmlController c = new fxmlController(user);
            //c.goToRegistrationForm(event, "shopFXML.fxml", "Shop");
            c.goToRegistrationForm(event,"AccountManagment.fxml", "Shop", 6);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void goToShop(ActionEvent event) throws IOException
    {
        fxmlController c = new fxmlController(user);
        //c.goToRegistrationForm(event, "shopFXML.fxml", "Shop");
        c.goToRegistrationForm(event,"shopFXML.fxml", "Shop", 2);
    }
    @FXML
    private void goToServer(ActionEvent event) throws IOException {
        fxmlController c = new fxmlController();
        c.goToRegistrationForm(event, "servers.fxml", "Servers",0);
    }

    @FXML
    private void goToCharacters(ActionEvent event) throws IOException {
        fxmlController c = new fxmlController(user);
        c.goToRegistrationForm(event, "characters.fxml", "Characters",3);
    }
    
    /*
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
    } */
    
    
    public void addCharactersToMenu() {
        List results =  em.createNativeQuery("SELECT o.name FROM owns as o, characters as c WHERE o.user_name = ? AND o.name = c.name ORDER BY c.level DESC")
                .setParameter(1, user.getUserName())
                .getResultList();
        
        if(!results.isEmpty()) {
            //Characters menuChar = (Characters) results.get(0);
            String name = (String) results.get(0);
            //String name = menuChar.getName();
            characterChoiceBox.setValue(name);
        
            for(int i = 0; i < results.size(); i++) {
                System.out.println(results.get(i));
                //Characters menuChar2 = (Characters) results.get(i);
                String menuName = (String) results.get(i);
                //String result = menuChar2.getName();
                characterChoiceBox.getItems().add(i, menuName);
            }
        }
    }
    
    public void getCharacterStats() {
        List results = em.createNamedQuery("Characters.findByName")
                .setParameter("name", selectedCharacter)
                .getResultList();
        
        if(!results.isEmpty()) {      
            Characters newChar = (Characters) results.get(0);
            characterName = newChar.getName();
            selectedClass = newChar.getClass1();
            race = newChar.getRace();
            level = newChar.getLevel();
        }
    }
    
    public void changeFields() {
        characterNameLabel.setText("");
        characterClassLabel.setText("");
        characterRaceLabel.setText("");
        characterLevelLabel.setText("");
        
        characterNameLabel.setText(characterName);
        characterClassLabel.setText(selectedClass);
        characterRaceLabel.setText(race);
        characterLevelLabel.setText(Integer.toString(level));
    }
    
    public void onSelectedCharacterChanged() {
        characterChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedCharacter = (String) characterChoiceBox.getItems().get((Integer) newValue);
                System.out.println(selectedCharacter);
                getCharacterStats();
                changeFields();
            }
        });
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setupApplication();
        onSelectedCharacterChanged();        
        
        if(!characterChoiceBox.getItems().isEmpty()) {
            selectedCharacter = (String) characterChoiceBox.getItems().get(0);
        }
        
        getCharacterStats();
        changeFields();
        
    }

}
