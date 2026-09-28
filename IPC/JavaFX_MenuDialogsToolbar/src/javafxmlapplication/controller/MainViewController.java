/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sovacu
 */
public class MainViewController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button amazon;
    @FXML
    private Button bing;
    @FXML
    private Button ebay;
    @FXML
    private Button facebook;
    @FXML
    private Button google;
    @FXML
    private WebView wb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void salir(ActionEvent event){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Salir");
        alerta.setContentText("Seguro que quieres terminar?");
        Optional<ButtonType> respuesta = alerta.showAndWait();
        if (respuesta.get()==ButtonType.OK){
            ((Stage) borderPane.getScene().getWindow()).close();
        }
    }

    @FXML
    private void lanzar(ActionEvent event) {
        if(((Button)event.getSource())==amazon){
            WebView webEngine = new WebView();
            webEngine.getEngine().load("https://amazon.es/");
            borderPane.setCenter(webEngine);
        }
        
        if(((Button)event.getSource())==bing){
            WebView webEngine = new WebView();
            webEngine.getEngine().load("https://bing.es/");
            borderPane.setCenter(webEngine);
        }
        
        if(((Button)event.getSource())==ebay){
            WebView webEngine = new WebView();
            webEngine.getEngine().load("https://ebay.es/");
            borderPane.setCenter(webEngine);
        }
        
        if(((Button)event.getSource())==facebook){
            WebView webEngine = new WebView();
            webEngine.getEngine().load("https://facebook.com/");
            borderPane.setCenter(webEngine);
        }
        
        if(((Button)event.getSource())==google){
            WebView webEngine = new WebView();
            webEngine.getEngine().load("https://google.com/");
            borderPane.setCenter(webEngine);
        }
    }
    
    
    
    
}
