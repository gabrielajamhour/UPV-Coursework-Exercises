/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class VistaTablaController implements Initializable {

    private ObservableList<Persona> datos = null; // Colecci�n vinculada a la vista.

    @FXML
    private Button addButton;
    @FXML
    private Button modificarButton;
    @FXML
    private Button borrarButton;
    @FXML
    private TableColumn<Persona, String> nombreColumn;
    @FXML
    private TableColumn<Persona, String> apellidosColumn;
    @FXML
    private TableView<Persona> personasTableV;
    @FXML
    private TableColumn<Persona, String> imageColumn;
    private Object stage;

    private void inicializaModelo() {
        ArrayList<Persona> misdatos = new ArrayList<Persona>();
//        misdatos.add(new Persona("Pepe", "García"));
//        misdatos.add(new Persona("María", "Pérez"));
        misdatos.add(new Persona("Pepe", "García","/resources/images/Lloroso.png"));
        misdatos.add(new Persona("María", "Pérez","/resources/images/Sonriente.png"));
        
        datos = FXCollections.observableList(misdatos);
        personasTableV.setItems(datos);
        
        nombreColumn.setCellValueFactory(personaFila -> personaFila.getValue().NombreProperty());
        apellidosColumn.setCellValueFactory(personaFila -> personaFila.getValue().ApellidosProperty());
        imageColumn.setCellValueFactory(persona -> new SimpleStringProperty(persona.getValue().getImagePath()));
        
        imageColumn.setCellFactory(p -> new VistaIcono());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializaModelo();
    }

    @FXML
    private void addPersona(ActionEvent event) throws IOException {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/vista/VistaPersona.fxml"));
        Parent root = loader.load();
        VistaPersonaController controlador2 = loader.getController();
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena
        Scene scene = new Scene(root);
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir persona");
        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.show();
        stage.showAndWait();
//        if(controlador2.isOkClicked()){
//            datos.set(personasTableV.getSelectionModel().get)
////            datos.add(controlador2.getPersona());
//        }
    }

    @FXML
    private void updatePersona(ActionEvent event) {
    }

    @FXML
    private void delPersona(ActionEvent event) {
    }

    class VistaIcono extends TableCell<Persona, String> {

        @Override
        protected void updateItem(String t, boolean bln) {
            super.updateItem(t, bln);
            
            if(bln || t == null || t.isEmpty()) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(new ImageView(new Image(t,25,25,true,true)));
            }
        }
        
    }

}
