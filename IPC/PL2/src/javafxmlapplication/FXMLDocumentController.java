/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import static javafxmlapplication.Utils.*;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private GridPane tablero;
    @FXML
    private Circle pelota;
    
    private double sceneX0;
    private double sceneY0;
    @FXML
    private ToggleButton rellenoB;
    @FXML
    private Slider sliderRadio;
    @FXML
    private ColorPicker color;
 
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        var baseRadius = Bindings.min(tablero.widthProperty().divide(5).divide(2).add(-4), 
                                                  tablero.heightProperty().divide(5).divide(2).add(-4));
        
        pelota.radiusProperty().bind(baseRadius.multiply(sliderRadio.valueProperty()));
        
        pelota.strokeProperty().bind(color.valueProperty());
        pelota.fillProperty().bind(color.valueProperty());
    }    
    
    
    @FXML
    private void pintaRelleno(ActionEvent event) {
        if(rellenoB.isSelected()){
            pelota.fillProperty().unbind();
            pelota.setFill(Color.TRANSPARENT);
        }
        else {
            pelota.fillProperty().bind(color.valueProperty());
        }
    }

    @FXML
    private void moverPelota(KeyEvent event) {
        KeyCode teclaPulsada = event.getCode();
        int columna = tablero.getColumnIndex(pelota);
        int fila = tablero.getRowIndex(pelota);
        switch (teclaPulsada) {
            case UP:
                fila--;
                fila = rowNorm(tablero, fila);
                break;
            case DOWN:
                fila++;
                fila = rowNorm(tablero, fila);
                break;
            case LEFT:
                columna--;
                columna = columnNorm(tablero, columna);
                break;
            case RIGHT:
                columna++;
                columna = columnNorm(tablero, columna);
                break;
        }
        tablero.setConstraints(pelota, columna, fila);
    }

    @FXML
    private void saltarPelota(MouseEvent event) {
        int fila = rowCalc(tablero, event.getSceneY());
        int columna = columnCalc(tablero, event.getSceneX());
        tablero.setConstraints(pelota, columna, fila);
    }

    @FXML
    private void sueltaPelota(MouseEvent event) {
        pelota.setTranslateX(0);
        pelota.setTranslateY(0);
        saltarPelota(event);
    }

    @FXML
    private void arrastraPelota(MouseEvent event) {
        pelota.setTranslateX(event.getSceneX() - sceneX0);
        pelota.setTranslateY(event.getSceneY() - sceneY0);
    }

    @FXML
    private void pulsaPelota(MouseEvent event) {
        sceneX0 = event.getSceneX();
        sceneY0 = event.getSceneY();
        event.consume();
    }

}
