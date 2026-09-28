/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Rectangle caja;
    @FXML
    private Slider sliderAnchura;
    @FXML
    private Slider sliderAltura;
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //sliderAnchura.valueProperty().addListener((a,b,c)->{ caja.setWidth((double) c); });
        //sliderAltura.valueProperty().addListener((a,b,c)->{ caja.setHeight((double) c); });
        
        caja.widthProperty().bind(sliderAnchura.valueProperty());
        caja.heightProperty().bind(sliderAltura.valueProperty());
        // does the same thing
        
        //caja.heightProperty().bind(sliderAltura.valueProperty().divide(2));
    }    
    
}
