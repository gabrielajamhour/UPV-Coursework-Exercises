/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class VistaPersonaController implements Initializable {

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    
    private boolean okPressed = false;
    private Persona persona;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aceptar(ActionEvent event) {
        okPressed = true;
        
        if(persona == null) { persona = new Persona("", ""); }
        
        persona.setApellidos(apellidosTextField.getText());
        persona.setNombre(nombreTextField.getText());
        
        nombreTextField.getScene().getWindow().hide();
        // Esa línea cerra la ventana cuando le das Aceptar
    }

    @FXML
    private void cancelar(ActionEvent event) {
        nombreTextField.getScene().getWindow().hide();
    }

    boolean isOKPressed() {
        return okPressed;
    }

    Persona getPersona() {
        return persona;
    }


    
}
