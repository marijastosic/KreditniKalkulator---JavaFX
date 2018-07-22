/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vezbakalkulator;

import com.sun.javafx.binding.StringFormatter;
import java.text.DecimalFormat;
import java.util.Locale;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Marija
 */
public class VezbaKalkulator extends Application {

    Label labelTk = new Label("Trazeni iznos kredita");
    Label labelValuta = new Label("Valuta");
    Label labelKs = new Label("Godisnja kamatna stopa (%)");
    Label labelBrojMeseci = new Label("Period otplate u mesecima");
    Label labelDetalji = new Label("Detalji kredita");
    Label labelMesecnaRata = new Label("Mesecna rata:");
    Label labelIznosKredita = new Label("Iznos kredita:");
    Label labelUkupnoOtplate = new Label("Ukupno otplate:");
    Label labelUkupnoKamate = new Label("Ukupno kamate:");
    Label labelKamata = new Label("Kamata:");
    Label labelPeriodOtplate = new Label("Period otplate:");

    Label valueMesecnaRata = new Label("");
    Label valueIznosKredita = new Label("");
    Label valueUkupnoOtplate = new Label("");
    Label valueUkupnoKamate = new Label("");
    Label valueKamata = new Label("");
    Label valuePeriodOtplate = new Label("");
    
    TextField tfTk = new TextField();
    TextField tfKs = new TextField();
    TextField tfBrojMeseci = new TextField();
    
    ObservableList<String> listaValuta = FXCollections.observableArrayList("RSD", "EUR");
    ComboBox<String> comboValute = new ComboBox<>(listaValuta);
    
    Button btnIzracunaj = new Button("Izracunaj");
    

    @Override
    public void start(Stage primaryStage) {
        labelDetalji.setFont(new Font(20));
        comboValute.getSelectionModel().selectFirst();
        GridPane pane = new GridPane();
        pane.add(labelTk, 0, 0);
        pane.add(tfTk, 1, 0);
        pane.add(labelValuta, 0, 1);
        pane.add(comboValute, 1, 1);
        pane.add(labelKs, 0, 2);
        pane.add(tfKs, 1, 2);
        pane.add(labelBrojMeseci, 0, 3);
        pane.add(tfBrojMeseci, 1, 3);
        pane.add(btnIzracunaj, 0, 4);
        pane.add(labelDetalji, 0, 5);
        pane.add(labelMesecnaRata, 0, 6);
        pane.add(valueMesecnaRata, 1, 6);
        pane.add(labelIznosKredita, 0, 7);
        pane.add(valueIznosKredita, 1, 7);
        pane.add(labelUkupnoOtplate, 0, 8);
        pane.add(valueUkupnoOtplate, 1, 8);
        pane.add(labelUkupnoKamate, 0, 9);
        pane.add(valueUkupnoKamate, 1, 9);
        pane.add(labelKamata, 0, 10);
        pane.add(valueKamata, 1, 10);
        pane.add(labelPeriodOtplate, 0, 11);
        pane.add(valuePeriodOtplate, 1, 11);
        pane.setVgap(10);
        pane.setHgap(10);
        
        pane.setPadding(new Insets(10));
        
        DecimalFormat df = new DecimalFormat("#,##0.00");
        
        btnIzracunaj.setOnAction(e->{
            try{
                
                
                double iznosKredita = Double.parseDouble(tfTk.getText());
                double kamatnaStopa = Double.parseDouble(tfKs.getText());
                int brojMeseci = Integer.parseInt(tfBrojMeseci.getText());
                
                double rata = (iznosKredita * kamatnaStopa) / (1200 * (1 - Math.pow((1 + kamatnaStopa / 1200), (-brojMeseci))));
                
                valueMesecnaRata.setText(df.format(rata) + " " + comboValute.getValue().toLowerCase());
                valueIznosKredita.setText(df.format(iznosKredita) + " " + comboValute.getValue().toLowerCase());
                valueUkupnoOtplate.setText(df.format(rata*brojMeseci) + " " + comboValute.getValue().toLowerCase());
                valueUkupnoKamate.setText(df.format((rata*brojMeseci)-iznosKredita) + " " + comboValute.getValue().toLowerCase());
                valueKamata.setText(kamatnaStopa + "%");
                valuePeriodOtplate.setText(brojMeseci + " meseci");
                
                System.out.println("rata: " + rata);
            } catch (Exception ex) {
                Alert a = new  Alert(Alert.AlertType.ERROR);
                a.setContentText("Pogresan unos");
                a.show();
            }
            
        });
        Scene scene = new Scene(pane, 450, 550);
        
        primaryStage.setTitle("Hello World!");
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
