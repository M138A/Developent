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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author M. Hartgring
 */
public class LoginController implements Initializable {
    @FXML
    private Button regButton;
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    private String raceName;
    private String className;
    private Users loggingUser = null;
    private String username = "";
    private String password = "";
    public static Users loginUser;
    
    Users u = new Users();
    Servers s = new Servers();
    Characters c = new Characters();
    
    ArrayList<Users> us = new ArrayList<Users>();
    ArrayList<Servers> serversShit = new ArrayList<Servers>();
    ArrayList<Characters> charactersShit = new ArrayList<Characters>();
    /*
    Login method
    */
    
    @FXML
    private void startRegistrationForm(ActionEvent event) throws IOException {
        fxmlController x = new fxmlController();
        x.goToRegistrationForm(event, "registration.fxml", "Register",0 );
        
       /* String name = "Mark";
        Characters x = new Characters();
        x.setName(name);
        x.persist(x);
        Users y = new Users();
        y.setUserName(name);
        y.setPassword("pass");
        y.persist(y);*/
        
        
    }
    private void disableAllInput()
    {
        usernameField.setDisable(true);
        passwordField.setDisable(true);
        regButton.setDisable(true);
        button.setDisable(true);    
        
        
    }
    private ActionEvent ev;
    @FXML
    private void checkInput(ActionEvent event) {
        
        if(!usernameField.getText().equals("") && !passwordField.getText().equals(""))
        {
            ev = event;
            processLogin(usernameField.getText(), passwordField.getText());
        }
        else
        {
            label.setText("Fill in all fields");
        }
    }
    private void moveToMainScreen(Users loggingInUser)
    {
        try {
            fxmlController controller = new fxmlController(loggingInUser);
            controller.goToRegistrationForm(ev,"mainScreen.fxml", "Main", 1);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void processLogin(String user, String pass)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEV_FXPU");
        EntityManager em = emf.createEntityManager();        
        List results = em.createNamedQuery("Users.findByUserName")
                .setParameter("userName", user)
                .getResultList();
        if(results.size() != 0)
        {
            loggingUser = (Users) results.get(0);            
            if(String.valueOf(pass).equals(loggingUser.getPassword()))
            {
                loginUser = loggingUser;
                disableAllInput();
                moveToMainScreen(loggingUser);
            }
        }
        else
        {
            label.setText("Wrong credentials");
        }       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void pompCharactersVol(int i)
    {
        
            Random rand = new Random();
            int class1 = rand.nextInt(4) + 1;
            int race = rand.nextInt(3) + 1;
            
            if(class1 == 1)
            {
                className = "Assassin";
            }
            if(class1 == 2)
            {
                className = "Mage";
            }
            if(class1 == 3)
            {
                className = "Warrior";
            }
            if(class1 == 4)
            {
                className = "Ranger";
            }
            
            if(race == 1)
            {
                raceName = "Grasshopper";
            }
            if(race == 2)
            {
                raceName = "Elf";
            }
            if(race == 3)
            {
                raceName = "Human";
            }
         
            
            c.setClass1(className);
            c.setLevel(rand.nextInt(100) + 1);
            c.setName("Mark" + i);
            c.setRace(raceName);
            charactersShit.add(c);
        
    }
    public void pompServersVol(int a)
    {
        
       
        
        Random rand = new Random();
        int class1 = rand.nextInt(20) + 1; 
        
            
        s.setAdress("" + a);
        s.setConnectedUsers(0);
        s.setLocation("EU");
        s.setMaxUsers(30);
        s.setName("Wesley" + a);
        serversShit.add(s);
        
        
    }
    public void pompOwnsVol()
    {
        
    }
    public void pompUsersVol(int b)
    {
        
        Date today = new Date();
        
        
            Random random = new Random();
            boolean a = random.nextBoolean();
            int c = random.nextInt(5) + 1;
            
            
        
            u.setBalance(0);
            u.setBanned(a);
            u.setCharacterSlots(4);
            u.setFirstName("Brian" + 1);
            u.setIban("" + b);
            u.setLastName("" + b);
            u.setLastPayment(today);
            u.setMonthsPayed(c);
            u.setPassword("123");
            u.setUserName("Brian" + b);
            us.add(u);
        
        
        
    }
    public void pompVol()
    {
        
        
        for (int b = 1; b < 1001; b++){
            pompCharactersVol(b);
            pompServersVol(b);
            pompUsersVol(b);

            u.persist(u);
            c.persist(c);
            s.persist(s);
            
        }
        System.out.println("Yeaaah");
        
        charactersShit.clear();
        serversShit.clear();
        us.clear();
        
        addCollections();
    }

    public void addCollections() {
        ShopFXMLController shop = new ShopFXMLController();
        
        for(int b = 1; b < 1001; b++){
            pompCharactersVol(b);
            pompServersVol(b);
            pompUsersVol(b);
        
            u.setCharactersCollection(charactersShit);
            u.setServersCollection(serversShit);
            s.setUsersCollection(us);
            c.setUsersCollection(us);
        
        
            shop.mergeEntityObject(u);
            shop.mergeEntityObject(s);
            shop.mergeEntityObject(c);
        }
    }
}
