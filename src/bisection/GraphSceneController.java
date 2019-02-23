/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bisection;

import static java.lang.Math.log10;
import static java.lang.Math.sqrt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Hindu
 */
public class GraphSceneController implements Initializable {

    @FXML
    private LineChart<Double, Double> graph;

    double diam, rough, reynold, epsilon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void plotLineG() {
        final XYChart.Series<Double, Double> seriesG = new XYChart.Series<Double, Double>();
        for (double x = -10; x <= 10; x = x + 0.01) {
            seriesG.getData().add(new XYChart.Data<Double, Double>(x, func(x)));
        }
        graph.getData().add(seriesG);

    }

    public double func(double x) {
        double m = (rough / diam) + (9.35 / (reynold * sqrt(x)));
        double f = (1 / sqrt(x)) - 1.14 + (2 * log10(m));
        return f;
    }

    void SetConstants(double d, double r, double re, double e) {

        diam = d;
        rough = r;
        reynold = re;
        epsilon = e;

    }
}
