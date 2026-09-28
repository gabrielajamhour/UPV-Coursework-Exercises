package javafxmlapplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author gabij
 */

public class FXMLDocumentController implements Initializable {

    @FXML    private ToggleGroup sexo;
    
    @FXML    private VBox vBoxZonaAlumno;
    @FXML    private HBox hBoxCodigo;
    @FXML    private Label etiquetaCodigo1;
    @FXML    private Label etiquetaCodigo2;
    @FXML    private Label tituloPrincipal;
    
    @FXML    private TextField campoCodigo1;
    @FXML    private TextField campoCodigo2;
    @FXML    private TextField campoCodigo3;
    
    @FXML    private TextField labelNombre;
    @FXML    private RadioButton toggleMujer;
    @FXML    private RadioButton toggleHombre;
    
    private ObservableList<String> lista;
    @FXML    private ListView<String> listView;
    
    @FXML    private Button anadirButton;
    @FXML    private Button crearButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = FXCollections.observableArrayList(new ArrayList<>());
        listView.setItems(lista);
        
        /* CSS */
        vBoxZonaAlumno.getStyleClass().add("zonaAlumno");
        campoCodigo1.getStyleClass().add("codigo");
        campoCodigo2.getStyleClass().add("codigo");
        campoCodigo3.getStyleClass().add("codigo");
        hBoxCodigo.getStyleClass().add("hboxCodigo");
        etiquetaCodigo1.getStyleClass().add("etiquetasCodigo");
        etiquetaCodigo2.getStyleClass().add("etiquetasCodigo");
        tituloPrincipal.getStyleClass().add("tituloPrincipal");
        
        /* DESHABILITAR CAMPOS CODIGO */
        campoCodigo1.disableProperty().bind(campoCodigo1.textProperty().isNotEmpty());
        campoCodigo2.disableProperty().bind(campoCodigo1.textProperty().isEmpty().or(campoCodigo2.textProperty().isNotEmpty()));
        campoCodigo3.disableProperty().bind(campoCodigo2.textProperty().isEmpty().or(campoCodigo3.textProperty().isNotEmpty()));
        
        /* ALERTA */
        campoCodigo3.textProperty().addListener((ob, oldValue, newValue) -> {
            if(!campoCodigo1.getText().isEmpty() && !campoCodigo2.getText().isEmpty() && !campoCodigo3.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Grupos UPV");
                alert.setHeaderText("Gabriela Rego Jamhour");
                alert.setContentText("Grupo: " + campoCodigo1.getText() + campoCodigo2.getText() + campoCodigo3.getText());
                alert.showAndWait();
            }
        });
        
        /* DESHABILITAR BOTONES */        
        anadirButton.disableProperty().bind(labelNombre.textProperty().isEmpty().or(toggleHombre.selectedProperty().not().and(toggleMujer.selectedProperty().not())));
        crearButton.disableProperty().bind(Bindings.size(listView.getItems()).lessThan(2));
    }

    @FXML
    private void anadir(ActionEvent event) {
        lista.add(labelNombre.getText());
        labelNombre.setText("");
        sexo.getSelectedToggle().setSelected(false);
    }

    @FXML
    private void crear(ActionEvent event) {
        lista.clear();
        campoCodigo1.setText("");
        campoCodigo2.setText("");
        campoCodigo3.setText("");
    }
    
}
