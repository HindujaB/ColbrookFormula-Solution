/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bisection;

import java.io.IOException;
import static java.lang.Math.abs;
import static java.lang.Math.log10;
import static java.lang.Math.sqrt;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hindu
 */
public class SolutionSceneController implements Initializable {

    @FXML
    private Button graphBtn;
    @FXML
    private Label iterLbl;
    @FXML
    private Label rootLbl;

    double diam, rough, reynold, epsilon, LEP, REP;
    @FXML
    private Button ConGraphBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void graphButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bisection/GraphScene.fxml"));

            Parent gr = loader.load();
            GraphSceneController grControl = loader.getController();
            grControl.SetConstants(diam, rough, reynold, epsilon);
            grControl.plotLineG();

            Stage stg = new Stage();
            stg.setTitle("Graph representation of the formula");
            stg.getIcons().add(new Image("/image/ic.png"));
            stg.setScene(new Scene(gr));
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.show();

        } catch (IOException ex) {
            Logger.getLogger(InputSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double func(double x) {
        double m = (rough / diam) + (9.35 / (reynold * sqrt(x)));
        double f = (1 / sqrt(x)) - 1.14 + (2 * log10(m));
        return f;
    }

    public String bisection(double a, double b) {
        double c = (a + b) / 2.0;
        double n = ((log10(abs(b - a)) - log10(epsilon)) / log10(2));
        if (func(a) * func(b) > 0.0) {
            String error = "Solution does not exist between the boundaries! \n Please change the end points and Try again.";
            return error;
        }
        int i = 1;
        while (n >= 0.0) {
            // Find middle point
            c = (a + b) / 2.0;
            System.out.println("Iteration number :" + i);
            System.out.println("a =" + a);
            System.out.println("b =" + b);
            System.out.println("c =" + c);
            System.out.println("f a =" + func(a));
            System.out.println("f b =" + func(b));
            System.out.println("f c =" + func(c)+"\n");

            // Check if middle point is root
            if (func(c) == 0.0) {

                String rt = " = " + c + "";
                return rt;
            } // Decide the side to repeat the steps
            else if (func(c) * func(a) < 0.0) {
                b = c;
            } else {
                a = c;
            }
            i++;
            n--;
        }

        String s = " = " + c + "";
        return s;
    }

    void Display(double a, double b) {

        int num = (int) ((log10(abs(b - a)) - log10(epsilon)) / log10(2));
        this.rootLbl.setText(bisection(a, b));
        this.iterLbl.setText(String.valueOf(num));
        
    }

    void SetConstants(double d, double r, double re, double e, double a, double b) {
        LEP = a;
        REP = b;
        diam = d;
        rough = r;
        reynold = re;
        epsilon = e;

    }

    @FXML
    private void ConGraphBtnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bisection/ConGraphScene.fxml"));

            Parent Cgr = loader.load();
            ConGraphSceneController CgrControl = loader.getController();
            CgrControl.SetConstants(diam, rough, reynold, epsilon, LEP, REP);
            CgrControl.plotLineO();
            CgrControl.plotLineB();

            Stage stg = new Stage();
            stg.setTitle("Comparison of approximate root with the real root");
            stg.getIcons().add(new Image("/image/ic.png"));
            stg.setScene(new Scene(Cgr));
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.show();
        } catch (IOException ex) {
            Logger.getLogger(InputSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
