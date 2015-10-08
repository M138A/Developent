package dev.fx;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by mark&bart on 9-3-15.
 */
public class fxmlController extends DEVFX {

    private Users u = null;
    private Servers s = null;
    
    
    
    public fxmlController(Users x) {
        u = x;
    }
    
    public fxmlController(Servers a, Users b){
        s = a;
        u = b;
    }

    public fxmlController() {

    }

   /* public void setMainStage(String Title, String fxmlURL, int size1, int size2, int ControllerType) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            fxmlURL
                    )
            );
            Stage stage = new Stage();
            stage.setScene(
                    new Scene(
                            (AnchorPane) loader.load()
                    )
            );
            //AnchorPane pane = (AnchorPane) loader.load();
            if (ControllerType == 1) {
                MainScreenController controller = loader.<MainScreenController>getController();
                // init data
                controller.setUser(u);
            }
            if (ControllerType == 2) {
                ShopFXMLController controller = loader.<ShopFXMLController>getController();
                // init data
                controller.setUser(u);
            }
            theStage.close();
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(fxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    public void goToRegistrationForm(ActionEvent event, String fxmlURL, String Title, int ControllerType) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
        Parent loginParent = (Parent) loader.load();        
        Scene scene2 = new Scene(loginParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();     
        app_stage.setTitle(Title);
        app_stage.setScene(scene2);
            if (ControllerType == 1) {
                MainScreenController controller = loader.<MainScreenController>getController();
                // init data
                controller.setUser(u);
            }
           if (ControllerType == 2) {
                ShopFXMLController controller = loader.<ShopFXMLController>getController();        
                // init data
                controller.setUser(u);
            }
            if (ControllerType == 3) {
                charactersController controller = loader.<charactersController>getController();        
                // init data
                controller.setUser(u);
                
            }
            if (ControllerType == 4) {
                ServerController controller = loader.<ServerController>getController();
                controller.setSU(s, u);
            }
            if (ControllerType == 5) {
                ServersController controller = loader.<ServersController>getController();
                controller.setSU(s, u);
            }
            if (ControllerType == 6) {
                AccountManagmentController controller = loader.<AccountManagmentController>getController();
                controller.setUser(u);
            }
            if(ControllerType == 7) {
                ServersController controller = loader.<ServersController>getController();
                controller.setU(u);
            }
            
        app_stage.setResizable(false);
        app_stage.show();
    }
}
