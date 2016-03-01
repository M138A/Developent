/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dev.fx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author weseykone
 */
public class charactersController {
    
    @FXML
    TextField characterName;
    @FXML
    RadioButton assassinClass, mageClass, warriorClass, rangerClass, 
                elfRace, grasshopperRace, humanRace;
    
    String selectedClass;
    boolean classSelected = false;
    String race;
    boolean raceSelected = false;
    Characters character = new Characters();
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
    EntityManager em = emf.createEntityManager();
    
    MainScreenController msc = new MainScreenController();
    //String userName = msc.username;
    ArrayList<Users> usersCollection = new ArrayList<>();
    ArrayList<Characters> charactersCollection = new ArrayList<>();
    
    private Users user;
    public void setUser(Users u)
    {
        user = u;
        System.out.println(user);
    }
    
    public void selectedClass() {
        if(assassinClass.isSelected()) {
            selectedClass = "Assassin";
            classSelected = true;
        }
        if(mageClass.isSelected()) {
            selectedClass = "Mage";
            classSelected = true;
        }
        if(rangerClass.isSelected()) {
            selectedClass = "Ranger";
            classSelected = true;
        }
        if(warriorClass.isSelected()) {
            selectedClass = "Warrior";
            classSelected = true;
        }
    }
    
    public void selectedRace() {
        if(elfRace.isSelected()) {
            race = "Elf";
            raceSelected = true;
        }
        if(grasshopperRace.isSelected()) {
            race = "Grasshopper";
            raceSelected = true;
        }
        if(humanRace.isSelected()) {
            race = "Human";
            raceSelected = true;
        }
    }
    
    
    public void addCharacter() {
        selectedRace();
        selectedClass();
        
        Random rand = new Random();
        
        if(!characterName.getText().trim().isEmpty()){
            
            if(user.getCharacterSlots() > 0) {
                character.setName(characterName.getText());
                character.setClass1(selectedClass);
                character.setRace(race);
                character.setLevel(rand.nextInt(100) + 1);
                charactersCollection.add(character);
                usersCollection.add(user);                
                int amountOfSlots = user.getCharacterSlots() - 1;
                user.setCharacterSlots(amountOfSlots);                
                em.merge(user);               
                character.persist(character);
                insertIntoOwns();
                emptyFields();
            }
        }
    }

    private void insertIntoOwns() {
        ShopFXMLController merger = new ShopFXMLController();
        
        user.setCharactersCollection(charactersCollection);
        character.setUsersCollection(usersCollection);
        
        merger.mergeEntityObject(user);
        merger.mergeEntityObject(character);
        
    }
    
    private void emptyFields() {
        classSelected = false;
        raceSelected = false;
        
        characterName.setText("");
        
        assassinClass.setSelected(false);
        mageClass.setSelected(false);
        rangerClass.setSelected(false);
        warriorClass.setSelected(false);
        
        elfRace.setSelected(false);
        grasshopperRace.setSelected(false);
        humanRace.setSelected(false);
    }
    
    public void toMainMenu(ActionEvent event){
        fxmlController c = new fxmlController(user);   
        try {
            c.goToRegistrationForm(event, "mainScreen.fxml", "Main", 1);
        } catch (IOException ex) {
            Logger.getLogger(ShopFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
