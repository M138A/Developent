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

        } catch (IOException ex) {
            Logger.getLogger(fxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToRegistrationForm(String fxmlURL, String Title) {
        try {

            root = FXMLLoader.load(getClass().getResource(fxmlURL));
            theStage.setTitle(Title);
            theStage.setScene(new Scene(root, 200,200));
            theStage.centerOnScreen();
            theStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
