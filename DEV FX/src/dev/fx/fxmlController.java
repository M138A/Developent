package dev.fx;

import java.awt.Toolkit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by mark&bart on 9-3-15.
 */
public class fxmlController extends DEVFX {
    
     public void setMainStage(String Title, String fxmlURL, int size1, int size2) {
        try {            
            root = FXMLLoader.load(getClass().getResource(fxmlURL));            
            theStage.setScene(new Scene(root, size1, size2));
            theStage.setTitle(Title);
            try {
                theStage.show();
                
            } catch (Exception ex) {
                Logger.getLogger(fxmlController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
