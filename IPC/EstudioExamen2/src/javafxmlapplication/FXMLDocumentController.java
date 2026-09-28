package javafxmlapplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;

/**
 * @author gabij
 */

public class FXMLDocumentController implements Initializable {
    
    @FXML    private Label labelMoneda;
    @FXML    private Label selectedMoneda;
    
    @FXML    private ToggleGroup cryptomonedas;
    @FXML    private RadioMenuItem menuBitCoin;
    @FXML    private RadioMenuItem menuEther;
    @FXML    private RadioMenuItem menuLiteCoin;
    
    @FXML    private Button venderButton;
    
    @FXML    private ListView<String> listView;
    private ObservableList<String> lista = null;
    
    private final StringProperty monedaActual = new SimpleStringProperty("");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = FXCollections.observableArrayList(new ArrayList<>());
        
        labelMoneda.textProperty().bind(monedaActual);
        selectedMoneda.textProperty().bind(monedaActual);

        StringBinding monedaDesdeLista = Bindings.createStringBinding(() -> {
            String sel = listView.getSelectionModel().getSelectedItem();
            if (sel != null && !sel.isEmpty()) {
                return sel.split("\\s+")[0];
            }
            return monedaActual.get(); // no machaca si no hay selección
        },
        listView.getSelectionModel().selectedItemProperty()
        );

        monedaDesdeLista.addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isEmpty()) {
                monedaActual.set(newVal);
            }
        });
        
        BooleanBinding monedaSeleccionadaVender = Bindings.createBooleanBinding(() -> {
            String seleccion = listView.getSelectionModel().getSelectedItem();
            String moneda = labelMoneda.getText();
            
            return seleccion != null && seleccion.startsWith(moneda + "     ");
        },
            listView.getSelectionModel().selectedItemProperty(),
            labelMoneda.textProperty()
        );
        
        venderButton.disableProperty().bind(monedaSeleccionadaVender.not());
    }    

    @FXML
    private void comprar(ActionEvent event) {
        String moneda = labelMoneda.getText();
        
        TextInputDialog dialog = new TextInputDialog(""); // Por defecto
        dialog.setTitle("Comprar");
        dialog.setHeaderText("Comprar " + moneda);
        dialog.setContentText("Introduce la cantidad");
        
        Button comprarOKButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        comprarOKButton.setText("Comprar");
        
        dialog.showAndWait();
        
        int cantidadAdd = Integer.parseInt(dialog.getResult());
        
        for (int i=0; i < lista.size(); i++) {
            
            String entrada = lista.get(i);
            
            if (entrada.startsWith(moneda + "     ")) {
                lista.remove(entrada);
                String[] partes = entrada.split("     ");
                int cantidadActual = Integer.parseInt(partes[1]);
                
                cantidadAdd += cantidadActual;
            }
        }
        
        lista.add(moneda + "     " + cantidadAdd);
        listView.setItems(lista);
    }

    @FXML
    private void vender(ActionEvent event) {
        String moneda = labelMoneda.getText();
        
        TextInputDialog dialog = new TextInputDialog(""); // Por defecto
        dialog.setTitle("Vender");
        dialog.setHeaderText("Vender " + moneda);
        dialog.setContentText("Introduce la cantidad");
        
        Button comprarOKButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        comprarOKButton.setText("Vender");
        
        dialog.showAndWait();
        
        int cantidadRemove = Integer.parseInt(dialog.getResult());
        
        for (int i=0; i < lista.size(); i++) {
            
            String entrada = lista.get(i);
            
            if (entrada.startsWith(moneda + "     ")) {
                String[] partes = entrada.split("     ");
                int cantidadActual = Integer.parseInt(partes[1]);
                
                if (cantidadActual > cantidadRemove) cantidadActual -= cantidadRemove;
                
                else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error en venta de " + moneda);
                    alert.setContentText("No hay " + cantidadRemove + " en el wallet");
                    alert.showAndWait();
                }
                
                lista.remove(entrada);
                lista.add(moneda + "     " + cantidadActual);
                listView.setItems(lista);
            }
        }
    }

    @FXML
    private void nueva(ActionEvent event) {
        lista.clear();
        listView.setItems(lista);
        monedaActual.set("");
        menuBitCoin.selectedProperty().set(false);
        menuEther.selectedProperty().set(false);
        menuLiteCoin.selectedProperty().set(false);
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void menuMonedaSelected(ActionEvent event) {
        RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
        monedaActual.set(menuItem.getText());
    }   
}