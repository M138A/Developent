/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.fx;

import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author M. Hartgring
 */
public class DEVFX extends Application {
    public static Parent root;
    public static Stage theStage;  
    

    public Stage getTheStage() {
        return theStage;
    }
 
    @Override
    public void start(Stage stage) throws Exception {        
          root = FXMLLoader.load(getClass().getResource("servers.fxml"));  
          
          theStage = stage;
            theStage.setTitle("Login");
            theStage.setScene(new Scene(root, 400 , 400));
            theStage.centerOnScreen();
            theStage.show();
                  
        
    }
 public  double GetScreenWorkingWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    public  double GetScreenWorkingHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
