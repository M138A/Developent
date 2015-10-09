/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dev.fx;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author weseykone
 */
public class randomDataClass {
       
    Users user = new Users();
    Servers server = new Servers();
    Characters character = new Characters();
    
    ArrayList<Users> usersCollection = new ArrayList<>();
    ArrayList<Servers> serversCollection = new ArrayList<>();
    ArrayList<Characters> charactersCollection = new ArrayList<>();
    
    private String raceName;
    private String className;
    
    public void addCharacters(int i) 
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
            
            character.setClass1(className);
            character.setLevel(rand.nextInt(100) + 1);
            character.setName("Mark" + i);
            character.setRace(raceName);
            charactersCollection.add(character);
        
    }
    public void addServers(int a)
    {
        Random rand = new Random();
        int class1 = rand.nextInt(20) + 1; 
        
            
        server.setAdress("" + a);
        server.setConnectedUsers(1);
        server.setLocation("EU");
        server.setMaxUsers(30);
        server.setName("Wesley" + a);
        serversCollection.add(server);    
    }

    public void addUsers(int b)
    {      
        Date today = new Date();

        Random random = new Random();
        boolean a = random.nextBoolean();
        int c = random.nextInt(7);

        user.setBalance(0);
        user.setBanned(a);
        user.setCharacterSlots(4);
        user.setFirstName("Brian" + 1);
        user.setIban("" + b);
        user.setLastName("" + b);
        user.setLastPayment(today);
        user.setMonthsPayed(c);
        user.setPassword("123");
        user.setUserName("Brian" + b);
        usersCollection.add(user);    
    }
    
        public void addCollections() {
        ShopFXMLController merger = new ShopFXMLController();
        
        for(int b = 1; b < 1001; b++){
            addCharacters(b);
            addServers(b);
            addUsers(b);
        
            user.setCharactersCollection(charactersCollection);
            user.setServersCollection(serversCollection);
            server.setUsersCollection(usersCollection);
            character.setUsersCollection(usersCollection);
        
        
            merger.mergeEntityObject(user);
            merger.mergeEntityObject(server);
            merger.mergeEntityObject(character);
        }
    }
    
    public void addDataToDatabase()
    {       
        ShopFXMLController merger = new ShopFXMLController();
        
        for (int b = 1; b < 1001; b++){
            addCharacters(b);
            addServers(b);
            addUsers(b);

            user.persist(user);
            character.persist(character);
            server.persist(server);
        }
        
        charactersCollection.clear();
        serversCollection.clear();
        usersCollection.clear();
        
        addCollections();
        
        System.out.println("Data is inserted into the database");
    }
    
    
}
