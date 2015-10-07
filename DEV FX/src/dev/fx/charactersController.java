/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dev.fx;

import java.util.List;
import java.util.Random;
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
    
    String classSelected;
    String race;
    Characters character = new Characters();
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
    EntityManager em = emf.createEntityManager();
    
    MainScreenController msc = new MainScreenController();
    //String userName = msc.username;
    private Users user;
    public void setUser(Users u)
    {
        user = u;
        System.out.println(user);
    }
    
    public void selectedClass() {
        if(assassinClass.isSelected()) {
            classSelected = "Assassin";
        }
        if(mageClass.isSelected()) {
            classSelected = "Mage";
        }
        if(rangerClass.isSelected()) {
            classSelected = "Ranger";
        }
        if(warriorClass.isSelected()) {
            classSelected = "Warrior";
        }
    }
    
    public void selectedRace() {
        if(elfRace.isSelected()) {
            race = "Elf";
        }
        if(grasshopperRace.isSelected()) {
            race = "Grasshopper";
        }
        if(humanRace.isSelected()) {
            race = "Human";
        }
    }
    
    
    public void addCharacter() {
        selectedRace();
        selectedClass();
        
        Random rand = new Random();
        
        List results = em.createNamedQuery("Users.findByUserName")
                .setParameter("userName", user.getUserName())
                .getResultList();
        
        if(results.size() > 0){
            Users user = (Users) results.get(0);
            
            if(user.getCharacterSlots() > 0) {
                character.setName(characterName.getText());
                character.setClass1(classSelected);
                character.setRace(race);
                character.setLevel(rand.nextInt(100) + 1);
                
                int amountOfSlots = user.getCharacterSlots() - 1;
                user.setCharacterSlots(amountOfSlots);
                
                em.merge(user);
                
                System.out.println("Sure mate");
                
                character.persist(character);
            }
            
            insertIntoOwns();
            
            
        }
    }

    public void insertIntoOwns() {
        em.getTransaction().begin();
        try {
            em.createNativeQuery("INSERT INTO owns(name, user_name) VALUES (?,?)")
                    .setParameter(1, characterName.getText())
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
    
    
    
}
