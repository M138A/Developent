package dev.fx;

import java.awt.Toolkit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by mark&bart on 9-3-15.
 */
public class fxmlController extends DEVFX {

    private Users u = null;

    public fxmlController(Users x) {
        u = x;
    }

    public fxmlController() {

    }

    public void setMainStage(String Title, String fxmlURL, int size1, int size2) {
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

            MainScreenController controller = loader.<MainScreenController>getController();
            // init data
            controller.setUser(u);
            theStage.close();
            stage.show();
            

            /*
             try {
             FXMLLoader x = FXMLLoader.load(getClass().getResource(fxmlURL));
             root = FXMLLoader.load(MainScreenController.class.getResource(fxmlURL));
             //root = x.load();
             MainScreenController controller = (MainScreenController) x.getController();
             theStage.setScene(new Scene(root, size1, size2));
             theStage.setTitle(Title);
             try {
             theStage.show();
            
             } catch (Exception ex) {
             Logger.getLogger(fxmlController.class.getName()).log(Level.SEVERE, null, ex);
             }
             } catch (IOException e) {
             e.printStackTrace();
             }*/
        } catch (IOException ex) {
            Logger.getLogger(fxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
