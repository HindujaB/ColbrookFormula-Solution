/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bisection;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Hindu
 */
public class InputSceneController implements Initializable {

    @FXML
    private Button SolnBtn;
    @FXML
    private TextField Dtext;
    @FXML
    private TextField RCtext;
    @FXML
    private TextField REtext;
    
    @FXML
    private TextField LEPtext;
    @FXML
    private TextField REPtext;

    @FXML
    private TextField Etext;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void SolnButtonAction(ActionEvent event) throws IOException {

        final double d = Double.parseDouble(Dtext.getText());
        final double r = Double.parseDouble(RCtext.getText());
        final double re = Double.parseDouble(REtext.getText());
        final double e = Double.parseDouble(Etext.getText());
        double a = Double.parseDouble(LEPtext.getText());
        double b = Double.parseDouble(REPtext.getText());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bisection/SolutionScene.fxml"));
            Parent sol = loader.load();
            SolutionSceneController SScont = loader.getController();
            SScont.SetConstants(d, r, re, e, a, b);
            SScont.Display(a, b);

            Stage stg = new Stage();
            stg.setTitle("Solution of the formula");
            stg.getIcons().add(new Image("/image/ic.png"));
            stg.setScene(new Scene(sol));
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.show();

        } catch (IOException ex) {
            Logger.getLogger(InputSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
