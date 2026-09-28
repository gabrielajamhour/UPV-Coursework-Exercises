package javafxmlapplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    @FXML    private Button addButton;
    @FXML    private Button downButton;
    @FXML    private Button upButton;
    @FXML    private TextField textField;
    
    private ObservableList<String> lista;
    
    @FXML    private ListView<String> listView;
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = FXCollections.observableArrayList(new ArrayList<>());
        
        String css = this.getClass().getResource("style.css").toExternalForm();
        downButton.getStylesheets().add(css);
        upButton.getStylesheets().add(css);
        listView.getStylesheets().add(css);
        
        addButton.disableProperty().bind(textField.textProperty().isEmpty());
        upButton.disableProperty().bind(listView.getSelectionModel().selectedIndexProperty().lessThan(1));

        downButton.disableProperty().bind(
            listView.getSelectionModel().selectedIndexProperty().lessThan(0)
                .or(listView.getSelectionModel().selectedIndexProperty()
                        .greaterThanOrEqualTo(Bindings.size(lista).subtract(1)))
        );
        
        textField.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                listView.getSelectionModel().clearSelection();
            }
        });
    }

    @FXML
    private void showAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("GG338978\nGabriela Rego Jamhour");
        alert.show();
    }

    @FXML
    private void clear(ActionEvent event) {
        lista.clear();
        listView.setItems(lista);
    }

    @FXML
    private void add(ActionEvent event) {
        if ("".equals(textField.getText())) { return; }
        lista.add(textField.getText());
        listView.setItems(lista);
        textField.clear();
    }
    
    @FXML
    private void addEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            if ("".equals(textField.getText())) { return; }
            lista.add(textField.getText());
            listView.setItems(lista);
            textField.clear();
        }
    }

    @FXML
    private void down(ActionEvent event) {
        int index = listView.getSelectionModel().getSelectedIndex();

        Collections.swap(lista, index, index + 1);
        listView.getSelectionModel().select(index + 1);
    }

    @FXML
    private void up(ActionEvent event) {
        int index = listView.getSelectionModel().getSelectedIndex();

        Collections.swap(lista, index, index - 1);
        listView.getSelectionModel().select(index - 1);
    }
}
