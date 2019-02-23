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
public class ConGraphSceneController implements Initializable {

    @FXML
    private LineChart<Double, Double> graph;
    

    double diam, rough, reynold, epsilon, a, b;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
        
    }
    public void plotLineB() {
        graph.setStyle(".chart-series-line{-fx-stroke-width:1px;}");
	 final XYChart.Series<Double, Double> seriesB = new XYChart.Series<Double, Double>();
        for (double x = -10; x <= 10; x = x + 0.1) {
            seriesB.getData().add(new XYChart.Data(x, iteration(a, b, x)));
        }
        seriesB.setName("Approximate root from bisection method");
	graph.getData().add(seriesB);
        
}
    public void plotLineO() {
        
	final XYChart.Series<Double, Double> seriesO = new XYChart.Series<Double, Double>();
        for (double x = -10; x <= 10; x = x + 0.1) {
            seriesO.getData().add(new XYChart.Data(x, iteration(a, b, 50)));
        }
        seriesO.setName("Real root value");
	graph.getData().add(seriesO);
        
}
public double func(double x) {
        double m = (rough / diam) + (9.35 / (reynold * sqrt(x)));
        double f = (1 / sqrt(x)) - 1.14 + (2 * log10(m));
        return f;
    }

public double iteration(double a, double b, double n) {
        double c = a;
        if (func(a) * func(b) > 0.0) {
            return 0.0;
        }

        while (n >= 0.0) {
            // Find middle point
            c = (a + b) / 2.0;
            if (func(c) == 0.0) {
                return c;
            } // Decide the side to repeat the steps
            else if (func(c) * func(a) < 0.0) {
                b = c;
            } else {
                a = c;
            }
            n--;
        }
        return c;
    }
     void SetConstants(double d, double r, double re, double e,double A,double B) {
        a = A;
        b = B;
        diam = d;
        rough = r;
        reynold = re;
        epsilon = e;

    }
    }    
    

